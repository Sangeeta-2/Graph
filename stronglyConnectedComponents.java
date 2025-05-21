import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
Kosaraju's algorithm
only valid for directed graphs
We need to find out how many components are there
Steps
1. Sort all the edges according to finishing time--> doing DFS and storing in a stack
2. Reverse the graph
3. do DFS again and count the scc(strongly connected components)
TC->O(V+E)
SC->O(V+E)
 */
public class stronglyConnectedComponents {
    public int Kosaraju(int N, ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> st=new Stack<>();
        int[] visited=new int[N];
        ArrayList<ArrayList<Integer>> adjT=new ArrayList<>();
        int scc=0;
        //Performing step 1
        for(int i=0;i<N;i++){
            if(visited[i]==0){//The node is not visited yet
               dfs1(i,visited,st,adj);
            }
        }
        //Performing step 2
        for(int i=0;i<N;i++){
            adjT.add(new ArrayList<>());
        }
        for(int i=0;i<N;i++){
            visited[i]=0;// making it zero again as we need to perform dfs again
            for(Integer neighbor:adj.get(i)){
                adjT.get(neighbor).add(i);
            }
        }
        //Performing step 3
        while(!st.isEmpty()){
            int node=st.peek();
            st.pop();
            if(visited[node]==0){
                scc++;
                dfs2(node,visited,adjT);
            }
        }
        return scc;
    }

    private void dfs2(int node, int[] visited, ArrayList<ArrayList<Integer>> adjT) {
        visited[node]=1;
        for(int neighbor:adjT.get(node)){
            if(visited[neighbor]==0){
                dfs2(neighbor,visited,adjT);
            }
        }
    }

    private void dfs1(int node, int[] visited, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {
        visited[node]=1;
        for(int neighbor:adj.get(node)){
            if(visited[neighbor]==0){
                dfs1(neighbor,visited,st,adj);
            }
        }
        st.push(node);
    }

    public static void main(String[] args){
        int N=8;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<N;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(5);
        adj.get(4).add(7);
        adj.get(5).add(6);
        adj.get(6).add(4);
        adj.get(6).add(7);
        stronglyConnectedComponents obj =new stronglyConnectedComponents();
        System.out.println("The number of strongly connected components are "+obj.Kosaraju(N,adj));
    }

}
