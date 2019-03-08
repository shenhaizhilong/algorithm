package com.hui.Hash;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hui.BitOperation.BytesUtil.bytesLEtoInt;
import static com.hui.BitOperation.BytesUtil.bytesLEtoLong;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/13 18:07
 */
public class HashFunctions {


    /* Thomas Wang's32 bit Mix Function */
    public static int fmix32(int key) {
        key = ~key + (key <<15);
        key = key^(key>>>12);
        key = key + (key <<2);
        key = key^(key >>>4);
        key = (key + (key<<3)) + (key<<11);
        key = key^(key>>>16);
        return key;
    }

    // jdk 1.7 ConCurrentHashMap hash function
    public static int hash(Object k) {
        int h = 0x5bd1e995;
        h ^= k.hashCode();
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }

    // jdk 1.8 spread hash function
    static final int spread(int h) {
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }

    /* For the hashing step we use TomasWang's 64 bit integer hash. */
    public static long fmix64(long key) {
        key = ~key + (key << 21);
        key = key^(key>>> 24);
        key = (key + (key << 3)) + (key << 8);
        key = key^(key >>> 14);
        key = (key + (key << 2)) + (key << 4);
        key = key^(key>>> 28);
        key = key + (key << 31);
        return key;
    }


    public static int MurmurHash2(String key) {
        int seed = 5381;
        return MurmurHash2(key, seed);
    }

    /**
     *   MurmurHash2, by Austin Appleby
     *     https://www.seebug.org/vuldb/ssvid-92615
     *     https://sites.google.com/site/murmurhash/
     *
     *       Note - This code makes a few assumptions about how your machine behaves -
     *       1. We can read a 4-byte value from any address without crashing
     *       2. sizeof(int) == 4
     *
     *       Murmur可以计算字符串的hash code,基本思想就是把key分成n组，每组4个字符，把这4个字符看成是一个uint_32，
     *       进行n次运算，得到一个h，然会在对h进行处理，得到一个相对离散的hash code;
     *
     *       And it has a few limitations -
     *
     *       1. It will not work incrementally.
     *       2. It will not produce the same results on little-endian and big-endian machines.
     *       3.
     *         The seed parameter is a means for you to randomize the hash function. You should provide the same seed value
     *         for all calls to the hashing function in the same application of the hashing function.
     *         However, each invocation of your application (assuming it is creating a new hash table)
     *         can use a different seed, e.g., a random value.
     *
     *
     *       Why is it provided?
     *
     *        One reason is that attackers may use the properties of a hash function to construct a denial of service attack.
     *        They could do this by providing strings to your hash function that all hash to the same value destroying the performance of your hash table.
     *        But if you use a different seed for each run of your program, the set of strings the attackers must use changes.
     *
     * @param key
     * @return
     */
    public static int MurmurHash2(String key, int seed) {
        /* 'm' and 'r' are mixing constants generated offline.
        They're not really 'magic', they just happen to work well.  */

        int  m = 0x5bd1e995;
        int r = 24;


        /* Mix 4 bytes at a time into the hash */
        byte[] data = key.getBytes();
        int len = data.length;
        /* Initialize the hash to a 'random' value*/
        int h = seed ^ len;

        int k;
        for (int i = 0; i < data.length -4; i+=4) {

            k = bytesLEtoInt(data,i);
            k *= m;
            k ^= k>>>r;
            k *= m;

            h *= m;
            h ^= k;
            len -= 4;

        }

        /* Handle the last few bytes of the input array  */
        switch(len) {
            case 3:
                h ^= data[data.length -1] << 16;
            case 2:
                h ^= (data[data.length -len +1] << 8);
            case 1:
                h ^= (data[data.length - len]);
                h *= m;
            default:
                break;
        }

        /* Do a few final mixes of the hash to ensure the last few
         * bytes are well-incorporated. */
        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;
        return h;
    }


    public static int MyMurmurHash2(String key, long seed) {
        /* 'm' and 'r' are mixing constants generated offline.
        They're not really 'magic', they just happen to work well.  */

        long  m = 0x5bd1e995L;
        int r = 24;


        /* Mix 4 bytes at a time into the hash */
        byte[] data = key.getBytes();
        int len = data.length;
        /* Initialize the hash to a 'random' value*/
        long h = seed ^ len;

        long k;
        for (int i = 0; i < data.length -4; i+=4) {

            k =(long) bytesLEtoInt(data,i);
            k *= m;
            k= k&0xffffffffL;
            k ^= k>>>r;
            k *= m;
            h *= m;
            h ^= k;
            len -= 4;

        }

        /* Handle the last few bytes of the input array  */
        switch(len) {
            case 3:
                h ^= (long)data[data.length -1] << 16;
            case 2:
                h ^= (long)(data[data.length -len +1] << 8);
            case 1:
                h ^= (long)(data[data.length - len]);
                h *= m;
            default:
                break;
        }

        /* Do a few final mixes of the hash to ensure the last few
         * bytes are well-incorporated. */
        h= h&0xffffffffL;
        h ^= h >>> 13;
        h *= m;
        h= h&0xffffffffL;
        h ^= h >>> 15;

        //map to [2^31,2^31 -1]
        return (int)(h&0xffffffffL);
    }



