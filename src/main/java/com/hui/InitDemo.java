package com.hui;

/**
 * @author: shenhaizhilong
 * @date: 2019/3/13 9:21
 */
public class InitDemo {
    private int b = 2;

    public InitDemo(int b)
    {

        // this.b ? 求b 的值
        System.out.println("this is init block:" + this.b);

        this.b = b;

    }

    {
        // this.b ? 求b 的值
        System.out.println("this is code block:" + b);
        this.b = 3;
    }

    public static void main(String[] args) {

        InitDemo initDemo = new InitDemo(5);

    }
}
