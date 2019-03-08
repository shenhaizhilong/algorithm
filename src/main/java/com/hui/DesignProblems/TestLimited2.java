package com.hui.DesignProblems;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/17 23:46
 */
public class TestLimited2 {

    private  final int limitedTimes;
    private volatile int count;
    private volatile long lastTime;
    TestLimited2(int limitedTimes){
        this.limitedTimes=limitedTimes;
        this.count = 0;
    }
    public synchronized void qdsAllowed(){
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
        TestLimited2 testLimited = new TestLimited2(20);
        while (true)
        {
            testLimited.qdsAllowed();
        }
    }
}
