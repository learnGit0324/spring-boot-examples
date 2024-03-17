package com.neo.config;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastUtil {
    private final HazelcastInstance hazelcastInstance;
    private static final String MAP_NAME = "test:cache";

    public HazelcastUtil(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public Object get(String key){
        IMap<Object, Object> map = hazelcastInstance.getMap(MAP_NAME);
        return map.get(key);
    }

    public void set(String key, String value){
        IMap<Object, Object> map = hazelcastInstance.getMap(MAP_NAME);
        map.put(key, value);
    }

    public void del(String key){
        IMap<Object, Object> map = hazelcastInstance.getMap(MAP_NAME);
        map.remove(key);
    }

    public void delAllCache(){
        IMap<Object, Object> map = hazelcastInstance.getMap(MAP_NAME);
        map.clear();
    }
}
