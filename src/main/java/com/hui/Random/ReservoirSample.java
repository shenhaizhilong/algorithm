package com.hui.Random;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * https://en.wikipedia.org/wiki/Reservoir_sampling
 * http://www.cnblogs.com/HappyAngel/archive/2011/02/07/1949762.html
 * https://blog.csdn.net/yinjunshishui/article/details/14647805
 *Reservoir sampling
 *
 *
 *Init : a reservoir with the size： k
 *
 * for i= k+1 to N
 *
 *     M=random(1, i);
 *
 *     if( M < k)
 *
 *      SWAP the Mth value and ith value
 *
 * end for
 *
 * @author: shenhaizhilong
 * @date: 2018/10/4 20:48
 */
public class ReservoirSample {


    // data's length > k
    public void select(int[] data, int[] reservoir, int k)
    {
        // init the reservoir array
        reservoir = Arrays.copyOf(data, k);
        Random random = new Random();
        for (int i = k; i < data.length; i++) {
            int id = random.nextInt(i);
            if(id < k)
            {
                int temp = data[id];
                data[id] = reservoir[id];
                reservoir[id] = temp;
            }
        }

    }

}
