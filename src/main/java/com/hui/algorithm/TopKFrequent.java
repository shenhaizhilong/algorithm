package com.hui.algorithm;

import java.util.*;

/**
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 9:59
 */
public class TopKFrequent {
    public static List<String> topKFrequent(String[] words, int k) {


        Map<String, Integer>  counter = new HashMap<>();

        //O(n)
        for (String w :
                words) {
            counter.put(w, counter.getOrDefault(w,0) + 1);
        }


        //O(n)
        Comparator<Map.Entry<String, Integer>> comparator = (w1,w2)->(w1.getValue().equals(w2.getValue()) ? (w1.getKey().compareTo(w2.getKey())): (-1)*(w1.getValue().compareTo(w2.getValue())));
        Set<Map.Entry<String,Integer>> wordsEntrySet = counter.entrySet();
        PriorityQueue<Map.Entry<String,Integer>> heap = new PriorityQueue<>(comparator);
        for (Map.Entry<String, Integer> entry :
                wordsEntrySet) {
            heap.add(entry);
        }

        List<String> results = new ArrayList<>(k);
        int count = k;
        while (!heap.isEmpty() && count>0)
        {
            results.add(heap.poll().getKey());
            count--;
        }

        return results;

    }


    private static class WordWithFrequency implements Comparable<WordWithFrequency> {

        private int frequency;
        private final String word;

        public WordWithFrequency(String word) {
            this.word = word;
            this.frequency = 0;
        }

        public void addOne() {
            this.frequency = this.frequency + 1;
        }

        public String getWord(){
            return this.word;
        }

        public int compareTo(WordWithFrequency o){
            if(o.frequency == this.frequency){
                return this.word.compareTo(o.word);
            }

            return o.frequency - this.frequency;
        }
    }

    public static List<String> topKFrequent2(String[] words, int k) {

        Map<String, WordWithFrequency> mapWordWithFrequency = new HashMap<>();

        for(String word : words){
            WordWithFrequency wordWithFrequency = mapWordWithFrequency.get(word);
            if(wordWithFrequency == null){
                wordWithFrequency = new WordWithFrequency(word);
                mapWordWithFrequency.put(word, wordWithFrequency);
            }
            wordWithFrequency.addOne();
        }

        Queue<WordWithFrequency> q = new PriorityQueue<WordWithFrequency>(mapWordWithFrequency.size());
        for(WordWithFrequency wordWithFrequency : mapWordWithFrequency.values()) {
            q.add(wordWithFrequency);
        }

        List<String> output = new ArrayList<>(k);
        for(int i = 0; i < k; i++){
            output.add(q.poll().getWord());
        }

        return output;

    }

    public static void main(String[] args) {
        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequent(words1,1));

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int  k2 = 4;

        System.out.println(topKFrequent(words2,k2));

    }
}
