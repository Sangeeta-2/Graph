import java.util.ArrayList;
import java.util.List;

/*
DFS->Depth First Search Traversal
For DFS we use recursion technique always
TC->(undirected) ->(V+2E)
directed->(V+E)
SC->O(N)
 */
public class DFSTraversal {
    public List<Integer> dfsOfGraph(int N, ArrayList<ArrayList<Integer>> adj){
        int[] visited=new int[N+1];
        List<Integer> dfsArray=new ArrayList<>();
        dfs(1,visited,adj,dfsArray);
        return dfsArray;
    }
    public void dfs(int node,int[] visited,ArrayList<ArrayList<Integer>> adj,List<Integer> dfsArray){
        visited[node]=1;
        dfsArray.add(node);
        for(Integer neighbor:adj.get(node)){
            if(visited[neighbor]==0){
               dfs(neighbor,visited,adj,dfsArray);
            }
        }
    }
    public static void main(String[] args){
        int N=8;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=N;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(2).add(5);
        adj.get(2).add(6);
        adj.get(3).add(1);
        //adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(3).add(4);
        adj.get(4).add(3);
        adj.get(4).add(8);
        adj.get(5).add(2);
        adj.get(6).add(2);
        adj.get(7).add(3);
        adj.get(7).add(8);
        adj.get(8).add(4);
        adj.get(8).add(7);
        DFSTraversal obj=new DFSTraversal();
        List<Integer> dfs=obj.dfsOfGraph(N,adj);
        System.out.println("The DFS traversal is ");
        for(int i=0;i<dfs.size();i++){
            System.out.print(dfs.get(i) +" ");
        }

    }

}
