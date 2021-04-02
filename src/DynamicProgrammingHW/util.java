package DynamicProgrammingHW;

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


}
