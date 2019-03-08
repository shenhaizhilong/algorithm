package com.hui.DesignProblems;

import com.hui.Encoding.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 535. Encode and Decode TinyURL
 * DescriptionHintsSubmissionsDiscussSolution
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/10/8 18:15
 */
public class EncodeandDecodeTinyURL {
    private static final String TINY_Host = "https://gc.com/";
    private String host;
    private static  final long defaultValue = 100000000;
    private long key;
    Map<Long,String> cache;

    public EncodeandDecodeTinyURL()
    {
        key = defaultValue;
        cache = new HashMap<>();
    }


    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        cache.put(key, longUrl );
        String tail = Base64.CompressNumber(key);
        key++;
        return TINY_Host + tail;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {

        String tail = shortUrl.substring(shortUrl.lastIndexOf('/') +1);
        long val = Base64.UnCompressNumber(tail);
        return cache.getOrDefault(val,"");
    }

    public static void main(String[] args) {

        EncodeandDecodeTinyURL encodeandDecodeTinyURL = new EncodeandDecodeTinyURL();
        String encoded = encodeandDecodeTinyURL.encode("https://leetcode.com/problems/design-tinyurl");
        String url = encodeandDecodeTinyURL.decode(encoded);
        System.out.println(encoded);
        System.out.println(url);

    }
}
