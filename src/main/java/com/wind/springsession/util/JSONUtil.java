package com.wind.springsession.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wind.springsession.model.Skill;

import java.io.IOException;

public class JSONUtil {

    public static final String USER_SESSION_NAME = "userId";

    public static String toJSONString(Object object) {

        ObjectMapper objectMapper = getObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static<E> E toBean(String jsonString, Class<E> Class) {

        ObjectMapper objectMapper = getObjectMapper();

        try {
            return objectMapper.readValue(jsonString, Class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static ObjectMapper getObjectMapper() {

        ObjectMapper objectMapper=new ObjectMapper();

        /*// 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);*/

        return objectMapper;
    }


    /*//将json字符串转换成对象
    Map map = objectMapper.readValue(jsonString, Map.class);
    //转换对象类型
    SomethingPOJO pojo = objectMapper.convertValue(map, SomethingPOJO.class);
    //将对象转换成json字符串
    Sting string = objectMapper.writeValueAsString(pojo);
    //将json字符串转换成List
    JavaType javaType = mapper.getTypeFactory()
            .constructParametricType(List.class, Person.class);
    List<Person> jsonToPersonList = objectMapper.readValue(arrayToJson, mapType);*/
}
