package com.wind.springsession.util;

import com.wind.springsession.exception.CustomException;

public class ObjectUtil {

    public static <E> boolean isNull(E value){

        return value == null || value.equals("");
    }

    public static <E> void isNotNull(E value, String code, String message) throws CustomException {

        if(value == null || value.equals("")){
           throw new CustomException(code, message);
        }
    }

    public static int convertToInt(String value, String code, String message) throws CustomException {

        try{
            return Integer.parseInt(value);
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomException(code, message);
        }

    }

}
