package com.quiz.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.repository.IQuestionRepo;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private IQuestionRepo quesRepo;
	
	@Override
	public Question getQuesById(int quesId) {
		Optional<Question> result = quesRepo.findById(quesId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<Question> getAllQuestions() {	
		return quesRepo.findAll();
	}

	@Override
	public Question createQuestion(Question question) {
		return quesRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return quesRepo.save(question);
	}

	@Override
	public String deleteQuestion(int quesId) {
		quesRepo.deleteById(quesId);
		return "Question Id "+quesId+ " Successfully deleted";
	}

	@Override
	public List<Question> saveAllQuestiond(List<Question> questions) {
		return quesRepo.saveAll(questions);
	}

}
