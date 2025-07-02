package com.example.Model;

public class Student {

    private Long id;
    private Person person;
    private String name;
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Person getPerosn() {
        return person;
    }
    public void setPerosn(Person person) {
        this.person = person;
    }
    
}