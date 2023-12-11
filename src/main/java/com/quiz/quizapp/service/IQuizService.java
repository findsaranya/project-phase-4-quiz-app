package com.quiz.quizapp.service;

import java.util.List;

import com.quiz.quizapp.entity.Quiz;

public interface IQuizService {
Quiz getQuizById(int id);
List<Quiz> getAllQuiz();
Quiz createQuiz(Quiz quiz);
Quiz updateQuiz(Quiz quiz);
String deleteQuiz(int quizId);
}
