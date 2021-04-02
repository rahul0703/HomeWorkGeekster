package DynamicProgrammingHW;
import java.util.*;

public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int[] array = {34, 50};
//        int[][] array1 = {{0, 1, 1, 0, 1}, {1, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};

//        System.out.println(util.maximumSizeSquareMatrix(array1));
//        System.out.println(util.uglyNumber(15));
        System.out.println(util.optimalBST(array, 2));
    }
}
