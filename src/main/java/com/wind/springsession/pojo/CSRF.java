package com.wind.springsession.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CSRF {

    private String key;
    private Object value;
}
