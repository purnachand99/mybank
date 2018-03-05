package com.rvfs.challenge.mybank.util;

import java.util.concurrent.atomic.AtomicLong;

public class AccountNumberGenerator {

    private static final AtomicLong counter = new AtomicLong(1000);

    public static long getNextNumber(){
        return counter.incrementAndGet();
    }
}