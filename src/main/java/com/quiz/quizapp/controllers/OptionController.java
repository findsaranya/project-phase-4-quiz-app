package com.quiz.quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.entity.Option;
import com.quiz.quizapp.service.IOptionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/option")
public class OptionController {
	
	@Autowired
	IOptionService optionService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Option> createOption(@RequestBody Option option){
		Option result = optionService.createOption(option);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<Option> updateOption(@RequestBody Option option){
		Option result = optionService.createOption(option);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/delete/{optId}")
	public ResponseEntity<String> deleteOption(@PathVariable int optId){
		String result = optionService.deleteOption(optId);
		return ResponseEntity.ok(result);
	}

}
