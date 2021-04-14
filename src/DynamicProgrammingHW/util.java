package DynamicProgrammingHW;
import java.util.*;
public class util {

    public static int uglyNumber(int n){
        int[] array = new int[n];
        int i = 0;
        int j = 0;
        int k = 0;
        array[0] = 1;
        int max = 1;
        for(int count = 1; count < n; count++){
            while(array[i] * 2 <= max){
                i++;
            }
            while(array[j] * 3 <= max){
                j++;
            }
            while(array[k] * 5 <= max){
                k++;
            }
            if(array[i] * 2 <= array[j] * 3 && array[i] * 2 <= array[k] * 5){
                array[count] = array[i] * 2;
                i++;
            }else if(array[j] * 3 <= array[i] * 2 && array[j] * 3 <= array[k] * 5){
                array[count] = array[j] * 3;
                j++;
            }else{
                array[count] = array[k] * 5;
                k++;
            }
            max = array[count];
        }
        return array[n-1];
    }


//    ============================================================================================================================
    public static int superUglyNumber(int n, int[] array){
        int[] count = new int[array.length];
        int[] answer = new int[n];
        answer[0] = 1;
        int max = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < array.length; j++){
                while(answer[count[j]] * array[j] <= max){
                    count[j]++;
                }
            }

            int minIndex = 0;
            int minValue = answer[count[0]] * array[0];
            for(int j = 1; j < array.length; j++){
                int index = count[j];
                if(minValue > answer[index] * array[j]){
                    minValue = answer[index] * array[j];
                    minIndex = j;
                }
            }

            answer[i] = minValue;
            max = answer[i];
            count[minIndex]++;
        }

        return answer[n-1];
    }

//==================================================================================================================================
//    Maximum size square submatrix of all 1s
    public static int maximumSizeSquareMatrix(int[][] array){
        int n = array.length;
        int m = array[0].length;
        int[][] answer = new int[n][m];
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0){
                    answer[i][j] = array[i][j];
                    max = Math.max(max, answer[i][j]);
                }else if(i == 0){
                    answer[i][j] = (array[i][j] == 1) ?  1 : 0;
                }else if(j == 0){
                    answer[i][j] = (array[i][j] == 1) ?  1 : 0;
                }else{
                    answer[i][j] = (array[i][j] == 1) ? Math.min(answer[i-1][j-1], Math.min(answer[i-1][j], answer[i][j-1])) + 1 : 0;
                }
                max = Math.max(max, answer[i][j]);
            }
        }
        return max;
    }

//    =====================================================================================================================================
    public static int optimalBST(int[] frequency, int n){
        int[][] array = new int[n][n];
        int answer = calculate(frequency, 0, n-1, array);
        return answer;
    }

    private static int calculate(int[] freq, int start, int end,int[][] array){
        if(start > end){
            return 0;
        }
        if(start == end){
            return array[start][end] = freq[start];
        }

        if(array[start][end] != 0){
            return array[start][end];
        }
        int min = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            int ans1 = calculate(freq, start, i-1, array);
            int ans2 = calculate(freq, i+1, end, array);
            if(min > ans1+ ans2){
                min = ans1 + ans2;
            }
        }

        for(int i = start; i <= end; i++){
            min += freq[i];
        }

        return array[start][end] = min;
    }


//    number of solution of liner equation
    public static int linearEqSol(int[] coeff, int value){
        int[] dp = new int[value+1];
        for(int i = 0; i < coeff.length; i++){
            for(int j = coeff[i]; j <= value; j++){
                dp[j] += dp[j - coeff[i]];
            }
        }
        return dp[value];
    }


    public static int subsetSumTest(int n){
        ArrayList<Integer> list = new ArrayList();
        int i = 1;
        while(i*i <= n){
            int x = i*i;
            list.add(x);
            i++;
        }
        int size = list.size();
        int[][] dp = new int[size][n+1];
        for(int r = 0; r < size; r++){
            for(int s = 0; s <= n; s++){
                int curr = list.get(r);
                if(r == 0){
                    dp[r][s] = s;
                }else{
                    int min = dp[r-1][s];
                    if(s >= curr){
                        min = Math.min(min, dp[r][s - curr] + 1);
                    }
                    dp[r][s] = min;
                }
            }
        }
        for(int[] k : dp){
            for(int h : k){
                System.out.print(h + " ");
            }
            System.out.println();
        }
        return dp[size-1][n];
    }


