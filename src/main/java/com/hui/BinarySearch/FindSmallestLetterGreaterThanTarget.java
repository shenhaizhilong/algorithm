package com.hui.BinarySearch;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 21:00
 */
public class FindSmallestLetterGreaterThanTarget {


    /**
     *744. Find Smallest Letter Greater Than Target
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
     *
     * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
     *
     * Examples:
     * Input:
     * letters = ["c", "f", "j"]
     * target = "a"
     * Output: "c"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "c"
     * Output: "f"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "d"
     * Output: "f"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "g"
     * Output: "j"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "j"
     * Output: "c"
     *
     * Input:
     * letters = ["c", "f", "j"]
     * target = "k"
     * Output: "c"
     * Note:
     * letters has a length in range [2, 10000].
     * letters consists of lowercase letters, and contains at least 2 unique letters.
     * target is a lowercase letter.
     *
     * @param letters
     * @param target
     * @return
     */
    public static char nextGreatestLetter(char[] letters, char target) {

        int end = letters.length -1;
        int start = 0;
        if(target >= letters[end])
        {
            return letters[0];
        }


        while (start <= end)
        {
            int middle = (start + end)>>>1;
            if(letters[middle] > target)
            {
                if( middle == 0 || letters[middle -1] <= target)return letters[middle];
                end = middle -1;
            }else {
                start = middle + 1;
            }
        }
        return letters[start];

    }

    public static void main(String[] args) {
        char[] chars = {'c','d'};
        System.out.println(nextGreatestLetter(chars, 'c'));
    }
}
