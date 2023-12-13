package com.quiz.quizapp.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class QuizDto {
private Integer quizId;
private String quizName;
private List<QuestionDto> questions;
public QuizDto() {
}


public QuizDto(Integer quizId, String quizName, List<QuestionDto> questions) {
	super();
	this.quizId = quizId;
	this.quizName = quizName;
	this.questions = questions;
}


public QuizDto(Integer quizId, String quizName) {
	super();
	this.quizId = quizId;
	this.quizName = quizName;
}

}
