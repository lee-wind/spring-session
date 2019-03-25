package com.wind.springsession.util;

import java.util.Base64;

public class DecryptionUtil {

    public static String base64Decrypt(String value){

        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}
