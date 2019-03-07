package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 14:45
 *
 *
 *748. Shortest Completing Word
 * DescriptionHintsSubmissionsDiscussSolution
 * Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate
 *
 * Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
 *
 * It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
 *
 * The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.
 *
 * Example 1:
 * Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * Output: "steps"
 * Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
 * Note that the answer is not "step", because the letter "s" must occur in the word twice.
 * Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
 * Example 2:
 * Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * Output: "pest"
 * Explanation: There are 3 smallest length words that contains the letters "s".
 * We return the one that occurred first.
 * Note:
 * licensePlate will be a string with length in range [1, 7].
 * licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
 * words will have a length in the range [10, 1000].
 * Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 *
 */
public class ShortestCompletingWord {

    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public String shortestCompletingWord(String licensePlate, String[] words) {
        long plateHash = hash(licensePlate.toLowerCase().toCharArray());
        int minLen = Integer.MAX_VALUE;
        String ans = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.length() < minLen &&  hash(word.toCharArray()) % plateHash == 0)
            {
                minLen = word.length();
                ans = word;
            }
        }
        return ans;
    }

    private long hash(char[] word)
    {

        long hash = 1L;
        for (int i = 0; i < word.length; i++) {
            int idx = word[i] - 'a';
            if( idx >=0 && idx <= 25)
            {
                hash *= primes[idx];
            }
        }
        return hash;
    }

    public static void main(String[] args) {

        ShortestCompletingWord completingWord = new ShortestCompletingWord();
        System.out.println(completingWord.shortestCompletingWord("1s3 PSt",new String[] {"step", "steps", "stripe", "stepple"}));
        System.out.println(completingWord.shortestCompletingWord("1s3 456",new String[] {"looks", "pest", "stew", "show"}));
    }
}
