package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.checker.utils.Role;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.EventFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.NON_INTERACTIVE_ELEMENT_WITH_EVENT_HAS_ROLE;
import static com.uzabase.checker.wcag.Shared.*;

/**
 * Role for non interactive elements with an even also has an Aria role
 *
 * Created by Trung on 1/30/2016 1:39 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class NonInteractiveElementWithEventHasRole extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new EventFilter();
    }

    public Issue check(Element element) {
        List<String> interactive = Arrays.asList(ANCHOR, BUTTON, INPUT, SELECT);

        if (!interactive.contains(element.tagName()) && !hasAriaRole(element))
            return new Issue(this.getClass().getSimpleName(),
                    NON_INTERACTIVE_ELEMENT_WITH_EVENT_HAS_ROLE,
                    ERROR,
                    element);

        return null;
    }

    private boolean hasAriaRole(Element element) {
        if (!element.hasAttr(ROLE))
            return false;

        for (Role role : Role.values())
            if (element.attr(ROLE).contains(role.toString()))
                return true;

        return false;
    }
}
