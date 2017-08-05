package leetcode;

import java.util.ArrayList;
import java.util.List;

public final class TextJustification {
    public static void main(String[] args) {
        System.out.println(new Solution().fullJustify(
                new String[]{"Don't", "go", "around", "saying", "the", "world", "owes", "you", "a", "living;", "the", "world", "owes", "you", "nothing;", "it", "was", "here", "first."},
                30));
    }

    public static final class Solution {
        private static void appendSpaces(StringBuilder builder, int count) {
            for (int i = 0; i < count; i++) {
                builder.append(' ');
            }
        }

        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>(words.length);

            StringBuilder builder = new StringBuilder(maxWidth);

            for (int i = 0; i < words.length; ) {
                builder.setLength(0);

                builder.append(words[i]);

                int j = i + 1;
                int length = maxWidth - words[i].length();
                while (j < words.length && length >= words[j].length() + 1) {
                    length -= words[j].length() + 1;
                    j++;
                }

                int gapCount = j - i - 1;

                if (gapCount == 0 || j >= words.length) {
                    for (int k = i + 1; k < j; k++) {
                        builder.append(' ')
                                .append(words[k]);
                    }
                    while (builder.length() < maxWidth)
                        builder.append(' ');
                } else {
                    int spaceCount = length + j - i - 1;
                    int spaceBeetweenCount = spaceCount / gapCount;
                    int remainSpace = spaceCount % gapCount;
                    for (int k = i + 1; k < j; k++) {
                        appendSpaces(builder, spaceBeetweenCount);
                        if (remainSpace > 0) {
                            builder.append(' ');
                            remainSpace--;
                        }
                        builder.append(words[k]);
                    }
                }
                result.add(builder.toString());

                i = j;
            }

            return result;
        }
    }
}
