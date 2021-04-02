package DynamicProgClass;

import java.util.ArrayList;
import java.util.Collections;

public class util {

    public static int[] fibonaci(int N){
        int[] answer = new int[N];
        answer[0] = 0;
        answer[1] = 1;
        for(int i = 2; i < N; i++){
            answer[i] = answer[i-1] + answer[i-2];
        }
        for(int i : answer){
            System.out.print(i + " ");
        }
        return answer;
    }


//    =============================================================================================================
    public static int steps(int n){
        int[] array = new int[n+1];
        array[0] = 1;
        for(int i = 1; i < n+1; i++){
            if(i == 1){
                array[i] = 1;
            }
            else if(i == 2){
                array[i] = 2;
            }
            else if(i == 3){
                array[i] = 4;
            }else{
                array[i] = array[i-1] + array[i-2] + array[i-3];
            }
        }
        return array[n];
    }


//  maxepath
    public static int maxePath(int sX, int sY, int eX, int eY){
        int x = eX - sX + 1;
        int y = eY - sY + 1;
        int[][] answer = new int[x][y];

        for(int i = 0; i < x; i++){
            answer[i][0] = 1;
        }
        for(int j = 0; j < y; j++){
            answer[0][j] = 1;
        }

        for(int i = 1; i < x; i++){
            for(int j = 1; j < y; j++){
                answer[i][j] = answer[i-1][j] + answer[i][j-1];
            }
        }
        return answer[x-1][y-1];
    }


    public static int maxePathBottomUp(int sX, int sY, int eX, int eY){
        int x = eX - sX + 1;
        int y = eY - sY + 1;
        int[][] answer = new int[x][y];

        for(int i = 0; i < x; i++){
            answer[i][y-1] = 1;
        }
        for(int j = 0; j < y; j++){
            answer[x-1][j] = 1;
        }

        for(int i = x-2; i >= 0; i--){
            for(int j = y-2; j >= 0; j--){
                answer[i][j] = answer[i+1][j] + answer[i][j+1];
            }
        }
        return answer[0][0];
    }


//    ==============================================================================================================
    public static int MinPathCost(int[][] Cost){
        int[][] answer = new int[Cost.length][Cost[0].length];
        int n = Cost.length;
        int m = Cost[0].length;
        for(int i = 0; i < n; i++){
            if(i == 0){
                answer[i][0] = Cost[i][0];
            }else{
                answer[i][0] = Cost[i][0] + answer[i-1][0];
            }
        }
        for(int j = 1; j < m; j++){
            answer[0][j] = Cost[0][j] + answer[0][j-1];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                answer[i][j] = Math.min(answer[i-1][j], answer[i][j-1]) + Cost[i][j];
            }
        }
        return answer[n-1][m-1];
    }


//    ====================================================================================================================

    public static int minCostPathBottomUP(int[][] cost){
        int n = cost.length;
        int m = cost[0].length;
        int[][] answer = new int[n][m];
        answer[n-1][m-1] = cost[n-1][m-1];
        for(int i = n-2; i >= 0; i--){
            answer[i][m-1] = answer[i+1][m-1] + cost[i+1][m-1];
        }
        for(int j = m-2; j >= 0; j--){
            answer[n-1][j] = answer[n-1][j+1] + cost[n-1][j];
        }

        for(int i = n-2; i >= 0; i--){
            for(int j = m-2; j >= 0; j--){
                answer[i][j] = Math.min(answer[i+1][j], answer[i][j+1]) + cost[i][j];
            }
        }
        return answer[0][0];

    }


//    ================================================================================================================
    public static int MinPathCostWithPath(int[][] Cost){
        int[][] answer = new int[Cost.length][Cost[0].length];
        int n = Cost.length;
        int m = Cost[0].length;
        String[][] path = new String[n][m];
        for(int i = 0; i < n; i++){
            if(i == 0){
                answer[i][0] = Cost[i][0];
                path[i][0] = "";
            }else{
                answer[i][0] = Cost[i][0] + answer[i-1][0];
                path[i][0] = path[i-1][0] + " V";
            }
        }
        for(int j = 1; j < m; j++){
            answer[0][j] = Cost[0][j] + answer[0][j-1];
            path[0][j] = path[0][j-1] + " H";
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                answer[i][j] = Math.min(answer[i-1][j], answer[i][j-1]) + Cost[i][j];
                path[i][j] = answer[i-1][j] < answer[i][j-1] ? path[i-1][j] + " V" : path[i][j-1] + " H";
            }
        }
        System.out.println(path[n-1][m-1]);
        return answer[n-1][m-1];
    }

