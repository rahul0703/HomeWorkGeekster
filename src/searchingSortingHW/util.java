package searchingSortingHW;
import java.lang.reflect.Array;
import java.util.*;
class util {

//  Universal functions that will be used in many function
    public static int binarySearch(int[] array, int n, int x){
        int max = n-1;
        int min = 0;
        int mid = min + (max-min)/2;
        while(min <= max){
            mid = min + (max - min)/2;
            if(array[mid] == x){
                return mid;
            }else if(array[mid] > x){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return -1;
    }

    public static int binarySearch(ArrayList<Integer> array, int x){
        int n = array.size();
        int max = n-1;
        int min = 0;
        int mid = min + (max-min)/2;
        while(min <= max){
            mid = min + (max - min)/2;
            if(array.get(mid) == x){
                return mid;
            }else if(array.get(mid) > x){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return -1;
    }


    private static  class elements implements Comparable<elements>{
        public int start;
        public int end;
        public int sum;
        public elements(int start, int end){
            this.start = start;
            this.end = end;
            this.sum = start+end;
        }

        public int compareTo(elements a){
            return this.sum - a.sum;
        }
    }

//    =================================================Question 1====================================================================
    public static int majorityElement(int[] array, int n){
        int count = 1;
        int number = array[0];
        for(int i = 1; i < n; i++){
            if(array[i] == number){
                count++;
            }else if(count == 0){
                number = array[i];
                count++;
            }else{
                count--;
            }
        }
        count = 0;
        for(int i = 0; i < n; i++){
            if(array[i] == number){
                count++;
            }
        }
        if(count > n/2){
            return number;
        }else{
            return Integer.MIN_VALUE;
        }
    }

//    =========================================Majority 2 N/3 greatest===============================================================
    public static int majority2(int[] array, int n){
        int count1 = 0;
        int count2 = 0;
        int number1 = 0;
        int number2 = 0;
        for(int i = 0; i < n; i++){
            if(array[i] == number1){
                count1++;
            }else if(array[i] == number2){
                count2++;
            }else if(count2 == 0){
                number2 = array[i];
                count2++;
            }else if(count1 == 0){
                number1 = array[i];
                count1++;
            }else{
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int i = 0; i < n; i++){
            if(array[i] == number1){
                count1++;
            }
            if(array[i] == number2){
                count2++;
            }
        }
        if(count1 > n/3){
            return number1;
        }else if(count2 > n/3){
            return number2;
        }
        return Integer.MIN_VALUE;
    }

//    =========================================================Question 2========================================================
//    Searching in an array where adjacent differ by at most k
    public static int elementDifferByK(int[] array, int x, int k){
        int i = 0;
        int a = array[i];
        while(i < array.length){
            if(array[i] == x){
                return array[i];
            }else{
                i += Math.max(1, (x - a)/k);
            }
        }
        return array[i];
    }


//    ======================================================Question 3============================================================
//    Find the repeating and the missing | Added 3 new methods
    public static int[] repeatingMissing(int[] array, int n){
        int[] ans = new int[2];
        int repeting = Integer.MAX_VALUE;
        int missing = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int a = Math.abs(array[i]);
            if(array[a-1] < 0){
                repeting = a;
            }else{
                array[a-1] = -1 * array[a-1];
            }
        }
        for(int i = 0; i < n; i++){
            if(array[i] > 0){
                missing = i+1;
            }
        }
        ans[0] = repeting;
        ans[1] = missing;
        return  ans;
    }


//    ========================================================Question 4============================================================
//    Ceiling in a sorted array
    public static int[] floorAndCeiling(int[] array, int n, int x){
        int index = binarySearch(array, n, x);
        int floor = Integer.MIN_VALUE;
        int ceiling = Integer.MAX_VALUE;
        int i = index;
        while(array[i] <= x){
            i++;
        }
        if(i < n){
            ceiling = array[i];
        }
        int j = index;
        while(array[j] >= index){
            j--;
        }
        if(j >= 0){
            floor = array[j];
        }
        int[] ans = new int[2];
        ans[0] = floor;
        ans[1] = ceiling;
        return ans;
    }


//======================================================Question 5===========================================================
//Find a pair with given difference let say k....................
    public static int[] pairWithGivenDiff(int[] array, int n, int x){
        Arrays.sort(array);
        int[] ans = new int[2];
        for(int i = 0; i < n-1; i++){
            int a = array[i];
            int index = binarySearch(array, n, a+x);
            if(index != -1){
                ans[0] = array[i];
                ans[1] = array[index];
                return  ans;
            }
        }
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }

//    ================================================Question 6=============================================================
//  Find 4 number that sum to the given value..................................
    public static int[] pairOf4WithGivenSum(int[] array, int n, int sum){
        int[] ans = new int[4];
        ArrayList<elements> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int a = array[i];
                int b = array[j];
                elements ele = new elements(a, b);
                list.add(ele);
            }
        }
        Collections.sort(list);
        int n2 = list.size();
        int j = 0;
        int k = n2-1;
        while(j < k){
            elements one = list.get(j);
            elements two = list.get(k);
            if(one.sum + two.sum == sum){
                if(one.start != two.start && one.start != two.end && one.end != two.start && one.end != two.end){
                    ans[0] = one.start;
                    ans[1] = one.end;
                    ans[2] = two.start;
                    ans[3] = two.end;
                    return ans;
                }
                j++;
            }else if(one.sum + two.sum < sum){
                j++;
            }else{
                k--;
            }
        }
        return  ans;
    }

//    ================================================Question 6 extended================================================================
    public static ArrayList<ArrayList<Integer>> AllpairOf4WithGivenSum(int[] array, int n, int sum) {
        ArrayList<ArrayList<Integer>>  ans = new ArrayList<ArrayList<Integer>>();
        ArrayList<elements> list = new ArrayList<elements>();
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = array[i];
                int b = array[j];
                elements ele = new elements(a, b);
                list.add(ele);
            }
        }
        Collections.sort(list);
        int n2 = list.size();
        int j = 0;
        int k = n2 - 1;
        while (j < k) {
            elements one = list.get(j);
            elements two = list.get(k);
            if (one.sum + two.sum == sum) {
                for(int i = j; i <= k; i++){
                    for (int l = i+1; l <= k; l++){
                        elements start = list.get(i);
                        elements end = list.get(l);
                        if(start.sum + end.sum == sum){
                            if(start.start != end.start && start.start != end.end && start.end != end.start && start.end != end.end){
                                ArrayList<Integer> ans1 = new ArrayList<>();
                                ans1.add(start.start);
                                ans1.add(start.end);
                                ans1.add(end.start);
                                ans1.add(end.end);
                                Collections.sort(ans1);
                                if(!set.contains(ans1)){
                                    set.add(ans1);
                                    ans.add(ans1);
                                }
                            }
                        }
                    }
                }
                return ans;
            } else if (one.sum + two.sum < sum) {
                j++;
            } else {
                k--;
            }
        }
        return ans;
    }

//    =====================================================Question 7================================================================
//    Median of 2 sorted array of different sizes
    public static int median2SortedArrays(int[] array1, int[] array2){
        int x = array1.length;
        int y = array2.length;
        if(x > y){
            return  median2SortedArrays(array2, array1);
        }
        int low = 0;
        int high = x;
        while(low <= high){
            int partition1 = low + (high-low)/2;
            int partition2 = (x+y+1)/2 - partition1;
            int maxPartition1 = (partition1 == 0) ? Integer.MIN_VALUE : array1[partition1-1];
            int minPartition1 = (partition1 == x) ? Integer.MAX_VALUE: array1[partition1];
            int minPartition2 = (partition2 == y) ? Integer.MAX_VALUE: array2[partition2];
            int maxPartition2 = (partition2 == 0) ? Integer.MIN_VALUE: array2[partition2-1];

            if(maxPartition2 <= minPartition1 && maxPartition1 <= minPartition2){
                if(!((x + y)% 2 == 0)){
                    return Math.max(maxPartition1, maxPartition2);
                }else{
                    return (Math.max(maxPartition1, maxPartition2) + Math.min(minPartition1, minPartition2)) /2;
                }
            }else if(maxPartition1 > minPartition2 || maxPartition2 > minPartition1){
                high = partition1 -1;
            }else{
                low = partition1 + 1;
            }
        }
        throw new IllegalArgumentException();
    }

//    =====================================================Question 8=================================================================
//  (imp) maximum sum such that no 2 number are adjacent.....................................
    public static int FindMaxSum(int arr[], int n){
        // Your code here
        if(n == 1){
            return arr[0];
        }else if(n == 2){
            return Math.max(arr[0], arr[1]);
        }
        int maxPrev = arr[0];
        int maxCurr = arr[1];
        for(int i = 2; i < n; i++){
            int maxAll = Math.max(maxPrev, maxCurr);
            maxCurr = maxPrev + arr[i];
            maxPrev = maxAll;
        }
        return Math.max(maxPrev, maxCurr);
    }

//   ===========================================================Question 9========================================================
//    Find common element from 3 sorted array;
    public static int commonIn3SortedArrays(int[] array1, int[] array2, int[] array3){
        int x = array1.length;
        int y = array2.length;
        int z = array3.length;

        if(y < x && y < z){
            return commonIn3SortedArrays(array2, array1, array3);
        }else if(z < x && z < y){
            return commonIn3SortedArrays(array3, array1, array2);
        }
        for(int i = 0; i < x; i++){
            int a = array1[i];
            int ans1 = binarySearch(array2, y, a);
            if(ans1 != -1){
                int ans2 = binarySearch(array3, z, a);
                if(ans2 != -1){
                    return a;
                }
            }
        }
        throw new NoSuchElementException();
    }


//    ==================================================Question 9 ===================================================================
//    return the common elements from 2 list...............
    public static ArrayList<Integer> common_element(ArrayList<Integer>v1, ArrayList<Integer>v2) {
        //Your code here
        int x = v1.size();
        int x2 = v2.size();
        if(x2 > x){
            return common_element(v2, v1);
        }
        HashSet<Integer> set = new HashSet<>();
        Collections.sort(v2);
        ArrayList<Integer> ansFinal = new ArrayList<>();
        for(int i = 0; i < x; i++){
            int a = v1.get(i);
            int ans = binarySearch(v2, a);
            if(ans != -1){
                ansFinal.add(v2.get(ans));
                v2.remove(ans);
            }
        }
        Collections.sort(ansFinal);
        return ansFinal;

    }

//
}