package com.bgl.mall.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Bu Guoliang
 * @date 2019/03/03 23:25
 * @since 1.8
 */
public class UniqueKeyUtil {

    private static AtomicLong count = new AtomicLong(0);

    private static final int MAX = 9999;

    public static String genUniqueId() {
        if(count.get() > MAX) {
            count.set(1);
        }
        long num = count.incrementAndGet();
        long time = System.currentTimeMillis() * 1000;
        return String.valueOf(time + num);
    }

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer i = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() * 100000 + String.valueOf(i);
    }
}
