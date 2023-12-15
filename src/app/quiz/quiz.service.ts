import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Question, Quiz, QuizResponse } from './quiz.model';
import { quizEndpoints } from './quiz.endpoints';

@Injectable()
export class QuizService {
    private _http = inject(HttpClient);

    getAllQuizes():Observable<Quiz[]>{
        const url = quizEndpoints.getAllQuzies;
        console.log(url);
        return this._http.get<Quiz[]>(url);
    }

    getQuizById(quizId:number):Observable<QuizResponse>{
        const url = quizEndpoints.getQuizById;
        return this._http.get<QuizResponse>(url + quizId).pipe(map(response => {
         const transformedQuestions = response.questions.map(item => {
            const options = item.options.map(option => ({...option,isSelected:false}));
            return {...item,options}
         });
         return { ...response, questions: transformedQuestions } as unknown as QuizResponse
        }));
    }
}