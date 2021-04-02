package Rivision;

import java.io.OutputStream;
import java.util.*;

public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        int[] array = {1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1,1, 1,  0, 0, 0, 0, 1};
//        int[] arr = {0, 1, 1, 0, 0, 0, 2, 1, 1, 2, 0, 0, 2, 2, 2, 1, 1, 0, 0, 1, 1, 0, 2, 1, 0, 1, 2, 1, 0,1, 2, 2,1, 2, 2, 2};
//        System.out.println(Arrays.maxSizeSubArrayEqual01(arr));
//        Arrays.sort012(arr);
//        for(int i : arr){
//            System.out.print(i + " ");
//        }
//        ArrayList<ArrayList<Integer>> list= new ArrayList<>();
//        for(int i = 0; i < 5; i++){
//            ArrayList<Integer> ele = new ArrayList<>();
//            ele.add(scan.nextInt());
//            ele.add(scan.nextInt());
//            list.add(ele);
//        }
//        Arrays.mergeIntervals(list);
//        for(ArrayList<Integer> ans : list){
//            System.out.print(ans.get(0) + " " + ans.get(1));
//            System.out.println();
//        }
        int[] array = {10, 22, 5, 75, 65, 80};
        System.out.println(Arrays.buyAndSellStocksAtMost2Times(array, 1));

    }
}
