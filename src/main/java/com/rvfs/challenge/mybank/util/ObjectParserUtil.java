package com.rvfs.challenge.mybank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ObjectParserUtil {
    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static ObjectParserUtil ourInstance = new ObjectParserUtil();

    private ObjectParserUtil() {
    }

    public static ObjectParserUtil getInstance() {
        return ourInstance;
    }

    public String toString(Object o) {
        String result;

        try {
            result = new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String toJsonString(File file) {
        String result = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, String.class);

        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return result;
    }
}
