package com.springapp.mvc.validation;

import com.springapp.mvc.domain.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ContactValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","required.name","Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","required.value","Genre is required");
    }
}
