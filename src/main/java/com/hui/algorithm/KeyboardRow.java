package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/26 10:04
 */
public class KeyboardRow {
    private static Set<Character> firstRow;
    private static Set<Character> secondRow;
    private static Set<Character> thirdRow;
    static {
        firstRow = new HashSet<>(10);
        secondRow = new HashSet<>(9);
        thirdRow = new HashSet<>(7);
        Character[] f = {'Q','q','W','w','E','e','R','r','T','t','Y','y','U','u','I','i','O','o','P','p'};
        Character[] s = {'A','a','S','s','D','d','F','f','G','g','H','h','J','j','K','k','L','l'};
        Character[] t = {'Z','z','X','x','C','c','V','v','B','b','N','n','M','m'};
        firstRow.addAll(Arrays.asList(f));
        secondRow.addAll(Arrays.asList(s));
        thirdRow.addAll(Arrays.asList(t));

    }
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        for (String w :
                words) {

            boolean first = false;
            boolean second = false;
            boolean third = false;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if(firstRow.contains(c))
                {
                    first = true;
                }else if(secondRow.contains(c))
                {
                    second = true;
                }else {
                    third = true;
                }
            }

            if(first == true && second == false && third == false)
            {
                res.add(w);
            }else if(first == false && second == true && third == false)
            {
                res.add(w);
            }else if(first == false && second == false && third == true)
            {
                res.add(w);
            }
        }

        return res.toArray(new String[res.size()]);
    }



    private static byte[] hash = new byte[]{
            1,2,2,1,0,1,1,1,0,1,1,1,2,2,0,0,0,0,1,0,0,2,0,2,0,2,
            4,4,4,4,4,4,
            1,2,2,1,0,1,1,1,0,1,1,1,2,2,0,0,0,0,1,0,0,2,0,2,0,2
    };

    /**
     * 解法二，非常巧妙，hash
     * @param words
     * @return
     */
    public String[] findWords2(String[] words) {
        List<String> result = new ArrayList<>();
        int tmp;
        boolean flag;
        for(String word : words) {
            tmp = hash[word.charAt(0) - 'A'];
            flag = true;
            char[] chars = word.toCharArray();
            for(char c: chars) {
                if(tmp != hash[c - 'A']) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result.add(word);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) {

        KeyboardRow keyboardRow = new KeyboardRow();
//        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
//        System.out.println(Arrays.toString(keyboardRow.findWords(words)));
        String[]  words2 = new String[]{"a","b"};

        System.out.println(Arrays.toString(keyboardRow.findWords2(words2)));
    }

}
