package graphHW;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
//        graph gf = new graph();
//        gf.addEgde(0, 1, 10);
//        gf.addEgde(1, 2, 10);
//        gf.addEgde(2, 3, 10);
//        gf.addEgde(0, 3, 40);
//        gf.addEgde(3, 4, 2);
//        gf.addEgde(4, 5, 3);
//        gf.addEgde(5, 6, 9);
//        gf.addEgde(4, 6, 8);


//        System.out.println(client.shortestPath(0, 6, gf));
//        ArrayList<ArrayList<Integer>> ans = client.getConnctedComponents(gf);
//        for(ArrayList<Integer> subAns : ans){
//            for(int val : subAns){
//                System.out.print(val + " ");
//            }
//            System.out.println();
//        }

        int[][] matrix = {
                {1, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 1}

        };
//        System.out.println(client.numberOfIslands(matrix));
//        client.hamiltonPathAndCycle(0, gf);
//        client.knightTour(0, 0, matrix);
//        client.dijkstraAlgorithm(0, gf);
//        client.primsAlgorithm(0, gf);

        graph gf1 = new graph();
        gf1.addEgde(0, 1, -10);
        gf1.addEgde(3, 2, 10);
        gf1.addEgde(2, 1, -10);
        gf1.addEgde(0, 3, 40);
        gf1.addEgde(3, 4, -2);
        gf1.addEgde(4, 5, 3);
        gf1.addEgde(6, 5, -9);
        gf1.addEgde(4, 6, 8);

        client.topologicalSort(matrix);
    }
}
