package com.uzabase.checker;

import com.uzabase.checker.service.Issue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by Trung on 1/21/2016 4:42 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class App {
    private static final String HTML = "<html>" +
            "<head><title>Some things are just blah</title></head>" +
            "<body>" +
            "<a accesskey=y>FirstLink</a>" +
            "<div onclick=\"alert('hi')\">eat me</div>" +
            "<div onclick=\"alert('hi')\">drink me</div>" +
            "<a accesskey=r>SecondLink</a>" +
            "<div ondblclick=\"alert('badness');\">Interact</div>" +
            "<div onkeyup=\"alert('badness');\">Interact</div>" +
            "<div onkeydown=\"alert('badness');\">Interact</div>" +
            "<div onkeypress=\"alert('badness');\">Interact</div>" +
            "<div onmousedown=\"alert('badness');\">Interact</div>" +
            "<div onmouseup=\"alert('badness');\">Interact</div>" +
            "<div onmousemove=\"alert('badness');\">Interact</div>" +
            "<div onmouseout=\"alert('badness');\">Interact</div>" +
            "<div onmouseover=\"alert('badness');\">Interact</div>" +
            "<div onselect=\"alert('badness');\">Interact</div>" +
            "<div onchange=\"alert('badness');\">Interact</div>" +
            "<div onsubmit=\"alert('badness');\">Interact</div>" +
            "<div onreset=\"alert('badness');\">Interact</div>" +
            "<div onfocus=\"alert('badness');\">Interact</div>" +
            "<div onblur=\"alert('badness');\">Interact</div>" +
            "<form action=\"/\"><input type=submit />" +
            "<fieldset>" +
            "<input type=\"button\" />" +
            "<textarea>text</textarea>" +
            "</fieldset>" +
            "<input type=\"reset\" />" +
            "<button>click me</button>" +
            "</form>" +
            "<blink>I'm going to flash</blink>" +
            "<marquee>I'm going to scroll, classy eh?</marquee>" +
            "<iframe src=\"http://www.google.com/\" />" +
            "<frame src=\"http://www.google.com/\" />" +
            "<h1>Heading 1</h1>" +
            "<h2>Heading 2</h2>" +
            "<h3>Heading 3</h3>" +
            "<h4>Heading 4</h4>" +
            "<img src=\"/nowhere.gif\" />" +
            "<select><option>a</option><option>b</option></select>" +
            "<table><tr><td>I'm a cell</td></tr></table>" +
            "</body></html>";
    private static final int TIMEOUT = 20000;

    public static void main(String[] args) throws Exception {

        Document document;
        try {
            document = Jsoup.parse(new URL("http://localhost:8080/bodgeit/"), TIMEOUT);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error due to malformed URL parameter", e);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception fetching page.", e);
        }

        System.out.println(document.toString());
        System.out.println("*************************************");
        Evaluator evaluator = new Evaluator();
        evaluator.addPackage("java.com.uzabase.checker.wcag.operable");
        List<Issue> result = evaluator.collectIssues(document);

        for (Issue issue : result) {
            System.out.println(issue.toString());
            System.out.println("*************************************");
        }
        System.out.println("*************************************");
    }
}
