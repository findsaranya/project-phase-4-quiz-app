package com.quiz.quizapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.dto.OptionDto;
import com.quiz.quizapp.dto.QuestionDto;
import com.quiz.quizapp.dto.QuizDto;
import com.quiz.quizapp.entity.Option;
import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.entity.Quiz;
import com.quiz.quizapp.service.IOptionService;
import com.quiz.quizapp.service.IQuestionService;
import com.quiz.quizapp.service.IQuizService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private IQuizService quizService;

	@Autowired
	private IQuestionService quesService;

	@Autowired
	private IOptionService optionService;

	@GetMapping(value = "/getQuiz/{quizId}")
	public ResponseEntity<QuizDto> getQuiz(@PathVariable int quizId) {
		Quiz quiz = quizService.getQuizById(quizId);
		QuizDto result = new QuizDto();
		if (quiz != null) {
			result.setQuizId(quiz.getQuizId());
			result.setQuizName(quiz.getQname());
			List<QuestionDto> newQueList = quiz.getQuestions().stream().map(x -> {
				QuestionDto newQues = new QuestionDto();
				newQues.setQuestId(x.getQuesId());
				newQues.setQuesName(x.getQuesname());
				List<OptionDto> newOptList = x.getOptions().stream().map(y -> {
					OptionDto newOpt = new OptionDto();
					newOpt.setId(y.getOptionId());
					newOpt.setName(y.getOptionName());
					newOpt.setIsAns(y.getIsAnswerable());
					return newOpt;
				}).collect(Collectors.toList());
				newQues.setOptions(newOptList);
				return newQues;
			}).collect(Collectors.toList());
			result.setQuestions(newQueList);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/getAllQuiz")
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		List<Quiz> quiz = quizService.getAllQuiz();
		return ResponseEntity.ok(quiz);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quiz) {
		Quiz quizNew = new Quiz();

		List<Option> optionList = new ArrayList<Option>();
		List<Question> quesList = new ArrayList<Question>();
		quiz.getQuestions().stream().map(x -> {
			Question quesNew = new Question();
			quesNew.setQuesId(x.getQuestId());
			quesNew.setQuiz(quizNew);
			quesNew.setQuesname(x.getQuesName());
			List<Option> optList = x.getOptions().stream().map(y -> {
				Option optionNew = new Option();
				optionNew.setOptionId(y.getId());
				optionNew.setOptionName(y.getName());
				optionNew.setIsAnswerable(y.getIsAns());
				optionNew.setQues(quesNew);
				return optionNew;
			}).collect(Collectors.toList());
			optionList.addAll(optList);
			return quesNew;
		}).collect(Collectors.toList());
		quizNew.setQname(quiz.getQuizName());

		quizService.createQuiz(quizNew);
		quesService.saveAllQuestiond(quesList);
		optionService.saveAllOptiond(optionList);
		return ResponseEntity.ok("Quiz Created Successfully");
	}

	@GetMapping(value = "/delete/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable int quizId) {
		String result = quizService.deleteQuiz(quizId);
		return ResponseEntity.ok(result);
	}

}