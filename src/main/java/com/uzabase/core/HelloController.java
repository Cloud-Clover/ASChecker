package com.uzabase.core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Trung on 2/15/2016 6:38 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("greeting", "Hello Spring MVC");

        return "welcome";

    }
}
