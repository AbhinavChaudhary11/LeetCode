class Solution {
    public long subArrayRanges(int[] nums) {
        return subArrayMax(nums) - subArrayMin(nums);
    }

    private long subArrayMax(int[] nums) {
        long res = 0;
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] < nums[i])) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                res += (long) nums[mid] * (mid - left) * (right - mid);
            }
            stack.push(i);
        }
        return res;
    }

    private long subArrayMin(int[] nums) {
        long res = 0;
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] > nums[i])) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                res += (long) nums[mid] * (mid - left) * (right - mid);
            }
            stack.push(i);
        }
        return res;
    }
}
