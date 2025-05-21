import java.util.ArrayList;

/*
Disjoint Set Datastructure-> It is used in dynamic graph, it can perform operations in constant time.
It helps us to determine whether two nodes belong to the same component or not.
2 major methods:-
1. Find Parent
2. Union -> This can be done by 2 methods-(i)union by rank and (ii) union by size
TC->O(4 Alpha) ~ O(constant)
 */
public class DisjointSet {
    ArrayList<Integer> parent=new ArrayList<>();
    ArrayList<Integer> rank=new ArrayList<>();
    ArrayList<Integer> size=new ArrayList<>();
    DisjointSet(int n){
        for(int i=0;i<=n;i++){
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }

    int findUlpParent(int node){
        if(node == parent.get(node)){
            return node;
        }
        int ulp=findUlpParent(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }

    void unionByRank(int u,int v){
        int ulp_u=findUlpParent(u);
        int ulp_v=findUlpParent(v);
        if(ulp_u==ulp_v){
            return;
        }
        if(rank.get(ulp_u)<rank.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
        }
        else if(rank.get(ulp_v)<rank.get(ulp_u)){
            parent.set(ulp_v,ulp_u);
        }
        else{
            parent.set(ulp_v,ulp_u);
            rank.set(ulp_u,rank.get(ulp_u)+1);
        }
    }
    void UnionBySize(int u,int v){
        int ulp_u=findUlpParent(u);
        int ulp_v=findUlpParent(v);
        if(ulp_u==ulp_v){
            return;
        }
        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_u)+size.get(ulp_v));
        }
        else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
        }
    }

    public static void main(String[] args){
        DisjointSet disjointSet=new DisjointSet(7);
        //Using Union by Rank
//        disjointSet.unionByRank(1,2);
//        disjointSet.unionByRank(2,3);
//        disjointSet.unionByRank(4,5);
//        disjointSet.unionByRank(6,7);
//        disjointSet.unionByRank(5,6);
//        //Checking if 3 and 7 belongs to same component or not
//        if(disjointSet.findUlpParent(3)==disjointSet.findUlpParent(7)){
//            System.out.println("3 and 7 belong to the same component");
//        }
//        else{
//            System.out.println("3 and 7 does not belong to the same component");
//        }
//        disjointSet.unionByRank(3,7);
//        //Checking if 3 and 7 belongs to same component or not
//        if(disjointSet.findUlpParent(3)==disjointSet.findUlpParent(7)){
//            System.out.println("3 and 7 belong to the same component");
//        }
//        else{
//            System.out.println("3 and 7 does not belong to the same component");
//        }
        //Using Union By size
        disjointSet.UnionBySize(1,2);
        disjointSet.UnionBySize(2,3);
        disjointSet.UnionBySize(4,5);
        disjointSet.UnionBySize(6,7);
        disjointSet.UnionBySize(5,6);
        //Checking if 3 and 7 belongs to same component or not
        if(disjointSet.findUlpParent(3)==disjointSet.findUlpParent(7)){
            System.out.println("3 and 7 belong to the same component");
        }
        else{
            System.out.println("3 and 7 does not belong to the same component");
        }
        disjointSet.UnionBySize(3,7);
        //Checking if 3 and 7 belongs to same component or not
        if(disjointSet.findUlpParent(3)==disjointSet.findUlpParent(7)){
            System.out.println("3 and 7 belong to the same component");
        }
        else{
            System.out.println("3 and 7 does not belong to the same component");
        }
    }
}
