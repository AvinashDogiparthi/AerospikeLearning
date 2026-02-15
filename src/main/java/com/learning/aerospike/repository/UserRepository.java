package com.learning.aerospike.repository;

import com.learning.aerospike.model.UserRequest;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends AerospikeRepository<User, Integer> {

    // Spring automatically generates the query for this method!
    // It uses the secondary index 'dept_index' you defined in User.java
    List<User> findByDepartment(String department);

    // This uses the 'exp_index'
    List<User> findByExperienceGreaterThan(int experience);
}