//    ======================================================================================================================
    public static int booleanParanthesis(String str1, String str2){
        Character[] operands = new Character[str1.length()];
        Character[] operator = new Character[str2.length()];
        for(int i = 0; i < operands.length; i++){
            char ch1 = str1.charAt(i);
            operands[i] = ch1;
        }
        for(int j = 0; j < operator.length; j++){
            char ch2 = str2.charAt(j);
            operator[j] = ch2;
        }
        int[][] dp = new int[str1.length()][str1.length()];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp.length; j++){
                dp[i][j] = -1;
            }
        }
        int[][] dp1 = new int[str1.length()][str1.length()];
        for(int i = 0; i < dp1.length; i++){
            for(int j = 0; j < dp1.length; j++){
                dp1[i][j] = -1;
            }
        }
        pair answer = booleanParanthesisHelper(dp,dp1, operands, operator, 0, operands.length-1);
        return answer.trueCount;
    }

    private static pair booleanParanthesisHelper(int[][] dp, int[][] dp1, Character[] operands, Character[] operator, int start, int end){
        if(start > end){
            return new pair(-1, -1);
        }else if(start == end){
            if(operands[start] == 'T'){
                dp[start][end] = 1;
                dp1[start][end] = 0;
                return new pair(1, 0);
            }
            dp[start][end] = 0;
            dp1[start][end] = 1;
            return new pair(0, 1);
        }

        if(dp[start][end] != -1){
            return new pair(dp[start][end], dp1[start][end]);
        }
        int TrueCount = 0;
        int FalseCount = 0;
        for(int i = start; i < end; i++){
            char oper = operator[i];
            pair ans1 = booleanParanthesisHelper(dp, dp1, operands, operator, start, i);
            pair ans2 = booleanParanthesisHelper(dp, dp1, operands, operator, i+1, end);
            if(oper == '|'){
                TrueCount += ans1.trueCount * (ans2.trueCount + ans2.falseCount) + ans1.falseCount * ans2.trueCount;
                FalseCount += ans1.falseCount * ans2.falseCount;
            }else if(oper == '&'){
                TrueCount += ans1.trueCount * ans2.trueCount;
                FalseCount += ans1.falseCount * (ans2.falseCount + ans2.trueCount) + ans1.trueCount * ans2.falseCount ;
            }else{
                TrueCount += ans1.trueCount * ans2.falseCount + ans1.falseCount * ans2.trueCount;
                FalseCount += ans1.falseCount * ans2.falseCount + ans1.trueCount * ans2.trueCount;
            }
        }
        dp[start][end] = TrueCount;
        dp1[start][end] = FalseCount;
        return new pair(TrueCount, FalseCount);
    }





    public static int countWays(int N, String S){
        // code here
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < N; i++){
            char ch = S.charAt(i);
            if(ch == '|' || ch == '&' || ch == '^'){
                sb2.append(ch);
            }else{
                sb1.append(ch);
            }
        }
        String str1 = sb1.toString();
        String str2 = sb2.toString();
        System.out.println(booleanParanthesisTabu(str1, str2) + " " + booleanParanthesis(str1, str2));
        return booleanParanthesisTabu(str1, str2);
    }


    public static int booleanParanthesisTabu(String str1, String str2){
        Character[] operands = new Character[str1.length()];
        Character[] operator = new Character[str2.length()];
        for(int i = 0; i < operands.length; i++){
            char ch1 = str1.charAt(i);
            operands[i] = ch1;
        }
        for(int j = 0; j < operator.length; j++){
            char ch2 = str2.charAt(j);
            operator[j] = ch2;
        }
        int[][] dp1 = new int[str1.length()][str1.length()];
        int[][] dp2 = new int[str1.length()][str1.length()];
        for(int gap = 0; gap < str1.length(); gap++){
            for(int i = 0, j = gap; j < str1.length(); i++, j++){
                if(gap == 0){
                    char ch = operands[i];
                    if(ch == 'T'){
                        dp1[i][j] = 1;
                        dp2[i][j] = 0;
                    }else{
                        dp2[i][j] = 1;
                        dp1[i][j] = 0;
                    }
                }else{
                    int Truecount = 0;
                    int Falsecount = 0;
                    for(int k = i; k < j; k++){
                        char oper = operator[k];
                        int tlc = dp1[i][k];
                        int trc = dp1[k+1][j];
                        int flc = dp2[i][k];
                        int frc = dp2[k+1][j];
                        if(oper == '|'){
                            Truecount += tlc * trc + tlc * frc + flc * trc;
                            Falsecount += flc * frc;
                        }else if(oper == '&'){
                            Truecount += tlc * trc;
                            Falsecount += flc * trc + flc * frc + tlc * frc;
                        }else{
                            Truecount += tlc * frc + flc * trc;
                            Falsecount += tlc * trc + flc * frc;
                        }
                    }
                    dp1[i][j] = Truecount;
                    dp2[i][j] = Falsecount;
                }
            }
        }
        return dp1[0][str1.length()-1];
    }

    public static int solution(String str1, String str2) {
        int n = str1.length();
        int[][] t = new int[n][n];
        int[][] f = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            int si = 0, ei = gap;
            while (ei < n) {
                if (gap == 0) {
                    t[si][ei] = str1.charAt(si) == 'T' ? 1 : 0;
                    f[si][ei] = str1.charAt(si) == 'F' ? 1 : 0;
                } else {
                    for (int cp = si; cp < ei; cp++) {
                        char sign = str2.charAt(cp);
                        if (sign == '&') {
                            t[si][ei] += t[si][cp] * t[cp + 1][ei];
                            f[si][ei] += ((t[si][cp] * f[cp + 1][ei]) + (f[si][cp] * t[cp + 1][ei])
                                    + (f[si][cp] * f[cp + 1][ei]));
                        }
                        if (sign == '|') {
                            t[si][ei] += ((t[si][cp] * t[cp + 1][ei]) + (t[si][cp] * f[cp + 1][ei])
                                    + (f[si][cp] * t[cp + 1][ei]));
                            f[si][ei] += ((f[si][cp]) * (f[cp + 1][ei]));
                        }
                        if (sign == '^') {
                            t[si][ei] += ((t[si][cp] * f[cp + 1][ei]) + (f[si][cp] * t[cp + 1][ei]));
                            f[si][ei] += ((t[si][cp] * t[cp + 1][ei]) + (f[si][cp] * f[cp + 1][ei]));
                        }
                    }
                }
                si++;
                ei++;
            }
        }
        return (t[0][t[0].length - 1]);
    }


