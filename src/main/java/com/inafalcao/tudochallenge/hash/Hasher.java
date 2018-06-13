package com.inafalcao.tudochallenge.hash;

import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Hasher {

    /**
     * Make a md5 hash from concatenating an array of keys.
     * @param keys
     * @return
     */
    public static String from(String ... keys) {
        StringBuilder stringBuilder = new StringBuilder();
        String toHash = Arrays.stream(keys).map(stringBuilder::append).collect(Collectors.joining());
        return DigestUtils.md5DigestAsHex(toHash.getBytes());
    }

}
