package com.neo.config;

import com.hazelcast.config.*;
import com.hazelcast.instance.impl.HazelcastInstanceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config config(){
        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.NONE);
        evictionConfig.setMaxSizePolicy(MaxSizePolicy.PER_NODE);
        Config config = new Config();

        config.setInstanceName("testInstanceName")
                .setClusterName("testClusterName")
                .addMapConfig(new MapConfig().setName("test:cache")
                        .setBackupCount(1)
                        .setEvictionConfig(evictionConfig)
                        .setMaxIdleSeconds(0)
                        //永久存活
                        .setTimeToLiveSeconds(0));
        return config;
    }

    @Bean
    public HazelcastUtil hazelcastUtil(Config config){
        return new HazelcastUtil(HazelcastInstanceFactory.getOrCreateHazelcastInstance(config));
    }
}
