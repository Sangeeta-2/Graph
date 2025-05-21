import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
Spanning Tree-> A tree in which we have N nodes and N-1 edges and all nodes are reachable from each other.
A graph can have one or more spanning tree.
The least sum is called Minimum Spanning tree.
Prims algo is one of the algo to find minimum spanning tree(MST)
TC->)(ElogV)
SC->O(E)
 */
public class PrimsAlgoReturnSum {
    static class Pair{
        int node;
        int dist;
        Pair(int _node,int _dist){
            node=_node;
            dist=_dist;
        }
    }
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        int[] visited=new int[V];
        PriorityQueue<Pair> pq=new PriorityQueue<>((x,y)->x.dist-y.dist);
        pq.add(new Pair(0,0));
        int sum=0;
        while(!pq.isEmpty()){
            int node=pq.peek().node;
            int wt=pq.peek().dist;
            pq.poll();
            if(visited[node]==1){
                continue;
            }
            if(visited[node]==0){
                visited[node]=1;
                sum+=wt;
                //Traversing its neighbor
                for(int i=0;i<adj.get(node).size();i++){
                    int neighborNode=adj.get(node).get(i).get(0);
                    int neighborDist=adj.get(node).get(i).get(1);
                    if(visited[neighborNode]==0){
                        pq.add(new Pair(neighborNode,neighborDist));
                    }
                }
            }
        }
        return sum;
    }
    public static void main(String[] args){
        int V=5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        /* we can mention only one edge like suppose if there is an edge b/w 0 to 1. We can consider from 0 to 1 once.
        No need to consider from 1 to 0 again
         */
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.get(0).add(new ArrayList<>(Arrays.asList(3, 6)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(3, 8)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(4, 5)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
        //adj.get(1).add(new ArrayList<>(Arrays.asList(0, 2)));
       // adj.get(2).add(new ArrayList<>(Arrays.asList(1, 3)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(4, 7)));
        //adj.get(3).add(new ArrayList<>(Arrays.asList(0, 6)));
        //adj.get(3).add(new ArrayList<>(Arrays.asList(1, 8)));
        //adj.get(4).add(new ArrayList<>(Arrays.asList(1, 5)));
        //adj.get(4).add(new ArrayList<>(Arrays.asList(2, 7)));
        System.out.println("The least sum of the MST is "+spanningTree(V,adj));
    }
}
