package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ConcatenatedWords {
    public static void main(String[] args) {
        System.out.println(new Solution().findAllConcatenatedWordsInADict(
                new String[]{"cat", "dog", "catdog"}));
    }

    public static final class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            return Arrays.stream(words)
                    .parallel()
                    .map(w1 -> new ConcatenableWord(
                            w1,
                            Arrays.stream(words)
                                    .filter(w2 -> w2.length() < w1.length() && w1.contains(w2))
                                    .sorted(Comparator.comparing(String::length))
                                    .collect(Collectors.toList())))
                    .filter(ConcatenableWord::isConcatenable)
                    .map(ConcatenableWord::getWord)
                    .collect(Collectors.toList());
        }

        private static final class ConcatenableWord {
            final String word;
            final List<String> candidates;

            ConcatenableWord(String word, List<String> candidates) {
                this.word = word;
                this.candidates = candidates;
            }

            String getWord() {
                return word;
            }

            boolean isConcatenable() {
                if (candidates.size() < 2) {
                    return false;
                }
                boolean[] concatenable = new boolean[word.length() + 1];
                concatenable[word.length()] = true;
                for (int i = word.length() - candidates.get(0).length(); i >= 0; i--) {
                    for (int j = 0; j < candidates.size() && !concatenable[i]; j++) {
                        String candidate = candidates.get(j);
                        concatenable[i] = word.startsWith(candidate, i) && concatenable[i + candidate.length()];
                    }
                }
                return concatenable[0];
            }
        }
    }
}
