package RecursionHW;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        int[][] answer = client.KnightTourProblem(8);
//        for(int[] i : answer){
//            for(int j : i){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }

        int[][] question = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
        int[][] answer = client.rateInMaze(question);
        for(int[] i : answer){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
