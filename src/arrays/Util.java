package arrays;
import java.util.*;
public class Util {

    //    ====================================================== Question 1===============================================
    public static long KContenation(int[] array, int k) {
        long sum1 = maximumSum(array);
        int array2[] = new int[array.length * 2];
        for (int i = 0; i < array.length * 2; i++) {
            if (i < array.length) {
                array2[i] = array[i];
            } else {
                array2[i] = array[i - array.length];
            }
        }
        long sum2 = maximumSum(array2);
        long sumall = getFullSum(array);
        long sumFinal = Integer.MIN_VALUE;
        if (sumall > 0) {
            sumFinal = sum1 + (k - 1) * sumall;
        } else {
            if (k == 1) {
                sumFinal = sum1;
            } else {
                sumFinal = sum2;
            }
        }
        return sumFinal;
    }


    //    ....................................................................
    public static long maximumSum(int[] array) {
        long max = 0;
        long temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp = temp + array[i];
            if (temp > max) {
                max = temp;
            }
            if (temp < 0) {
                temp = 0;
            }
        }
        return max;
    }

    // ....................................................................
    public static long getFullSum(int[] array) {
        long ans = 0;
        for (int i = 0; i < array.length; i++) {
            ans += array[i];
        }
        return ans;
    }


    //=================================================Question 2==========================================================
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        ArrayList<Integer> lst = new ArrayList<>();
        // Your code here
        int start = 0;
        int end = 0;
        int sum = arr[0];
        while (sum != s && end < n && start < n) {
            if (sum < s) {
                if (end < n - 1) {
                    end++;
                    sum = sum + arr[end];
                } else {
                    lst.add(-1);
                    return lst;
                }
            }
            if (sum > s) {
                sum = sum - arr[start];
                start++;
            }
        }
        if (start > end) {
            lst.add(-1);
            return lst;
        }
        lst.add(start + 1);
        lst.add(end + 1);
        return lst;
    }



    //=================================================Question 3==========================================================
    public static int equilibriumPoint(long arr[], int n) {

        // Your code here
        if(n == 1){
            return 1;
        }
        long sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum+= arr[i];
        }
        long sumStart = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(sumStart == sum - sumStart - arr[i]){
                return i+1;
            }
            sumStart+=arr[i];
        }
        return -1;
    }
//===================================================Question 4=======================================================
    public int maxSumIS(int arr[], int n) {
        //code here.
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = arr[i];
            int maxSoFar = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && arr1[j] + arr1[i] > maxSoFar) {
                    maxSoFar = arr1[j] + arr1[i];
                }
            }
            arr1[i] = maxSoFar;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        return max;
    }

//    ===============================================Question 5==========================================================================
}
