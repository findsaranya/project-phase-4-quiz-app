package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.entity.Question;

@Repository
public interface IQuestionRepo extends JpaRepository<Question, Integer> {

}
