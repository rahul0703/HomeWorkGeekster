package HashHeapHW;
import java.util.*;
public class util {
//    print k largest elements..................................
//    next greater element......................................
//    print Kth smallest element................................
    public static int PrintKthSmallestElement(int[] arr, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < arr.length; i++){
            if(i < k){
                queue.add(arr[i]);
            }else{
                if(arr[i] < queue.peek()){
                    queue.poll();
                    queue.add(arr[i]);
                }
            }
        }
        return queue.poll();
    }

//(IMP) Maximum element of the sliding window of size k
    public static ArrayList<Integer> maxiInAllSlidingWindow(int[] array, int k) {
//        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
//        ArrayList<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            if (i < k - 1) {
//                queue.add(array[i]);
//            } else {
//                queue.add(array[i]);
//                ans.add(queue.peek());
//                queue.remove(array[i - k + 1]);
//            }
//        }
//        return ans;
        Deque<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                if (queue.isEmpty()) {
                    queue.add(i);
                } else {
                    if (array[i] > array[queue.peekLast()]) {
                        while (!queue.isEmpty() && array[queue.peekLast()] < array[i]) {
                            queue.pollLast();
                        }
                        queue.addLast(i);
                    } else {
                        queue.addLast(i);
                    }
                }
            } else {
                ans.add(array[queue.peekFirst()]);
                if (queue.peekFirst() <= i - k) {
                    queue.pollFirst();
                }
                if (!queue.isEmpty() && array[i] > array[queue.peekLast()]) {
                    while (!queue.isEmpty() && array[queue.peekLast()] < array[i]) {
                        queue.pollLast();
                    }
                    queue.addLast(i);
//                    System.out.println(i + " " + array[i]);
                } else {
                    queue.addLast(i);
                }
            }
        }
        ans.add(array[queue.peekFirst()]);
//        while(!queue.isEmpty()){
//            System.out.println(queue.pollFirst());
//        }
        return ans;
    }

//    Smallest Positive missing number
    static int missingNumber(int arr[], int size) {
        int shift = segregate(arr, size);
        int arr2[] = new int[size - shift];
        int j = 0;
        for (int i = shift; i < size; i++) {
            arr2[j] = arr[i];
            j++;
        }
        return findMissingPositive(arr2, j);
    }

    public static int segregate(int arr[], int size) {
        int j = 0, i;
        for (i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return j;
    }

    public static int findMissingPositive(int arr[], int size)
    {
        int i;
        for (i = 0; i < size; i++) {
            int x = Math.abs(arr[i]);
            if (x - 1 < size && arr[x - 1] > 0)
                arr[x - 1] = -arr[x - 1];
        }

        for (i = 0; i < size; i++)
            if (arr[i] > 0)
                return i + 1;
        return size + 1;
    }


//    Maximum repeating number
    public static int maxiRepeatNum(int[] array){
        int n = array.length;
        int max = Integer.MIN_VALUE;
        for(int i : array){
            if(i > max){
                max = i;
            }
        }
        max++;
        for(int i = 0; i < n; i++){
            int x = array[i]%max;
            array[x] += max;
        }
        int maxRec = 1;
        int ans = -1;
        for(int i = 0; i < n; i++){
            int c = array[i] / max;
            if(c > maxRec){
                maxRec = c;
                ans = i;
            }
        }
        return ans;
    }
}
