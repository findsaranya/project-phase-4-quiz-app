package com.quiz.quizapp.service;

import java.util.List;

import com.quiz.quizapp.entity.Question;


public interface IQuestionService {
	Question getQuesById(int quesId);
	List<Question> getAllQuestions();
	Question createQuestion(Question question);
	Question updateQuestion(Question question);
	String deleteQuestion(int quesId);
	List<Question> saveAllQuestiond(List<Question> questions);
}
