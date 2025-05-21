import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* There are total number of N tasks,labeled from 0 to N-1
Some tasks may have prerequisites, for eg- to do task 0 you have to first complete task 1,
which is expressed as a pair[0,1]
Given the total number of tasks N and a list of prerequisite pairs P.
Find if it is possible to finish all task
TC->O(V+E)
SC->O(V)
 */
public class courseSchedule {
    static boolean isCourseSchedulePossible(int N, int m, ArrayList<ArrayList<Integer>> prerequisites){
        //Need to form adj graph from this prerequisites
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<N;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
        }
        int[] orderList= topoSort(N,adj);
        if(orderList.length == N){
            return true;
        }
        //List<Integer> orderList= TopologicalSort.topologicalSortBFS(N,adj);
//        if(orderList.size() == N){
//            return true;
//        }

        return false;
    }
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj){
        int visited[]=new int[V];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<V;i++){
            if(visited[i]==0){
                //not visited yet
                if(hasCycle(i,visited,stack,adj)){
                    return new int[0]; // Return empty array if cycle detected
                }
            }
        }
        int ans[]=new int[V];
        int i=0;
        while(!stack.isEmpty()){
            ans[i++]=stack.peek();
            stack.pop();
        }
        return ans;
    }

    static boolean  hasCycle(int node, int[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
        visited[node]=1;
        for(int neighbor:adj.get(node)){
            if(visited[neighbor]==1){
                // Back edge found - cycle detected(A->B->C->A)
                return true;
            }
            if(visited[neighbor]==0){
                //not visited yet
                if(hasCycle(neighbor,visited,stack,adj)){
                    return true;
                }
            }
        }
        visited[node]=2;//Node completely visited
        stack.push(node);
        return false;
    }

    public static void main(String args[]){
       // int N=2,m=2;
        int N=4,m=3;
        ArrayList<ArrayList<Integer>> prerequisites=new ArrayList<>();
        for(int i=0;i<m;i++){
            prerequisites.add(new ArrayList<>());
        }
        prerequisites.get(0).add(1);
        prerequisites.get(0).add(0);
        prerequisites.get(1).add(2);
        prerequisites.get(1).add(1);
        prerequisites.get(2).add(3);
        prerequisites.get(2).add(2);
//        prerequisites.get(0).add(1);
//        prerequisites.get(0).add(0);
//        prerequisites.get(1).add(0);
//        prerequisites.get(1).add(1);

        if(isCourseSchedulePossible(N,m,prerequisites)){
            System.out.println("Couse Schedule is possible");
        }
        else{
            System.out.println("Couse Schedule is not possible");
        }
    }
}
