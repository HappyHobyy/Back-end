package org.v1.implementation.content;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class CachedContentHandler {
    private final ConcurrentHashMap<String, Object> cache;

    public CachedContentHandler() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }
}