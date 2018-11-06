package com.example.robin.myapplication;


public class Contact {
    private String id,message;

    public Contact(String id,String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}