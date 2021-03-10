package PriorityQueueClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class util {

//    =============================================Question 1======================================================
    public static ArrayList<Integer> LargestKElements(int[] array, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < array.length-k; i++){
            queue.add(array[i]);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int j = array.length -k; j < array.length; j++){
            if(array[j] > queue.peek()){
                ans.add(array[j]);
            }else{
                ans.add(queue.poll());
            }
        }
        return ans;
    }

//    ==========================================Question 2=========================================================
    public static int[] mergerKSortedList(int[][] array){
        int x = array.length;
        int start = 0;
        int end = x-1;
        int[] array1 = mergerArrays(array, start, end);
        return array1;
    }
    private static int[] mergerArrays(int[][] array, int start, int end){
        if(start >= end){
            return array[start];
        }
        int mid = start + (end - start)/2;
        int[] array1 = mergerArrays(array, start, mid);
        int[] array2 = mergerArrays(array,mid+1, end);
        int[] array3 = new int[array1.length + array2.length];
        merge(array3, array1, array2);
        return array3;
    }

    private static void merge(int[] finalArray, int[] array1, int[] array2){
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < array1.length && j < array2.length){
            if(array1[i] <= array2[j]){
                finalArray[k] = array1[i];
                i++;
                k++;
            }else{
                finalArray[k] = array2[j];
                j++;
                k++;
            }
        }
        if(i >= array1.length && j < array2.length){
            while(j < array2.length){
                finalArray[k] = array2[j];
                k++;
                j++;
            }
        }else{
            while(i < array1.length) {
                finalArray[k] = array1[i];
                k++;
                i++;
            }
        }
    }

//    With Priority queue...............................................

    public static ArrayList<Integer> mergeKSortedArraysSir(int[][] arrays){
        PriorityQueue<element> queue = new PriorityQueue<>();
        for(int i = 0; i < arrays.length; i++){
            element ele = new element();
            ele.listno = i;
            ele.index = 0;
            ele.value = arrays[ele.listno][ele.index];
            queue.add(ele);
        }
        ArrayList<Integer> ans = new ArrayList<>();
         while (!queue.isEmpty()){
             element popEle = queue.poll();
             ans.add(popEle.value);
             int listNo = popEle.listno;
             int index = popEle.index + 1;
             if(index < arrays[listNo].length){
                 element pushEle = new element();
                 pushEle.value = arrays[listNo][index];
                 pushEle.index = index;
                 pushEle.listno = listNo;
                 queue.add(pushEle);
             }
         }
         return ans;
    }

//    sort nearly sorted list with k dis far.......................
    public static ArrayList<Integer> kDistanceFar(int[] array, int k){
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < array.length; i++){
            if(i < k +1){
                queue.add(array[i]);
            }else{
                ans.add(queue.poll());
                queue.add(array[i]);
            }
        }
        while(!queue.isEmpty()){
            ans.add(queue.poll());
        }
        return ans;
    }

}


