package com.quiz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.entity.Option;

@Repository
public interface IOptionRepo extends JpaRepository<Option, Integer> {

}
