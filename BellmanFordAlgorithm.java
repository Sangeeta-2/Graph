import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
It helps to find the shortest path from any node to any other nodes.
It also helps to detect negative cycle.
Here we will perform N-1 iterations because in a graph of N nodes in worst case we will take N-1 edges
to reach from starting node to the ending node
TC->O(V*E)
SC->O(V)
 */
public class BellmanFordAlgorithm {
    public int[] bellmanFord(int N, List<List<Integer>> edges,int startPoint){
        int[] dist=new int[N];
        for(int i=0;i<N;i++){
            dist[i]=(int) (1e8);//this determines infinite
        }
        dist[startPoint]=0;
        for(int i=0;i<N-1;i++){
            for(List<Integer> neighbor:edges){
                int u=neighbor.get(0);
                int v=neighbor.get(1);
                int wt=neighbor.get(2);
                if(dist[u]!=1e8 && dist[v]>dist[u]+wt){
                    dist[v]=dist[u]+wt;
                }
            }
        }
        /*If you are asked to return -1 if there is negative cycle then perform the below steps.
        Do the Nth iteration to check negative cycle.
        */
        for(List<Integer> neighbor:edges){
            int u=neighbor.get(0);
            int v=neighbor.get(1);
            int wt=neighbor.get(2);
            if(dist[u]!=1e8 && dist[v]>dist[u]+wt){
                int[] temp=new int[1];
                temp[0]=-1;
                return temp;
            }
        }
        return dist;
    }
    public static void main(String[] args){
        int N=5;
        List<List<Integer>> edges=new ArrayList<>();
        //Building the edges with its weight
        edges.add(Arrays.asList(0,1,5));
        edges.add(Arrays.asList(1,2,1));
        edges.add(Arrays.asList(1,3,2));
        edges.add(Arrays.asList(2,4,1));
        edges.add(Arrays.asList(4,3,-1));
        BellmanFordAlgorithm obj=new BellmanFordAlgorithm();
        int dist[]=obj.bellmanFord(N,edges,0);
        System.out.print("The shortest path array is ");
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }
}
