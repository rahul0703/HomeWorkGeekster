package Rivision;
import java.util.*;
public class Arrays {
//    =============================================================================================================================
//    https://www.codechef.com/JAN18/problems/KCON/

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

//    ============================================================================================================================

//    https://www.geeksforgeeks.org/trapping-rain-water/
    static int trappingWater(int arr[], int n) {

        // Your code here
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int left = arr[0];
        int right = arr[n-1];
        for(int i = 0; i < n; i++){
            if(arr[i] > left){
                left = arr[i];
            }
            leftMax[i] = left;
            if(arr[n-1-i] > right){
                right = arr[n-1-i];
            }
            rightMax[n-1-i] = right;
        }
        int water = 0;
        for(int j = 0; j < n; j++){
            water += Math.min(rightMax[j], leftMax[j]) - arr[j];
        }
        return water;

    }

//    =================================================================================================================================

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int maxProfit = 0;
        int min = prices[0];
        int maxTillNow = 0;
        for(int i = 0; i < n; i++){
            if(maxTillNow - prices[i] > fee && maxTillNow > min + fee){
                maxProfit += maxTillNow - fee - min;
                maxTillNow = prices[i];
                min = maxTillNow;
            }

//          we don't need to update min as sell is always after the buy.
            maxTillNow = Math.max(maxTillNow, prices[i]);
//            when we update min we have to update maximum also as buy will always be before sell.
            if(prices[i] < min){
                maxTillNow = prices[i];
                min = prices[i];
            }
        }
        if(maxTillNow > min + fee){
            maxProfit += maxTillNow - fee - min;
        }
        return maxProfit;
    }

//    Pepcoding method
    public static int buySellStockWithFee(int[] array, int fee){
        int oldBoughtState = -array[0];
        int oldSellState = 0;
        for(int i = 1; i < array.length; i++){
            int newBoughtState = (oldSellState - array[i] > oldBoughtState) ? oldBoughtState - array[i] : oldBoughtState;
            int newSellState = (oldBoughtState + array[i] - fee > oldSellState) ? oldBoughtState + array[i] - fee : oldSellState;

            oldSellState = newSellState;
            oldBoughtState = newBoughtState;
        }
        return oldSellState;
    }

//    ==============================================================================================================================


//    ==============================================================================================================================
//    https://www.geeksforgeeks.org/find-k-pairs-smallest-sums-two-arrays/
    static void kSmallestPair(int arr1[], int n1, int arr2[], int n2, int k) {
        if (k > n1*n2) {
            System.out.print("k pairs don't exist");
            return ;
        }
        int index2[] = new int[n1];
        while (k > 0) {
            int min_sum = Integer.MAX_VALUE;
            int min_index = 0;
            for (int i1 = 0; i1 < n1; i1++) {
                if (index2[i1] < n2 && arr1[i1] + arr2[index2[i1]] < min_sum) {
                    min_index = i1;
                    min_sum = arr1[i1] + arr2[index2[i1]];
                }
            }
            System.out.print("(" + arr1[min_index] + ", " +
                    arr2[index2[min_index]]+ ") ");

            index2[min_index]++;
            k--;
        }
    }

