package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 22:56
 */
public class ValidateIPAddress {

    /**
     *468. Validate IP Address
     * DescriptionHintsSubmissionsDiscussSolution
     * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
     *
     * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
     *
     * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
     *
     * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
     *
     * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
     *
     * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
     *
     * Note: You may assume there is no extra space or special characters in the input string.
     *
     * Example 1:
     * Input: "172.16.254.1"
     *
     * Output: "IPv4"
     *
     * Explanation: This is a valid IPv4 address, return "IPv4".
     * Example 2:
     * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
     *
     * Output: "IPv6"
     *
     * Explanation: This is a valid IPv6 address, return "IPv6".
     * Example 3:
     * Input: "256.256.256.256"
     *
     * Output: "Neither"
     *
     * Explanation: This is neither a IPv4 address nor a IPv6 address.
     *
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        String ipv4Regex = "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
        String ipv6Regex = "(([0-9a-fA-F]{1,4}):){7}([0-9-a-fA-F]{1,4})";
        if(IP.matches(ipv4Regex))return "IPv4";
        else if(IP.matches(ipv6Regex))return "IPv6";
        else return "Neither";
    }


    /**
     * 别人的解法效率很高
     * @param IP
     * @return
     */
    public String validIPAddress2(String IP) {
        if (IP.indexOf('.') > 0) {
            if (validIPV4(IP))
                return "IPv4";
        }
        else if (IP.indexOf(':') > 0) {
            if (validIPV6(IP))
                return "IPv6";
        }

        return "Neither";
    }

    private boolean validIPV4(String IP) {
        String[] tokens = IP.split("\\.");
        if (IP.charAt(0) == '.' || IP.charAt(IP.length()-1) == '.') return false;
        if (tokens.length != 4) return false;
        for (String t: tokens) {
            if (t.length() > 3 || t.length() == 0) return false;
            int digit = 0;
            for (int i=0;i<t.length();i++) {
                char c = t.charAt(i);
                if (digit == 0 && c == '0' && i != t.length()-1) return false;
                if (c < '0' || c > '9') return false;
                digit = digit * 10 + (c-'0');
            }
            if (digit > 255) return false;
        }
        return true;
    }

    private boolean validIPV6(String IP) {
        String[] tokens = IP.split(":");
        if (IP.charAt(0) == ':' || IP.charAt(IP.length()-1) == ':') return false;
        if (tokens.length != 8) return false;
        for (String t : tokens) {
            if (t.length() > 4 || t.length() == 0) return false;
            for (int i=0;i<t.length();i++) {
                char c = t.charAt(i);
                if (! ((c >= '0' && c <= '9')
                        || (c >= 'A' && c <= 'F')
                        || (c >= 'a' && c <= 'f')))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {


    }
}
