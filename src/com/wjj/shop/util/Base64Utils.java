package com.wjj.shop.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 */
public class Base64Utils {
    /**
     * 编码
     * @param source
     * @return
     */
    public static String encode(String source){
        return Base64.getEncoder().encodeToString(source.getBytes());

    }



    public static String decode(String source){
        return new String(Base64.getDecoder().decode(source.getBytes()));

    }
}
