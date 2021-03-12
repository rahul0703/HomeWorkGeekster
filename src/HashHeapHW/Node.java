package HashHeapHW;

import java.util.ArrayList;

public class Node {
    Character data;
    ArrayList<Node> children = new ArrayList<>();

    public Node(Character data){
        this.data = data;
    }
}
