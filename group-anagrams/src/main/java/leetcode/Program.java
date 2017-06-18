package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Program {
    public static void main(String[] args) {
        System.out.println(new Solution().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public static final class Solution {
        List<List<String>> groupAnagrams(String[] strs) {
            return new ArrayList<>(
                    Arrays.stream(strs)
                            .collect(Collectors.groupingBy(AnagramKey::new))
                            .values());
        }

        private static final class AnagramKey {
            private final int[] frequencies = new int[26];

            AnagramKey(String s) {
                for (int i = 0; i < s.length(); i++) {
                    frequencies[s.charAt(i) - 'a']++;
                }
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }

                AnagramKey that = (AnagramKey) o;

                return Arrays.equals(frequencies, that.frequencies);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(frequencies);
            }
        }
    }
}
