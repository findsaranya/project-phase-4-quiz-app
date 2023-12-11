package com.quiz.quizapp.service;

import java.util.List;

import com.quiz.quizapp.entity.Option;

public interface IOptionService {
	Option createOption(Option option);
	Option updateOption(Option option);
	String deleteOption(int optId);
	List<Option> saveAllOptiond(List<Option> options);
}
