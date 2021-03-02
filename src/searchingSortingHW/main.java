package searchingSortingHW;
import java.lang.reflect.Array;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = scan.nextInt();
        }
//        int x = scan.nextInt();
//        int k = scan.nextInt();
//        System.out.println(util.elementDifferByK(array, x, k));
//        System.out.println(util.repeatingMissing(array, n)[0]);
//        System.out.println(util.repeatingMissing(array, n)[1]);
//        ArrayList<ArrayList<Integer>> ans  = util.AllpairOf4WithGivenSum(array, n, x);
//        for(ArrayList<Integer> ans1 : ans){
//            System.out.println(ans1.get(0) + " "+ ans1.get(1) + " " + ans1.get(2) + " " + ans1.get(3));
//        }
//        int[] array1 = new int[k];
//        for(int i = 0; i < k; i++){
//            array1[i] = scan.nextInt();
//        }
//        util.merge2SortedArraysO1space(array, array1, n, k);
//        System.out.println(util.median2SortedArrays(array1, array));
//        System.out.println(util.countTriplets(array, n, k));
        util.radixSort(array, n);
        for(int i : array){
            System.out.print(i + " ");
        }
    }
}
