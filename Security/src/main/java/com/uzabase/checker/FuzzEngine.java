package com.uzabase.checker;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.uzabase.checker.crawler.FuzzyForm;
import com.uzabase.checker.crawler.FuzzyPage;
import com.uzabase.checker.utils.FuzzVectors;
import com.uzabase.checker.utils.ResultsProcessor;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trung on 2/2/2016 7:53 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FuzzEngine {

    public static void fuzzFormInputs(FuzzyPage page) {
        for (FuzzyForm form : page.getAllForms()) {
            // try fuzzing one input at a time
            for (HtmlElement input : form.getAllInputs()) {
                // fuzz all items of each attack class
                fuzzInputWithAllVectors(input, form.getSubmitButton());
            }
        }
    }

    private static void fuzzInputWithAllVectors(HtmlElement input,
                                                List<HtmlSubmitInput> submits) {
//        for (String vectorName : FuzzVectors.getAllVectorClasses()) {
            for(HtmlSubmitInput submit : submits) {
                fuzzInputWithStrings(input, submit,
                        FuzzVectors.getAttackClass("passiveSQL"), "passiveSQL");
            }
//        }
    }

    private static void fuzzInputWithStrings(HtmlElement input,
                                             HtmlSubmitInput submit, String[] strings, String attackVector) {
        for (String randomInput : strings) {
            ResultsProcessor.setLastInput(input.getAttribute("name") + "=" + randomInput);
            ResultsProcessor.setLastInputName(input.getAttribute("name"));
            input.setAttribute("value", randomInput);

            submitForm(submit, attackVector);
        }
    }

    private static void submitForm(HtmlSubmitInput submit, String attackVector) {
        try {
            Thread.sleep(Long.parseLong("0"));

            boolean noErrorIsError = false;
            if(attackVector.equals("xss") || attackVector.equals("activeSQL") || attackVector.equals("passiveSQL")) {
                noErrorIsError = true;
            }
            ResultsProcessor.processWebResponse(submit.<HtmlPage> click()
                    .getWebResponse(), noErrorIsError, attackVector);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
