package com.uzabase.checker.wcag.perceivable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractPerceivableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.TableFilter;
import org.jsoup.nodes.Element;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.TABLE_HAS_SUMMARY_ATTRIBUTE;
import static com.uzabase.checker.wcag.Shared.SUMMARY;

/**
 * Rule for table summary attribute presence
 *
 * Created by Trung on 1/30/2016 2:07 AM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class TableHasSummaryAttribute extends AbstractPerceivableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new TableFilter();
    }

    public Issue check(Element element) {
        if (!element.hasAttr(SUMMARY)) {
            return new Issue(this.getClass().getSimpleName(),
                    TABLE_HAS_SUMMARY_ATTRIBUTE,
                    ERROR,
                    element);
        }

        return null;
    }
}
