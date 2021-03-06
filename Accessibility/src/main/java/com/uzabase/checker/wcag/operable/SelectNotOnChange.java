package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.SelectFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.SELECT_NOT_ON_CHANGE;
import static com.uzabase.checker.wcag.Shared.ON_CHANGE;

/**
 * Rule for select not using on change events
 *
 * Created by Trung on 1/30/2016 1:58 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class SelectNotOnChange extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new SelectFilter();
    }

    public Issue check(Element element) {
        if (element.hasAttr(ON_CHANGE))
            return new Issue(this.getClass().getSimpleName(),
                    SELECT_NOT_ON_CHANGE,
                    ERROR,
                    element);

        return null;
    }
}
