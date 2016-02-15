package com.uzabase.checker.wcag.understandable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.FormControlFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.FORM_CONTROL_HAS_DESCRIPTION;
import static com.uzabase.checker.wcag.Shared.TITLE;
import static com.uzabase.checker.wcag.Shared.VALUE;

/**
 * Rule for form control has description
 *
 * Created by Trung on 1/29/2016 8:44 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FormControlHasDescription extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new FormControlFilter();
    }

    public Issue check(Element element) {
        if (element.hasAttr(TITLE) || element.hasAttr(VALUE))
            return null;

        if (!element.text().trim().isEmpty())
            return null;

        return new Issue(this.getClass().getSimpleName(),
                FORM_CONTROL_HAS_DESCRIPTION,
                ERROR,
                element);
    }
}
