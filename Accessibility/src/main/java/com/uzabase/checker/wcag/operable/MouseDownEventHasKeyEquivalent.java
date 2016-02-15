package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.MouseDownFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.MOUSE_DOWN_EVENT_HAS_KEY_EQUIVALENT;
import static com.uzabase.checker.wcag.Shared.KEY_DOWN;

/**
 * Rule for mouse down having equivalent keyboard event.
 *
 * Created by Trung on 1/30/2016 1:28 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class MouseDownEventHasKeyEquivalent extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new MouseDownFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(KEY_DOWN))
            return new Issue(this.getClass().getSimpleName(),
                    MOUSE_DOWN_EVENT_HAS_KEY_EQUIVALENT,
                    ERROR,
                    element);

        return null;
    }
}
