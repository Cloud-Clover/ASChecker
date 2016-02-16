package com.uzabase.core.controller;

import com.uzabase.core.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Trung on 2/16/2016 8:30 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Controller
@RequestMapping("/url.html")
public class UrlController {
    @Autowired
    @Qualifier("urlValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(Model model){
        Url url = new Url();
        model.addAttribute("url", url);
        return "url";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            Model model, @Validated Url url, BindingResult result) {
        String returnVal = "result";
        if(result.hasErrors()) {
            returnVal = "url";
        } else {
            model.addAttribute("url", url);
        }
        return returnVal;
    }
}
