import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
It helps to find the shortest path from any node to any other nodes.
It does not work for negative cycle.
TC->O(ElogV)
SC->O(V)
 */
public class DijkstraAlgorithm {
    static class Pair{
        int node;
        int wt;
        Pair(int _node,int _wt){
            node=_node;
            wt=_wt;
        }
    }

    public static int[] dijkstra(int N, ArrayList<ArrayList<ArrayList<Integer>>> adj,int startPoint){
        int[] dist=new int[N];
        for(int i=0;i<N;i++){
            dist[i]=(int)(1e9);
        }
       dist[startPoint]=0;
        PriorityQueue<Pair> pq=new PriorityQueue<>((x,y)->x.wt-y.wt);
        pq.add(new Pair(startPoint,0));
        while(!pq.isEmpty()){
            int node=pq.peek().node;
            int wt=pq.peek().wt;
            pq.poll();
            for(int i=0;i<adj.get(node).size();i++){
                int neighbor=adj.get(node).get(i).get(0);
                int edgeWeight=adj.get(node).get(i).get(1);
                if(edgeWeight+wt<dist[neighbor]){
                    dist[neighbor]=edgeWeight+wt;
                    pq.add(new Pair(neighbor,dist[neighbor]));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args){
        int N=6;
        int startPoint=0;
        ArrayList<ArrayList<ArrayList<Integer>>> adj=new ArrayList<>();
        // Initialize
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges for a more complex graph
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.get(0).add(new ArrayList<>(Arrays.asList(2, 4)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 1)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(3, 7)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(3, 3)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(4, 1)));
        adj.get(3).add(new ArrayList<>(Arrays.asList(5, 1)));
        adj.get(4).add(new ArrayList<>(Arrays.asList(3, 2)));
        adj.get(4).add(new ArrayList<>(Arrays.asList(5, 5)));

        int[] distance = dijkstra(N, adj, 0);
        System.out.print("Shortest distances from node 0: ");
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println();

    }
}
