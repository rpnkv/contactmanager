package com.springapp.mvc.validation;

import com.springapp.mvc.domain.ContactRecord;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RecordValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return ContactRecord.class.isAssignableFrom((aClass));
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "required.type", "Type is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value","required.value","Value is required");
    }
}
