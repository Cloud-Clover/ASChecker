package com.uzabase.checker;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.Rule;
import com.uzabase.checker.wcag.operable.*;
import com.uzabase.checker.wcag.perceivable.*;
import com.uzabase.checker.wcag.robust.StylingElementNotUsed;
import com.uzabase.checker.wcag.understandable.*;
import com.uzabase.crawler.Filter;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Trung on 1/30/2016 2:30 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class Evaluator {

    private Set<Rule> rules = new HashSet<Rule>();
    private int elementsChecked = 0;

    public int getElementsChecked() {
        return elementsChecked;
    }

    /**
     * Add a package containing rule implementations to the evaluator. Every
     * class implementing the Rule interface in the given package will be added
     * to the evaluator.
     *
     * @param packageName of package name in the current classpath to add.
     */
    public void addPackage(String packageName) {
        addRule(new AccessKeyValueUnique());
        addRule(new ActiveTextElementNotPresent());
        addRule(new FrameHasTitle());
        addRule(new HeadingHasText());
        addRule(new IeReservedAccessKeyValueNotUsed());
        addRule(new LinkTextNotReplicated());
        addRule(new MouseDownEventHasKeyEquivalent());
        addRule(new MouseEventHasKeyboardEvent());
        addRule(new MouseUpEventHaskeyEquivalent());
        addRule(new NonInteractiveElementWithEventHasRole());
        addRule(new OnClickIsFocusable());
        addRule(new OnMouseOutAndOnBlur());
        addRule(new OnMouseOverAndOnFocus());
        addRule(new SelectNotOnChange());
        addRule(new TitleHasEnoughWord());
        addRule(new TitleIsConcise());
        addRule(new TitleIsNotEmpty());
        addRule(new AltTextLengthReasonable());
        addRule(new AltTextOnImage());
        addRule(new AltTextOnImageNotBad());
        addRule(new ComplexTableDataHasHeading());
        addRule(new ComplexTableHeadingHasId());
        addRule(new ComplexTableHeadingIdUnique());
        addRule(new InvisibleImageNoTitle());
        addRule(new TableHasHeadings());
        addRule(new TableHasSummaryAttribute());
        addRule(new TableSummaryUnique());
        addRule(new StylingElementNotUsed());
        addRule(new ButtonHasContent());
        addRule(new ControlHasDescriptor());
        addRule(new ControlIdUnique());
        addRule(new DescriptionHasText());
        addRule(new FieldSetHasLegend());
        addRule(new FormControlHasDescription());
        addRule(new HtmlHasValidLanguageCode());
        addRule(new ImageInputHasDescription());
//        Reflections reflection = new  Reflections(packageName);
//        Set<Class<? extends Rule>> classes =
//                reflection.getSubTypesOf(Rule.class);
//        for (Class<? extends Rule> rule : classes) {
//            if (Modifier.isAbstract(rule.getModifiers())) {
//                continue;
//            }
//            try {
//                Rule instance = rule.newInstance();
//                addRule(instance);
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    /**
     * Add a single rule implementation to the evaluator.
     *
     * @param rule to add to the evaluator.
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Collect the issues for the current loaded set of
     * rules
     * @param root element for analysis.
     * @return the collection of issues identified.
     */
    public List<Issue> collectIssues(Element root) {
        List<Issue> result = new ArrayList<Issue>();
        elementsChecked = 0;
        for (Rule rule : rules) {
            Filter filter = rule.getFilter();
            for (Element target : filter.result(root)) {
                Issue issue = rule.check(target);
                elementsChecked++;
                if (null != issue) {
                    result.add(issue);
                }
            }
        }
        return result;
    }
}
