package com.ssh.client.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * @className: CacheUtil
 * @package: com.ssh.client.cache
 * @describe: 本地缓存工具类
 * @author:（willdas）
 * @date: 2020/09/22 09:57
 **/
public class CacheUtil<T> {

    private Logger log = LogManager.getLogger();

    private static CacheUtil cacheUtil;

    public static CacheUtil getInstance() {
        if (cacheUtil == null) {
            synchronized (CacheUtil.class) {
                if (cacheUtil == null) {
                    cacheUtil = new CacheUtil<>();
                }
            }
        }
        return cacheUtil;
    }

    private CacheUtil() {
        new ExpireThread();
        log.info("缓存线程已启动");
    }

    public T get(Object key) {
        if (key == null || key.toString().length() == 0) {
            return null;
        }

        if (CacheMap.cacheMap.isEmpty()) {
            return null;
        }

        CacheValue cacheValue = CacheMap.cacheMap.get(key);
        if (Objects.isNull(cacheValue)) {
            return null;
        }

        //惰性删除
        if (cacheValue.getExpireTime() != -1 && cacheValue.getExpireTime() <= System.currentTimeMillis()) {
            CacheMap.cacheMap.remove(key);
            return null;
        }

        cacheValue.setAccessCount(cacheValue.getAccessCount() + 1);
        cacheValue.setLastTime(System.currentTimeMillis());
        CacheMap.cacheMap.put(key, cacheValue);
        return (T) cacheValue.getValue();
    }

    public T set(Object key, Object value, long expireTime) {
        if (key == null || key.toString().length() == 0) {
            throw new CacheException("key is not empty");
        }
        if (expireTime == 0) {
            throw new CacheException("expireTime is not zero");
        }

        CacheValue cacheValue = CacheMap.cacheMap.get(key);
        if (cacheValue != null) {
            cacheValue.setAccessCount(cacheValue.getAccessCount() + 1);
            cacheValue.setLastTime(System.currentTimeMillis());
            cacheValue.setExpireTime(System.currentTimeMillis() + expireTime);
            cacheValue.setValue(value);
        }

        cacheValue = CacheValue.buildCacheValue(key, value, expireTime);
        CacheMap.cacheMap.put(key, cacheValue);

        return (T) value;
    }

    public void set(Object key, Object value) {
        if (key == null || key.toString().length() == 0) {
            throw new CacheException("key is not empty");
        }

        CacheMap.cacheMap.put(key, CacheValue.buildCacheValue(key, value, -1));
    }

    public static void clear() {
        CacheMap.cacheMap.keySet().forEach(k -> {
            if (CacheMap.cacheMap.get(k).getExpireTime() != -1) {
                CacheMap.cacheMap.remove(k);
            }
        });
    }
}
