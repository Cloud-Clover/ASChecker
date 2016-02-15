package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.MouseOverFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.ON_MOUSE_OVER_AND_ON_FOCUS;
import static com.uzabase.checker.wcag.Shared.FOCUS;

/**
 * Created by Trung on 1/30/2016 1:55 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class OnMouseOverAndOnFocus extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new MouseOverFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(FOCUS))
            return new Issue(this.getClass().getSimpleName(),
                    ON_MOUSE_OVER_AND_ON_FOCUS,
                    ERROR,
                    element);

        return null;
    }
}
