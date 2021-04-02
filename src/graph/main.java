package graph;

public class main {
    public static void main(String[] args){
        graphClass graph = new graphClass();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.removeVertex("K");

        graph.addEdge("A", "B", 20);
        graph.addEdge("B", "C", 21);
        graph.addEdge("C", "D", 23);
        graph.addEdge("D", "A", 15);
        graph.addEdge("E", "D", 10);
        graph.addEdge("E", "F", 19);
        graph.addEdge("F", "G", 12);
        graph.addEdge("E", "G", 13);

//        graph.display();
//        System.out.println(graph.countVertex() + " " + graph.countEdges());
//        graph.removeVertex("C");
//        graph.removeEdge("E", "F");
//        graph.display();
//        System.out.println(graph.countVertex() + " " + graph.countEdges());

        graph.hasPath1("A", "G");
    }
}
