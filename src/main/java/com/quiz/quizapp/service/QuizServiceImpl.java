package com.quiz.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.entity.Quiz;
import com.quiz.quizapp.repository.IQuizRepo;

@Service
public class QuizServiceImpl implements IQuizService {
	
	@Autowired
	private IQuizRepo quizRepo;

	@Override
	public Quiz getQuizById(int id) {
		Optional<Quiz> result = quizRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<Quiz> getAllQuiz() {
          System.out.println("service");
          System.out.println( quizRepo.findAll());
		return quizRepo.findAll();
	}

	@Override
	public Quiz createQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public String deleteQuiz(int quizId) {
		quizRepo.deleteById(quizId);
		return "Quiz Id "+quizId +" Successfully deleted";
	}

}
