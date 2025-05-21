import java.util.*;

/*using DFS
Linear ordering of vertices such that if there is an edge between u and v (u->v),
u appears before v in that odering.
TC->O(V+E)
SC->O(V)
 */
public class TopologicalSort {
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj){
        int visited[]=new int[V];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<V;i++){
            if(visited[i]==0){
                //not visited yet
                dfs(i,visited,stack,adj);
            }
        }
        int ans[]=new int[V];
        int i=0;
        while(!stack.isEmpty()){
            ans[i++]=stack.peek();
            stack.pop();
        }
        return ans;
    }

    private static void dfs(int node, int[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
        visited[node]=1;
        for(int neighbor:adj.get(node)){
            if(visited[neighbor]==0){
                //not visited yet
                dfs(neighbor,visited,stack,adj);
            }
        }
        stack.push(node);
    }

    public static List<Integer> topologicalSortBFS(int V,
                                                   ArrayList<ArrayList<Integer>> adj) {
        // Calculate in-degree for each vertex
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Queue for vertices with 0 in-degree ---> have no links or edges can complete independently
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        int count = 0; // Count of vertices processed

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            count++;

            // Reduce in-degree for all neighbors
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If count doesn't match V, there's a cycle
        if (count != V) {
            return new ArrayList<>(); // Cycle detected
        }

        return result;
    }

    public static void main(String[] args){
        int V=6;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        //Adding edges directed
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);
        int ans[]=topoSort(V,adj);
        List<Integer> result =topologicalSortBFS(V,adj);
        //Printing the answer for DFS
        for(int i=0;i< ans.length;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println();
        //Printing the answer for BFS --->Detect cycle here only
        for(int i=0;i< ans.length;i++){
            System.out.print(result.get(i)+" ");
        }
    }
}
