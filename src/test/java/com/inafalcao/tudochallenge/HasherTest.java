package com.inafalcao.tudochallenge;

import com.inafalcao.tudochallenge.hash.Hasher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HasherTest {


    @Test
    public void hashesAreTheSame() {

        String test = "19134237253110319901374951924455";

        assertEquals(
            Hasher.from("19134237253110319901374951924455"),
            Hasher.from("19134237253", "11031990", "1374951924455")
        );

    }

}
