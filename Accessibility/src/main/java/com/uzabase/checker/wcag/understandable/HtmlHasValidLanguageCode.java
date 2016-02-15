package com.uzabase.checker.wcag.understandable;

import com.uzabase.checker.service.Issue;
import com.uzabase.checker.service.wcag.AbstractUnderstandableRule;
import com.uzabase.crawler.Filter;
import com.uzabase.crawler.filter.HtmlFilter;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.uzabase.checker.service.Issue.Severity.ERROR;
import static com.uzabase.checker.utils.Message.HTML_HAS_VALID_LANGUAGE_CODE;
import static com.uzabase.checker.wcag.Shared.LANG;

/**
 * Rule for HTML must have a valid language code
 *
 * Created by Trung on 1/29/2016 9:22 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class HtmlHasValidLanguageCode extends AbstractUnderstandableRule {
    public String getRuleName() {
        return this.getClass().getSimpleName();
    }

    public Filter getFilter() {
        return new HtmlFilter();
    }

    public Issue check(Element element) {
        List<String> codes = Arrays.asList(Locale.getISOLanguages());

        if (!element.hasAttr(LANG))
            return new Issue(this.getClass().getSimpleName(),
                    HTML_HAS_VALID_LANGUAGE_CODE,
                    ERROR,
                    element);

        String code = element.attr(LANG);
        if (code.length() > 2)
            code = code.substring(0, 2);

        if (!codes.contains(code.toLowerCase()))
            return new Issue(this.getClass().getSimpleName(),
                    HTML_HAS_VALID_LANGUAGE_CODE,
                    ERROR,
                    element);

        return null;
    }
}
