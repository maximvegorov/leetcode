package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RegularExpressionMatching {
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
    }

    public static final class Solution {
        private static List<String> parseTerms(String p) {
            List<String> result = new ArrayList<>(p.length());
            result.add("\0");
            int i = 0;
            while (i < p.length()) {
                if (p.charAt(i) != '*') {
                    result.add(String.valueOf(p.charAt(i)));
                } else {
                    if (result.isEmpty()) {
                        throw new IllegalArgumentException("p: " + p);
                    }
                    String lastTerm = result.get(result.size() - 1);
                    if (lastTerm.charAt(lastTerm.length() - 1) != '+') {
                        result.set(result.size() - 1, lastTerm + '*');
                        result.add(lastTerm + "++");
                    }
                }
                i++;
            }
            result.add("\0");
            result.add("");
            return result;
        }

        private static void processChar(
                List<String> terms,
                boolean[] states0,
                boolean[] states1,
                char prevChar,
                char curChar)
        {
            Arrays.fill(states1, false);
            for (int j = 0; j < states0.length; j++) {
                if (states0[j]) {
                    String term0 = terms.get(j);
                    if (term0.length() == 3) {
                        if (prevChar == curChar) {
                            states1[j] = true;
                        }
                    }
                    int k = j + 1;
                    while (terms.get(k).length() > 1) {
                        String term = terms.get(k);
                        if (term.length() == 3 && term.charAt(0) == '.' || term.charAt(0) == curChar) {
                            states1[k] = true;
                        }
                        k++;
                    }
                    String term1 = terms.get(k);
                    if (term1.charAt(0) == '.' || term1.charAt(0) == curChar) {
                        states1[k] = true;
                    }
                    if (states1[k]) {
                        while (--k > j) {
                            if (terms.get(k).length() == 2) {
                                states1[k] = true;
                            }
                        }
                    }
                }
            }
        }

        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            List<String> terms = parseTerms(p);
            boolean[] states0 = new boolean[terms.size() - 1];
            states0[0] = true;
            ;
            boolean[] states1 = new boolean[states0.length];
            char prevChar = '\0';
            for (int i = 0; i < s.length(); i++) {
                char curChar = s.charAt(i);
                processChar(terms, states0, states1, prevChar, curChar);
                prevChar = curChar;
                boolean[] tmp = states0;
                states0 = states1;
                states1 = tmp;
            }
            processChar(terms, states0, states1, prevChar, '\0');
            return states1[states1.length - 1];
        }
    }
}
