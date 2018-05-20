package leetcode;

public class ConsecutiveNumbersSum {
    public static void main(String[] args) {
        System.out.println(new Solution().consecutiveNumbersSum(5));
    }

    static class Solution {
        public int consecutiveNumbersSum(int m) {
            int count = 1;
            int m_times_2 = 2 * m;
            int a1_times_2 = m_times_2;
            for (int n = 2; a1_times_2 >= 1; n++) {
                if ((n & 1) == 0) {
                    int n_div_2 = n / 2;
                    if (m % n_div_2 != 0)
                        continue;
                    a1_times_2 = m / n_div_2 - n + 1;
                }
                else {
                    if (m_times_2 % n != 0)
                        continue;
                    a1_times_2 = m_times_2 / n - n + 1;
                }
                if (a1_times_2 <= 0 || (a1_times_2 & 1) != 0 || a1_times_2 > m_times_2)
                    continue;
                count++;
            }
            return count;
        }
    }
}