//    ======================================================================================================================
    public static int burstBallon(int[] array){
        int[][] dp = new int[array.length][array.length];
        int answer = burstBallonHelper(array, dp, 0, array.length-1);
        for(int[] arr : dp){
            for(int val : arr){
                System.out.print(val + " ");
            }
            System.out.println();
        }
        return answer;
    }

    private static int burstBallonHelper(int[] array, int[][] dp, int start, int end){
        if(start == end){
            return dp[start][end] = array[start];
        }
        if(dp[start][end] != 0){
            return dp[start][end];
        }
        int max = 0;
        for(int i = start; i <= end; i++){
            if(i == start){
                int ans = burstBallonHelper(array, dp, i+1, end);
                ans += array[start] + array[start+1];
                max = Math.max(max, ans);
            }else if(i == end){
                int ans = burstBallonHelper(array, dp, start, end-1);
                ans += array[end] + array[end-1];
                max = Math.max(max, ans);
            }else{
                int ans = burstBallonHelper(array, dp, start, i-1) + burstBallonHelper(array, dp, i+1, end);
                ans += array[i-1] + array[i] + array[i+1];
                max = Math.max(max, ans);
            }
        }
        return dp[start][end] = max;
    }

    public static void wordWrap(int[] array, int capacity){
        int n = array.length;
        pair2[] dp = new pair2[n];
        pair2 ans = wordWrapHelper(array, capacity, dp, 0);
        System.out.println(ans.ans);
    }

    private static pair2 wordWrapHelper(int[] array, int capacity, pair2[] dp, int index){
        int n = array.length;
        if(index >= n){
            return new pair2(0, "");
        }

        if(dp[index] != null){
            return dp[index];
        }


        int cap = capacity;
        String answerString = "";
        int costFinal = Integer.MAX_VALUE;

        int i = index;
        while(i < array.length && cap >= array[i]){
            cap -= array[i];
            int costCurr = cap*cap*cap;
            if(i == array.length-1){
                costCurr = 0;
            }
            i++;
            cap--;
            pair2 remain = wordWrapHelper(array, capacity, dp, i);
            int costRemain = remain.cost;
            if(costRemain + costCurr < costFinal){
                costFinal = costRemain + costCurr;
                int x = index+1;
                answerString = x + " " + i + " " + remain.ans;
            }
        }
        return dp[index] = new pair2(costFinal, answerString);
    }


