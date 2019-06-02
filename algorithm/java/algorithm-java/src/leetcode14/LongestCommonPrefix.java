package leetcode14;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author shengfei
 * Leetcode #14, Easy
 */
public class LongestCommonPrefix {

    @Test
    public void test() {
        Assert.assertTrue(longestCommonPrefix(new String[]{"flow", "flex", "flxxx"}).equals("fl"));
        Assert.assertTrue(longestCommonPrefix(new String[]{"dog", "car", "racecar"}).equals(""));
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String longestPrefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            while(!strs[i].startsWith(longestPrefix)) {
                longestPrefix = longestPrefix.substring(0, longestPrefix.length() - 1);
                if (longestPrefix.isEmpty()) {
                    return "";
                }
            }
        }
        return longestPrefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int max = strs[0].length();
        char[] cArray = new char[max];
        for (int i = 0; i < max; i++){
            char current = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i+1 || strs[j].charAt(i) != current) {
                    return new String(cArray).substring(0, i);
                }
            }
            cArray[i] = current;
        }
        return new String(cArray);
    }

    public String longestCommonPrefix1(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int min = strs[0].length();
        for(int i = 1; i < strs.length; i++) {
            if(strs[i].length() < min) {
                min = strs[i].length();
            }
        }
        char[] cArray = new char[min];
        for (int i = 0; i < min; i++){
            char current = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != current) {
                    return new String(cArray).substring(0, i);
                }
            }
            cArray[i] = current;
        }
        return new String(cArray);
    }
}