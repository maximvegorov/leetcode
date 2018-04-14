package leetcode;

import java.util.ArrayList;
import java.util.List;

public class AddAndSearchWordDataStructureDesign {
    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        System.out.println(dictionary.search("pad"));
        System.out.println(dictionary.search("bad"));
        System.out.println(dictionary.search(".ad"));
        System.out.println(dictionary.search("b.."));
    }

    static class WordDictionary {
        private final TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                current = current.getOrAddChild(word.charAt(i));
            }
            current.setWord();
        }

        public boolean search(String word) {
            List<TrieNode> prev = new ArrayList<>();
            root.appendChildren(word.charAt(0), prev);
            List<TrieNode> next = new ArrayList<>();
            for (int i = 1; i < word.length() && !prev.isEmpty(); i++) {
                for (TrieNode node : prev) {
                    node.appendChildren(word.charAt(i), next);
                }

                List<TrieNode> temp = prev;
                prev = next;
                next = temp;
                next.clear();
            }
            return prev.stream().anyMatch(TrieNode::isWord);
        }

        static class TrieNode {
            private TrieNode[] children;
            private boolean isWord;

            boolean isWord() {
                return isWord;
            }

            void setWord() {
                isWord = true;
            }

            TrieNode getOrAddChild(char letter) {
                if (children == null)
                    children = new TrieNode[26];
                TrieNode child = children[letter - 'a'];
                if (child == null) {
                    child = new TrieNode();
                    children[letter - 'a'] = child;
                }
                return child;
            }

            void appendChildren(char letter, List<TrieNode> list) {
                if (children == null)
                    return;
                if (letter != '.') {
                    TrieNode node = children[letter - 'a'];
                    if (node != null)
                        list.add(node);
                } else {
                    for (TrieNode node : children) {
                        if (node != null)
                            list.add(node);
                    }
                }
            }
        }
    }
}
