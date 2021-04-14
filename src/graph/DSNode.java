package graph;

public class DSNode {
    int data;
    DSNode parent;
    int rank;

    public DSNode(int data){
        this.data = data;
        this.parent = new DSNode(data);
        this.rank = 0;
    }
}
