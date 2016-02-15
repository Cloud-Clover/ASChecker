package com.uzabase.checker.crawler;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by Trung on 2/1/2016 11:16 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FuzzyCrawler {

    private static HashMap<Boolean, HashMap<URI, FuzzyPage>> mapForPagesFoundByLoginStatus = new HashMap<Boolean, HashMap<URI, FuzzyPage>>();

    static {
        mapForPagesFoundByLoginStatus.put(false, new HashMap<URI, FuzzyPage>());
        mapForPagesFoundByLoginStatus.put(true, new HashMap<URI, FuzzyPage>());
    }

    public static HashMap<URI, FuzzyPage> getFuzzyPageMap(boolean loggedIn) {
        return mapForPagesFoundByLoginStatus.get(loggedIn);
    }

}
