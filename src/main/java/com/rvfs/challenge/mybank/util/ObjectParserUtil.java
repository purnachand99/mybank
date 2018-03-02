package com.rvfs.challenge.mybank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectParserUtil {
    private static ObjectParserUtil ourInstance = new ObjectParserUtil();

    public static ObjectParserUtil getInstance() {
        return ourInstance;
    }

    private ObjectParserUtil() {
    }

    public String toString(Object o){
        String result = "";

        try {
            System.out.println(new ObjectMapper().writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
