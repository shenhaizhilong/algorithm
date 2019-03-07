package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/1 10:38
 *
 * 929. Unique Email Addresses
 * DescriptionHintsSubmissionsDiscussSolution
 * Every email consists of a local name and a domain name, separated by the @ sign.
 *
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 *
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 *
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)
 *
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 *
 * It is possible to use both of these rules at the same time.
 *
 * Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 *
 *
 *
 * Example 1:
 *
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 *
 *
 * Note:
 *
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * Each emails[i] contains exactly one '@' character
 */
public class UniqueEmailAddresses {

    public int numUniqueEmails(String[] emails) {

        if(emails == null || emails.length  <1)return 0;
        Set<String> set = new HashSet<>();
        for(String email: emails)
        {
            StringBuilder sb = new StringBuilder();
            boolean seenAt = false;
            boolean seenPlus = false;
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if(!seenAt && c == '.')continue;
                if(c == '+')
                {
                    seenPlus = true;
                    continue;
                }
                if(c == '@')
                {
                    seenAt = true;
                    continue;
                }
                if(seenPlus && !seenAt) continue;
                sb.append(c);
            }

            set.add(sb.toString());
        }

        return set.size();

    }


    public int numUniqueEmails2(String[] emails) {

        if(emails == null || emails.length  <1)return 0;
        Set<String> set = new HashSet<>();
        for(String email: emails)
        {
            StringBuilder sb = new StringBuilder();

            boolean seenPlus = false;
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if(c == '.')continue;
                if(c == '+')
                {
                    seenPlus = true;
                    continue;
                }
                if(c == '@')
                {
                   sb.append(email.substring(i));
                   set.add(sb.toString());
                   break;
                }
                if(seenPlus) continue;
                sb.append(c);
            }


        }

        return set.size();

    }

    public static void main(String[] args) {


        UniqueEmailAddresses uniqueEmailAddresses = new UniqueEmailAddresses();
        System.out.println(uniqueEmailAddresses.numUniqueEmails(new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
        System.out.println(uniqueEmailAddresses.numUniqueEmails2(new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
    }
}
