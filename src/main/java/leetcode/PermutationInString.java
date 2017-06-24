package leetcode;

import java.util.Arrays;
import java.util.Objects;

public final class PermutationInString {
    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("adc", "dcda"));
    }

    public static final class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s2.length() < s1.length()) {
                return false;
            }
            Matcher matcher = new Matcher(s1);
            for (int i = 0; i < s1.length() - 1; i++) {
                matcher.push(s2.charAt(i));
            }
            for (int i = s1.length() - 1; i < s2.length(); i++) {
                matcher.push(s2.charAt(i));
                if (matcher.isFound()) {
                    return true;
                }
                matcher.pop(s2.charAt(i - s1.length() + 1));
            }
            return false;
        }

        static final class Matcher {
            private final Freq[] freqs;
            private final int distictCount;
            private int matchedCount = 0;

            Matcher(String w) {
                freqs = new Freq[256];
                for (int i = 0; i < w.length(); i++) {
                    if (freqs[w.charAt(i)] == null) {
                        freqs[w.charAt(i)] = new Freq();
                    }
                    freqs[w.charAt(i)].incRequired();
                }
                distictCount = (int) Arrays.stream(freqs).filter(Objects::nonNull).count();
                matchedCount = 0;
            }

            @Override
            public String toString() {
                return "Matcher{" +
                        "freqs=" + Arrays.toString(freqs) +
                        ", distictCount=" + distictCount +
                        ", matchedCount=" + matchedCount +
                        '}';
            }

            int distictCount() {
                return distictCount;
            }

            void pop(char ch) {
                Freq freq = freqs[ch];
                if (freq != null) {
                    if (freq.decActual()) {
                        matchedCount--;
                    }
                }
            }

            void push(Character ch) {
                Freq freq = freqs[ch];
                if (freq != null) {
                    if (freq.incActual()) {
                        matchedCount++;
                    }
                }
            }

            boolean isFound() {
                return matchedCount == distictCount;
            }

            private static final class Freq {
                private int required;
                private int actual;

                @Override
                public String toString() {
                    return "Freq{" +
                            "required=" + required +
                            ", actual=" + actual +
                            '}';
                }

                void incRequired() {
                    required++;
                }

                boolean incActual() {
                    actual++;
                    return actual == required;
                }

                boolean decActual() {
                    boolean result = actual == required;
                    actual--;
                    return result;
                }
            }
        }
    }
}
