package com.quiz.quizapp.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.service.IQuestionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private IQuestionService queService;
	
	@PostMapping(value="/create")
	public ResponseEntity<QuestionDto> ceateQuestion (@RequestBody Question ques) {
		Question quesResult = queService.createQuestion(ques);
		QuestionDto quesDto = new QuestionDto();
		quesDto.setQuestId(quesResult.getQuesId());
		quesDto.setQuesName(quesResult.getQuesname());
		List<OptionDto> optionList = new ArrayList<OptionDto>();
		if(quesResult.getOptions() != null){
		 optionList = quesResult.getOptions().stream().map(eachOption -> new OptionDto(eachOption.getOptionId(),eachOption.getOptionName(),eachOption.getIsAnswerable())).toList();
		}	
		quesDto.setOptions(optionList);
		return ResponseEntity.ok(quesDto);
	}
	
	@GetMapping(value = "/getQues/{quesId}")
	public ResponseEntity<QuestionDto> getQuestionBasesOnId(@PathVariable int quesId){
		Question quesResult = queService.getQuesById(quesId);
	    QuestionDto quesDto = new QuestionDto();
	    List<OptionDto> optList = new ArrayList<OptionDto>();
	    if(quesResult.getOptions() != null){
	     optList = quesResult.getOptions().stream().map(opt -> new OptionDto(opt.getOptionId(),opt.getOptionName(),opt.getIsAnswerable())).toList();
	    }
	     quesDto.setQuestId(quesResult.getQuesId());
		quesDto.setQuesName(quesResult.getQuesname());
		quesDto.setOptions(optList);
	     return ResponseEntity.ok(quesDto);
	}
	
	@GetMapping(value = "/deleteQues/{quesId}")
	public ResponseEntity<String> deleteQuestionBasedOnId(@PathVariable int quesId){
		String result = queService.deleteQuestion(quesId);
		return ResponseEntity.ok(result);
	}
	
	

}
