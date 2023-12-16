import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, of, startWith, switchMap } from 'rxjs';
import { Question, Quiz } from './quiz.model';
import { QuizService } from './quiz.service';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export default class QuizComponent implements OnInit {

  quizzes$ :Observable<Quiz[]> = of([]);
  questions:Question[] = [];
  userAnsweredQuestions :Question[] = [];
  quizName = "";
 
  mode = 'quiz';

  quizFormControl = new FormControl(null);
  config  = {
    'allowBack': true,
    'allowReview': true,
    'autoMove': true,  // if true, it will move to next question automatically when answered.
    'duration': 300,  // indicates the time (in secs) in which quiz needs to be completed. 0 means unlimited.
    'pageSize': 1,
    'requiredAll': false,  // indicates if you must answer all the questions before submitting.
    'richText': false,
    'shuffleQuestions': false,
    'shuffleOptions': false,
    'showClock': false,
    'showPager': true,
    'theme': 'none'
  };

  pager = {
    index: 0,
    size: 1,
    count: 1
  };
  timer = 0;
  startTime = new Date();
  ellapsedTime = '00:00';
  duration = '';
  

  
  private quizService = inject(QuizService);

  ngOnInit(): void {
    this.quizzes$ = this.quizService.getAllQuizes();
    this.quizFormControl.valueChanges.pipe(
      startWith(null),
      switchMap(quizId => 
     quizId ?  this.quizService.getQuizById(quizId) : of(null))).subscribe({
      next : (quesList) => {
        if(quesList){
        if(this.timer) clearInterval(this.timer);
        this.quizName= quesList?.quizName || '';
        this.questions = this.userAnsweredQuestions = quesList?.questions || []
        this.pager.count = quesList?.questions?.length || 0;
        this.startTimer();
        }
      },
      error : () => {
        this.quizName=  '';
        this.questions = [];
        this.pager.count =  0;
      }
    });
   

  }

  tick() {
    const now = new Date();
    const diff = (now.getTime() - this.startTime.getTime()) / 1000;
    if (diff >= this.config.duration) {
      this.onSubmit();
    }
    this.ellapsedTime = this.parseTime(diff);
  }

  startTimer():void{
    this.startTime = new Date();
    this.ellapsedTime = '00:00';
    this.timer = setInterval(() => { this.tick(); }, 1000) as unknown as  number;
    this.duration = this.parseTime(this.config.duration);
  }

  resetPage():void{
    this.pager = {
      index: 0,
      size: 1,
      count: 1
    };
  }

  parseTime(totalSeconds: number) {
    let mins: string | number = Math.floor(totalSeconds / 60);
    let secs: string | number = Math.round(totalSeconds % 60);
    mins = (mins < 10 ? '0' : '') + mins;
    secs = (secs < 10 ? '0' : '') + secs;
    return `${mins}:${secs}`;
  }

 get filteredQuestions():Question[] {
    return (this.questions.length) ?
      this.questions.slice(this.pager.index, this.pager.index + this.pager.size) : [];
  }

  goTo(index: number):void {
    console.log("goto")
    if (index >= 0 && index < this.pager.count) {
      this.pager.index = index;
      this.mode = 'quiz';
    }
  }

  isAnswered(question: Question):string {
    return question.options.find(x => x.isSelected) ? 'Answered' : 'Not Answered';
  }

  isCorrect(question: Question):string {
    return question.options.every(x => x.isSelected === x.isAns) ? 'correct' : 'wrong';
  }

  onSelect(questionId:number, optionId:number):void {
    console.log(questionId,optionId);
    const original = this.userAnsweredQuestions;
    const transformedOption = original[questionId].options.map(opt => {
      if(opt.id === optionId){
        opt.isSelected = true;
      }
      return opt;
    })

    original[questionId].options = transformedOption;
    this.userAnsweredQuestions = [...original];
    if (this.config.autoMove) {
      this.goTo(this.pager.index + 1);
    }
  }

  onModeChange(mode:string):void{
    this.mode = mode;
    if(this.timer) clearInterval(this.timer);
    this.resetPage();
    if(mode === "quiz") {
      this.startTimer();
    }
  }

  onSubmit() {
    let answers = [];
    clearInterval(this.timer);
    this.resetPage();
    this.mode = 'result';
  }

}
