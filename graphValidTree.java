import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Need to find out if a graph is a valid tree or not.
To be a valid tree, for n nodes it shoud have n-1 edges and there should be no cycle
TC->DFS->O(V+E)
 */
public class graphValidTree {
    int count;
    public boolean isValidTree(int N, int E, ArrayList<List<Integer>> edges){
        if(E != N-1){
            return false;
        }
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[N+1];  // Size N+1 for 1-based indexing

        // Initialize adjacency list from 0 to N (N+1 elements)
        for(int i = 0; i <= N; i++){
            adj.add(new ArrayList<>());
        }

        // Add edges (access edges from 0 to E-1)
        for(int i = 0; i < E; i++){
            adj.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            adj.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }

        count = 1;
        dfs(adj, visited, 1);  // Start from node 1 instead of 0
        return (count == N);
    }

    private void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int node){
        visited[node] = true;
        for(int neighbor : adj.get(node)){
            if(visited[neighbor] == false){
                count++;
                dfs(adj, visited, neighbor);
            }
        }
    }

    public static void main(String[] args){
        int N = 5;  // 5 nodes numbered 1 to 5
        int E = 4;  // 4 edges for a tree with 5 nodes
        ArrayList<List<Integer>> edges = new ArrayList<>();

        // Edges with 1-based node numbering
        edges.add(Arrays.asList(1, 2));
        edges.add(Arrays.asList(1, 5));
        edges.add(Arrays.asList(2, 3));
        edges.add(Arrays.asList(2, 4));

        graphValidTree obj = new graphValidTree();
        System.out.println("Is the graph a valid tree: " + obj.isValidTree(N, E, edges));
    }
}