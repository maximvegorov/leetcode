package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LetterCombinationsOfaPhoneNumber {
    public static void main(String[] args) {
        System.out.println(new Solution().letterCombinations("23"));
    }

    public static class Solution {
        private static String[] chars = new String[]{
                " ",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        private static boolean next(String digits, byte[] s) {
            for (int i = 0; i < s.length; i++) {
                s[i]++;
                if (s[i] < chars[digits.charAt(i) - '0'].length()) {
                    break;
                } else if (i < s.length - 1) {
                    s[i] = 0;
                } else {
                    return false;
                }
            }
            return true;
        }

        public List<String> letterCombinations(String digits) {
            digits = digits.replaceAll("1", "");

            if (digits.isEmpty()) {
                return Collections.emptyList();
            }

            int capacity = digits.chars()
                    .map(d -> chars[d - '0'].length())
                    .reduce(1, (p, l) -> p * l);

            List<String> result = new ArrayList<>(capacity);

            byte[] s = new byte[digits.length()];
            StringBuilder builder = new StringBuilder(digits.length());
            do {
                for (int i = 0; i < s.length; i++) {
                    builder.append(chars[digits.charAt(i) - '0'].charAt(s[i]));
                }
                result.add(builder.toString());
                builder.setLength(0);
            } while (next(digits, s));

            return result;
        }
    }
}
