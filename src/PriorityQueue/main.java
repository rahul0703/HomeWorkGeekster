package PriorityQueue;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
//        int[] array = {1, 10, 8, 9, 50, 11};
//        ArrayList<Integer> ans = util.LargestKElements(array, 4);
//        for(int i : ans){
//            System.out.println(i);
//        }

//        int[][] array = {{1,2,3,4,5,6,7,8}, {11,12,13,14,15,16,17,18}, {21,22,23,34,45,56,67,68}};
//        ArrayList<Integer> ans = util.mergeKSortedArraysSir(array);
//        for(int i : ans){
//            System.out.print(i + " ");
//        }

        int[] array = {1, 3,2,5,4,7,6,9,8,10,11,14,13,12};
        ArrayList<Integer> ans = util.kDistanceFar(array, 3);
        for(int i : ans){
            System.out.print(i + " ");
        }
    }
}
