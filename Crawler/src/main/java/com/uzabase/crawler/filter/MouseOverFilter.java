package com.uzabase.crawler.filter;

import com.uzabase.crawler.ElementFilter;
import com.uzabase.crawler.store.ElementStore;
import org.jsoup.nodes.Element;

/**
 * Created by Trung on 1/23/2016 3:46 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class MouseOverFilter extends ElementFilter {
    @Override
    public Iterable<Element> result(Element root) {
        return root.select(ElementStore.MOUSEOVER_SELECTOR);
    }
}
