package com.redisapps.utils;

import com.redisapps.Service.RedisService;

public class DistributionLock extends Thread {
    private RedisService redisService;
    private String threadName;
    private String key = "dl";

    public DistributionLock(RedisService redisService, String threadName) {
        this.redisService = redisService;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            String rs = redisService.getValues(key);
            if (rs.equals("false")) {
                redisService.setValuesNX(key, threadName);
                System.out.println(threadName + " enrolls data");
                try {
                    Thread.sleep(5000);
                    System.out.println(threadName + " delete data");
                    redisService.removeValues(key);
                    break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    System.out.println(threadName + " wait..");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
