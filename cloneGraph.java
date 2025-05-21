import java.util.HashMap;
import java.util.HashSet;

/* Given a reference of a node in a connected undirected graph, return the deep copy(clone) of the graph
TC->O(V+E)
SC->O(V)
*/
public class cloneGraph {
public Node clonedGraph(Node node){
    if(node == null) return null;
    HashMap<Node,Node> map=new HashMap<>();//mapping old node with the new node
    return cloneUtil(node,map);
}

    private Node cloneUtil(Node node, HashMap<Node,Node> map) {
    Node newNode=new Node(node.val);
    map.put(node,newNode);
    //we need to clone the node's neighbors as well
        for(Node neighbor: node.neighbors){
            if(!map.containsKey(neighbor)){
                //it is not cloned yet, so we need to clone that
                newNode.neighbors.add(cloneUtil(neighbor,map));
            }
            else{
                newNode.neighbors.add(map.get(neighbor));
            }
        }
        return newNode;
    }

    public static void main(String[] args){
    cloneGraph cloner = new cloneGraph();
    Node node1=new Node(1);
    Node node2=new Node(2);
    Node node3=new Node(3);
    Node node4=new Node(4);
    //forming the graph
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node3);
        node4.neighbors.add(node1);

        System.out.println("Original Graph");
        cloner.printGraph(node1,new HashSet<>());
        Node clonedNode =cloner.clonedGraph(node1);
        System.out.println("Cloned Graph");
        cloner.printGraph(clonedNode,new HashSet<>());
    }

    private void printGraph(Node node, HashSet<Node> visited) {
    if(node == null || visited.contains(node)){
        return;
    }
    visited.add(node);
    System.out.println("Node "+node.val+" -> Neighbors: ");
    for(Node neighbor:node.neighbors){
        System.out.print(neighbor.val +" , ");
    }
    System.out.println();
    for(Node neighbor:node.neighbors){
        printGraph(neighbor,visited);
    }
    }
}
