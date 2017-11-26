package leetcode.common;

public final class Interval {
    public int start;
    public int end;

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}
