import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Bridge -> Any edge on whose removal the graph is broken down into 2 separate components is called a bridge.
TC->O(V+2E)
SC->(V)
 */
public class bridgesInGraph {
    private int timer=1;
    private List<List<Integer>> adj;
    private int[] tin;//tin-> time of insertion
    private int[] low;// lowest time to reach the node
    private boolean[] visited;
    private List<List<Integer>> bridges;

    public List<List<Integer>> criticalConnections(int n,List<List<Integer>> connections){
        adj=new ArrayList<>();
        tin=new int[n];
        low=new int[n];
        visited=new boolean[n];
        bridges=new ArrayList<>();

        //Build the graph
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(List<Integer> conn:connections){
            int u=conn.get(0);
            int v=conn.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(0,-1,low,tin,visited,adj,bridges);
        return bridges;
    }

    private void dfs(int node,int parent,int[] low,int[] tin,boolean[] visited,List<List<Integer>> adj,List<List<Integer>> bridges){
        visited[node]=true;
        low[node]=tin[node]=timer;
        timer++;
        //traversing its neighbors
        for(int neighbor:adj.get(node)){
            if(neighbor == parent) continue; //not considering its parents
            if(!visited[neighbor]){
                dfs(neighbor,node,low,tin,visited,adj,bridges);
                low[node]=Math.min(low[node],low[neighbor]);
                if(low[neighbor]>tin[node]){
                    bridges.add(Arrays.asList(node,neighbor));
                }
            }
            else{
                low[node]=Math.min(low[node],low[neighbor]);
            }
        }
    }

    public static void main(String[] args){
        int n=11;
        List<List<Integer>> connections=new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(0, 2));
        connections.add(Arrays.asList(0, 10));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(3, 4));
        connections.add(Arrays.asList(3, 5));
        connections.add(Arrays.asList(4, 6));
        connections.add(Arrays.asList(5, 6));
        connections.add(Arrays.asList(6, 7));
        connections.add(Arrays.asList(7, 8));
        connections.add(Arrays.asList(7, 9));
        connections.add(Arrays.asList(8, 9));

        bridgesInGraph obj=new bridgesInGraph();
        List<List<Integer>> output=obj.criticalConnections(n,connections);
        for(List<Integer> list:output){
            for(int index:list){
                System.out.print(index +" ");
            }
            System.out.println();
        }
    }
}
