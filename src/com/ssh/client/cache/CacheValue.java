package com.ssh.client.cache;


import java.io.Serializable;

/**
 * @className: CacheValue
 * @package: com.ssh.client.cache
 * @describe: 缓存对象
 * @author:（willdas）
 * @date: 2020/09/21 21:39
 **/
public class CacheValue implements Serializable, Comparable<CacheValue> {

    //缓存的Key
    private Object key;

    //缓存的值
    private Object value;

    //最后访问时间 单位：毫秒
    private long lastTime;

    //写入时间 单位：毫秒
    private long writeTime;

    //过期时间 单位：毫秒
    private long expireTime;

    //访问次数
    private Integer accessCount;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public long getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(long writeTime) {
        this.writeTime = writeTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    @Override
    public int compareTo(CacheValue o) {
        return accessCount.compareTo(o.accessCount);
    }

    public static CacheValue buildCacheValue(Object key, Object value, long expireTime) {
        CacheValue cacheValue = new CacheValue();
        cacheValue.setKey(key);
        cacheValue.setValue(value);
        cacheValue.setExpireTime(expireTime > 0 ? System.currentTimeMillis() + expireTime : expireTime);
        cacheValue.setAccessCount(0);
        cacheValue.setLastTime(0);
        cacheValue.setWriteTime(System.currentTimeMillis());
        return cacheValue;
    }

    @Override
    public String toString() {
        return "CacheValue{" +
                "key=" + key +
                ", value=" + value +
                ", expireTime=" + expireTime +
                '}';
    }
}