//    =======================================================================================================================
//    largest sum subarray with atleast k numbers

    public static int largestSumSubarray(int[] array, int k){
        int n = array.length;

        int[] sumWithKElements = new int[n-k+1];
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            if(i < k -1){
                sum += array[i];
            }else{
                sum += array[i];
                sumWithKElements[i - k + 1] = sum;
                sum -= array[i - k + 1];
            }
        }
//        for(int val : sumWithKElements){
//            System.out.print(val + " ");
//        }
        int[] maxSumSubarray = new int[array.length];
        int sum2 = 0;
        for(int i = 0; i < array.length; i++){
            sum2 += array[i];
            if(sum2 < 0){
                sum2 = 0;
            }
            maxSumSubarray[i] = sum2;
        }
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < sumWithKElements.length; i++){
            if(i == 0){
                ans = Math.max(ans, sumWithKElements[i]);
            }else{
                ans = Math.max(ans, sumWithKElements[i] + maxSumSubarray[i-1]);
            }
        }
        return ans;
    }


//    program to find the amount of water in given glass

    public static double waterOverflow(double k, int row, int col){
        int n = row * (row + 1)/2;
        double[] dp = new double[n];
        for(int i = 0; i < dp.length; i++){
            dp[i] = -1;
        }
        double ans = waterOverflowHelper(k, row-1, col-1, dp);
        if(ans < 0){
            return 0;
        }else if(ans > 1){
            return 1;
        }
        return ans;
    }

    private static double waterOverflowHelper(double k, int row, int col, double[] dp){
        if(row == 0){
            dp[0] = k;
            return k;
        }

        int index = (row)*(row+1)/2 + col;
        if(dp[index] != -1){
            return dp[index];
        }

        int lastCol = row;
        double ans = 0;
        if(col == 0){
            ans = (waterOverflowHelper(k, row-1, col, dp) - 1)/2;
        }else if(col == lastCol){
            ans = (waterOverflowHelper(k, row-1, col-1, dp) - 1)/2;
        }else{
            ans = (waterOverflowHelper(k, row-1, col-1, dp) - 1)/ 2 + (waterOverflowHelper(k, row-1, col, dp) - 1)/2;
        }
        return ans;
    }

//    =======================================================================================================================

    public static int removeMinElements(int[] array){
        int n = array.length;
        int[][] dp = new int[n][n];

        int ans = removeMinElementsHelper(array, dp, 0, n-1, 0);
        return ans;
    }

    private static int removeMinElementsHelper(int[] array, int[][] dp, int start, int end, int count){
        if(start >= end){
            return count;
        }

        if(dp[start][end] != 0){
            return dp[start][end];
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = start; i <= end; i++){
            int x = array[i];
            if(x > max){
                max = x;
            }
            if(x < min){
                min = x;
            }
        }
        if(min * 2 > max){
            return count;
        }

        int count1 = removeMinElementsHelper(array, dp, start+1, end, count+1);
        int count2 = removeMinElementsHelper(array, dp, start, end-1, count + 1);
        return dp[start][end] = Math.min(count1, count2);
    }


//    number of sequence of form a^1 b^j c^k

    public static long countSequence(String str){
        int n = str.length();
//        int[][] dp = new int[3][n+1];
//        for(int i = 0; i <= n; i++){
//            for(int j = 0; j < 3; j++){
//                if(i == 0){
//                    dp[j][i] = 0;
//                }else{
//                    char ch = str.charAt(i-1);
//                    if(ch == 'a'){
//                        dp[0][i] = dp[0][i-1] * 2 + 1;
//                        dp[1][i] = dp[0][i-1];
//                        dp[2][i] = dp[0][i-1];
//                    }else if(ch == 'b'){
//                        dp[0][i] = dp[0][i-1];
//                        dp[1][i] = dp[1][i-1]*2 + dp[0][i-1];
//                        dp[2][i] = dp[2][i-1];
//                    }else{
//                        dp[0][i] = dp[0][i-1];
//                        dp[1][i] = dp[1][i-1];
//                        dp[2][i] = dp[2][i-1]*2 + dp[1][i-1];
//                    }
//                }
//            }
//        }
//        return dp[2][n];
        double a = 0;
        double ab = 0;
        double abc = 0;
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(ch == 'a'){
                a = 2*a + 1;
            }else if(ch == 'b'){
                ab = 2*ab + a;
            }else{
                abc = 2 * abc + ab;
            }
        }
        return (long) (abc % (1000000007));
    }


