package com.learning.aerospike.model;


public class UserRequest {
    private Integer id;
    private String name;
    private String email;
    private int experience;
    private String department;

    public UserRequest(Integer id, String name, String email, int experience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    public UserRequest(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}