package leetcode;

public final class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(
                new Solution().multiply("123", "123"));
    }

    public static final class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }

            char[] result = new char[num1.length() + num2.length() + 1];

            for (int i = 0; i < num2.length(); i++) {
                int digit1 = num2.charAt(num2.length() - i - 1) - '0';
                int carry = 0;
                for (int j = 0; j < num1.length(); j++) {
                    int digit2 = num1.charAt(num1.length() - j - 1) - '0';
                    int prod = digit1 * digit2 + result[result.length - i - j - 1] + carry;
                    result[result.length - i - j - 1] = (char) (prod % 10);
                    carry = prod / 10;
                }
                result[result.length - i - num1.length() - 1] = (char) carry;
            }

            int startIndex = 0;
            while (startIndex < result.length - 1 && result[startIndex] == 0) {
                startIndex++;
            }

            for (int i = startIndex; i < result.length; i++) {
                result[i] += '0';
            }

            return new String(result, startIndex, result.length - startIndex);
        }
    }
}
