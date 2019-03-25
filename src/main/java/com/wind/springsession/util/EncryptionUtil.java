package com.wind.springsession.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

public class EncryptionUtil {

    public static String Base64Encrypt(String value){

        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String md5HexEncrypt(String value){

        return DigestUtils.md5Hex(value);
    }

    public static String sha1HexEncrypt(String value){

        return DigestUtils.sha1Hex(value);
    }

    public static String sha256HexEncrypt(String value){

        return DigestUtils.sha256Hex(value);
    }
}
