class TaskManager {
    private Map<Integer, Integer> taskToUser = new HashMap<>();
    private Map<Integer, Integer> taskToPriority = new HashMap<>();
    private PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        if (a[0] != b[0]) return b[0] - a[0];
        return b[1] - a[1];
    });

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> t : tasks) {
            int u = t.get(0), id = t.get(1), p = t.get(2);
            taskToUser.put(id, u);
            taskToPriority.put(id, p);
            pq.offer(new int[]{p, id});
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskToUser.put(taskId, userId);
        taskToPriority.put(taskId, priority);
        pq.offer(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        if (taskToPriority.containsKey(taskId)) {
            taskToPriority.put(taskId, newPriority);
            pq.offer(new int[]{newPriority, taskId});
        }
    }

    public void rmv(int taskId) {
        if (taskToPriority.containsKey(taskId)) {
            taskToPriority.remove(taskId);
            taskToUser.remove(taskId);
        }
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int p = top[0], id = top[1];
            Integer curr = taskToPriority.get(id);
            if (curr != null && curr == p) {
                int user = taskToUser.get(id);
                taskToPriority.remove(id);
                taskToUser.remove(id);
                return user;
            }
        }
        return -1;
    }
}