//    ==================================================================================================================
    public static int maxGold(int[][] matrix){
        int[][] answer = new int[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++){
            answer[i][0] = matrix[i][0];
        }

        for(int j = 1; j < matrix.length; j++){
            for(int i = 0; i < matrix.length; i++){
                if(i == 0){
                    answer[i][j] = matrix[i][j] + Math.max(answer[i+1][j-1], answer[i][j-1]);
                }else if(i == matrix.length-1){
                    answer[i][j] = matrix[i][j] + Math.max(answer[i][j-1], answer[i-1][j-1]);
                }else{
                    answer[i][j] = matrix[i][j] + Math.max(Math.max(answer[i][j-1], answer[i-1][j-1]), answer[i+1][j-1]);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            max = Math.max(max, answer[i][answer.length-1]);
        }
        return max;
    }


    public static int maxGoldWithPath(int[][] matrix){
        int[][] answer = new int[matrix.length][matrix.length];
        String[][] path = new String[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++){
            answer[i][0] = matrix[i][0];
            path[i][0] = "" + i + " ";
        }

        for(int j = 1; j < matrix.length; j++){
            for(int i = 0; i < matrix.length; i++){
                if(i == 0){
                    answer[i][j] = matrix[i][j] + Math.max(answer[i+1][j-1], answer[i][j-1]);
                    if(answer[i+1][j-1] > answer[i][j-1]){
                        path[i][j] = path[i+1][j-1] + "DiaUp ";
                    }else{
                        path[i][j] = path[i][j-1] + "H ";
                    }
                }else if(i == matrix.length-1){
                    answer[i][j] = matrix[i][j] + Math.max(answer[i][j-1], answer[i-1][j-1]);
                    if(answer[i-1][j-1] > answer[i][j-1]){
                        path[i][j] = path[i-1][j-1] + "DiaDown ";
                    }else{
                        path[i][j] = path[i][j-1] + "H ";
                    }
                }else{
                    answer[i][j] = matrix[i][j] + Math.max(Math.max(answer[i][j-1], answer[i-1][j-1]), answer[i+1][j-1]);
                    if(answer[i-1][j-1] > answer[i][j-1] && answer[i-1][j-1] > answer[i+1][j-1]){
                        path[i][j] = path[i-1][j-1] + "DiaDown ";
                    }else if(answer[i+1][j-1] > answer[i][j-1] && answer[i+1][j-1] > answer[i-1][j-1]){
                        path[i][j] = path[i+1][j-1] + "DiaUp ";
                    }else{
                        path[i][j] = path[i][j-1] + "H ";
                    }
                }
            }
        }

        int max = 0;
        int index = 0;
        for(int i = 0; i < matrix.length; i++){
            if(answer[i][answer.length-1] > max){
                index = i;
            }
            max = Math.max(max, answer[i][answer.length-1]);
        }
        System.out.println(path[index][matrix.length-1]);
        return max;
    }

    public static int minJumps(int[] array){
        int n = array.length;
        int[] answer = new int[n];
        String[] path = new String[n];
        answer[0] = 0;
        for(int i = 1; i < n; i++){
            int max = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++){
                int sum = 0;
                int req = i - j;
                if(array[j] >= req){
                    sum = answer[j] + 1;
                    if(sum < max){
                        max = sum;
                    }
                }
            }
            answer[i] = max;
        }
        return answer[n-1];
    }


    public static int LongestIncreasingSubsequence(int[] array){
        int n = array.length;
        int[] answer = new int[n];
        answer[0] = 0;
        for(int i = 1; i < n; i++){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j < i; j++){
                int sum = 0;
                if(array[j] < array[i]){
                    sum = answer[j] + 1;
                    if(sum > max){
                        max = sum;
                    }
                }
            }
            answer[i] = max;
        }
        int retu = 0;
        for(int i : answer){
            retu = Math.max(i, retu);
        }
        return retu;
    }


//    =======================================================================================================
    public static int maxHeightOfBox(box[] array){
        int n = array.length;
        int[] answer = new int[3*n];
        ArrayList<box> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            box value = array[i];
            box value1 = new box(value.height, Math.max(value.length, value.bredth), Math.min(value.length, value.bredth));
            box value2 = new box(value.length, Math.max(value.height, value.bredth), Math.min(value.height, value.bredth));
            box value3 = new box(value2.bredth, Math.max(value.length, value.height), Math.min(value.length, value.height));
            list.add(value1);
            list.add(value2);
            list.add(value3);
        }

        Collections.sort(list);
        answer[0] = list.get(0).height;
        for(int i = 1; i < 3*n; i++){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j < i; j++){
                int sum = 0;
                if(list.get(j).area < list.get(i).area){
                    sum = answer[j] + list.get(i).height;
                    if(sum > max){
                        max = sum;
                    }
                }
            }
            answer[i] = max;
        }
        return answer[3*n-1];
    }


//    =====================================================================================================================================
    public static int rodCuttingProblem(int rodLength, int[] prices){
        int[] answer = new int[rodLength+1];
        String[] ans = new String[rodLength+1];
        int n = rodLength + 1;
        for(int i = 0; i < n; i++){
            int max = prices[i];
            String st = i + "";
            for(int j = 0; j <= i; j++){
                if(answer[i-j] + answer[j] > max){
                    st = ans[i-j] + " " + ans[j];
                }
                max = Math.max(max, answer[i-j] + answer[j]);
            }
            answer[i] = max;
            ans[i] = st;
        }
        System.out.println(ans[rodLength]);
        return answer[rodLength];
    }

