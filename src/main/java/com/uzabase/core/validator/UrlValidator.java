package com.uzabase.core.validator;

import com.uzabase.core.model.Url;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Trung on 2/16/2016 8:26 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class UrlValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return Url.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "valid.url");
    }
}
