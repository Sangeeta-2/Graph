import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
Here we will be using Disjoint Set to find the minimum spanning tree
TC->O(ElogE)
SC->O(V+E)
 */
public class KrushkalAlgorithm {

    public int minimumSpanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        int mst=0;
        ArrayList<Edge> list=new ArrayList<>();
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                int adjNode=adj.get(i).get(j).get(0);
                int wt=adj.get(i).get(j).get(1);
                int node=i;
                Edge tempEdge=new Edge(node,adjNode,wt);
                list.add(tempEdge);
            }
        }
        //Sorting the list of Edges based on weight
        Collections.sort(list);
        DisjointSet ds=new DisjointSet(V);
        for(int i=0;i<list.size();i++){
            int sourceNode=list.get(i).source;
            int destinationNode=list.get(i).destination;
            int wt=list.get(i).wt;
            if(ds.findUlpParent(sourceNode)!=ds.findUlpParent(destinationNode)){
                mst+=wt;
                ds.UnionBySize(sourceNode,destinationNode);
            }
        }
        return mst;
    }

    public static void main(String[] args){
        int V=5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.get(0).add(new ArrayList<>(Arrays.asList(3, 6)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(3, 8)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(4, 5)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(4, 7)));
        KrushkalAlgorithm obj=new KrushkalAlgorithm();
        int mst=obj.minimumSpanningTree(V,adj);
        System.out.println("The minimum spanning tree weight is "+mst);
    }
}

