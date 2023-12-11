package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.entity.Quiz;

@Repository
public interface IQuizRepo extends JpaRepository<Quiz, Integer> {

}
