package org.v1.implementation;

import org.springframework.stereotype.Component;
import org.v1.model.Contents;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class CachedPhotoArticlesHandler {
    private final ConcurrentHashMap<String, Object> cache;

    public CachedPhotoArticlesHandler() {
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