package com.ssh.client.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CacheMap {

    public static ConcurrentHashMap<Object, CacheValue> cacheMap = new ConcurrentHashMap<>();

}