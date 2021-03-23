package HashHeapHW;
import java.sql.Struct;
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


//    ======================================================================================================================
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

//    ====================================================================================================================
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

//=======================================================================================================================
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

//=========================================================================================================================

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

//    ================================================================================================================
//    (IMP) Kth largest element in the stream of array..........................................................
    public static int[] kthLargest(int k, int[] arr, int n) {
        int[] answer = new int[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            if(i < k-1){
                queue.add(arr[i]);
                answer[i] = -1;
            }else{
                if(queue.size() < k){
                    queue.add(arr[i]);
                    answer[i] = queue.peek();
                }else{
                    if(arr[i] < queue.peek()){
                        answer[i] = queue.peek();
                    }else{
                        queue.poll();
                        queue.add(arr[i]);
                        answer[i] = queue.peek();
                    }
                }
            }
        }
        return answer;
    }

//    ==============================================================================================================
//    K-th smallest element after removing some integers from natural numbers
    public static int KthSmallestAfterRemoving(int[] array, int k){
        Arrays.sort(array);
        int i = 0;
        while(array[i] <= k){
            i++;
            k++;
        }
        return k;
    }

//    Count the surpasser of the element in the array
//    public static int[] surpasserCount(int[] array){
//
//    }

//    =====================================================================================================================
// (IMP) Count the number of inversion in the array...........................................
    private static int inversion;
    public static int countInversion(int[] array){
        int i = 0;
        int j = array.length-1;
        merge(array, i, j);
        int ans = inversion;
        inversion = 0;
        return ans;
    }

    private static void merge(int[] array, int start, int end){
        if(start >= end) {
            return;
        }
        int mid = start + (end - start)/2;
        merge(array, start, mid);
        merge(array, mid+1, end);
        part(array, start, mid, end);

        return;
    }

    private static void part(int[] array, int start, int mid, int end){
        int[] firsthalf = new int[mid - start + 1];
        int[] secondHalf = new int[end - mid];
        for(int i = start; i < mid+1; i++){
            firsthalf[i-start] = array[i];
        }
        for(int i = mid+1; i < end+1; i++){
            secondHalf[i-mid-1] = array[i];
        }
        int st1 = 0;
        int st2 = 0;
        int h = start;
        while(st1 < firsthalf.length && st2 < secondHalf.length){
            if(firsthalf[st1] > secondHalf[st2]){
                inversion += firsthalf.length - st1;
                array[h] = secondHalf[st2];
                st2++;
                h++;
            }else{
                array[h] = firsthalf[st1];
                h++;
                st1++;
            }
        }
        if(st1 >= firsthalf.length && st2 < secondHalf.length){
            while(st2 < secondHalf.length){
                array[h] = secondHalf[st2];
                h++;
                st2++;
            }
        }
        if(st2 >= secondHalf.length && st1 < firsthalf.length){
            while (st1 < firsthalf.length){
                array[h] = firsthalf[st1];
                h++;
                st1++;
            }
        }
        return;
    }

//    =====================================================================================================================
//    Pancake problem
    public static ArrayList<Integer> PancakePrblem(int[] array, int n){
        int i = n-1;
        ArrayList<Integer> ans = new ArrayList<>();
        while(i >= 0){
            int maxIndedx = findMaxIndex(array, i);
            if(maxIndedx > 0){
                swap(array, maxIndedx);
                ans.add(maxIndedx+1);
            }
            swap(array, i);
            ans.add(i+1);
            i--;
        }
        return ans;
    }

    public static int findMaxIndex(int[] array, int i){
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        for(int c = 0; c <= i; c++){
            if(array[c] > max){
                max = array[c];
                maxIndex = c;
            }
        }
        return maxIndex;
    }

    public static void swap(int[] array, int index){
        int start = 0;
        int end = index;
        while(start <= end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
        return;
    }

//    =============================================================================================================================
//Find k closest elements
public static int[] KClosestElemets(int[] array, int k, int num){
    int i = 0;
    int j = array.length -1;
    int index = binarySearch(array, num, i , j);
    int[] answer = new int[k];
    if(array[index] == num){
        int kL = index-1;
        int kR = index+1;
        int count = 0;
        while(count < k){
            if(kL >= 0 && kR < array.length && array[index] - array[kL] < array[kR] - array[index]){
                answer[count] = array[kL];
                count++;
                kL--;
            }else if(kL >= 0 && kR < array.length && array[index] - array[kL] >= array[kR] - array[index]){
                answer[count] = array[kR];
                count++;
                kR++;
            }else if(kR < array.length){
                answer[count] = array[kR];
                count++;
                kR++;
            }else{
                answer[count] = array[kL];
                count++;
                kL--;
            }
        }
        return answer;
    }else{
        int kL = index;
        int kR = index+1;
        int count = 0;
        while(count < k) {
            if(kL >= 0 && kR < array.length && num - array[kL] < array[kR] - num){
                answer[count] = array[kL];
                count++;
                kL--;
            }else if(kL >= 0 && kR < array.length && num - array[kL] >= array[kR] - num){
                answer[count] = array[kR];
                count++;
                kR++;
            }else if(kR < array.length){
                answer[count] = array[kR];
                count++;
                kR++;
            }else{
                answer[count] = array[kL];
                count++;
                kL--;
            }
        }
        return answer;
    }
}


    public static int binarySearch(int[] array, int num, int start, int end){
        if(start > end){
            return -1;
        }
        int mid = start + (end-start)/2;
        if(array[mid] == num){
            return mid;
        }else if(mid > 0 && mid < array.length-1 && array[mid-1] < num && array[mid+1] > num){
            return mid;
        }else if(mid == 0){
            return mid;
        }else if(mid == array.length-1){
            return mid;
        }else{
            if(array[mid] > num){
                return binarySearch(array, num, start, mid);
            }else{
                return binarySearch(array, num, mid+1, end);
            }
        }
    }

//Connect n ropes to reduce cost
    public static int ConnectRopes(int[] array){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : array){
            queue.add(i);
        }
        int sum = 0;
        while(!queue.isEmpty()){
            if(queue.size() == 1){
                return sum;
            }else{
                int a = queue.poll();
                int b = queue.poll();
                int sum1 = a + b;
                sum += sum1;
                queue.add(sum1);
            }
        }
        return sum;
    }


