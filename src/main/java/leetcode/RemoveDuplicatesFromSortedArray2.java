package leetcode;

public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lastNum = ~nums[0];
        int dupCount = 1;
        int writeIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == lastNum) {
                dupCount++;
                if (dupCount <= 2) {
                    nums[writeIndex++] = lastNum;
                }
            }
            else {
                lastNum = nums[i];
                dupCount = 1;
                nums[writeIndex++] = lastNum;
            }
        }
        return writeIndex;
    }
}
