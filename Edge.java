public class Edge implements Comparable<Edge>{
    int source,destination,wt;
    Edge(int src,int dest,int _wt){
        source=src;
        destination=dest;
        wt=_wt;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.wt-compareEdge.wt;
    }
}
