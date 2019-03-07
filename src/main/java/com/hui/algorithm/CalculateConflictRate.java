package com.hui.algorithm;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/15 10:35
 */
public class CalculateConflictRate {



    public static void main(String[] args) {

        String path = "C:\\Users\\zhang\\Desktop\\words.txt";
        List<String> wordList = getWordsList(path);
        System.out.println(wordList.get(0));
        System.out.println("Count: " + wordList.size());
        List<Long>  DJBHashes = getWordsHashes(wordList, "DJBHash");
        List<Long> DJB2Hashes = getWordsHashes(wordList, "DJB2Hash");
        List<Long>  MurmurHash2Hashes = getWordsHashes(wordList, "MurmurHash2");
        List<Long>  MurmurHash3Hashes = getWordsHashes(wordList, "MurmurHash3");
        List<Long> MurmurHash64AHashes = getWordsHashes(wordList, "MurmurHash64A");
        List<Long> FNV_1_32Hashes = getWordsHashes(wordList, "FNV_1_32Bit");
        List<Long> FNV_1a_32Hashes = getWordsHashes(wordList, "FNV_1a_32Bit");
        List<Long> FNV_2_32Hashes = getWordsHashes(wordList, "FNV_2_32Bit");
        List<Long> FNV_1_64BitHashes = getWordsHashes(wordList, "FNV_1_64Bit");
        List<Long> SipHash_2_4Hashes = getWordSip2_4_Hashes(wordList);
        List<Long> HornerHashes = getWordsHashes(wordList, "horner");
        List<Long> Horner31Hashes = getWordsHashes(wordList, "horner31");
        List<Long> RSHashes = getWordsHashes(wordList, "robertSegewicks");
        List<Long> JSHashes = getWordsHashes(wordList, "JSHash");
        List<Long> cycleHashes =getWordsHashes(wordList, "cycleShift");
        List<Long> knuthHashes = getWordsHashes(wordList, "KnuthHash");
        List<Long> APHashes = getWordsHashes(wordList, "APHash");
        List<Long> oneWayHashes = getWordsHashes(wordList, "oneWayHash");


        calculateConflictRate("DJB", DJBHashes);
        calculateConflictRate("DJB2", DJB2Hashes);
        calculateConflictRate("MurmurHash2",MurmurHash2Hashes);
        calculateConflictRate("MurmurHash3", MurmurHash3Hashes);
        calculateConflictRate("MurmurHash64A",MurmurHash64AHashes);
        calculateConflictRate("FNV_1_32Hashes", FNV_1_32Hashes);
        calculateConflictRate("FNV_1a_32Hashes", FNV_1a_32Hashes);
        calculateConflictRate("FNV_2_32Hashes", FNV_2_32Hashes);
        calculateConflictRate("FNV_1_64BitHashes", FNV_1_64BitHashes);
        calculateConflictRate("SipHash_2_4", SipHash_2_4Hashes);
        calculateConflictRate("horner", HornerHashes);
        calculateConflictRate("horner31", Horner31Hashes);
        calculateConflictRate("RSHashes", RSHashes);
        calculateConflictRate("JSHashes", JSHashes);
        calculateConflictRate("cycleHashes", cycleHashes);
        calculateConflictRate("knuthHashes", knuthHashes);
        calculateConflictRate("APHashes", APHashes);
        calculateConflictRate("oneWayHash", oneWayHashes);


//
//        Map<Long, Long> DJBMap = partition(DJBHashes);
//        Map<Long, Long> DJB2Map = partition(DJB2Hashes);
//        Map<Long, Long> MurmurHash2Map = partition(MurmurHash2Hashes);
//        Map<Long, Long> MurmurHash3Map = partition(MurmurHash3Hashes);
//        Map<Long, Long> MurmurHash64AMap = partition(MurmurHash64AHashes);
//        Map<Long, Long> FNV_32Map = partition(FNV_32Hashes);
//        Map<Long, Long> FNV_1_64BitMap = partition(FNV_1_64BitHashes);
//        Map<Long, Long> SipHash_2_4Map = partition(SipHash_2_4Hashes);

//       writeToFile(DJBMap, "DJBMap.csv");
//       writeToFile(DJB2Map, "DJB2Map.csv");
//       // DJBMap = null;
//        writeToFile(MurmurHash2Map, "MurmurHash2Map.csv");
//        writeToFile(MurmurHash3Map, "MurmurHash3Map.csv");
//       // MurmurHash2Map = null;
//        writeToFile(MurmurHash64AMap, "MurmurHash64AMap.csv");
//        //MurmurHash64AMap = null;
//        writeToFile(FNV_32Map, "FNV_32Map.csv");
//       // FNV_32Map =null;
//
//        writeToFile(FNV_1_64BitMap, "FNV_1_64BitMap.csv");
//
//        writeToFile(SipHash_2_4Map, "SipHash_2_4Map.csv");

    }


