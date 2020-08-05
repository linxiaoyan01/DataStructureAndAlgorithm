package com.algorithm.linkedList;

// 此代码不是王争写的
//这个题目暂且先放一边
//下面链接可以看王争写的代码
// https://github.com/wangzheng0822/algo/blob/master/java/06_linkedlist/LRUBasedArray.java

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LRUBaseArray {

    private static String[] lruArray = new String[10];//缓存最大容量为10
    private static String[] copyArray = new String[10];//数组元素变更时，用于数据相互拷贝
    private static HashMap<String, Integer> hashMap = new HashMap<>();


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            //取随机数
            String str = "str" + Math.round(Math.random() * 20);
            //每秒调用方法打印一次
            TimeUnit.SECONDS.sleep(1);
            LRUArithmetic(str);
        }
    }

    public static void LRUArithmetic(String str) {
        //如果缓存中已有
        if (hashMap.containsKey(str)) {
            //如果str不是第一位，就重排序
            if (hashMap.get(str) > 0) {
                int pos = hashMap.get(str);
                //把str放在第一位
                copyArray[0] = str;
                //实现两个数组数据的移动
                for (int i = 1; i <= pos; i++) {
                    copyArray[i] = lruArray[i - 1];
                }
                for (int i = pos + 1; i < 10; i++) {
                    copyArray[i] = lruArray[i];
                }
                //现在copyArray存放的就是新的数据，现在进行数据移动
                for (int i = 0; i < copyArray.length; i++) {
                    lruArray[i] = copyArray[i];
                    hashMap.put(lruArray[i], i);
                }
                copyArray = new String[10];
            }
        } else {
            //数组中不存在，就把str放在头部，其他元素挨个后移
            copyArray[0] = str;
            hashMap.put(str, 0);
//            if (!StringUtils.isEmpty(lruArray[9])) {
//                hashMap.remove(lruArray[9]);
//            }
            for (int i = 1; i < copyArray.length; i++) {
                copyArray[i] = lruArray[i - 1];
            }
            //现在copyArray存放的就是新的数据，现在进行数据移动
            for (int i = 0; i < copyArray.length; i++) {
                lruArray[i] = copyArray[i];
                hashMap.put(lruArray[i], i);
            }
            copyArray = new String[10];
        }
        //打印输出一下数组内的数据
        for (int i = 0; i < lruArray.length; i++) {
            System.out.print(lruArray[i] + ",");
        }
        System.out.println("--------------------分隔符---------------------");
    }
}
