package com.project.polly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer greetingId;
    private String content;

    public Greeting() {}

    public Greeting(Integer greetingId, String content) {
        this.greetingId = greetingId;
        this.content = content;
    }

    public Integer getGreetingId(){
        return greetingId;
    }

    public String getContent(){
        return content;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "greetingId=" + greetingId +
                ", content='" + content + '\'' +
                '}';
    }
}