//    Employee chaining
//    public static void employeeChaining(HashMap<Character, Character> hashmap){
//        HashSet<Node> set = new HashSet<>();
//        for(Map.Entry<Character, Character> entry : hashmap.entrySet()){
//            Character employee = entry.getKey();
//            Character employer = entry.getValue();
//            Node employ = new Node(employee);
//            Node employr = new Node(employer);
//            if(!set.contains(employ)){
//                set.add(employ);
//            }
//            if(!set.contains(employr)){
//                set.add(employr);
//            }
//            Node parent =
//        }

    public static void patternMatching(String str){
        int n = str.length();
        int max = (n-2)/3;
        for(int i = 1; i <= max; i++){
            String a = str.substring(0, i);
            String b = str.substring(i, 2*i);
            String c = str.substring(2*i, 3*i);
            if(a.equals(b) && a.equals(c)){
                boolean ans = checkPattern(str.substring(3*i));
                if(ans == true){
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
        return;
    }


    public static boolean checkPattern(String str){
        int n = str.length();
        if(n % 2 != 0){
            return false;
        }
        String a = str.substring(0, n/2);
        String b = str.substring(n/2);
        if(a.equals(b)){
            return true;
        }
        return false;
    }



    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] answer = new int[n][m];
        for(int i = 0; i < n; i++){
            answer[i][0] = matrix[i][0];
        }

        for(int j = 0; j < m; j++){
            answer[0][j] = matrix[0][j];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                int x = Math.min(Math.min(answer[i-1][j], answer[i][j-1]), answer[i-1][j-1]);
                if(x >= 1){
                    answer[i][j] = x+1;
                }else{
                    answer[i][j] = Math.max(matrix[i][j], x);
                }
            }
        }
        for(int[] ans : answer){
            for(int k : ans){
                System.out.print(k + " ");
            }
            System.out.println();
        }
        int answerFinal = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                answerFinal += answer[i][j];
            }
        }
        return answerFinal;
    }


//
    public static void solution(int n){
        int count = 0;
        long start = 0;
        int index = n;
        for(int i = 2; i <= index; i++){
            int max = sequence(i);
            if(max > count){
                count = max;
                start = i;
            }

        }
        System.out.println(start + " " + count);
//        System.out.println(sequence(9));
    }

    public static int sequence(int n){
        int count = 1;
        while(n != 1){
            if(n % 2 == 0){
                n = n/2;
            }else{
                n = 3*n + 1;
            }
            count++;
        }
        return count;
    }


    public static int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        pair ans = findSol(0, n-1, arr);
        return ans.sum;
    }
    public static HashMap<ArrayList<Integer>, pair>  map = new HashMap<>();


    public static pair findSol(int start, int end, int[] array){
        if(start == end){
            return new pair(array[start], 0);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        if(map.containsKey(list)){
            return map.get(list);
        }
        int maxLeaf = 0;
        int MinValue = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            pair answer1 = findSol(start, i, array);
            pair answer2 = findSol(i+1, end, array);
            maxLeaf = Math.max(maxLeaf, Math.max(answer1.leaf, answer2.leaf));
            MinValue = Math.min(MinValue, answer1.sum + answer2.sum + answer1.leaf * answer2.leaf);
        }
        map.put(list, new pair(maxLeaf, MinValue));
        return new pair(maxLeaf, MinValue);
    }

}
