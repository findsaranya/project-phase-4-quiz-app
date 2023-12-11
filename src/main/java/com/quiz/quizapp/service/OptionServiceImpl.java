package com.quiz.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.entity.Option;
import com.quiz.quizapp.repository.IOptionRepo;

@Service
public class OptionServiceImpl implements IOptionService {
	
	@Autowired
	IOptionRepo optionRepo;

	@Override
	public Option createOption(Option option) {
		return optionRepo.save(option);
	}

	@Override
	public Option updateOption(Option option) {
		return optionRepo.save(option);
	}

	@Override
	public String deleteOption(int optId) {
		optionRepo.deleteById(optId);
		return "Option Id "+optId+" successfully deleted";
	}

	@Override
	public List<Option> saveAllOptiond(List<Option> options) {
		return optionRepo.saveAll(options);
	}

}
