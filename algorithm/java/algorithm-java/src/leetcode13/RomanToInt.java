package leetcode13;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author shengfei
 * Leetcode #13, Easy
 */
public class RomanToInt {

    @Test
    public void test() {
        Assert.assertTrue(this.romanToInt("III") == 3);
        Assert.assertTrue(this.romanToInt("IV") == 4);
        Assert.assertTrue(this.romanToInt("LVIII") == 58);
        Assert.assertTrue(this.romanToInt("MCMXCIV") == 1994);
    }

    public int romanToInt(String s) {
        int result = 0;
        int temp = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            int current = this.char2int(s.charAt(i));
            if (current >= temp) {
                result += current;
            } else {
                result -= current;
            }
            temp = current;
        }
        return result;
    }

    private int char2int(char x) {
        if(x=='I') {
            return 1;
        }
        if(x=='V') {
            return 5;
        }
        if(x=='X') {
            return 10;
        }
        if(x=='L') {
            return 50;
        }
        if(x=='C') {
            return 100;
        }
        if(x=='D') {
            return 500;
        }
        if(x=='M') {
            return 1000;
        }
        return 0;
    }
}