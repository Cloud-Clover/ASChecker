package com.uzabase.checker.wcag.perceivable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.ImageFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.INVISIBLE_IMAGE_NO_TITLE;
import static com.uzabase.checker.wcag.Shared.TITLE;
import static com.uzabase.checker.wcag.Shared.isVisible;

/**
 * Rule for invisible image with title
 *
 * Created by Trung on 1/30/2016 12:52 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class InvisibleImageNoTitle extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new ImageFilter();
    }

    public Issue check(Element element) {
        if (!isVisible(element) && element.hasAttr(TITLE))
            return new Issue(this.getClass().getSimpleName(),
                    INVISIBLE_IMAGE_NO_TITLE,
                    ERROR,
                    element);

        return null;
    }
}
