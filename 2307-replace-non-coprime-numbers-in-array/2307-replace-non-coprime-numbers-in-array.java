import java.util.*;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            st.push(nums[i]);

            
            while (st.size() > 1) {
                int first = st.pop();
                int second = st.pop();

                int gcd = getGCD(first, second);

                if (gcd > 1) {
                    long lcm = (long) first / gcd * second; 
                    st.push((int) lcm);
                } else {
                    st.push(second);
                    st.push(first);
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!st.isEmpty()) {
            ans.add(0, st.pop());
        }
        return ans;
    }

    private int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}
