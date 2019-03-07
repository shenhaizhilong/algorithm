package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/13 11:02
 */
public interface HashFunction {
    /**
     * return the key's hash value
     * @param key
     * @return int
     */
     int hash(String key);

}
