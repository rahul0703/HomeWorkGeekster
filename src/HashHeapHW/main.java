package HashHeapHW;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        int[] array = {1, 21, 3, 12, 9, 8, 20};
        int[] array1 = {8, 45, -21, -13, -15, 43, -32, -22, -7, -39, -22, -21, 26, -46, -7, 13, -37, -12, -44, -10, -46, -32};
//        int[] array = {1};
//        ArrayList<Integer> ans = util.maxiInAllSlidingWindow(array, 3);
//        for(int i : ans){
//            System.out.print(i + " ");
//        }
//        System.out.println(util.missingNumber(array1, array1.length));
//        Character[] arrayChar = {'a', 'a', 'b', 'c', 'c', 'b', 'c'};
//        util.shortestPalindromicString(5, 5, arrayChar);
//        int[] arrayanswer = util.kthLargest(3, array, 9);
//        for(int i : arrayanswer){
//            System.out.print(i + " ");
//        }
//        int answer = util.KthSmallestAfterRemoving(array, 10);
//        System.out.println(answer);
        System.out.println(util.countInversion(array));
        for(int i : array){
            System.out.print(i + " ");
        }

    }
}
