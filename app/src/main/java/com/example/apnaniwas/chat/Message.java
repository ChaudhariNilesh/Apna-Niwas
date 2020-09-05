package com.example.apnaniwas.chat;

public class Message {

    private int id;
    private String name,message;
    private Long time;

    public Message() {

    }

    public Message(int id, String name, String message, Long time) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
