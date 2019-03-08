package com.hui;

import java.util.HashSet;

/**
 *
 *
 * https://www.cnblogs.com/relucent/p/4955340.html
 * https://blog.csdn.net/en_joker/article/details/79806061
 *
 *  * Snowflake from twitter
 *  * SnowFlake struct
 *  * sign -   (current timestamp - startTimeStamp)<<12  (41bits)  - dataCenterId bits(5bits)  - work id bits(5bits) - sequence ID(12bits)
 *  * 0    -   0000000000 0000000000 0000000000 0000000000 0       - 00000                     - 00000               - 000000000000
 *
 *  1. we don't use sign bit
 *  2. we use 41 bits as the time part, time year = ((1L)<<41)/1000/3600/24/365 = 69 years
 *  3. 5 bits data center id bits
 *  4. 5 bits worker id bits
 *  5. 12 bits sequence id, we can produce 2^12 ids in one ms,in one machine( the same dataCenterId, work id,diff time stamp) at most.
 *
 *  the feature of snowflake : id Monotonous increase by time stamp, and we didn't see collision in  distributed system
 * @author: shenhaizhilong
 * @date: 2018/8/6 13:02
 */
public final class SnowFlakeIdWorker {
    //start time stamp 2018/08/06
    private final long startTimeStamp = 1533531858106L;

    private final int workerIdBits = 5;
    private final int dataCenterIdBits = 5;
    private final int sequenceBits = 12;

    private final long maxWorkerId = (1<< workerIdBits) -1;
    private final long maxDataCenterId = (1<< dataCenterIdBits) -1;
    private final long maxSequeceId = (1 << sequenceBits) -1;

    private final int workerIdShift = sequenceBits;
    private final int dataCenterIdShift = sequenceBits + workerIdBits;
    private final int timeStampIdShift = sequenceBits + workerIdBits + dataCenterIdBits;

    // singleton instance
    private static volatile SnowFlakeIdWorker workerSingleInstance = null;
    private static Object lock = new Object();

    private long workerId;
    private long dataCenterId;
    private long sequenceId = 0L;
    private long lastTimeStamp = -1L;


    /**
     *
     * @param workerId in 0-31
     * @param dataCenterId in 0-31
     */
    private SnowFlakeIdWorker(long workerId, long dataCenterId)
    {
        this.workerId = workerId & maxWorkerId;
        this.dataCenterId = dataCenterId & maxDataCenterId;
    }

    public static  SnowFlakeIdWorker getInstance(long workerId, long dataCenterId)
    {
        // double checked locking
        if(workerSingleInstance == null)
        {
            synchronized (lock)
            {
                if(workerSingleInstance == null)
                {
                    workerSingleInstance = new SnowFlakeIdWorker(workerId, dataCenterId);
                }

            }

        }
        return workerSingleInstance;
    }


    public synchronized long getNextId()
    {
        long timestamp = getTime();
        if(timestamp < lastTimeStamp)
        {
            lastTimeStamp = timestamp;
        }

        // 还在这一毫秒内，我们就增加 sequence id
        if(timestamp == lastTimeStamp)
        {
            sequenceId = (sequenceId + 1) & maxSequeceId;
            // 当增加 后的sequence id 与maxSequenceID 位与为0时，说明sequence 溢出，我们需要更新下一毫秒的时间戳
            if(sequenceId == 0)
            {
                timestamp = getNextMs(lastTimeStamp);
            }

        }else {
            // 不在这一毫秒内
            sequenceId = 0L;
        }

        lastTimeStamp = timestamp;
        return ((timestamp - startTimeStamp)<<timeStampIdShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequenceId;


    }

    /**
     * get next ms
     * @param lastTimeStamp
     * @return  next ms
     */
    private long getNextMs(long lastTimeStamp)
    {
        long currentTimestamp = getTime();
        while (currentTimestamp <= lastTimeStamp)
        {
            currentTimestamp = getTime();
        }
        return currentTimestamp;
    }
    /**
     *
     * @return current ms time
     */
    private long getTime()
    {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {

        int maxCount = 400000;
        HashSet<Long> ids = new HashSet<>(maxCount*10);
        for (int j = 0; j < 10; j++) {
            long t1 = System.currentTimeMillis();
            SnowFlakeIdWorker worker = SnowFlakeIdWorker.getInstance(11,37);
            for (int i = 0; i < maxCount; i++) {
                long id = worker.getNextId();

                if(ids.contains(id))
                {
                    System.out.println("find a collision :");
                    System.out.println( id + " ," + Long.toBinaryString(id));
                }else {
                    ids.add(id);
                }
            }

            System.out.println("set :size: " + ids.size());
            long t2 = System.currentTimeMillis();
            System.out.println(maxCount*1000/(t2-t1));
        }

      //  System.out.println(ids.size());
    }
}
