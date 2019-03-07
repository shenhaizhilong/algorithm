package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 11:18
 *
 *
 *  same with 809. Expressive Words
 *
 *
 * aabbcccdddd => RLE.key = abcd
 * RLE.count = [2,2,3,4]
 *
 */
public class ExpressiveWordsII {
    public int expressiveWords(String S, String[] words) {
        int Len = S.length();
        char[] enChar = new char[Len];
        int[] enCnt = new int[Len];
        int[] idx = {0};
        RunLenEnc(S, enChar, enCnt, idx);
        int ans = 0;
        for(String word: words)
        {
            if(word.length() > Len)continue;
            if(compare(word, enChar, enCnt, idx[0]))ans++;
        }
        return ans;


    }


    /**
     * Run length encoding
     * @param word
     * @param enChar
     * @param enCnt
     * @param idx
     */
    private void RunLenEnc(String word, char[] enChar, int[] enCnt, int[] idx)
    {
        int prev = -1;
        int N = word.length();
        for (int i = 0; i < word.length(); i++) {
            if(i == N -1 || word.charAt(i) != word.charAt(i +1))
            {
                enChar[idx[0]] = word.charAt(i);
                enCnt[idx[0]] = i - prev;
                prev = i;
                idx[0]++;
            }
        }
    }

    private boolean compare(String word, char[] enChar, int[] enCnt, int endIdx)
    {

        int idx = 0;
        int N = word.length();
        int prev = -1;
        for (int j = 0; j < N ; j++) {
            if(j == N -1 || word.charAt(j) != word.charAt(j +1))
            {
                if(word.charAt(j) != enChar[idx])return false;
                int count = j - prev;
                prev = j;
                if(enCnt[idx] < 3 && enCnt[idx] != count)return false;
                if(enCnt[idx] >= 3 && count > enCnt[idx])return false;
                idx++;
                if(idx > endIdx)return false;
            }
        }
        return idx == endIdx;
    }

