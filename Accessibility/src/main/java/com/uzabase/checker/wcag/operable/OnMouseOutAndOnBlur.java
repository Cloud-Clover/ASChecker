package com.uzabase.checker.wcag.operable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractOperableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.MouseOutFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.ON_MOUSE_OUT_AND_ON_BLUR;
import static com.uzabase.checker.wcag.Shared.BLUR;

/**
 * Created by Trung on 1/30/2016 1:53 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class OnMouseOutAndOnBlur extends AbstractOperableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new MouseOutFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(BLUR))
            return new Issue(this.getClass().getSimpleName(),
                    ON_MOUSE_OUT_AND_ON_BLUR,
                    ERROR,
                    element);

        return null;
    }
}
