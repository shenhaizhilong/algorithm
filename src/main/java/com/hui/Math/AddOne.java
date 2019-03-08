package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/30 21:28
 */
public class AddOne {

    private boolean[] val;
    private  final   int length;
    private static final int DEFAULTLENGTH = 64;
    public AddOne(int length)
    {
        this.length = length;
        val = new boolean[this.length];
    }

    public AddOne()
    {
       this(DEFAULTLENGTH);
    }

    private long toLong()
    {
        long results = 0;
        for (int i = 0; i < val.length; i++) {
            if(val[i] == true)
            {
                results += (1L<<i);
            }
        }
        return results;
    }

    public long addOne()
    {
        int i = 0;
        while (i < val.length && val[i] == true)
        {
            val[i] = false;
            i++;
        }
        if(i < val.length)
        {
            val[i] = true;
        }

        return toLong();
    }

    public static void main(String[] args) {

        AddOne addOne = new AddOne();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            addOne.addOne();
        }
        System.out.println(addOne.addOne());
    }

}
