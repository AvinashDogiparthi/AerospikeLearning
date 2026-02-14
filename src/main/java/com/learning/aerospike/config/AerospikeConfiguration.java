package com.learning.aerospike.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AerospikeConfiguration {

    @Value("${aerospike.host}")
    private String host;

    @Value("${aerospike.port}")
    private int port;

    @Value("${aerospike.timeout}")
    private int timeout;

    @Value("${aerospike.maxConnsPerNode}")
    private int maxConnsPerNode;

    @Bean(destroyMethod = "close")
    public AerospikeClient aerospikeClient() {
        ClientPolicy policy = new ClientPolicy();

        // 1. Connection Pooling: Critical for high concurrency
        policy.maxConnsPerNode = maxConnsPerNode;

        // 2. Timeout: Global timeout for connection establishment
        policy.timeout = timeout;

        // 3. Fail Fast: If DB is down, app should not start
        policy.failIfNotConnected = true;

        // 4. Connect
        Host[] hosts = new Host[]{new Host(host, port)};
        return new AerospikeClient(policy, hosts);
    }
}