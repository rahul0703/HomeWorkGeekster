package DynamicProgClass;

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
}
