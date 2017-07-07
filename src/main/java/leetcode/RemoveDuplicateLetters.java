package leetcode;

public final class RemoveDuplicateLetters {
    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("bbcaac"));
    }

    public static class Solution {
        public String removeDuplicateLetters(String s) {
            StringBuilder builder = new StringBuilder(s.length());

            int[] freqs = new int[26];
            for (int k = 0; k < s.length(); k++) {
                freqs[s.charAt(k) - 'a']++;
            }

            int i = 0;
            while (i < s.length()) {
                while (i < s.length() && freqs[s.charAt(i) - 'a'] == Integer.MAX_VALUE) {
                    i++;
                }
                if (i >= s.length()) {
                    break;
                }
                int min = i;
                int j = i;
                while (j < s.length() && freqs[s.charAt(j) - 'a'] > 1) {
                    if (freqs[s.charAt(j) - 'a'] < Integer.MAX_VALUE) {
                        if (s.charAt(j) < s.charAt(min)) {
                            min = j;
                        }
                        freqs[s.charAt(j) - 'a']--;
                    }
                    j++;
                }
                if (j < s.length()) {
                    if (s.charAt(j) < s.charAt(min)) {
                        min = j;
                    }
                }
                builder.append(s.charAt(min));
                freqs[s.charAt(min) - 'a'] = Integer.MAX_VALUE;
                for (int k = min + 1; k < j; k++) {
                    if (freqs[s.charAt(k) - 'a'] < Integer.MAX_VALUE) {
                        freqs[s.charAt(k) - 'a']++;
                    }
                }
                i = min + 1;
            }
            return builder.toString();
        }
    }
}
