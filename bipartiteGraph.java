import java.util.*;

/*
Color the graph with 2 colors such that no adjacent nodes have the same color
Here we are representing colors with 0 and 1
TC->same as BFS->O(V+2E)
SC->(V)
 */
public class bipartiteGraph {
    public boolean isBipartite(int N, List<List<Integer>> adj){
        int[] color=new int[N];
        for(int i=0;i<N;i++){
            color[i]=-1;
        }
        // If the graph has multiple components, so its better to do this way
        for(int i=0;i<N;i++){
            if(color[i]==-1){//not colored yet
                if(canColor(i,color,adj) == false){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canColor(int start, int[] color, List<List<Integer>> adj) {
        color[start]=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int node =q.peek();
            q.poll();
            for(int neighbor:adj.get(node)){
                if(color[neighbor]==-1){
                    color[neighbor]=1-color[node];
                    q.add(neighbor);
                }
                else if(color[neighbor]==color[node]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        int N=9;
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<N;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(2).add(6);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(4).add(3);
        adj.get(4).add(5);
        adj.get(4).add(7);
        adj.get(4).add(8);
        adj.get(5).add(4);
        adj.get(5).add(6);
        adj.get(6).add(2);
        adj.get(6).add(5);

        bipartiteGraph obj = new bipartiteGraph();
        System.out.println("Is the graph Bipartite "+obj.isBipartite(N,adj));
    }
}
