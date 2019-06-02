package leetcode9;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shengfei
 * # Leetcode #9, Easy
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        Assert.check(!isPalindrome3(-121));
        Assert.check(isPalindrome3(121));
        Assert.check(isPalindrome3(1221));
        Assert.check(isPalindrome3(11));
        Assert.check(isPalindrome3(1));
        Assert.check(isPalindrome3(10001));
    }

    /**
     * 6ms
     * @param x
     * @return
     */
    public static boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        } if (x < 10) {
            return true;
        } else {
            int temp = x;
            int y = 0;
            while (temp > 0) {
                y = 10 * y + temp % 10;
                temp = temp / 10;
            }

            return x == y;
        }

    }

    /**
     * 7ms
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        } else {
            int[] array = new int[10];
            int index = 0;
            while (x > 0) {
                array[index++] = x % 10;
                x = x / 10;
            }
            for (int i = 0; i < index / 2; i++) {
                if (array[i] != array[index - 1 - i]) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * 9ms
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        } if (x < 10) {
            return true;
        } else {
            List<Integer> list = new ArrayList<>();
            while (x > 0) {
                list.add(x % 10);
                x = x / 10;
            }
            int size = list.size();
            for(int i = 0; i < size / 2; i++) {
                if (list.get(i).intValue() != list.get(size - 1 - i).intValue()) {
                    return false;
                }
            }
            return true;
        }

    }

}