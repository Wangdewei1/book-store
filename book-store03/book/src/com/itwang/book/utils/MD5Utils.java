package com.itwang.book.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Utils {
    public static String getMD5Utils(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest();
            String s = Base64.getEncoder().encodeToString(md5.digest(str.getBytes()));
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}