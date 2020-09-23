package com.ssh.client.cache;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: ExpireThread
 * @package: com.ssh.client.cache
 * @describe: 定时线程
 * @author:（willdas）
 * @date: 2020/09/22 10:05
 **/
public class ExpireThread {

    {
        this.startThread();
    }

    private void startThread() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            this.expireCache();
        }, 1, 10, TimeUnit.MINUTES);
    }

    private void expireCache() {
        if (CacheMap.cacheMap.isEmpty()) {
            return;
        }

        for (Object key : CacheMap.cacheMap.keySet()) {
            CacheValue cacheValue = CacheMap.cacheMap.get(key);
            if (cacheValue.getExpireTime() != -1 && cacheValue.getExpireTime() <= System.currentTimeMillis()) {
                CacheMap.cacheMap.remove(key);
            }
        }
    }

}
