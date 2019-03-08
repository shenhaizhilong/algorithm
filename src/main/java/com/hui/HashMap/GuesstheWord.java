package com.hui.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/29 10:00
 *
 *
 *
 * 843. Guess the Word
 * DescriptionHintsSubmissionsDiscussSolution
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 *
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 *
 * Explanation:
 *
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 *
 */
public class GuesstheWord {


    /**
     *     match                 0             1                             2               3            4         5          6
     *
     * probability   (25/26)^6 = 0.790      (25/26)^5 * (6/26)= 0.189   (25/26)^4*(1/26)^2*15
     * @param wordlist
     */
    public void findSecretWord(String[] wordlist) {

        int x = 0;
        for (int i = 0; i <= 10 && x < 6; i++) {

            Map<String,Integer> counter = new HashMap<>();
            for (int j = 0; j < wordlist.length; j++) {
                for (int k = 0; k < wordlist.length; k++) {
                    if(match(wordlist[j], wordlist[k]) == 0)
                    {
                        int count =  counter.getOrDefault(wordlist[j], 0) +1;
                        counter.put(wordlist[j],count);
                    }
                }
            }

            Pair minPair = new Pair("",1000);
            for(String word: wordlist)
            {
                if(counter.getOrDefault(word,0) < minPair.value)
                {
                    minPair = new Pair(word, counter.getOrDefault(word,0));
                }
            }

            List<String> list = new ArrayList<>();
            x = guess(minPair.key);
            if(x == 6)break;
            for(String word: wordlist)
            {
                if(match(minPair.key, word) == x)
                {
                    list.add(word);
                }
            }
            wordlist = list.toArray(new String[0]);



        }
    }

    private int match(String s1, String s2)
    {
        int ans = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(i))ans++;
        }
        return ans;
    }

    private class Pair{

        String key;
        int value;
        public Pair(String key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private int guess(String word)
    {
        return match(word, "aafedc");
    }




    public static void main(String[] args) {
        String[] wordList = {"aazyxw","aayxwv","aaxwvu","aawvut","aavuts","aautsr","aatsrq","aasrqp","aarqpo","aaqpon","aaponm","aaonml","aanmlk","aamlkj","aalkji","aakjih","aajihg","aaihgf","aahgfe","aagfed","aafedc","ccwwww","ccssss","ccoooo","cckkkk","ccgggg","cccccc","ccyyyy","ccuuuu","ccqqqq","ccmmmm","ddwwww","ddssss","ddoooo","ddkkkk","ddgggg","ddcccc","ddyyyy","dduuuu","ddqqqq","ddmmmm","eewwww","eessss","eeoooo","eekkkk","eegggg","eecccc","eeyyyy","eeuuuu","eeqqqq","eemmmm","ffwwww","ffssss","ffoooo","ffkkkk","ffgggg","ffcccc","ffyyyy","ffuuuu","ffqqqq","ffmmmm","ggwwww","ggssss","ggoooo","ggkkkk","gggggg","ggcccc","ggyyyy","gguuuu","ggqqqq","ggmmmm","hhwwww","hhssss","hhoooo","hhkkkk","hhgggg","hhcccc","hhyyyy","hhuuuu","hhqqqq","hhmmmm","iiwwww","iissss","iioooo","iikkkk","iigggg","iicccc","iiyyyy","iiuuuu","iiqqqq","iimmmm","jjwwww","jjssss","jjoooo","jjkkkk","jjgggg","jjcccc","jjyyyy","jjuuuu","jjqqqq"};
        GuesstheWord guesstheWord = new GuesstheWord();
        guesstheWord.findSecretWord(wordList);

    }

}
