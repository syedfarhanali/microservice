package com.learning.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by amits on 27/09/15.
 */
public class BeforeSaveValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println(o);
    }
}
