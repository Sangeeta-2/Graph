import java.util.*;

/*
BFS->Breadth First Search Traversal
For BFS we use queue
TC->(undirected) ->(V+2E)
directed->(V+E)
SC->O(N)
 */
public class BFSTraversal {
    public ArrayList<Integer> bfsOfGraph(int N, List<List<Integer>> adj){
        int[] visited=new int[N+1];
        ArrayList<Integer> bfsArray=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        queue.add(1);
        visited[1]=1;
        while(!queue.isEmpty()){
            int node=queue.peek();
            queue.poll();
            bfsArray.add(node);
            for(Integer neighbor:adj.get(node)){
                if(visited[neighbor]==0){
                    visited[neighbor]=1;
                    queue.add(neighbor);
                }
            }
        }
        return bfsArray;
    }
    public static void main(String[] args){
        int N=8;
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=N;i++){
            adj.add(new ArrayList<>());
        }
       adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(2).add(5);
        adj.get(2).add(6);
        adj.get(3).add(1);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(3);
        adj.get(4).add(8);
        adj.get(5).add(2);
        adj.get(6).add(2);
        adj.get(7).add(3);
        adj.get(7).add(8);
        adj.get(8).add(4);
        adj.get(8).add(7);
        BFSTraversal obj=new BFSTraversal();
        List<Integer> bfs=obj.bfsOfGraph(N,adj);
        System.out.println("The BFS traversal is ");
        for(int i=0;i<bfs.size();i++){
            System.out.print(bfs.get(i) +" ");
        }

    }
}
