package com.uzabase.checker.wcag.understandable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.SummaryFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.DESCRIPTION_HAS_TEXT;

/**
 * Rule for description element has text
 *
 * Created by Trung on 1/29/2016 6:10 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class DescriptionHasText extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new SummaryFilter();
    }

    public Issue check(Element element) {
        if (element.hasText() &&
                !element.text().trim().isEmpty())
            return null;

        return new Issue(this.getClass().getSimpleName(),
                DESCRIPTION_HAS_TEXT,
                ERROR,
                element);
    }
}
