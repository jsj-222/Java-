package com.wjj.shop.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String source){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //把明文进行加密操作
            md5.update(source.getBytes());
            return new BigInteger(md5.digest(source.getBytes())).toString(16);
//            return new BigInteger(md5.digest(source.getBytes())).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
