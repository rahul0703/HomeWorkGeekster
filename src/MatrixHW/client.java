package MatrixHW;
import java.util.*;
public class client {

//    ===============================UTILITY FUNCTION==================================================================
    public static void printMatrix(Character[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return;
    }


    public static void printMatrixInt(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return;
    }
//======================================================================================================================

//    ==================================================================================================================


    public static void digonalTraversal(int[][] array){
        int n = array.length;
        int m = array[0].length;
        for(int i = 0; i < m; i++){
            int x = 0;
            int y = i;
            while(y >= 0 && x < n){
                System.out.print(array[x][y] + " ");
                x++;
                y--;
            }
            System.out.println();
        }
        for(int j = 1; j < n; j++){
            int x1 = j;
            int y1 = m-1;
            while(y1 >= 0 && x1 < n){
                System.out.print(array[x1][y1] + " ");
                x1++;
                y1--;
            }
            System.out.println();
        }
    }

//    ===============================================================================================================
    public static void alternateXO(int n, int m){
        Character[][] array= new Character[n][m];
        int x1 = 0;
        int x2 = n-1;
        int y1 = 0;
        int y2 = m-1;
        char ch1 = 'x';
        char ch2 = 'o';
        while(x1 <= x2 && y1 <= y2){
            for(int i = x1; i <= x2; i++){
                array[i][y1] = ch1;
                array[i][y2] = ch1;
            }
            for(int j = y1; j <= y2; j++){
                array[x1][j] = ch1;
                array[x2][j] = ch1;
            }
            x1++;
            x2--;
            y1++;
            y2--;
            char ch = ch1;
            ch1 = ch2;
            ch2 = ch;
        }
        printMatrix(array);
    }


// ====================================================================================================================
    public static void replaceOwithX(Character[][] array){
        boolean[][] boolArray = new boolean[array.length][array[0].length];
        int n = array.length;
        int m = array[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                boolArray[i][j] = false;
            }
        }
        for(int i = 0; i < n; i++){
            boolArray[i][0] = true;
            if(array[i][0] == 'O'){
                array[i][0] = '1';
                dfs(array, boolArray, i, 0, n, m);
            }
            boolArray[i][m-1] = true;
            if(array[i][m-1] == 'O'){
                array[i][m-1] = '1';
                dfs(array, boolArray, i, m-1, n, m);
            }
        }

        for(int j = 0; j < m; j++){
            boolArray[0][j] = true;
            if(array[0][j] == 'O'){
                array[0][j] = '1';
                dfs(array, boolArray, 0, j, n, m);
            }
            boolArray[n-1][j] = true;
            if(array[n-1][j] == 'O'){
                array[n-1][j] = '1';
                dfs(array, boolArray, n-1, j, n, m);
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == '1'){
                    array[i][j] = 'O';
                }else if(array[i][j] == 'O'){
                    array[i][j] = 'X';
                }
            }
        }
        printMatrix(array);
    }

    private static void dfs(Character[][] array, boolean[][] boolArray, int i, int j, int n, int m){
        int[] array1 = {i-1, i+1};
        int[] array2 = {j-1, j+1};
        for(int value : array1){
            if(issafe(value, j, boolArray, n, m) && array[value][j] == 'O'){
                boolArray[value][j] = true;
                array[value][j] = '1';
                dfs(array, boolArray, value, j, n, m);
            }
        }
        for(int value : array2){
            if(issafe(i , value, boolArray, n, m) && array[i][value] == 'O'){
                boolArray[i][value] = true;
                array[i][value] = '1';
                dfs(array, boolArray, i, value, n, m);
            }
        }
        return;
    }

    private static boolean issafe(int i, int j, boolean[][] boolArray, int n, int m){
        if(i < 0 || i >= n || j < 0 || j >= m){
            return false;
        }

        if(boolArray[i][j] == true){
            return false;
        }
        return true;
    }


//    public static dfsXO(int[][] )

//    ====================================================================================================================
    public static int findLargestSqaue(int[][] array){
        int n = array.length;
        int max = 0;
        pair[][] fillMat = new pair[n][n];
        for(int i = 0; i < n; i++){
            if(array[i][0] == 0){
                fillMat[i][0] = new pair(0, 0);
            }else{
                if(i == 0){
                    fillMat[i][0] = new pair(1, 1);
                }else{
                    fillMat[i][0] = new pair(fillMat[i-1][0].row + 1, 1);
                }
            }
        }
        for(int i = 1; i < n; i++){
            if(array[0][i] == 0){
                fillMat[0][i] = new pair(0, 0);
            }else{
                fillMat[0][i] = new pair(1, fillMat[0][i-1].col + 1);
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(array[i][j] == 0){
                    fillMat[i][j] = new pair(0, 0);
                }else{
                    fillMat[i][j] = new pair(fillMat[i-1][j].row+1, fillMat[i][j-1].col+1);
                }
            }
        }
        for(int i = n-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                if(array[i][j] == 1){
                    int minCurr = Math.min(fillMat[i][j].row, fillMat[i][j].col);
                    while(minCurr > max){
                        int x = i - minCurr;
                        int y = j - minCurr;
                        int minX = fillMat[i][j-minCurr + 1].row;
                        int minY = fillMat[i-minCurr + 1][j].col;
                        if(minX >= minCurr && minY >= minCurr){
                            max = minCurr;
                            break;
                        }
                        minCurr--;
                    }
                }
            }
        }
        return max;
    }


    public static int findLargestSqaueChar(Character[][] array){
        int n = array.length;
        int max = 0;
        pair[][] fillMat = new pair[n][n];
        for(int i = 0; i < n; i++){
            if(array[i][0] == 'O'){
                fillMat[i][0] = new pair(0, 0);
            }else{
                if(i == 0){
                    fillMat[i][0] = new pair(1, 1);
                }else{
                    fillMat[i][0] = new pair(fillMat[i-1][0].row + 1, 1);
                }
            }
        }
        for(int i = 1; i < n; i++){
            if(array[0][i] == 'O'){
                fillMat[0][i] = new pair(0, 0);
            }else{
                fillMat[0][i] = new pair(1, fillMat[0][i-1].col + 1);
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(array[i][j] == 'O'){
                    fillMat[i][j] = new pair(0, 0);
                }else{
                    fillMat[i][j] = new pair(fillMat[i-1][j].row+1, fillMat[i][j-1].col+1);
                }
            }
        }
        for(int i = n-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                if(array[i][j] == 'X'){
                    int minCurr = Math.min(fillMat[i][j].row, fillMat[i][j].col);
                    while(minCurr > max){
                        int x = i - minCurr;
                        int y = j - minCurr;
                        int minX = fillMat[i][j-minCurr + 1].row;
                        int minY = fillMat[i-minCurr + 1][j].col;
                        if(minX >= minCurr && minY >= minCurr){
                            max = minCurr;
                            break;
                        }
                        minCurr--;
                    }
                }
            }
        }
        return max;
    }
}
