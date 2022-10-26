package leetcode;

public class FindTheWinnerOfTheCircularGame {
    public static void main(String[] args) {
        System.out.println(new Solution().findTheWinner(5, 2));
    }

    static class Solution {
        public int findTheWinner(int n, int k) {
            var ring = new Ring(n);
            while (!ring.isEndOfGame()) {
                ring.next(k);
            }
            return ring.getWinner();
        }

        private static class Ring {
            private int size;
            private Item current;

            public Ring(int n) {
                var current = new Item(1);
                this.current = current;
                for (var i = 2; i <= n; i++) {
                    current = current.insertAfter(i);
                }
                this.size = n;
            }

            public boolean isEndOfGame() {
                return size == 1;
            }

            public int getWinner() {
                return current.i;
            }

            public void next(int k) {
                k = (k - 1) % size;
                var p = current;
                while (k > 0) {
                    p = p.next;
                    k--;
                }
                current = p.remove();
                size--;
            }

            private static class Item {
                public int i;
                public Item prev;
                public Item next;

                public Item() {
                }

                public Item(int i) {
                    this.i = i;
                    this.prev = this;
                    this.next = this;
                }

                @Override
                public String toString() {
                    return Integer.toString(i);
                }

                public Item insertAfter(int v) {
                    var item = new Item();
                    item.i = v;
                    item.prev = this;
                    item.next = next;
                    next.prev = item;
                    next = item;
                    return item;
                }

                public Item remove() {
                    next.prev = prev;
                    prev.next = next;
                    return next;
                }
            }
        }
    }
}