    //-----------------------------------------------------------------------------
// MurmurHash2, 64-bit versions, by Austin Appleby

// The same caveats as 32-bit MurmurHash2 apply here - beware of alignment
// and endian-ness issues if used across multiple platforms.

// 64-bit hash for 64-bit platforms

    public static long MurmurHash64A(String key)
    {
        long m = 0xc6a4a7935bd1e995L;
        long seed = 5381L;
        int r = 47;
        byte[] data = key.getBytes();
        int len = data.length;
        long h = seed ^ (len * m);

        for (int i = 0; i < data.length -8; i+=8) {

            long k = bytesLEtoLong(data,i);
            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
            len -=8;
        }

            switch (len)
            {
                case 7:
                    h ^= data[data.length -1] << 48;
                case 6:
                    h ^= data[data.length - (len -6) -1] << 40;
                case 5:
                    h ^= data[data.length - (len -5) -1] << 32;
                case 4:
                    h ^= data[data.length - (len -4) -1] << 24;
                case 3:
                    h ^= data[data.length - (len -3) -1] << 16;
                case 2:
                    h ^= data[data.length - (len -2) -1] << 8;
                case 1:
                    h ^= data[data.length - len];
                    h *= m;
                default:
                    break;
            }

            h ^= h >>> r;
            h *= m;
            h ^= h >>> r;
        return h;
    }

    public static int MurmurHash3(String key) {
        int seed = 5381;
        return MurmurHash3(key, seed);
    }
    /**
     * https://github.com/aappleby/smhasher/wiki/MurmurHash3
     * https://github.com/aappleby/smhasher/blob/master/src/MurmurHash3.cpp
     *
     * @param key
     * @param seed
     * @return
     */
    public static int MurmurHash3(String key, int seed) {
        /* 'm' and 'r' are mixing constants generated offline.
        They're not really 'magic', they just happen to work well.  */

        long c1 = 0xcc9e2d51L;
        long c2 = 0x1b873593L;
        int m1 = 5;
        long n1 = 0xe6546b64L;


        /* Mix 4 bytes at a time into the hash */
        byte[] data = key.getBytes();
        int len = data.length;
        /* Initialize the hash to a 'random' value*/
        long h = seed;

        long k;
        for (int i = 0; i < data.length -4; i+=4) {

            k = bytesLEtoInt(data,i);
            k *= c1;
            k = k&0xffffffffL;
            k = Integer.rotateLeft((int)k, 15);
            k = k&0xffffffffL;
            k *= c2;
            k = k&0xffffffffL;


            h ^= k;
            h = h &0xffffffffL;
            h = Integer.rotateLeft((int)h, 13);
            h = h &0xffffffffL;
            h = h*m1 +n1;
            h = h &0xffffffffL;
            len -= 4;

        }


        /* Handle the last few bytes of the input array  */
        long k2 = 0L;
        switch(len) {
            case 3:
                k2 ^= data[data.length -1] << 16;
            case 2:
                k2 ^= (data[data.length -len +1] << 8);
            case 1:
                k2 ^= (data[data.length - len]);
                k2 *= c1;
                k2 = k2 & 0xffffffffL;
                k2 = Integer.rotateLeft((int)k2, 15);
                k2 *= c2;
                h ^=k2;
            default:
                break;
        }

        // finalization
        /* Do a few final mixes of the hash to ensure the last few
         * bytes are well-incorporated. */
        h = h &0xffffffffL;
        h ^=key.length();
        h = murmurMix32Bit((int)h);
        return (int)h;
    }

    //-----------------------------------------------------------------------------
// Finalization mix - force all bits of a hash block to avalanche
    public static int murmurMix32Bit(int h)
    {
        h ^= h >>> 16;
        h *= 0x85ebca6b;
        h ^= h >>> 13;
        h *= 0xc2b2ae35;
        h ^= h >>> 16;
        return h;
    }