    public static void writeToFile(Map<Long, Long> map, String fileName)
    {
        String s;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName))))
        {
            for (Map.Entry<Long, Long> entry :
                    map.entrySet()) {
                s = entry.getKey() + "," + entry.getValue() +"\n";
                writer.write(s);
            }
        }catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 将整个哈希空间等分成128份，统计每个空间内的哈希值数量
     * @param hashs
     * @return
     */
    public static Map<Long, Long> partition(List<Long> hashs) {
        // step = 2^32 / 2^6 = 2^26
        final long step = (1L<<26);
        List<Long> nums = new ArrayList<>();
        Map<Long, Long> statistics = new LinkedHashMap<>();
        long start = 0;
        for (long i = 0L; i < Integer.MAX_VALUE && i >=0L; i += step) {
            final long min = i;
            final long max = min + step;
            long num =  hashs.stream()
                    .filter(x -> x >= min && x < max).count();

            statistics.put(start++, num);
            nums.add(num);
        }

//        // double confirm with hashes size
        long hashNum = nums.stream().reduce((x, y) -> x + y).get();
        assert hashNum == hashs.size();

        return statistics;
    }

    public static void calculateConflictRate(String method, List<Long> hashesList)
    {
        long maxhash = hashesList.stream().max(Long::compareTo).get();
        long minhash = hashesList.stream().min(Long::compareTo).get();

        long uniqHashNum = hashesList.stream().distinct().count();
        long conflictNum = hashesList.size() - uniqHashNum;
        double conflictRate = conflictNum*100.0D/hashesList.size();
        System.out.println(String.format("method: %-12s, minhash: %6d, maxhash: %6d, conflictNum: %6d, conflictRate:%.6f%%",method, minhash, maxhash, conflictNum, conflictRate) );

    }

    public static List<String> getWordsList(String path)
    {
        List<String> list = new ArrayList<>(300000);
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path))))
        {
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                list.add(line);
            }
        }catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return list;
    }



    public static List<Long> getWordSip2_4_Hashes(List<String> wordsList)
    {
        SipHash_2_4 sipHash_2_4 = new SipHash_2_4();
        byte[] testKey = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
                (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e,
                (byte) 0x0f };
        long t1 = System.nanoTime();
        List<Long> hashList = new ArrayList<>(100000);
        for (String s :
                wordsList  ) {

            hashList.add(sipHash_2_4.hash(testKey, s.getBytes()));

        }

        long t2 = System.nanoTime();
        System.out.println("SipHash_2_4 used time: " + (t2-t1)/1000/1000);
        return hashList;

    }



    public static List<Long> getWordsHashes(List<String> wordsList, String functionName)
    {

        List<Long> hashList = new ArrayList<>(10000);
        long t1 = System.nanoTime();
        try
        {
            Method method = HashFunctions.class.getMethod(functionName, String.class);
            for (String word :
                   wordsList ) {
                Object obj = method.invoke(null, word);
                if(obj instanceof Integer)
                {
                    hashList.add(((Integer) obj).longValue());
                }else if( obj instanceof Long)
                {
                    hashList.add((Long)obj);
                }
            }

        }catch (NoSuchMethodException ex)
        {
            System.out.println(ex.getMessage());
        }catch (IllegalAccessException ex)
        {
            System.out.println(ex.getMessage());
        }catch (InvocationTargetException ex)
        {
            System.out.println(ex.getMessage());
        }

        long t2 = System.nanoTime();
        System.out.println( functionName + " used time: " + (t2-t1)/1000/1000);
        return hashList;
    }

}
