package reverseInteger;

/**
 * @author shengfei
 * Leetcode #7, Easy
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int result = reverse(1534236469);
        System.out.println(result);
        result = reverse(153423646);
        System.out.println(result);
        result = reverse(-123);
        System.out.println(result);
        result = reverse(2 << 29 - 1);
        System.out.println(result);
        result = reverse(2 << 29 * (-1) + 1);
        System.out.println(result);

        System.out.println(Integer.MAX_VALUE * 2 > Integer.MAX_VALUE);
    }

    public static int reverse(int x) {

        if (x > -10 && x < 10) {
            return x;
        }

        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }

        return (int)result;

    }

    public static int reverse2(int x) {

        if (x > -10 && x < 10) {
            return x;
        }

        int negetive = x < 0 ? -1 : 1;
        if (negetive == -1) {
            x = 0 - x;
        }
        int result = 0;
        while (x > 0) {
            int remainder = x % 10;
            if (result > Integer.MAX_VALUE / 10
                    || ((result == Integer.MAX_VALUE / 10) &&
                        ((negetive == 1 && remainder > 7) || (negetive == -1 && remainder > 8)))){
                return 0;
            }
            result = result * 10 + remainder;
            x = x / 10;
        }

        return result * negetive;

    }

}