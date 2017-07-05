package leetcode;

public final class IsSubsequence {
    public static void main(String[] args) {
        System.out.println(new Solution().isSubsequence("axc", "ahbgdc"));
    }

    public static class Solution {
        public boolean isSubsequence(String s, String t) {
            int matched = 0;
            for (int i = 0; i < t.length() && matched < s.length(); i++) {
                if (t.charAt(i) == s.charAt(matched)) {
                    matched++;
                }
            }
            return matched == s.length();
        }
    }
}
