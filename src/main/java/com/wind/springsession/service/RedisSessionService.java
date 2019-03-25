package com.wind.springsession.service;

public interface RedisSessionService {

    void add(String name, String wsSessionId);

    boolean delete(String name);

    String get(String name);
}
