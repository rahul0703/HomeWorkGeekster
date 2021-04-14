package graph;

public class edge implements Comparable<edge>{
    int src;
    int ngr;
    int len;

    public edge(int src, int ngr, int len){
        this.src = src;
        this.len = len;
        this.ngr = ngr;
    }

    public int compareTo(edge o){
        return this.len - o.len;
    }
}
