package org.v1.implementation;

import org.springframework.stereotype.Component;
import org.v1.model.Contents;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class CashedContentHandler {
    private final ConcurrentHashMap<String, Contents> cache;

    public CashedContentHandler() {
        this.cache = new ConcurrentHashMap<>();
    }
    public void put(String key, Contents value) {
        cache.put(key, value);
    }

    public Contents get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
