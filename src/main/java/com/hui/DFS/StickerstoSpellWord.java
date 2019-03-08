package com.hui.DFS;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/13 11:24
 *
 * 691. Stickers to Spell Word
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
 *
 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
 *
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input:
 *
 * ["with", "example", "science"], "thehat"
 * Output:
 *
 * 3
 * Explanation:
 *
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 * Example 2:
 *
 * Input:
 *
 * ["notice", "possible"], "basicbasic"
 * Output:
 *
 * -1
 * Explanation:
 *
 * We can't form the target "basicbasic" from cutting letters from the given stickers.
 * Note:
 *
 * stickers has length in the range [1, 50].
 * stickers consists of lowercase English words (without apostrophes).
 * target has length in the range [1, 15], and consists of lowercase English letters.
 * In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
 * The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class StickerstoSpellWord {

    public int minStickers(String[] stickers, String target) {
        int N = stickers.length;

        int[][] stickerCounters = new int[N][26];
        for (int i = 0; i < N; i++) {
            stickerCounters[i] = getCounter(stickers[i]);
        }

        Map<String,Integer> cache = new HashMap<>();  //组成string 所需要的最少sticker 数量
        cache.put("",0);
        return dfs(target, stickerCounters, cache, false);

    }

    /**
     * find the min stickers to combine target
     *
     * @param target
     * @param stickerCounter
     * @param cache
     * @param isOrder  whether target is orderd
     * @return
     */
    private int dfs(String target, int[][] stickerCounter, Map<String,Integer> cache, boolean isOrder)
    {
        if(cache.containsKey(target))return cache.get(target);
        int ans = Integer.MAX_VALUE;
        int[] tarCounter = getCounter(target);
        int stickerCount = stickerCounter.length;

        // 遍历所有的sticker
        for (int i = 0; i < stickerCount ; i++) {
            int len = target.length();

            int start = target.charAt(0) - 'a';
            // optimization
            if(stickerCounter[i][start] == 0)continue;
            StringBuilder sb = new StringBuilder();
            // apply a sticker
            start = isOrder ? start:0;
            for (int j = start; j < tarCounter.length && len > 0; j++) {
                if(tarCounter[j] > 0)
                {
                    len -= tarCounter[j]; // optimization to target's length
                    // after minus a sticker, remain chars
                    for (int k = 0; k < Math.max(0,tarCounter[j] - stickerCounter[i][j]); k++) {
                        sb.append((char)(j +'a'));
                    }
                }
            }

            String remain = sb.toString();
            int r = remain.isEmpty() ? 0: dfs(remain, stickerCounter, cache, true);
            if(r != -1)
            {
                ans = Math.min(ans, r +1);
            }

        }

        ans = ans == Integer.MAX_VALUE ? -1: ans;
        cache.put(target, ans);
        return ans;

    }

    private int[] getCounter(String s)
    {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            counter[idx]++;
        }
        return counter;
    }


    public static void main(String[] args) {

        StickerstoSpellWord spellWord = new StickerstoSpellWord();
//        System.out.println(spellWord.minStickers(new String[]{"with", "example", "science"}, "thehat"));
//        System.out.println(spellWord.minStickers(new String[]{"notice", "possible"}, "basicbasic"));
        System.out.println(spellWord.minStickers(new String[]{"these","guess","about","garden","him"}, "atomher"));
    }
}
