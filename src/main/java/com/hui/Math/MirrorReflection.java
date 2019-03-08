package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/26 21:28
 *
 *
 *
 * 858. Mirror Reflection
 * DescriptionHintsSubmissionsDiscussSolution
 * There is a special square room with mirrors on each of the four walls.  Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 *
 * The square room has walls of length p, and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.
 *
 * Return the number of the receptor that the ray meets first.  (It is guaranteed that the ray will meet a receptor eventually.)
 *
 *
 *
 * Example 1:
 *
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 *
 *
 *
 * Note:
 *
 * 1 <= p <= 1000
 * 0 <= q <= p
 *
 *
 *
 */
public class MirrorReflection {

    public int mirrorReflection(int p, int q) {

        // p/q = (2k+1)/(2*m +1) == > 1
        // p/q = (2k +1)/2m ==> 0
        // p/q = 2k/(2m +1) ==> 2
        int g = gcd(p,q);
        return 1 + ((q /g)& 1) - ((p /g) &1);
    }

    private int gcd(int a, int b)
    {
        while (b != 0)
        {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int mirrorReflection2(int p, int q) {

        // p/q = (2k+1)/(2*m +1) == > 1
        // p/q = (2k +1)/2m ==> 0
        // p/q = 2k/(2m +1) ==> 2
        while ((p & 1) == 0 && (q & 1) == 0)
        {
            p >>= 1;
            q >>= 1;
        }
        return 1 + (q & 1) - (p & 1);
    }

}
