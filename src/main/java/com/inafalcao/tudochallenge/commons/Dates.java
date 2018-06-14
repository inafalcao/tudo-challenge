package com.inafalcao.tudochallenge.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

    // Is there any way to make this deterministic?
    // how would I test it?
    public static String today() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMYYYHH");
        return formatter.format(today);
    }

}