//    ==================================================================================================================================
//    test match example by pepcoding
    public static boolean targetSumPair(int target, int[] array){
        int n = array.length;
        int[][] answer = new int[n+1][target+1];
        String[][] ans = new String[n+1][target+1];
        answer[0][0] = 1;
        ans[0][0] = "Do nothing ";
        for(int i = 1; i <= n; i++){
            answer[i][0] = 1;
            ans[i][0] = "do nothing ";
        }
        for(int j = 1; j <= target; j++){
            answer[0][j] = 0;
            ans[0][j] = "No possible";
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= target; j++){
                if(answer[i-1][j] == 1){
                    answer[i][j] = 1;
                    ans[i][j] = ans[i-1][j] + "do nothing ";
                }else if(array[i-1] <= j && answer[i-1][j-array[i-1]] == 1) {
                    answer[i][j] = 1;
                    ans[i][j] = ans[i-1][j-array[i-1]] + "play " + array[i-1] + " ";
                }else{
                    answer[i][j] = 0;
                    ans[i][j] = "Not possible";
                }
            }
        }
        System.out.println(ans[n][target]);
        return answer[n][target] == 1;
    }


//    ===========================================================================================================
    public static int coinDistibution(int target, int[] array){
        int[] answer = new int[target+1];
        for(int j = 0; j <= target; j++){
            if(j == 0){
                answer[j] = 1;
            }else {
                int x = 0;
                for (int i : array) {
                    if (i <= j) {
                        x += answer[j - i];
                    }
                }
                answer[j] = x;
            }
        }
        return answer[target];
    }


    public static int coinChangeCombination(int target, int[] array){
        int[] answer = new int[target+1];
        answer[0] = 1;
        for(int i : array){
            for(int j = 1; j <= target; j++){
                if(j >= i && answer[j-i] > 0){
                    answer[j] += answer[j-i];
                }
            }
        }
        return answer[target];
    }

//    ===========================================================================================================================
    public static int maxSum(int[] array){
        int[] answer = new int[array.length];
        int ans = maxSumElem(answer, array, 0);
        return ans;
    }

        public static int maxSumElem(int[] answer, int[] array, int index){
            if(index >= array.length){
                return 0;
            }

            if(answer[index] != 0){
                return answer[index];
            }

            int ans1 = array[index] + maxSumElem(answer, array, index+2);
            int ans2 = maxSumElem(answer, array, index+1);
            return answer[index] = Math.max(ans1, ans2);
        }

//        ======================================================================================================================
//    public static int count = 0;
//    public static int egepytianFraction(int a, int b){
//        egypt(a, b);
//        return count;
//    }
//
//    public static void egypt(int a, int b){
//        if(a == 1){
//            return;
//        }
//        while()
//    }

    public static int LongestCommonSubsequence(String str1, String str2){
        int[][] array = new int[str1.length()+1][str2.length()+1];
        for(int i = 0; i <= str1.length(); i++){
            for(int j = 0; j <= str2.length(); j++){
                if(i == 0 || j == 0){
                    array[i][j] = 0;
                }else if(str1.charAt(i-1) == str2.charAt(j-1)){
                    array[i][j] = Math.max(array[i-1][j-1]+1, Math.max(array[i-1][j], array[i][j-1]));
                }
            }
        }
        return array[str1.length()][str2.length()];
    }


    public static int LongestCommonSub(String str1, String str2){
        int[][] array = new int[str1.length()][str2.length()];
        int ans = lcs(str1, str2, array, 0, 0);
        return ans;
    }

    public static int lcs(String str1, String str2, int[][] array, int index, int index2){
        if(index >= str1.length() || index2 >= str2.length()){
            return 0;
        }

        if(array[index][index2] != 0){
            return array[index][index2];
        }
        if(str1.charAt(index) == str2.charAt(index2)){
            return array[index][index2] = Math.max(lcs(str1, str2, array, index+1, index2+1) + 1, Math.max(
                    lcs(str1, str2, array, index+1, index2), lcs(str1, str2, array, index, index2+1)));
        }
        return array[index][index2] = Math.max(lcs(str1, str2, array, index+1, index2), lcs(str1, str2, array, index, index2+1));
    }

//    ================================================================================================================================
    public static int mimimumPalindromicCut(String str){
        int n = str.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = -1;
            }
        }
        int ans = cuts(str, dp, 0);
        return ans;
    }

    private static int cuts(String str, int[][] dp, int index){
        int n = str.length();
        if(n == 1 || n == 0) {
            return dp[index][index + n -1] = 0;
        }
         if(isPalindromic(str)){
             return 0;
         }
//        if(str.charAt(0) == str.charAt(n-1)){
//            if(dp[index + 1][index + n - 2] == 0){
//                dp[index][index + n - 1] = 0;
//                return 0;
//            }
//        }
        if(dp[index][index + n - 1] != -1){
            return dp[index][index + n - 1];
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            int ans1 = cuts(str.substring(0, i), dp, index);
            int ans2 = cuts(str.substring(i), dp, i+index);
            answer = Math.min(answer, ans1+ans2+1);
        }
        return dp[index][index + n-1] = answer;
    }

    private static boolean isPalindromic(String str){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }


}
