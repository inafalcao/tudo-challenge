package com.inafalcao.tudochallenge.hash;

import org.springframework.util.DigestUtils;
import java.util.Arrays;

public class Hasher {

    /**
     * Make a md5 hash from concatenating an array of keys.
     * @param keys
     * @return
     */
    public static String from(String ... keys) {
        String toHash = String.join("", Arrays.asList(keys));
        return DigestUtils.md5DigestAsHex(toHash.getBytes());
    }

}
