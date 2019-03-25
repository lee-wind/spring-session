package com.wind.springsession.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;


public class XSSStringJsonSerializer extends JsonSerializer<String> {


    @Override
    public Class<String> handledType(){
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        System.out.println(value);

        if(value != null){
            jsonGenerator.writeString(HtmlUtils.htmlEscape(value));
        }
    }
}
