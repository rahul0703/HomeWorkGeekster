package HashHeapHW;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        int[] array = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int[] array1 = {8, 45, -21, -13, -15, 43, -32, -22, -7, -39, -22, -21, 26, -46, -7, 13, -37, -12, -44, -10, -46, -32};
//        int[] array = {1};
//        ArrayList<Integer> ans = util.maxiInAllSlidingWindow(array, 3);
//        for(int i : ans){
//            System.out.print(i + " ");
//        }
        System.out.println(util.missingNumber(array1, array1.length));
    }
}
