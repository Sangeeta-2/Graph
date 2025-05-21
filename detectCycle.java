import java.util.ArrayList;
/*
TC->O(V+E)
SC->(V)
 */
public class detectCycle {
    public static boolean isCyclic(int v, ArrayList<ArrayList<Integer>> adj){
        int[] visited=new int[v];
        for(int i=0;i<v;i++){
            if(visited[i]==0){
                if(dfsCheck(1,visited,adj)==true){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean dfsCheck(int node,int[] visited,ArrayList<ArrayList<Integer>> adj){
        visited[node]=2;//considering visiteing the node as well as path visited
        for(int neighbor:adj.get(node)){
            if(visited[neighbor]==0){
                if(dfsCheck(neighbor,visited,adj)==true){
                    return true;
                }
            }
            else if(visited[neighbor]==2){
                return true;
            }
        }
        visited[node]--;//making it only visited and not path visited
        return false;
    }

    public static void main(String[] args){
        int v=6;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        //graph without cycle
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(3).add(5);
        adj.get(4).add(5);
        //graph with cycle
//        adj.get(1).add(2);
//        adj.get(2).add(3);
//        adj.get(3).add(5);
//        adj.get(5).add(4);
//        adj.get(4).add(2);

        System.out.println("Is Cycle present in Directed graph "+isCyclic(v,adj));
    }
}
