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

//

    public static void shortestPalindromicString(int N, int k, Character[] array) {
        ArrayList<String> sample = new ArrayList<>();
        ArrayList<String> answer = shortestPalindrome("",array,0, k);
        Collections.sort(answer);
        System.out.println(answer.get(0));
        return;
    }
    private static ArrayList<String> shortestPalindrome(String ans, Character[] array, int current, int k){
        if(ans.length() == k && isPalindrome(ans)){
            ArrayList<String> answermain = new ArrayList<>();
            answermain.add(ans);
            return answermain;
        }
        if(ans.length() > k || current >= array.length){
            ArrayList<String> answermain = new ArrayList<>();
            return answermain;
        }
        ArrayList<String> finalAnswer = new ArrayList<>();
        for(int l = 0; l < ans.length(); l++){
            String que = ans.substring(0, l) + array[current] + ans.substring(l);
            ArrayList<String> quelist = shortestPalindrome(que, array, current+1, k);
            for(String st : quelist){
                finalAnswer.add(st);
            }
        }

        String que1 = ans;
        ArrayList<String> quelist1 = shortestPalindrome(que1, array, current+1, k);
        for(String st : quelist1){
            finalAnswer.add(st);
        }
        String que2 = array[current] + "";
        ArrayList<String> quelist2 = shortestPalindrome(que2, array, current+1, k);
        for(String st : quelist2){
            finalAnswer.add(st);
        }
        return finalAnswer;
    }

    private static boolean isPalindrome(String ans){
        for(int i = 0; i < ans.length(); i++){
            if(ans.charAt(i) != ans.charAt(ans.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }
}
