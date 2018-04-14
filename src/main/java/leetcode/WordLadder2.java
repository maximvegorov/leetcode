package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class WordLadder2 {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .findLadders(
                                "hit",
                                "cog",
                                Arrays.asList("hot","dot","dog","lot","log","cog")));
    }

    static class Solution {
        private boolean canMove(String s1, String s2) {
            if (s1.length() != s2.length())
                return false;
            int diffCount = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1)
                        return false;
                }
            }
            return diffCount == 1;
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord))
                return Collections.emptyList();

            Map<String, List<String>> transions = new HashMap<>();
            for (String si : wordList) {
                for (String sj : wordList) {
                    if (canMove(si, sj))
                        transions.computeIfAbsent(si, k -> new ArrayList<>()).add(sj);
                }
            }

            List<State> found = new ArrayList<>();
            List<State> unprocessed = wordList.stream()
                    .filter(w -> canMove(beginWord, w))
                    .map(w -> new State(w, null))
                    .collect(Collectors.toList());
            List<State> processed = new ArrayList<>();
            Set<String> visited = new HashSet<>(Collections.singleton(beginWord));
            while (!unprocessed.isEmpty() && found.isEmpty()) {
                for (State prev : unprocessed) {
                    visited.add(prev.getWord());
                    if (endWord.equals(prev.getWord())) {
                        found.add(prev);
                    } else {
                        for (String transition : transions.getOrDefault(prev.getWord(), Collections.emptyList())) {
                            if (!visited.contains(transition))
                                processed.add(new State(transition, prev));
                        }
                    }
                }

                List<State> temp = unprocessed;
                unprocessed = processed;
                processed = temp;
                processed.clear();
            }

            List<List<String>> result = new ArrayList<>(found.size());
            for (State last : found) {
                List<String> path = new ArrayList<>();
                for (State current = last; current != null; current = current.getPrev()) {
                    path.add(current.getWord());
                }
                path.add(beginWord);
                Collections.reverse(path);
                result.add(path);
            }
            return result;
        }

        static class State {
            private final String word;
            private final State prev;

            State(String word, State prev) {
                this.word = word;
                this.prev = prev;
            }

            @Override
            public String toString() {
                return "State{" +
                        "word='" + word + '\'' +
                        '}';
            }

            String getWord() {
                return word;
            }

            State getPrev() {
                return prev;
            }
        }
    }
}
