package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.FrameFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.FRAME_HAS_TITLE;
import static com.uzabase.checker.wcag.Shared.TITLE;

/**
 * Rule for frame having title
 *
 * Created by Trung on 1/29/2016 8:48 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FrameHasTitle extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new FrameFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(TITLE) || element.attr(TITLE).trim().isEmpty())
            return new Issue(this.getClass().getSimpleName(),
                    FRAME_HAS_TITLE,
                    ERROR,
                    element);

        return null;
    }
}