    public static long murmurMix64Bit(long h)
    {
        h ^= h>>>33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h>>>33;
        h *= 0xc4ceb9fe1a85ec53L;
        h ^= h >>>33;
        return h;
    }
    /**
     *  not good if key is similar, for an example:
     *  **************************************************
     *  MethodName: DJBHash, string: 192.168.0.1 #1, HashValue: 452888545
     *  MethodName: DJBHash, string: 192.168.0.1 #2, HashValue: 452888544
     *  MethodName: DJBHash, string: 192.168.0.1 #3, HashValue: 452888543
     *  MethodName: DJBHash, string: 192.168.0.1 #4, HashValue: 452888542
     *  MethodName: DJBHash, string: 192.168.0.1 #5, HashValue: 452888541
     *  MethodName: DJBHash, Min:452888541, Max: 452888545, Diff:4
     * @param key
     * @return
     */
    /* And a case sensitive hash function (based on djb hash) */
    public static int DJBHash(String key) {

        //5381 is a prime

        int hash = 5381;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            hash = ((hash << 5) + hash) + key.charAt(i); /* hash * 33 + c */
        }
        return Math.abs(hash);
    }

    /**
     * better than DJBHash
     * @param key
     * @return
     */
    public static int DJB2Hash(String key) {

        //5381 is a prime

        int hash = 5381;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            hash = ((hash << 5) + hash) + key.charAt(i); /* hash * 33 + c */
        }

        // mix
        hash += hash << 23;
        hash ^= hash >>> 7;
        hash += hash << 3;
        hash ^= hash >>> 27;
        hash += hash << 11;
        return hash;
    }


    /**
     *
     * FNV hashes are designed to be fast while maintaining a low collision rate. The FNV speed allows one to quickly
     * hash lots of data while maintaining a reasonable collision rate.
     * The high dispersion of the FNV hashes makes them well suited for hashing nearly identical strings such as URLs, hostnames,
     * filenames, text, IP addresses, etc.It can hash to zero, for an example string: +!=yG, HashValue: 0
     * http://www.isthe.com/chongo/tech/comp/fnv/#other-folding
     *
     *  FNV_2_32Bit is better than FNV_1a_32Bit, FNV_1a_32Bit is better than FNV_1_32Bit
     *  **************************************************
     *  MethodName: FNV_2_32Bit, string: 192.168.0.1 #1, HashValue: 1103598735
     *  MethodName: FNV_2_32Bit, string: 192.168.0.1 #2, HashValue: -588245747
     *  MethodName: FNV_2_32Bit, string: 192.168.0.1 #3, HashValue: 280529813
     *  MethodName: FNV_2_32Bit, string: 192.168.0.1 #4, HashValue: 467510408
     *  MethodName: FNV_2_32Bit, string: 192.168.0.1 #5, HashValue: 1043595207
     *  MethodName: FNV_2_32Bit, Min:-588245747, Max: 1103598735, Diff:1691844482
     *  **************************************************
     *  MethodName: FNV_1a_32Bit, string: 192.168.0.1 #1, HashValue: 1504854733
     *  MethodName: FNV_1a_32Bit, string: 192.168.0.1 #2, HashValue: 1454521876
     *  MethodName: FNV_1a_32Bit, string: 192.168.0.1 #3, HashValue: 1471299495
     *  MethodName: FNV_1a_32Bit, string: 192.168.0.1 #4, HashValue: 1420966638
     *  MethodName: FNV_1a_32Bit, string: 192.168.0.1 #5, HashValue: 1437744257
     *  MethodName: FNV_1a_32Bit, string: +!=yG, HashValue: 0
     *  MethodName: FNV_1a_32Bit, Min:1420966638, Max: 1504854733, Diff:83888095
     *
     *  **************************************************
     *  MethodName: FNV_1_32Bit, string: 192.168.0.1 #1, HashValue: -2069285843
     *  MethodName: FNV_1_32Bit, string: 192.168.0.1 #2, HashValue: -2069285842
     *  MethodName: FNV_1_32Bit, string: 192.168.0.1 #3, HashValue: -2069285841
     *  MethodName: FNV_1_32Bit, string: 192.168.0.1 #4, HashValue: -2069285848
     *  MethodName: FNV_1_32Bit, string: 192.168.0.1 #5, HashValue: -2069285847
     *  MethodName: FNV_1_32Bit, Min:-2069285848, Max: -2069285841, Diff:7
     *
     * @param str
     * @return
     */
    public static int FNV_2_32Bit(String str){
        final int p = 16777619;
        int hash =  (int) 2166136261L;
        for(int i = 0; i< str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;

        // mix
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }


    /**
     *
     * There is a minor variation of the FNV hash algorithm known as FNV-1a:
     * hash = offset_basis
     * for each octet_of_data to be hashed
     *         hash = hash xor octet_of_data
     *         hash = hash * FNV_prime
     * return hash
     * @param str
     * @return
     */
    public static int FNV_1a_32Bit(String str){
        // https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function  素数选择
        final int p = 16777619;
        int hash =  (int) 2166136261L;
        for(int i = 0; i< str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        return hash;
    }


    /**
     *
     * The core of the FNV-1 hash algorithm is as follows:
     * hash = offset_basis
     * for each octet_of_data to be hashed
     *         hash = hash * FNV_prime
     *         hash = hash xor octet_of_data
     * return hash
     * return hash
     * @param str
     * @return
     */
    public static int FNV_1_32Bit(String str){
        final int p = 16777619;
        int hash =  (int) 2166136261L;
        for(int i = 0; i< str.length(); i++)
        {
            hash = hash  * p;
            hash = hash ^ str.charAt(i);
        }

        return hash;
    }

    /**
     * FNV_1_64Bit
     * @param str
     * @return
     */
    public static long FNV_1_64Bit(String str){

        final long p = 1099511628211L;
        long hash =  2166136261L;
        for(int i = 0; i< str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;

        // mix
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;

    }


    public static int SipHash_2_4_32bit(String key)
    {
        SipHash_2_4 sipHash_2_4 = new SipHash_2_4();
        byte[] testKey = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
                (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e,
                (byte) 0x0f };
        int hash =(int) sipHash_2_4.hash(testKey, key.getBytes());
        return hash;

    }


    /**
     * based on horner'r rule, I recommend use 4327,4637,because it's have 9 conflict hashcode in 466544 english words(/usr/dict/words.txt).
     *
     * y = (1<<3)*x -x
     * z = 4327*x = y + (y<<5) + (1<<12)*x
     * @param s
     * @param multiplier
     * @param seed
     * @return
     */
    public static long horner(String s,long multiplier, long seed)
    {
        long h = seed;
        for (int i = 0; i < s.length(); i++) {
            h = multiplier*h + s.charAt(i);
        }
        return h;

    }

    public static int horner(String s)
    {
       return horner(s,4327,0);

    }

    public static int horner31(String s)
    {
        return horner(s,31,0);

    }


    public static int horner(String s,int multiplier, int seed)
    {
        int h = seed;
        for (int i = 0; i < s.length(); i++) {
            h = multiplier*h + s.charAt(i);
        }
        return h;

    }

    /**
     * Algorithms in c, Robert Sedgwicks,
     *  如果字符串最后一个字符不同，其他字符相同，且最后一个字符递增或递减，
     *  则hash 值同样会连续分布，按照递增或者递减
     *  ip 类型数据冲突率很低约为0.001831%
     *
     *  http://www.partow.net/programming/hashfunctions/#RSHashFunction
     * @param s
     * @return
     */

    public static int robertSegewicks(String s)
    {
        return robertSegewicks(s, 31, 378551);
    }


    // multiplier = 31, 63689,37,73 etc
    // seed = 378551;
    public static int robertSegewicks(String s, int multiplier, int seed)
    {
        int hash = 0;
        for(int i = 0; i < s.length(); i++)
        {
            hash = (hash*multiplier)^ s.charAt(i);  // hash = (hash*multiplier) +  s.charAt(i); // also works well
            multiplier = multiplier*seed;
        }
        return hash;

    }

    /**
     * Justin Sobel hash
     * ip 类型数据冲突率高于RSHashes，约为0.1%
     * @param str
     * @return
     */
    public static int JSHash(String str)
    {
        int hash = 1315423911;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((hash <<5 ) + str.charAt(i) + (hash >>> 2));
        }
        return hash;
    }

    /**
     *
     * Data structures and algorithms in Java 6th Edition 2014
     * better distance is 5, seed is 0
     * ip 类型数据冲突率比较高，约为36%
     * @param s
     * @param distance
     * @param seed
     * @return
     */
    public static int cycleShift(String s, int distance, int seed)
    {
        int h = seed;
        for (int i = 0; i < s.length(); i++) {
            h = Integer.rotateLeft(h, distance);
            h += (int)s.charAt(i);
        }
        return h;
    }

    public static int cycleShift(String s)
    {
        return cycleShift(s, 5,0);
    }

    /**
     * knuth the art of programing.
     * ip 类型数据冲突率比较高，约为37%
     * @param str
     * @return
     */

    public static int KnuthHash(String str)
    {
        int hash = str.length();
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 5)|(hash >>>27);
            hash ^= str.charAt(i);
        }
        return hash;
    }

    /**
     * it's also belong to horner's rule algorithm
     *
     * http://www.partow.net/programming/hashfunctions/#SDBMHashFunction
     * @param str
     * @return
     */
    public static int SDBMHash(String str)
    {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 6) + (hash << 16) - hash + str.charAt(i);  // equal to hash = hash *65599 + str.chart(i),
        }
        return hash;
    }





    /**
     * seed = 31,131, 1313, 13131,131313 etc, BKDR also belongs to horner's rule algorithm
     * http://www.partow.net/programming/hashfunctions/#BKDRHashFunction
     *
     * @param str
     * @param seed
     * @return
     */
    public static int BKDRHash(String str, int seed)
    {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash*seed) + str.charAt(i);
        }
        return hash;
    }


    public static int BKDRHash(String str)
    {
        return BKDRHash(str, 131);
    }


    /**
     * An algorithm produced by me Arash Partow.
     * better than JSHash/cycleHash/knuthHash, not good as RSHash
     * 当字符串只有最后一个字符不同时，hash 值很接近
     * http://www.partow.net/programming/hashfunctions/#APHashFunction
     * @param str
     * @return
     */
    public static int APHash(String str)
    {
        int hash = 0xAAAAAAAA;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ( (i&1) == 0) ? ((hash << 7)^(str.charAt(i))*(hash >> 3)):
                    (~((hash << 11) + (str.charAt(i)^(hash >>> 5))));
        }
        return hash;
    }

    private static long[] cryptTable = new long[0x500];
    // initialization cryptTable
    static {
        prepareCryptTable();
    }

    /**
     * https://blog.csdn.net/v_JULY_v/article/details/6256463
     */
    private static void prepareCryptTable()
    {

        long seed = 0x00100001, temp1, temp2;
        for (int i = 0; i < 0x100; i++) {
            for (int j = i, k = 0; k < 5; k++, j += 0x100) {
                seed = (seed * 125L + 3L)%0x2AAAABL;
                temp1 = (seed & 0xFFFFL) << 0x10;
                seed = (seed * 125L + 3L)%0x2AAAABL;
                temp2 = (seed & 0xFFFF);
                cryptTable[j] = (temp1 | temp2);
            }
        }
    }

    /**
     * https://blog.csdn.net/v_JULY_v/article/details/6256463
     * @param str
     * @return
     */
    public static long oneWayHash(String str)
    {
        return oneWayHash(str,2);
    }

    public static long oneWayHash(String str, int dwHashType)
    {
        long seed1 =0x7FED7FEDL, seed2 = 0xEEEEEEEEL;
        char c;
        int index;
        for (int i = 0; i < str.length(); i++) {
             c = str.charAt(i);
             index = ((dwHashType << 8) + c) &0x500;
             seed1 = cryptTable[index] ^ (seed1 + seed2);
             seed2 = c + seed1 + seed2 + (seed2 << 5) + 3;
        }
        return seed2;
    }





    /**
     * use reflect to pass a function
     * @param strs
     * @param functionname
     */
    public static void calcHashValue(String[] strs, String functionname)
    {
        List<Long> list = new ArrayList<>(strs.length);
         try
            {
                Method method = HashFunctions.class.getMethod(functionname, String.class);
                for (String s:
                     strs) {
                    Object value =  method.invoke(null, s);
                    if(value instanceof Integer)
                    {
                        list.add(((Integer) value).longValue());
                    }else if( value instanceof Long)
                    {
                        list.add((Long)value);
                    }

                    System.out.println("MethodName: " + functionname + ", string: " + s + ", HashValue: " + value );
                }

            }catch (NoSuchMethodException ex)
            {
                ex.printStackTrace();
            }catch (IllegalAccessException ex)
            {
                ex.printStackTrace();
            }catch (InvocationTargetException ex)
            {
                ex.printStackTrace();
            }



        long min = Collections.min(list);
        long max = Collections.max(list);
        System.out.println("MethodName: " + functionname + ", Min:" + min + ", Max: " + max + ", Diff:" + (max - min) );
        System.out.println("**************************************************");
    }


    public static void calcHashValue2(String[] strs, HashFunction hashFunction)
    {
        List<Long> list = new ArrayList<>(strs.length);
        String functionname = hashFunction.toString();
        int value;
        for (String s :
                strs) {


            value = hashFunction.hash(s);
            list.add((long)value);
            System.out.println("MethodName: " + functionname + ", string: " + s + ", HashValue: " + value );

        }

        long min = Collections.min(list);
        long max = Collections.max(list);
        System.out.println("MethodName: " + functionname + ", Min:" + min + ", Max: " + max + ", Diff:" + (max - min) );
        System.out.println("**************************************************");
    }


}
