package com.uzabase.checker;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.uzabase.checker.crawler.FuzzyPage;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Trung on 2/2/2016 7:53 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class pp {
    public static void main(String args[]) {
//        for (String x : FuzzVectors.getAttackClass("sql")) {
//            System.out.println(x);
//        }
        CollectingAlertHandler handler = new CollectingAlertHandler();
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
        webClient.setJavaScriptEnabled(true);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.setPrintContentOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setAlertHandler(handler);
        try {
            HtmlPage page = webClient.getPage("http://localhost:8080/bodgeit/login.jsp");
            System.out.println("OK HTML Page");
            FuzzyPage fuzzyPage =new FuzzyPage(page);
            for (URI x : fuzzyPage.getAllPageURIs())
            System.out.println(x);
            System.out.println("**************************************");
            System.out.println(fuzzyPage.getAllForms().size());

            FuzzEngine.fuzzFormInputs(fuzzyPage);



        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String x : handler.getCollectedAlerts())
            System.out.println(x);
    }
}
