package com.hui.algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hui.algorithm.CalculateConflictRate.getWordsList;



/**
 * @author: shenhaizhilong
 * @date: 2018/7/27 15:12
 */
public class CalculateConflictRate2 {
    public static void main(String[] args) {

        String path = "C:\\Users\\zhang\\Desktop\\words.txt";
        List<String> wordList = getWordsList(path);
        System.out.println("Count: " + wordList.size());
        int[] seeds = { 0, 5381, 2147483647, (int) 2166136261L};
        int[] multipliers = {1,2,5,8,10,31,33,37,39,41,73,100,101,991,4327,4637, 16777619,2147483647};
        for (int i = 0; i < seeds.length; i++) {
            for (int j = 0; j < multipliers.length; j++) {
                int seed = seeds[i];
                int multiplier = multipliers[j];
                String f = String.format("seed_%-12d, Multiplier_%-12d", seed, multiplier);
                List<Integer>  Hashes = getWordsDJBHashes(wordList, seed, multiplier );

                calculateConflictRate(f, Hashes);
                Map<Integer, Integer> hornerMap = partition(Hashes);
               // writeToFile(hornerMap, f+ ".csv");
            }

        }

       // findBestMultiplier(10000, wordList);

    }

    public static void findBestMultiplier(int n, List<String> wordList)
    {
        for (int i = 3; i <=n ; i+=2) {
            if(Prime.isPrime4(i))
            {
                List<Integer>  Hashes = getWordsDJBHashes(wordList, 0, i );
                String f = String.format("seed_%-12d, Multiplier_%-12d", 0, i);
                int conflictNum =   calculateConflictRate(f, Hashes);
                if(conflictNum < 12)
                {
                    f = String.format("seed_%-12d, Multiplier_%-12d, ConflictNum:_%-12d", 0, i, conflictNum);
                    System.out.println(f);
                }
            }
        }
    }

    public static List<Integer> getWordsDJBHashes(List<String> wordsList,int  seed, int  multiplier)
    {
        long t1 = System.nanoTime();
        List<Integer> hashList = new ArrayList<>(100000);
        for (String s :
                wordsList  ) {

            hashList.add(HashFunctions.horner(s, multiplier,seed));

        }
        long t2 = System.nanoTime();
//        String f = String.format("seed: %d, Multiplier: %d, Time: %s", seed, multiplier, (t2-t1)/1000/1000);
//        System.out.println(f);
        return hashList;

    }


    /**
     * 将整个哈希空间等分成128份，统计每个空间内的哈希值数量
     * @param hashs
     * @return
     */
    public static Map<Integer, Integer> partition(List<Integer> hashs) {
        // step = 2^32 / 2^6 = 2^26
        final int step = (1 << 26);

        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (int i = Integer.MIN_VALUE; start<64; i += step) {
            final int min = i;
            final int max = min + step == Integer.MIN_VALUE ? Integer.MAX_VALUE: min + step;
            long num = hashs.stream()
                    .filter(x -> x >= min && x < max).count();

            statistics.put(start++, (int) num);
        }



        return statistics;
    }


    public static int calculateConflictRate(String method, List<Integer> hashesList)
    {
        int maxhash = hashesList.stream().max(Integer::compareTo).get();
        int minhash = hashesList.stream().min(Integer::compareTo).get();

        int uniqHashNum =(int) hashesList.stream().distinct().count();
        int conflictNum = hashesList.size() - uniqHashNum;
        double conflictRate = conflictNum*1.0D/hashesList.size();
        System.out.println(String.format("method: %-12s, minhash: %012d, maxhash: %012d, conflictNum: %06d, conflictRate:%.6f%%",method, minhash, maxhash, conflictNum, conflictRate*100.0D) );
        return conflictNum;
    }


    public static void writeToFile(Map<Integer, Integer> map, String fileName)
    {
        String s;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName))))
        {
            for (Map.Entry<Integer, Integer> entry :
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


}
