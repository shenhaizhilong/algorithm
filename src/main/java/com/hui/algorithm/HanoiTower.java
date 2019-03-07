package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/7 16:07
 */
public class HanoiTower {
    public static void moveAndShow(int n, int x, int y, int z)
    {
        // we move top  n disks from x to y, using z as the transit point

        if(n > 0)
        {
            // move top n-1 disks from x to z
            moveAndShow(n-1, x, z, y);
            // move top disk from x to y
            System.out.println("move top disk from tower: " + x +" to top of tower: " + y);
            // move top n-1 disks from z to y
            moveAndShow(n-1, z, y, x);
        }
    }

    public static void main(String[] args) {
        moveAndShow(3,1,2,3);
    }
}
