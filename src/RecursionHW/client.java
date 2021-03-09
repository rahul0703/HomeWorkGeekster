package RecursionHW;

public class client {
//    ===================================================Important Functions=============================================================

//    ================================Question 1 ===================================================================
//   The knight tour problem.............................
    public static int[][] KnightTourProblem(int n){
        int[][] array = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                array[i][j] = -1;
            }
        }
        array[0][0] = 0;
        int[] arrayX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] arrayY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        knightTour(array, 0, 0, 1, arrayX, arrayY, array.length);
        return array;
    }

    private static boolean knightTour(int[][] array, int x, int y, int count, int[] arrayX, int[] arrayY, int n){
        if(count == n * n){
            return true;
        }
        for(int i = 0; i < arrayY.length; i++){
            int x1 = x + arrayX[i];
            int y1 = y + arrayY[i];
            if(safeKnight(array, x1, y1)){
                array[x1][y1] = count;
                count++;
                boolean ans = knightTour(array, x1, y1, count, arrayX, arrayY, n);
                if(ans){
                    return ans;
                }
                count--;
                array[x1][y1] = -1;
            }
        }
        return false;
    }

    private static boolean safeKnight(int[][] array, int x1, int y1){
        if(x1 >= array.length || x1 < 0 || y1 >= array.length || y1 < 0){
            return false;
        }
        if(array[x1][y1] != -1){
            return false;
        }
        return true;
    }

//    ==========================================question 2======================================================================
    public static int[][] rateInMaze(int[][] array){
        array[0][0] = 2;
        int[] xDir = {0, 1};
        int[] yDir = {1, 0};
        solveRateInMaze(array, 0, 0, xDir, yDir, array.length);
        return array;
    }

    private static boolean solveRateInMaze(int[][] array, int x, int y, int[] xDir, int[] yDir, int n){
        if(x == n-1 && y == n-1){
            return true;
        }
        for(int i = 0; i < xDir.length; i++){
            int x1 = x + xDir[i];
            int y1 = y + yDir[i];
            if(isSafeRateMove(array, x1, y1)){
                array[x1][y1] = 2;
                boolean ans = solveRateInMaze(array, x1, y1, xDir, yDir, n);
                if(ans){
                    return true;
                }
                array[x1][y1] = 1;
            }
        }
        return false;
    }

    private static boolean isSafeRateMove(int[][] array, int x1, int y1){
        if(x1 < 0 || x1 >= array.length || y1 < 0 || y1 >= array.length){
            return false;
        }
        if(array[x1][y1] == 0 || array[x1][y1] == 2){
            return false;
        }
        return true;
    }

//    ===========================================Question 3===============================================================

}
