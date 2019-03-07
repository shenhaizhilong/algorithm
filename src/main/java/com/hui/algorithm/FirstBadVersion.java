package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/29 22:30
 */
public class FirstBadVersion {

    /**
     *278. First Bad Version
     * DescriptionHintsSubmissionsDiscussSolution
     * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     *
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
     *
     * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
     *
     * Example:
     *
     * Given n = 5, and version = 4 is the first bad version.
     *
     * call isBadVersion(3) -> false
     * call isBadVersion(5) -> true
     * call isBadVersion(4) -> true
     *
     * Then 4 is the first bad version.
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while (l < r)
        {
            int middle = (l + r)>>>1;
            if(isBadVersion(middle))
            {
                r = middle;
            }else {
                l = middle + 1;
            }
        }
        return l;
    }

    private boolean isBadVersion(int n)
    {
        return false;
    }
}
