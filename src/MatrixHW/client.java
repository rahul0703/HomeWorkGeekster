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

//    =====================================================================================================================
    private static int[] rightSmaller(int[] array){
        int n = array.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for(int i = n-1; i >= 0; i--){
            int a = array[i];
            if(stack.isEmpty()){
                answer[i] = n;
            }else{
                while(!stack.isEmpty() && array[stack.peek()] >= a){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    answer[i] = n;
                }else{
                    answer[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return answer;
    }

    private static int[] leftSmaller(int[] array){
        int n = array.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for(int i = 0; i < n; i++){
            if(stack.isEmpty()){
                answer[i] = -1;
            }else {
                while (!stack.isEmpty() && array[i] <= array[stack.peek()]){
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    answer[i] = -1;
                } else {
                    answer[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return answer;
    }

    private static int[] largestHistogram(int[] array){
        int[] leftSmaller = leftSmaller(array);
        int[] rightSmaller = rightSmaller(array);
        int maxSum = Integer.MIN_VALUE;
        int x = 0;
        int y = 0;
        int[] answer = new int[3];
        for(int i = 0; i < array.length; i++){
            int sum = array[i] * (rightSmaller[i] - leftSmaller[i] -1);
            if(sum > maxSum){
                maxSum = sum;
                x = array[i];
                y = (rightSmaller[i] - leftSmaller[i] -1);
            }
        }
        answer[0] = x;
        answer[1] = y;
        answer[2] = maxSum;
        return answer;
    }

    public static int largestAreaMatrix(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[] finalAns = new int[3];
        int area = 1;
        for(int i = 1; i < n; i++){
            int[] array = new int[m];
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 1){
                    matrix[i][j] = matrix[i][j] + matrix[i-1][j];
                }
                array[j] = matrix[i][j];
            }
            int[] ans = largestHistogram(array);
            if(ans[2] > area){
                finalAns = ans;
                area = ans[2];
            }
        }
        for(int i = 0; i < finalAns[0]; i++){
            for(int j = 0; j < finalAns[1]; j++){
                System.out.print("1 ");
            }
            System.out.println();
        }
        return finalAns[2];
    }


//    find a specific pair in matrix

    public static int findSpecificPairInMatrix(int[][] matrix){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int n = matrix.length;
        int sumMax = 0;
        queue.add(matrix[0][0]);
        int i = 1;
        Stack<Integer> stack = new Stack<>();
        while(i < n){
            for(int j = 0; j <= i; j++){
                int sum = matrix[i][j] - queue.peek();
                sumMax = Math.max(sum, sumMax);
                sum = matrix[j][i] - queue.peek();
                sumMax = Math.max(sum, sumMax);
                stack.push(matrix[i][j]);
                stack.push(matrix[j][i]);
            }
            while(!stack.isEmpty()){
                queue.add(stack.pop());
            }
            i++;
        }
        return sumMax;
    }

//    =================================================================================================================

    private static int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * KMP algorithm of pattern matching.
     */
    public static  boolean KMP(char []text, char []pattern){

        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        if(j == pattern.length){
            return true;
        }
        return false;
    }
//    ====================================================================================================================
//    Shortest path in binary Maze
    public static int shortestPathInBinaryMaze(int[][] maze, int sourceX, int sourceY, int desX, int desY){
        int n = maze.length;
        boolean[][] boolArray = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                boolArray[i][j] = false;
            }
        }
        Queue<pair2> queue = new LinkedList<>();
        queue.add(new pair2(sourceX, sourceY, 0));
        int distance = depthFirstSearch(maze, boolArray, desX, desY, queue);
        return distance;
    }

    private static int depthFirstSearch(int[][] maze, boolean[][] boolArray, int x, int y, Queue<pair2> queue){
        int minDIs = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            pair2 pollPair = queue.poll();
            int xPair = pollPair.indexX;
            int yPair = pollPair.indexY;
            int dis = pollPair.disTravel;
            if(xPair == x && yPair == y){
                minDIs = Math.min(minDIs, dis);
            }
            if(!boolArray[xPair][yPair]){
                boolArray[xPair][yPair] = true;
                addAllNeighbour(xPair, yPair, boolArray, queue, dis, maze);
            }
        }
        return minDIs;
    }


    private static void addAllNeighbour(int x, int y, boolean[][] boolArray, Queue<pair2> queue, int dis, int[][] maze){
        int[] Xd = {1, -1, 0, 0};
        int[] Yd = {0, 0, 1, -1};

        for(int i = 0; i < Xd.length; i++){
            if(x + Xd[i] >= 0 && x + Xd[i] < maze.length && y + Yd[i] >= 0 && y + Yd[i] < maze.length && maze[x+ Xd[i]][y+ Yd[i]] == 1 && !boolArray[x + Xd[i]][y + Yd[i]]){
                queue.add(new pair2(x+ Xd[i], y + Yd[i], dis+1));
            }
        }
        return;
    }


//    ====================================================================================================================
//    Kth smallest element from matrix
    public static int KthMinElementFromMatrix(int[][] matrix, int k){
        PriorityQueue<pair3> queue = new PriorityQueue<>();
        for(int i = 0; i < matrix.length; i++){
            queue.add(new pair3(matrix[i][0], i, 0));
        }
        int ans = -1;
        for(int i = 0; i < k; i++){
            pair3 ele = queue.poll();
            int row = ele.row;
            int col = ele.col;
            if(col < matrix[0].length -1){
                queue.add(new pair3(matrix[row][col+1], row, col+1));
            }
            ans = ele.value;
        }
        return ans;
    }
//    ==================================================================================================================


}
