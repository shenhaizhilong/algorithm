package com.hui.Hash;


import com.hui.BitOperation.BitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * https://moz.com/devblog/near-duplicate-detection/
 *
 * @author: shenhaizhilong
 * @date: 2018/7/29 12:01
 */
public class SimHash {

    private List<String> getPhrases(String str)
    {
        Pattern pattern  = Pattern.compile("[^\\w]");
        String[] words = pattern.split(str);
        List<String> list = new ArrayList<>(words.length);
        for (int i = 0; i < words.length -2; i++) {
            String s = String.join(" ",words[i],words[i+1],words[i+2]);
            list.add(s);
        }

        System.out.println("Phrases: " + list);
        return list;
    }

    private long[] getHashes(List<String> phrases )
    {
        int size = phrases.size();
        long[] hashes = new long[size];
        int i =0;
        for (String s :
                phrases) {
            hashes[i] = HashFunctions.MurmurHash64A(s);
            //hashes[i] = HashFunctions.horner(s, 31, 0);// calc every phrase's hashcode, if you want, you can change the multiplier
            i++;
        }

        return hashes;
    }

    private long getFingerPrint(long[] hashes)
    {
        int[] results = new int[64];
        long fingerPrint = 0L;
        for (int i = 0; i < hashes.length; i++) {
            long hash = hashes[i];
            for (int j = 0; j < 64; j++) {
                results[j] += ((hash & 1L) == 1L )? 1 : -1;
                hash = (hash>>1);
            }

        }
        for (int i = 0; i < results.length; i++) {
            fingerPrint |= (results[i] > 0) ?(1L<<i):0L;
        }
        return fingerPrint;
    }


    /**
     * get SimHash value
     * @param str
     * @return simHash value
     */

    public long getSimHash(String str)
    {
        List<String> list = getPhrases(str);
        long[] hashes = getHashes(list );
        long fingerPrint = getFingerPrint(hashes);
        return fingerPrint;
    }

    /**
     * get hamming distance
     * @param strA
     * @param strB
     * @return int hamming distance
     */
    public int distance(String strA, String strB)
    {

        long fingerPrintA = getSimHash(strA);
        long fingerPrintB = getSimHash(strB);
        int hammingDistance = BitUtil.hammingDistance(fingerPrintA, fingerPrintB);
        return hammingDistance;

    }



    public static void main(String[] args) {

        String a = "the bottom of the sea";
        String aa = "a frog on the bump on the log in the hole in the bottom of the sea";
        String bb = "a bump on the log in the hole in the bottom of the sea";
        String c = "Your mother drives you in the car";
        String d = "In mother Russia, car drives you!";
        SimHash simHash = new SimHash();
        System.out.println(simHash.distance(aa,bb));
        System.out.println(simHash.distance(aa,a));
        System.out.println(simHash.distance(c,d));
    }

}
