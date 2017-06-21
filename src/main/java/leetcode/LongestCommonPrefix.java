package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"a", "abc"}));
    }

    public static final class Solution {
        String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            List<String> uniqueSortedStrs = Arrays.stream(strs)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
            if (uniqueSortedStrs.get(0).length() == 0) {
                return "";
            }
            String prefix = uniqueSortedStrs.get(0);
            int prefixLength = prefix.length();
            for (int i = 1; i < uniqueSortedStrs.size(); i++) {
                String s = uniqueSortedStrs.get(i);
                for (int j = 0; j < prefixLength; j++) {
                    if (s.charAt(j) != prefix.charAt(j)) {
                        prefixLength = j;
                    }
                }
            }
            return prefix.substring(0, prefixLength);
        }
    }
}
