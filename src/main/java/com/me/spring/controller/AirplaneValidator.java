package com.me.spring.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.spring.pojo.Airplane;

public class AirplaneValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Airplane.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Airplane airplane =(Airplane) obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airplane_id","error.invalid.airplane_id","Airplane Id Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airlineName","error.invalid.airlineName","Airplane Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"owner","error.invalid.owner","Owner Required");
		
		
	}

}
