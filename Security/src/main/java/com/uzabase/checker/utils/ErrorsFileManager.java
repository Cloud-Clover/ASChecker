package com.uzabase.checker.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 2/14/2016 10:26 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class ErrorsFileManager {

    public static List<String> sensitiveData = new ArrayList<String>();

    static {
        try {
            BufferedReader file = new BufferedReader(new FileReader(
                    "D:\\Code\\Spring\\ASChecker\\Security\\src\\main\\resources\\errors.txt"));

            String line;
            while ((line = file.readLine()) != null) {
                sensitiveData.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err
                    .println("File was not found: Not found errors file");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<String> getSensitiveData() {
        return sensitiveData;
    }
}
