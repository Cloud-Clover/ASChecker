package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.ClickFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.ON_CLINK_IS_FOCUSABLE;
import static com.uzabase.checker.wcag.Shared.*;

/**
 * Rule for on click elements also being focusable
 *
 * Created by Trung on 1/30/2016 1:46 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class OnClickIsFocusable extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ClickFilter();
    }

    public Issue check(Element element) {
        if (notFocusable(element))
            return new Issue(this.getClass().getSimpleName(),
                    ON_CLINK_IS_FOCUSABLE,
                    ERROR,
                    element);

        return null;
    }

    private boolean notFocusable(Element element) {
        List<String> focusable = Arrays.asList("button", "input", "select", "textarea");

        if (focusable.contains(element.tagName()))
            return false;

        if (ANCHOR.equals(element.tagName()) &&
                (element.hasAttr(HREF) ||
                element.hasAttr(TAB_INDEX)) &&
                0 <= Integer.parseInt(element.attr(TAB_INDEX)))
            return false;

        return true;
    }
}
