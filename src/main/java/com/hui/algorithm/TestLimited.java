package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/17 23:23
 *
 *
 * 3.1 实现一个类TestLimited, 类通过构造方法初始化int limitedTimes成员变量。
 *
 * 实现类的非静态成员方法qdsAllowed(),要求每秒钟qdsAllowed至多只能被调用limitedTimes次
 *
 *
 */
public class TestLimited {

    private  final int limitedTimes;
    private int count;
    private long lastTime;
    TestLimited(int limitedTimes){
        this.limitedTimes=limitedTimes;
        this.count = 0;
    }
    public void qdsAllowed(){
        if(lastTime == 0)
        {
            lastTime = System.currentTimeMillis();
        }
        long currTime = System.currentTimeMillis();
        if(currTime < lastTime + 2000 && count < limitedTimes)
        {
            this.count++;
            doSomething();

        }else if(currTime >= lastTime + 2000)
        {
            this.count = 1;
            doSomething();
            lastTime = currTime;
        }

    }

    public void doSomething()
    {
        System.out.println(this.count);
    }


    public static void main(String[] args) {
        TestLimited testLimited = new TestLimited(20);
       while (true)
       {
           testLimited.qdsAllowed();
       }
    }
}
