package com.learning.aerospike.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.aerospike.client.query.IndexType;

// Spring Data Aerospike Imports
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.aerospike.annotation.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private int id;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("dept")
    @Indexed(type = IndexType.STRING, name = "dept_index")
    private String department;
    
    @Field("experience")
    @Indexed(type = IndexType.NUMERIC, name = "exp_index")
    private int experience;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}