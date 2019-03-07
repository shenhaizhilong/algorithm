package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 17. Letter Combinations of a Phone Number
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * @author: shenhaizhilong
 * @date: 2018/9/10 8:30
 */
public class LetterCombinationsofaPhoneNumber {

    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if(digits == null || digits.isEmpty())
        {
            return res;
        }
        String[] maping = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        backTracking(res,maping,new StringBuilder(), digits, 0);
        return res;
    }

    private void backTracking(List<String> res, String[] maping,StringBuilder sb, String digits, int index)
    {
        if(index == digits.length())
        {
            res.add(sb.toString());
        }else {

                int id = digits.charAt(index) -'1';
                String t = maping[id];
                for (int j = 0; j < t.length() ; j++) {
                    sb.append(t.charAt(j));
                    backTracking(res,maping,sb,digits, index +1);
                    sb.deleteCharAt(sb.length() -1);
                }

        }
    }


    public static void main(String[] args) {


        LetterCombinationsofaPhoneNumber phoneNumber = new LetterCombinationsofaPhoneNumber();
        System.out.println(phoneNumber.letterCombinations("29"));
    }

}