    public static void main(String[] args) {


        ExpressiveWordsII expressiveWords = new ExpressiveWordsII();
             System.out.println(expressiveWords.expressiveWords("heeellooo", new String[]{"hello","hi","helo"}));

        String s =   "ggkyyyyffffbbhddddrxxsiixccqqqqkmmmiiiiiivvvyyuuujccuuuhhhhwssssnnttoyuuuussggttttfeeeebbbbeedddddqq";
        String[] words =   {"ggkyyfbbhdrxxsiixccqkmmiiivvvyyujccuuuhhwsnnttoyuuussggtttfeeebbbeedddqq",
                "ggkyyfffbbhddrxxsiixccqqkmmmiiiivvvyyuujccuuuhhhwsnnttoyuuussggtttfebeedddddqq",
                "ggkyyyyffbbhdrxxsiixccqkmmiiiivyyujccuhhwsssnnttoyuuussggtfebeeddddqq",
                "ggkyyfffbbhdddrxxsiixccqkmmmiiiiivyyujccuuhhwsssnnttoyuuussggtfebbeeddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqkmmmiiivvvyyuujccuhhwssnnttoyuuussggtfeeebbbeedddddqq",
                "ggkyyyyfffbbhddrxxsiixccqqkmiiiiivyyuuujccuuuhwsnnttoyuussggtfeebbbeedddddqq",
                "ggkyyffbbhdddrxxsiixccqqkmiiiiivvyyuujccuhwsnnttoyussggtttfeeebbbeedddqq",
                "ggkyyyfbbhddrxxsiixccqqqkmiiivvvyyuuujccuhhwsnnttoyuussggttfebeeddddqq",
                "ggkyyyfbbhdrxxsiixccqqqkmmiiiivvyyujccuuhwsnnttoyussggtfeebbeedddqq",
                "ggkyyyfbbhdddrxxsiixccqkmmmiiiivyyuujccuhhhwsssnnttoyuussggttfeeebeedddqq",
                "ggkyyyfbbhdrxxsiixccqkmmiiiiivyyujccuhhhwssnnttoyussggtttfeebeedddqq",
                "ggkyyyfffbbhddrxxsiixccqqqkmmmiiivvyyuuujccuuhhhwssnnttoyuussggttfeebeedddddqq",
                "ggkyyfffbbhdrxxsiixccqqkmmiiiiivvyyuuujccuuuhhwsnnttoyuussggttfeebbeedddddqq",
                "ggkyyfffbbhdddrxxsiixccqkmiiiivyyuuujccuuhwssnnttoyuussggtfebeedddddqq",
                "ggkyyyyfffbbhddrxxsiixccqqkmmiiivyyuujccuuuhhwssnnttoyussggtfebbbeedddddqq",
                "ggkyyyyffbbhdrxxsiixccqkmmiiiivyyujccuhwsssnnttoyussggtttfebeeddddqq",
                "ggkyyyfbbhddrxxsiixccqqkmiiiiivvyyuuujccuhhhwsssnnttoyuuussggttfeeebbbeedddqq",
                "ggkyyyyffbbhdddrxxsiixccqkmmmiiiivvvyyuuujccuuhhhwssnnttoyussggtttfeeebbbeeddddqq",
                "ggkyyyfbbhdddrxxsiixccqqqkmiiivvvyyuujccuuhhwsnnttoyuuussggtfeebbbeedddqq",
                "ggkyyyffbbhdddrxxsiixccqqqkmiiiivvyyuuujccuuhwssnnttoyuussggttfeebbbeedddqq",
                "ggkyyyyfbbhdddrxxsiixccqkmmmiiiiivvvyyujccuuhhwsnnttoyuuussggttfebbbeedddddqq",
                "ggkyyyfbbhdddrxxsiixccqqqkmmiiiivvyyuujccuuhhwssnnttoyuuussggttfebeeddddqq",
                "ggkyyyyfbbhddrxxsiixccqkmmiiivvvyyuujccuuhhhwsnnttoyussggtfeeebbbeedddqq",
                "ggkyyyfffbbhdrxxsiixccqqkmiiiiivvyyujccuuhwsnnttoyussggtttfeebbeedddddqq",
                "ggkyyyyfffbbhddrxxsiixccqqqkmiiiivyyuuujccuuuhhwsssnnttoyuuussggttfebbeedddqq",
                "ggkyyffbbhddrxxsiixccqkmiiivvyyujccuuhwssnnttoyuuussggtttfeebbeedddddqq",
                "ggkyyyfffbbhdddrxxsiixccqqkmmmiiiiivvyyuuujccuuuhhwssnnttoyussggtttfeeebbeeddddqq",
                "ggkyyyyfbbhddrxxsiixccqkmmmiiivvvyyujccuuhhhwssnnttoyuuussggtfeeebbeedddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqkmmmiiiivyyuuujccuhhhwsssnnttoyuussggtttfeeebbeedddddqq",
                "ggkyyyyfbbhdrxxsiixccqqkmmiiiiivyyuujccuuuhhwsnnttoyuussggttfebbeedddqq",
                "ggkyyyfbbhdrxxsiixccqkmiiiivvyyujccuhhhwsnnttoyussggttfeeebbeedddddqq",
                "ggkyyyfffbbhddrxxsiixccqqqkmiiivyyuujccuuuhhwssnnttoyuuussggtfeebeedddqq",
                "ggkyyffbbhdrxxsiixccqqkmmiiiiivyyuujccuhhhwsnnttoyuuussggtfebeedddddqq",
                "ggkyyyfffbbhddrxxsiixccqkmiiiiivvvyyuujccuuuhhwsnnttoyuuussggttfeeebbeeddddqq",
                "ggkyyyfffbbhdddrxxsiixccqqkmmmiiiivvyyuujccuuhwssnnttoyuussggtfebeedddqq",
                "ggkyyfbbhdddrxxsiixccqqkmiiiiivyyujccuuuhhwsssnnttoyuuussggtttfeeebeeddddqq",
                "ggkyyyyffbbhdddrxxsiixccqqkmmiiiiivvyyuuujccuuhhhwssnnttoyuussggtfeebbbeedddddqq",
                "ggkyyffbbhdrxxsiixccqkmmiiiivyyuujccuuhhhwssnnttoyuussggtfeebeeddddqq",
                "ggkyyyffbbhddrxxsiixccqkmmiiiiivvyyujccuuuhhwssnnttoyuussggtttfeeebbbeeddddqq",
                "ggkyyyfffbbhdrxxsiixccqqqkmiiiivvvyyuujccuhhhwsssnnttoyuuussggtttfebbeeddddqq",
                "ggkyyffbbhdrxxsiixccqqkmiiiiivyyuuujccuuuhwsnnttoyuuussggttfeeebbeeddddqq",
                "ggkyyyfbbhdrxxsiixccqqkmiiivyyujccuuuhhhwsnnttoyussggtfebbbeeddddqq",
                "ggkyyfffbbhddrxxsiixccqqkmmiiivyyuujccuuuhhwsnnttoyuussggtttfeeebbeedddddqq",
                "ggkyyyyfbbhdrxxsiixccqqkmmmiiiiivvvyyujccuuuhhhwssnnttoyuussggtttfeebbeeddddqq",
                "ggkyyyffbbhdrxxsiixccqqqkmiiiivvvyyuuujccuuhhhwsssnnttoyussggtttfeebeeddddqq",
                "ggkyyyyfbbhddrxxsiixccqkmiiiiivvvyyuuujccuuuhhwssnnttoyuussggttfeeebeeddddqq",
                "ggkyyyyffbbhdrxxsiixccqqkmmiiivvvyyuujccuuhhhwsnnttoyuussggttfeeebbbeedddqq",
                "ggkyyfffbbhddrxxsiixccqkmiiiiivvyyuuujccuuuhwsssnnttoyuuussggtttfebeedddddqq",
                "ggkyyyfbbhdrxxsiixccqkmmmiiiiivvyyuuujccuuuhwssnnttoyuussggttfeeebbeedddddqq",
                "ggkyyyffbbhdrxxsiixccqkmmiiiivyyujccuuuhhwssnnttoyussggtttfebbbeeddddqq",
                "ggkyyyffbbhdrxxsiixccqqkmmmiiiivvvyyuujccuhwssnnttoyussggtfebeeddddqq",
                "ggkyyyffbbhdddrxxsiixccqqkmiiivvyyuujccuuhhhwssnnttoyussggtfeebbeeddddqq",
                "ggkyyyffbbhdrxxsiixccqqqkmmiiiivvvyyujccuhhhwsnnttoyuuussggttfebbbeedddqq",
                "ggkyyyfbbhddrxxsiixccqqkmiiiiivvyyuujccuuhhwsssnnttoyuuussggtfebbbeeddddqq",
                "ggkyyffbbhddrxxsiixccqqkmmiiiiivvyyujccuhhhwsssnnttoyuuussggtttfeebbbeedddddqq",
                "ggkyyyfffbbhdrxxsiixccqqkmiiivvyyuujccuhhwsssnnttoyuussggttfeeebbeedddqq",
                "ggkyyyfffbbhdrxxsiixccqkmmmiiiivvyyuujccuuuhwssnnttoyussggtfebbbeeddddqq",
                "ggkyyyyffbbhdrxxsiixccqqkmiiiivvvyyuuujccuuhwsnnttoyuussggttfebbbeedddddqq",
                "ggkyyyyffbbhddrxxsiixccqqkmmmiiiiivvyyuuujccuhwsssnnttoyuussggtfeeebbeeddddqq",
                "ggkyyyyfbbhdddrxxsiixccqqkmmiiivyyujccuuuhhwsssnnttoyussggtfebbeedddqq",
                "ggkyyyffbbhdrxxsiixccqkmiiiiivvyyuujccuhhwssnnttoyussggtfebeedddqq",
                "ggkyyyffbbhdrxxsiixccqkmmiiivyyujccuuhhhwsssnnttoyuuussggtttfeeebbbeeddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiiivvyyujccuuhhhwssnnttoyuuussggtttfebbbeedddqq",
                "ggkyyyfbbhdddrxxsiixccqqkmmiiivvvyyujccuuuhhhwssnnttoyuussggtttfebbbeeddddqq",
                "ggkyyyfbbhdrxxsiixccqqqkmmmiiivvyyuuujccuuhhwsssnnttoyuuussggtttfebeedddqq",
                "ggkyyyyfbbhddrxxsiixccqkmmiiiiivvvyyuuujccuuhhwssnnttoyuuussggtfeeebeedddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqkmiiiivvyyujccuuhhwsssnnttoyussggtfebbbeedddqq",
                "ggkyyyffbbhdrxxsiixccqqkmmmiiivvyyuuujccuhhhwsssnnttoyussggtttfebbbeeddddqq",
                "ggkyyyfffbbhdddrxxsiixccqqkmmmiiiiivvvyyuuujccuuuhhwsnnttoyuussggttfeebeedddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiivvyyuuujccuuuhhhwsssnnttoyussggtfeebbbeedddddqq",
                "ggkyyyfbbhdddrxxsiixccqkmiiiiivyyuuujccuhhhwsnnttoyuussggtttfeebeedddqq",
                "ggkyyyfbbhdrxxsiixccqqqkmmmiiiiivyyujccuhhwsnnttoyuussggttfeebbeedddqq",
                "ggkyyyyffbbhdrxxsiixccqqqkmmiiivvyyujccuhhhwssnnttoyussggttfeeebbbeedddddqq",
                "ggkyyyfffbbhdrxxsiixccqqqkmiiiiivyyujccuhhwsssnnttoyuuussggtfeebbbeeddddqq",
                "ggkyyyffbbhdrxxsiixccqqkmiiiivvyyuuujccuhhhwssnnttoyussggttfeeebbbeedddqq",
                "ggkyyyyffbbhdrxxsiixccqkmiiiiivvyyuujccuhhwssnnttoyuussggtfeeebeedddqq"
                ,"ggkyyyfbbhdddrxxsiixccqkmmmiiivvyyujccuuhhhwsssnnttoyuussggtttfeebeedddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiiivvvyyuuujccuuhwssnnttoyuuussggtfeeebbeedddddqq",
                "ggkyyfbbhdrxxsiixccqkmiiiivvyyujccuuuhhhwssnnttoyuussggttfebbeedddqq",
                "ggkyyyfbbhddrxxsiixccqqqkmmiiiivyyuujccuuhhwsnnttoyuussggttfebbeedddddqq","" +
                "ggkyyyyfbbhdddrxxsiixccqkmmiiivyyujccuhwsssnnttoyussggttfeebbbeedddqq",
                "ggkyyyyfbbhdrxxsiixccqkmiiiiivvvyyuuujccuuuhhwsnnttoyuuussggtfeebeeddddqq",
                "ggkyyffbbhddrxxsiixccqqkmmiiiivyyuujccuuhhwsssnnttoyuussggtttfeeebbeedddqq",
                "ggkyyyfffbbhddrxxsiixccqqqkmmiiivvvyyuujccuhhwsnnttoyuussggttfebbbeeddddqq",
                "ggkyyfffbbhdrxxsiixccqkmmmiiivvvyyuuujccuuuhwsssnnttoyussggttfeeebeedddddqq",
                "ggkyyyyffbbhdrxxsiixccqqqkmmiiiiivvyyuuujccuhhwsnnttoyuuussggtttfeeebbeedddqq",
                "ggkyyyyfffbbhdrxxsiixccqkmmiiivvvyyuujccuhhwsssnnttoyuuussggttfeebbeedddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqqkmiiivvyyuuujccuuhhhwssnnttoyuuussggttfebbbeedddddqq",
                "ggkyyffbbhdrxxsiixccqqqkmmmiiiiivvvyyujccuuuhwsssnnttoyuussggtttfeeebbbeeddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqkmmmiiiiivyyujccuuuhwsnnttoyuuussggtttfeeebeedddddqq",
                "ggkyyfffbbhdrxxsiixccqkmmmiiiiivvyyuujccuuuhwsssnnttoyussggtfebbeedddddqq",
                "ggkyyyyfbbhddrxxsiixccqqqkmiiivyyuujccuuhhhwssnnttoyussggttfeeebbbeedddddqq",
                "ggkyyffbbhddrxxsiixccqkmmiiivvvyyuuujccuuhhwsssnnttoyuuussggtfeeebbeedddddqq",
                "ggkyyffbbhdddrxxsiixccqkmiiiivvvyyuujccuuhhhwsssnnttoyuuussggttfebbeedddqq",
                "ggkyyyyffbbhdrxxsiixccqkmmmiiiiivyyuujccuuuhwsnnttoyuuussggtttfebeeddddqq",
                "ggkyyffbbhddrxxsiixccqkmmmiiiivyyuuujccuuhhhwsssnnttoyuuussggtfeeebeedddqq",
                "ggkyyyyfbbhdrxxsiixccqkmmmiiivyyuujccuhwsnnttoyuuussggtttfeeebbeeddddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqkmiiivvyyuujccuhhhwsnnttoyuuussggttfeeebbeedddqq",
                "ggkyyyyfffbbhdddrxxsiixccqqkmmmiiivvyyuuujccuuuhwssnnttoyuussggtttfeebeedddqq",
                "ggkyyyyfffbbhdddrxxsiixccqkmiiiiivyyuujccuuuhhwssnnttoyussggtttfeeebeeddddqq"};

        System.out.println(expressiveWords.expressiveWords(s, words));
    }
}
