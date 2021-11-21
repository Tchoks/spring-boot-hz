package com.tchokonthe.hz.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.tchokonthe.hz.listeners.CarEventListener;
import com.tchokonthe.hz.listeners.CustomerEventListener;
import com.tchokonthe.hz.model.Car;
import com.tchokonthe.hz.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author martin
 * @created on 09/11/2021 at 23:46
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@Configuration
@RequiredArgsConstructor
public class CacheConfiguration {

    public static final String CARS = "cars";
    public static final String CUSTOMERS = "customers";

    private final CarEventListener carEventListener;
    private final CustomerEventListener customerEventListener;


    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(createConfig());
    }

    @Bean
    public IMap<String, Car> carMap() {
        return hazelcastInstance().getMap(CARS);
    }

    @Bean
    public IMap<String, Customer> customerMap() {
        return hazelcastInstance().getMap(CUSTOMERS);
    }

    @Bean
    public HazelcastCacheManager hazelcastCacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    public Config createConfig() {
        Config config = new Config();
        config.setClusterName("dev");
        config.addMapConfig(carsConfig());
        config.addMapConfig(customersConfig());
//        config.setManagementCenterConfig(new ManagementCenterConfig().setScriptingEnabled(false).addTrustedInterface("127.0.0.1").addTrustedInterface("localhost"));
        config.setNetworkConfig(new NetworkConfig().setPort(5701).setPortAutoIncrement(true).setPortCount(20).setJoin(new JoinConfig().setMulticastConfig(new MulticastConfig().setEnabled(false)).setTcpIpConfig(new TcpIpConfig().addMember("127.0.0.1").addMember("172.17.0.2").addMember("localhost").setEnabled(true))));
        return config;
    }

    private MapConfig carsConfig() {
        MapConfig mapConfig = new MapConfig(CARS);
        mapConfig.setTimeToLiveSeconds(600);
        mapConfig.setMaxIdleSeconds(600);
        mapConfig.setEvictionConfig(evictionConfig());
        mapConfig.setEntryListenerConfigs(List.of(entryListenerConfig()));
        return mapConfig;
    }

    private MapConfig customersConfig() {
        MapConfig mapConfig = new MapConfig(CUSTOMERS);
        mapConfig.setTimeToLiveSeconds(600);
        mapConfig.setMaxIdleSeconds(600);
        mapConfig.setEvictionConfig(evictionConfig());
        mapConfig.setEntryListenerConfigs(List.of(entryListenerConfig()));
        return mapConfig;
    }

    private EvictionConfig evictionConfig() {
        final EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setSize(1000);
        evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);
        evictionConfig.setMaxSizePolicy(MaxSizePolicy.PER_NODE);
        return evictionConfig;
    }

    private EntryListenerConfig entryListenerConfig() {
        final EntryListenerConfig listenerConfig = new EntryListenerConfig();
        listenerConfig.setImplementation(carEventListener);
        listenerConfig.setImplementation(customerEventListener);
        listenerConfig.setIncludeValue(true);
        return listenerConfig;
    }


}
