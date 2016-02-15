package com.uzabase.checker.wcag.perceivable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.TableFilter;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Set;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.COMPLEX_TABLE_HEADING_ID_UNIQUE;
import static com.uzabase.checker.wcag.Shared.*;

/**
 * Complex table heading unique rule
 *
 * Created by Trung on 1/29/2016 5:39 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ComplexTableHeadingIdUnique extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TableFilter();
    }

    public Issue check(Element element) {
        if (notComplexTable(element))
            return null;

        Set<String> idValues = new HashSet<String>();
        for (Element th : element.select(TH)) {
            if (!th.hasAttr(ID))
                continue;

            if (idValues.contains(th.attr(ID)))
                return new Issue(this.getClass().getSimpleName(),
                        COMPLEX_TABLE_HEADING_ID_UNIQUE,
                        ERROR,
                        element);
            else idValues.add(th.attr(ID));
        }

        return null;
    }
}
