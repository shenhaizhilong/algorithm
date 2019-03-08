package com.hui.TrieAlg;



import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 9:13
 *
 *
 * 648. Replace Words
 * DescriptionHintsSubmissionsDiscussSolution
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 * Note:
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 * 
 */
public class ReplaceWords {

    TrieNode root = new TrieNode();
    public String replaceWords(List<String> dict, String sentence) {

        addAllWords(dict);
        int N = sentence.length();
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < N)
        {
            int nextIdx = sentence.indexOf(" ", idx);
            if(nextIdx < 0)nextIdx = N;
            String nextWord = sentence.substring(idx, nextIdx);
            sb.append(match(nextWord));
            if(idx + nextWord.length() < N)
            {
                sb.append(" ");
            }
            idx = nextIdx +1;
        }
        return sb.toString();

    }


    private static class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode()
        {
            children = new TrieNode[26];
            word = null;
        }
    }

    private void addWord(String word)
    {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null)
            {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.word = word;
    }

    private String match(String word)
    {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                break;
            }
            curr = curr.children[idx];
            if (curr.word != null) {
                return curr.word;
            }
        }
        return word;

    }

    private void addAllWords(List<String> words)
    {
        for(String word: words)
        {
            addWord(word);
        }
    }


    public static void main(String[] args) {


        ReplaceWords replaceWords = new ReplaceWords();
        List<String> list = Arrays.asList(new String[]{"cat","bat","rat"});
        System.out.println(replaceWords.replaceWords(list, "the cattle was rattled by the battery"));
    }
}