//    ==========================================================================================================================
//    unbounded knapsack
    public static int unboundedKnapScak(int targetWeight, int[] weights, int[] values){
        int n = weights.length;
        int[][] dp = new int[n+1][targetWeight + 1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= targetWeight; j++){
                if(j == 0 || i == 0){
                    dp[i][j] = 0;
                }else{
                    int weight = weights[i-1];
                    if(weight > j){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-weight] + values[i-1]);
                    }
                }
            }
        }
        return dp[n][targetWeight];
    }

//    length of longest valid string
    public static int lenLongestValidParenthesis(String str){
        int n = str.length();
        int ans1 = answerFromLeft(str, n);
        int ans2 = answerFromRight(str, n);
        return Math.max(ans1, ans2);
    }

    private static int answerFromLeft(String str, int n){
        Stack<Character> stack = new Stack<>();
        int ansF = 0;
        int ans = 0;
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(ch == '('){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                    ansF = Math.max(ans,ansF);
                    ans = 0;
                }else{
                    stack.pop();
                    ans += 2;
                }
            }
            if(stack.isEmpty()){
                ansF = Math.max(ans, ansF);
            }
        }
        return ansF;
    }


    private static int answerFromRight(String str, int n){
        Stack<Character> stack = new Stack<>();
        int ansF = 0;
        int ans = 0;
        for(int i = n-1; i >= 0; i--){
            char ch = str.charAt(i);
            if(ch == ')'){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                    ansF = Math.max(ans,ansF);
                    ans = 0;
                }else{
                    stack.pop();
                    ans += 2;
                }
            }
            if(stack.isEmpty()){
                ansF = Math.max(ans, ansF);
            }
        }
        return ansF;
    }



//
//===============================================================================================================================
//    count possible decoding of digit sequence;
//    else if((ch == '1') || ((ch == '2') && (ch2 == '0' || ch2 == '1' || ch2 == '3' || ch2 == '4' || ch2 == '5' || ch2 == '6')

    public static int digitDecoding(String str){
        int n = str.length();
        int count = 0;
        int[][] dp = new int[2][n];
        for(int i = n-1; i >= 0; i--){
            char ch = str.charAt(i);
            if(i == n-1){
                dp[0][i] = 1;
                dp[1][i] = 0;
            }else{
                char ch2 = str.charAt(i+1);
                if(ch == '0'){
                    dp[0][i] = 0;
                    dp[1][i] = dp[0][i+1];
                }else if((ch == '1') || ((ch == '2') && (ch2 == '0' || ch2 == '1' || ch2 == '3' || ch2 == '4' || ch2 == '5' || ch2 == '6'))){
                    dp[0][i] = dp[0][i+1] + dp[1][i+1];
                    dp[1][i] = dp[0][i+1];
                }else{
                    dp[0][i] = dp[0][i+1] + dp[1][i+1];
                    dp[1][i] = 0;
                }
            }
        }
        return dp[0][0] + dp[1][0];
    }

//    ======================================================================================================================
//    longest substring such that sum of first half and second half is same
    public static int longestEvenLenSubstingEqual(String str){
        int n = str.length();
        char[] arr = str.toCharArray();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i > j){
                    dp[i][j] = 0;
                }else if(i == j){
                    dp[i][j] = (int) arr[i];
                }else{
                    dp[i][j] = dp[i][j-1] + arr[j];
                }
            }
        }

        int maxLength = 0;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                int x = dp[i][j];
                if(i + gap + 1 >= n || j + gap + 1 >= n){
                    continue;
                }else{
                    int y = dp[i+ gap + 1][j + gap + 1];
                    if(x == y){
                        maxLength = Math.max(maxLength, 2 * (gap + 1));
                    }
                }
            }
        }
        return maxLength;
    }

//    =====================================================================================================================
//    longest common increasing subsequence
    public static int longestCommonIncreasingSubsequence(int[] array1, int[] array2){
        int n = array1.length;
        int m = array2.length;
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0){
                    if(array1[i] == array2[j]){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = 0;
                    }
                }else if(i == 0){
                    if(array1[i] == array2[j]){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i][j-1];
                    }
                }else if(j == 0){
                    if(array1[i] == array2[j]){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }else{
                    if(array1[i] == array2[j]){
                        int x = array2[j];
                        int max = 0;
                        for(int k = 0; k < j; k++){
                            if(array2[k] < x){
                                max = Math.max(max, dp[i][k] + 1);
                            }
                        }
                        dp[i][j] = Math.max(max, Math.max(dp[i-1][j], dp[i][j-1]));
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[n-1][m-1];
    }

//    =======================================================================================================================

}
