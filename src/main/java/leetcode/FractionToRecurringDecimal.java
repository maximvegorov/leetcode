package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(-1, -2147483648));
    }

    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder sb = new StringBuilder();

            int signNum = Integer.compare(numerator, 0);
            int signDenom = Integer.compare(denominator, 0);

            long num = Math.abs((long)numerator);
            long denom = Math.abs((long)denominator);

            if (signNum * signDenom < 0)
                sb.append('-');

            sb.append(num / denom);

            long mod = num % denom;
            if (mod == 0)
                return sb.toString();

            sb.append('.');

            Map<Long, Integer> startOfCycle = new HashMap<>();
            while (mod != 0) {
                Integer prevPos = startOfCycle.get(mod);
                if (prevPos != null) {
                    sb.insert(prevPos.intValue(), '(');
                    sb.append(')');
                    break;
                }
                startOfCycle.put(mod, sb.length());
                for (mod *= 10; mod < denom; mod *= 10) {
                    sb.append('0');
                    startOfCycle.put(mod, sb.length());
                }
                sb.append(mod / denom);
                mod = mod % denom;
            }

            return sb.toString();
        }
    }
}
