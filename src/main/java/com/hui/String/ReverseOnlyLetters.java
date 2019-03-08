package com.hui.String;

/**
 *
 *
 * 917. Reverse Only Letters
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 *
 *
 *
 * Example 1:
 *
 * Input: "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 *
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 *
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 *
 *
 * Note:
 *
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S doesn't contain \ or "
 *
 * @author: shenhaizhilong
 * @date: 2018/10/17 15:16
 */
public class ReverseOnlyLetters {


    public String reverseOnlyLetters(String S) {
        if(S == null || S.length() < 2)return S;
        char[] vals = S.toCharArray();
        int i = 0; // left pointer
        int j = S.length() -1; // right pointer
        while (i < j)
        {
            while ( i < j && !Character.isLetter(vals[i]))i++; // from left side find the element which is letter
            while (j > i && !Character.isLetter(vals[j]))j--;  // from right side find the element which is letter
            // swap left and right element.
            char t = vals[i];
            vals[i] = vals[j];
            vals[j] = t;
            i++;
            j--;

        }

        return String.valueOf(vals);
    }


    public static void main(String[] args) {

        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        System.out.println(reverseOnlyLetters.reverseOnlyLetters("a-bC-dEf-ghIj").equals("j-Ih-gfE-dCba"));
        System.out.println(reverseOnlyLetters.reverseOnlyLetters("ab-cd").equals("dc-ba"));
        System.out.println(reverseOnlyLetters.reverseOnlyLetters("Test1ng-Leet=code-Q!").equals("Qedo1ct-eeLg=ntse-T!"));
    }
}
