package leetcode;

public class ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("trie");
        trie.insert("search");
        System.out.println(trie.search(""));
        System.out.println(trie.search("a"));
    }

    static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode(null);
        }

        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                if (current.children == null) {
                    current.children = new TrieNode[26];
                }
                int childIndex = word.charAt(i) - 'a';
                if (current.children[childIndex] == null) {
                    current.children[childIndex] = new TrieNode(current);
                    current.childrenCount++;
                }
                current = current.children[childIndex];
            }
            current.isLeaf = true;

        }

        private TrieNode findNode(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length() && current != null; i++) {
                if (current.children == null) {
                    current = null;
                } else {
                    current = current.children[prefix.charAt(i) - 'a'];
                }
            }
            return current;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isLeaf;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = findNode(prefix);
            return node != null;
        }

        public void remove(String word) {
            TrieNode node = findNode(word);
            if (node == null || !node.isLeaf) {
                return;
            }
            node.isLeaf = false;
            TrieNode current = node;
            for (int i = word.length(); i > 0 && current.childrenCount == 0; i--) {
                current.parent.children[word.charAt(i - 1) - 'a'] = null;
                current.parent.childrenCount--;
                current = current.parent;
            }
        }

        private static final class TrieNode {
            private TrieNode parent;
            private TrieNode[] children;
            private byte childrenCount;
            private boolean isLeaf;

            TrieNode(TrieNode parent) {
                this.parent = parent;
            }
        }
    }
}
