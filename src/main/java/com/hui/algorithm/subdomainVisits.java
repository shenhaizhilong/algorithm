package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/26 11:16
 */
public class subdomainVisits {

    /**
     * https://leetcode.com/problems/subdomain-visit-count/description/
     * 811. Subdomain Visit Count
     * DescriptionHintsSubmissionsDiscussSolution
     * A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
     *
     * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
     *
     * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
     *
     * Example 1:
     * Input:
     * ["9001 discuss.leetcode.com"]
     * Output:
     * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
     * Explanation:
     * We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
     *
     * Example 2:
     * Input:
     * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
     * Output:
     * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
     * Explanation:
     * We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
     *
     * Notes:
     *
     * The length of cpdomains will not exceed 100.
     * The length of each domain name will not exceed 100.
     * Each address will have either 1 or 2 "." characters.
     * The input count in any count-paired domain will not exceed 10000.
     * The answer output can be returned in any order.
     *
     * @param cpdomains
     * @return
     */
    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>(cpdomains.length);

        HashMap<String,Integer> counter = new HashMap<>(cpdomains.length);
        for(String cpdomain : cpdomains)
        {
            String[] pair = cpdomain.split(" ");
            int count = Integer.valueOf(pair[0]);
            String[] parts = pair[1].split("\\.");
            if(parts.length == 3)
            {
                String top = parts[2];
                String parentDomain = String.join(".",parts[1], top );
                String subDomain = pair[1];
                counter.put(top, counter.getOrDefault(top,0) + count);
                counter.put(parentDomain, counter.getOrDefault(parentDomain,0) + count);
                counter.put(subDomain, counter.getOrDefault(subDomain,0) + count);


            }else if(parts.length == 2)
            {
                String top = parts[1];
                String parentDomain = pair[1];
                counter.put(top, counter.getOrDefault(top,0) + count);
                counter.put(parentDomain, counter.getOrDefault(parentDomain,0) + count);
            }

        }

        for (String domain :
                counter.keySet()) {
            String temp = String.join(" ", counter.get(domain).toString(), domain);
            res.add(temp);
        }

        return res;
    }


    public static List<String> subdomainVisits2(String[] cpdomains) {
       HashMap<String,Integer> counter = new HashMap<>(cpdomains.length);
       for(String cp: cpdomains)
       {
           int i = cp.indexOf(" ");
           int count = Integer.valueOf(cp.substring(0,i));
           String domain = cp.substring(i +1);
           for (int j = 0; j <domain.length(); j++) {
               if(domain.charAt(j) == '.')
               {
                   String t = domain.substring(j +1);
                   counter.put(t, counter.getOrDefault(t,0) + count);
               }
           }
           counter.put(domain, counter.getOrDefault(domain,0 ) + count);
       }
       List<String> res = new ArrayList<>(counter.size());
       for(String key: counter.keySet())
       {
           res.add( counter.get(key) + " " + key);
       }
       return res;
    }

    public static void main(String[] args) {
       // String[] cpdomains = {"9001 discuss.leetcode.com"};
        String[] cpdomains2 = {"654 yaw.lmm.ca", "1225 lmm.ca",};
       // System.out.println(subdomainVisits2(cpdomains));
        System.out.println(subdomainVisits2(cpdomains2));
    }

}
