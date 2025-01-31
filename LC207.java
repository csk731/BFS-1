// TC: O(V+E)
// SC: O(V+E)
// where V is number of courses and E is number of prerequisites

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return true;
        int indegree[] = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int n = prerequisites.length;
        for (int i = 0; i < n; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            if (!map.containsKey(from))
                map.put(from, new ArrayList<>());
            map.get(from).add(to);
            indegree[to]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int p = q.poll();
            List<Integer> edges = map.getOrDefault(p, Arrays.asList());
            for (int e : edges) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    q.add(e);
                }
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] != 0)
                return false;
        }

        return true;
    }
}