//    ======================================================================================================================
//    https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/

    int search(int A[], int l, int h, int key){
        // Complete this function
        int low = l;
        int high = h;
        if(A[l] < A[h]){
            int index = binarySearch(A, l, h, key);
            return index;
        }
        int end = 0;
        while(l <= high){
            int mid = low + (high - low)/2;
            if(mid != l && mid != h && A[mid] > A[mid+1] && A[mid] >= A[mid-1]){
                end = mid;
                break;
//          edge case................................................
            }else if(A[mid] > A[mid+1]){
                end = mid;
                break;
            }else if(A[mid] > A[l]){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }
        if(key >= A[l]){
            return binarySearch(A, l, end, key);
        }
        return binarySearch(A, end+1, h, key);

    }

    int binarySearch(int[] array, int low, int high, int key){
        while(low <= high){
            int mid = low + (high - low)/2;
            if(array[mid] == key){
                return mid;
            }else if(array[mid] > key){
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

//    ==============================================================================================================================
//    https://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/
//    maximum of summation(i*A[i]) among all rotation
    public static int maxSum(int[] arr, int n){
        int sumArray = 0;
        int initialSum = 0;
        int j = 0;
        for(int i : arr){
            sumArray += i;
            initialSum += j*i;
            j++;
        }
        int maxSum = initialSum;
        for(int k = arr.length; k >= 1; k--){
            int newSum = initialSum + sumArray - arr.length*arr[k-1];
            maxSum = Math.max(maxSum, newSum);
            initialSum = newSum;
        }
        return maxSum;
    }

//    ==========================================================================================================================
//    https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
    public static int maxSizeSubArrayEqual01(int[] array){
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int value = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0){
                value--;
            }else{
                value++;
            }
            if(value == 0){
                maxLength = Math.max(maxLength, i+1);
            }
            if(map.containsKey(value)){
                maxLength = Math.max(maxLength, i - map.get(value));
            }else{
                map.put(value, i);
            }
        }
        return maxLength;
    }

//    ==============================================================================================================================
//    https://www.geeksforgeeks.org/maximum-product-subarray/
    public static int maximumProductSubarray(int[] array){
        int maxProduct = 0;
        int positive = 1;
        int negative = 1;
        for(int i : array){
            if(i == 0){
                positive = 1;
                negative = 1;
            }else if(i > 0){
                positive = positive * i;
                negative = negative * i;
            }else{
                int temp = positive;
                positive = Math.max(1, negative*i);
                negative = Math.min(i, temp*i);
            }
            maxProduct = Math.max(maxProduct, positive);
        }
        return maxProduct;
    }

//    =============================================================================================================================
//    https://www.geeksforgeeks.org/counting-inversions/
    public static int countInversionArray(int[] array){
        mergeSort(array, 0, array.length-1);
        return inversion;
    }
    private static int inversion;
    private static void mergeSort(int[] array, int start, int end){
        if(start >= end){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
        return;
    }
    private static void merge(int[] array, int start, int mid, int end){
        int[] array1 = new int[mid-start+1];
        int[] array2 = new int[end-mid];
        for(int i = start; i <= mid; i++){
            array1[i-start] = array[i];
        }
        for(int j = mid+1; j <= end; j++){
            array2[j-mid-1] = array[j];
        }
        int i = 0;
        int j = 0;
//        note to remember is k will start from start not 0 as we are just replacing elements from start to end not from start.
        int k = start;
        while(i < array1.length && j < array2.length){
            if(array1[i] <= array2[j]){
                array[k] = array1[i];
                i++;
            }else{
                array[k] = array2[j];
                inversion += array1.length - i;
                j++;
            }
            k++;
        }
        while(i <= array1.length-1){
            array[k] = array1[i];
            i++;
            k++;
        }
        while(j <= array2.length-1){
            array[k] = array2[j];
            j++;
            k++;

        }
        return;
    }


//    ===============================================================================================================================
//segregate 0, 1, 2
    public static void sort012(int a[]) {
        // code here
        int i = 0;
        int j = a.length-1;
        int k = 0;
        while(k < a.length){
            if(a[k] == 0){
                while(a[i] == 0){
                    i++;
                }
                if(i < k){
                    int temp = a[i];
                    a[i] = 0;
                    a[k] = temp;
//              Don't forget this k-- below as we have swap k and i the element at k may be a different that also need to get replace
                    k--;
                }
            }else if(a[k] == 2){
                while(a[j] == 2){
                    j--;
                }
                if(j > k){
                    int temp = a[j];
                    a[j] = 2;
                    a[k] = temp;
//              Same here also.
                    k--;
                }
            }
            k++;
        }
    }

//    ===========================================================================================================================
//    https://www.geeksforgeeks.org/merging-intervals/
    public static void mergeIntervals(ArrayList<ArrayList<Integer>> list){
        Collections.sort(list, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
        int i = 1;
        while(i < list.size()){
            ArrayList<Integer> curr = list.get(i);
            ArrayList<Integer> prev = list.get(i-1);
            if(curr.get(0) > prev.get(1)){
                i++;
            }else if(curr.get(1) > prev.get(1)){
                prev.set(1, curr.get(1));
                list.remove(i);
            }else{
                list.remove(i);
            }
        }
        return;
    }

//    ==============================================================================================================================
//    buy and sell stocks atmost 2 times..........................
    public static int buyAndSellStocksAtMost2Times(int[] array, int k){
        int[][] dp = new int[array.length+1][array.length+1];
        int[][] counntArray = new int[array.length+1][array.length+1];
        for(int i = 0; i <= array.length; i++){
            for(int j = 0; j <= array.length; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else if(counntArray[i-1][i] < k && array[j-1] - array[i-1] > 0){
                    int profit = array[j-1] - array[i-1] + dp[i-1][i];
                    int com = Math.max(dp[i-1][j], dp[i][j-1]);
                    if(profit > com){
                        dp[i][j] = profit;
                        counntArray[i][j] = counntArray[i-1][i] + 1;
                    }else{
                        counntArray[i][j] = (dp[i-1][j] > dp[i][j-1]) ? counntArray[i-1][j] : counntArray[i][j-1];
                        dp[i][j] = com;
                    }
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    counntArray[i][j] = (dp[i-1][j] > dp[i][j-1]) ? counntArray[i-1][j] : counntArray[i][j-1];
                }
            }
        }
        for(int[] arr : dp){
            for(int val : arr){
                System.out.print(val + " ");
            }
            System.out.println();
        }
        return dp[array.length][array.length];
    }
}
