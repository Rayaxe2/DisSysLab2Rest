package com.example.restservice;

import java.util.HashMap;

public class stockRespondant {
    private final Boolean noError;
    private final String content;

    public stockRespondant(Boolean noError, String content){
        this.noError = noError;
        this.content = content;
    }

    public Boolean getNoError() {
        return noError;
    }
    public String getContent() {
        return content;
    }

    //Example code
    /*
    private final long id;
    private final String content;
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    */
}
