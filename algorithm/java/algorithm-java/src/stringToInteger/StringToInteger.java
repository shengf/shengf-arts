package stringToInteger;

/**
 * @author shengfei
 * Leetcode #8, Medium
 */
public class StringToInteger {

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("  4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi(String.valueOf(Integer.MAX_VALUE)));
        System.out.println(myAtoi(String.valueOf(Integer.MIN_VALUE)));
    }

    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();
        int length = str.length();
        if (length == 0) {
            return 0;
        }

        boolean negative = false;
        long result = 0;
        int i = 0;
        char first = str.charAt(i);

        if (first < '0') {

            if (length == 1) {
                return 0;
            }

            if (first == '-') {
                negative = true;
            } else if (first != '+') {
                return 0;
            }

        } else if (first > '9') {
            return 0;
        } else {
            //result = Character.digit(first, 10);
            result = first - '0';
        }

        i++;
        while (i < length) {
            char charCode = str.charAt(i++);
            if (charCode < '0' || charCode > '9') {
                break;
            }

            //int digit = Character.digit(charCode, 10);
            int digit = charCode - '0';
            result = result * 10 + digit;
            if (result > Integer.MAX_VALUE) {
                break;
            }
        }
        result = negative ? -result : result;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }

}