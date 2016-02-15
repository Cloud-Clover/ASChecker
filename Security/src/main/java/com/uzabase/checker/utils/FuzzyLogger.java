package com.uzabase.checker.utils;

import java.util.Date;

/**
 * Created by Trung on 2/2/2016 7:51 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FuzzyLogger {
    public static void logError(String message) {
        System.err.println(new Date() + "::" + message);
    }
}
