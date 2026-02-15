package com.learning.aerospike.config;

import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeDataSettings;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableAerospikeRepositories(basePackages = "com.learning.aerospike.repository")
public class AerospikeConfiguration extends AbstractAerospikeDataConfiguration {

    @Value("${aerospike.host}")
    private String host;

    @Value("${aerospike.port}")
    private int port;

    @Value("${aerospike.namespace}")
    private String namespace;

    @Value("${aerospike.timeout}")
    private int timeout;

    @Value("${aerospike.maxConnsPerNode}")
    private int maxConnsPerNode;

    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host(host, port));
    }

    @Override
    protected String nameSpace() {
        return namespace;
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.maxConnsPerNode = maxConnsPerNode;
        clientPolicy.timeout = timeout;
        clientPolicy.failIfNotConnected = true;
        return clientPolicy;
    }

    @Override
    protected void configureDataSettings(AerospikeDataSettings.AerospikeDataSettingsBuilder builder) {
        builder.sendKey(true)
                .createIndexesOnStartup(true);
    }
}