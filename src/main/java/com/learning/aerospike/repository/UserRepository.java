package com.learning.aerospike.repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.learning.aerospike.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private AerospikeClient client;

    @Value("${aerospike.namespace}")
    private String namespace;

    @Value("${aerospike.set.users}")
    private String setName;

    // Save a User POJO to Aerospike
    public void save(User user) {
        // 1. Create Key
        Key key = new Key(namespace, setName, user.getId());

        // 2. Create Bins
        Bin binName = new Bin("name", user.getName());
        Bin binEmail = new Bin("email", user.getEmail());
        Bin binExp = new Bin("experience", user.getExperience());

        // 3. Define Write Policy (Optional: Add TTL here if needed)
        WritePolicy policy = new WritePolicy();
        policy.sendKey = true; // Store the user ID (key) with the record for debugging

        // 4. Write
        client.put(policy, key, binName, binEmail, binExp);
    }

    // Retrieve a User by ID
    public Optional<User> findById(String id) {
        Key key = new Key(namespace, setName, id);
        Record record = client.get(null, key);

        if (record == null) {
            return Optional.empty();
        }

        // Map Record back to POJO
        User user = new User();
        user.setId(id);
        user.setName(record.getString("name"));
        user.setEmail(record.getString("email"));
        user.setExperience(record.getInt("experience"));

        return Optional.of(user);
    }
    
    // Delete a User
    public boolean delete(String id) {
        Key key = new Key(namespace, setName, id);
        return client.delete(null, key);
    }
}