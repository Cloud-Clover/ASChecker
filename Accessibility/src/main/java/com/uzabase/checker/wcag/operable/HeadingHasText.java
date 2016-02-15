package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.HeadingFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.HEADING_HAS_TEXT;
import static com.uzabase.checker.wcag.Shared.containedText;

/**
 * Rule for heading having text
 *
 * Created by Trung on 1/29/2016 9:16 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class HeadingHasText extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new HeadingFilter();
    }

    public Issue check(Element element) {
        if (containedText(element).trim().isEmpty())
            return new Issue(this.getClass().getSimpleName(),
                    HEADING_HAS_TEXT,
                    ERROR,
                    element);

        return null;
    }
}
