package leetcode;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PermutationInString {
    public static void main(String[] args) {

    }

    public class Solution {
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Long> freqs = s1.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (int i = 0; i < s2.length(); i++) {

            }
        }
    }
}
