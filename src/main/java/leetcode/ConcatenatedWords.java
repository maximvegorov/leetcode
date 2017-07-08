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
            List<WordWithFreqs> wordsWithFreqs = Arrays.stream(words)
                    .map(WordWithFreqs::new)
                    .sorted(Comparator.comparingInt(w -> w.word.length()))
                    .collect(Collectors.toList());
            return wordsWithFreqs
                    .stream()
                    .map(w1 -> new WordWithSubWords(
                            w1.getWord(),
                            wordsWithFreqs.stream()
                                    .filter(w1::isCandidate)
                                    .map(WordWithFreqs::getWord)
                                    .collect(Collectors.toList())))
                    .filter(WordWithSubWords::isConcatenable)
                    .map(WordWithSubWords::getWord)
                    .collect(Collectors.toList());
        }

        private static final class WordWithFreqs {
            final String word;
            final short[] freqs;

            WordWithFreqs(String word) {
                this.word = word;
                this.freqs = new short[26];
                for (int i = 0; i < word.length(); i++) {
                    freqs[word.charAt(i) - 'a']++;
                }
            }

            public String getWord() {
                return word;
            }

            boolean isCandidate(WordWithFreqs wordWithFreqs) {
                if (word.length() <= wordWithFreqs.word.length()) {
                    return false;
                }
                for (int i = 0; i < freqs.length; i++) {
                    if (freqs[i] < wordWithFreqs.freqs[i]) {
                        return false;
                    }
                }
                return true;
            }
        }

        private static final class WordWithSubWords {
            final String word;
            final List<String> candidates;

            WordWithSubWords(String word, List<String> candidates) {
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
