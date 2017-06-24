package leetcode;

import java.util.HashMap;
import java.util.Map;

public final class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("cabefgecdaecf", "cae"));
    }

    public static class Solution {
        String minWindow(String s, String t) {
            if (s == null || s.equals("") || t == null || t.equals("") || s.length() < t.length()) {
                return "";
            }

            Matcher m = new Matcher(t);
            int lmin = 0;
            int rmin = 0;
            while (rmin < s.length() && !m.isFound()) {
                m.push(s.charAt(rmin));
                rmin++;
            }

            if (!m.isFound()) {
                return "";
            }

            while (m.isFound()) {
                m.pop(s.charAt(lmin));
                lmin++;
            }
            lmin--;
            m.push(s.charAt(lmin));

            int l = lmin;
            for (int r = rmin + 1; r <= s.length(); r++) {
                m.push(s.charAt(r - 1));

                if (s.charAt(l) != s.charAt(r - 1)) {
                    continue;
                }

                m.pop(s.charAt(l));

                int maxL = r - m.distictCharCount();
                do {
                    l++;
                    m.pop(s.charAt(l));
                }
                while (l <= maxL && m.isFound());
                if (r - l < rmin - lmin) {
                    lmin = l;
                    rmin = r;
                    if (rmin - lmin == m.distictCharCount()) {
                        break;
                    }
                }
                m.push(s.charAt(l));
            }

            return s.substring(lmin, rmin);
        }

        static final class Matcher {
            private final Map<Character, Freq> freqs;
            private int matchedCount = 0;

            Matcher(String w) {
                freqs = new HashMap<>(w.length());
                for (int i = 0; i < w.length(); i++) {
                    freqs.computeIfAbsent(w.charAt(i), c -> new Freq())
                            .incRequired();
                }
                this.matchedCount = 0;
            }

            @Override
            public String toString() {
                return "Matcher{" +
                        "freqs=" + freqs +
                        ", matchedCount=" + matchedCount +
                        '}';
            }

            int distictCharCount() {
                return freqs.size();
            }

            void pop(Character ch) {
                Freq freq = freqs.get(ch);
                if (freq != null) {
                    if (freq.decActual()) {
                        matchedCount--;
                    }
                }
            }

            void push(Character ch) {
                Freq freq = freqs.get(ch);
                if (freq != null) {
                    if (freq.incActual()) {
                        matchedCount++;
                    }

                }
            }

            boolean isFound() {
                return matchedCount == freqs.size();
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
