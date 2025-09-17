class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        List<Set<Integer>> knows = new ArrayList<>();
        for (int[] lang : languages) {
            Set<Integer> set = new HashSet<>();
            for (int l : lang) set.add(l);
            knows.add(set);
        }
        Set<Integer> need = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1;
            if (Collections.disjoint(knows.get(u), knows.get(v))) {
                need.add(u);
                need.add(v);
            }
        }
        if (need.isEmpty()) return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int u : need) if (!knows.get(u).contains(i)) cnt++;
            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}