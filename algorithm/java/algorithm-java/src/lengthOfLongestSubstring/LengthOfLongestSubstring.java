package lengthOfLongestSubstring;

/**
 * @author shengfei
 * Leetcode #3, Mediem
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.println(lengthOfLongestSubstring("a"));
        System.out.println(lengthOfLongestSubstring(""));
        System.out.println(lengthOfLongestSubstring("abcdcba"));
    }

    /**
     * 时间复杂度O(n*m), m为最大子串长度；空间复杂度O(n)
     * @param
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] maxArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            maxArray[i] = 1;
        }

        int max = 1;
        for (int i = 1; i < s.length(); i++) {

            for (int j = i - 1; j >= i - maxArray[i - 1]; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    break;
                } else {
                    maxArray[i] += 1;
                }
            }

            if (maxArray[i] > max) {
                max = maxArray[i];
            }
        }

        return max;

    }
}