package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.ActiveTextFiler;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.ACTIVE_TEXT_ELEMENT_NOT_PRESENT;

/**
 * Created by Trung on 1/29/2016 4:44 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ActiveTextElementNotPresent extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ActiveTextFiler();
    }

    public Issue check(Element element) {
        return new Issue(this.getClass().getSimpleName(),
                ACTIVE_TEXT_ELEMENT_NOT_PRESENT,
                ERROR,
                element);
    }
}
