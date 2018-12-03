package com.zj.library;

import java.util.Random;

/**
 * 随机数
 */

public class RandomUtils {
    public static Random random = new Random();//随机数1-num
    public static int getRandom(int num) {
        int result = random.nextInt(num);//返回[0,num)集合中的整数，注意不包括10
        return result + 1;
    }

}
