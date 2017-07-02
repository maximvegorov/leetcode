package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class WordBreak {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .wordBreak("cars", Arrays.asList("car", "ca", "rs")));
    }

    public static final class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            List<List<String>> wordsByFirstLetter = IntStream.rangeClosed('a', 'z')
                    .mapToObj(i -> new ArrayList<String>())
                    .collect(Collectors.toList());
            wordDict.forEach(w -> wordsByFirstLetter.get(w.charAt(0) - 'a').add(w));
            wordDict.sort(String::compareTo);
            boolean[] breaks = new boolean[s.length() + 1];
            breaks[s.length()] = true;
            for (int i = s.length() - 1; i >= 0; i--) {
                for (String w : wordsByFirstLetter.get(s.charAt(i) - 'a')) {
                    if (s.startsWith(w, i)) {
                        breaks[i] = breaks[i + w.length()];
                        if (breaks[i]) {
                            break;
                        }
                    }
                }
            }
            return breaks[0];
        }
    }
}
