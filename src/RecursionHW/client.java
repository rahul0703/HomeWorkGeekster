package RecursionHW;

import java.util.*;

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
//    Sudoku solver
    public static int[][] SudokuSolver(int[][] array){
        int n = 9;
        solveSudoku(array, 0, 0, n);
        return array;
    }
    private static boolean solveSudoku(int[][] array, int x, int y, int size){
        if(x == size || y == size){
            return true;
        }
        int x1 = x;
        int y1 = y;
        if(y == size -1){
            y1 = 0;
            x1 = x+1;
        }else{
            y1 = y+1;
            x1 = x;
        }
        if(array[x][y] != 0){
            boolean ans = solveSudoku(array, x1, y1, size);
            if(ans == true){
                return true;
            }else{
                return false;
            }
        }else{
            for(int i = 1; i < 10; i++){
                if(isSafeSudoku(array, x, y, i)){
                    array[x][y] = i;
                    boolean ans = solveSudoku(array, x1, y1, size);
                    if(ans){
                        return true;
                    }else{
                        array[x][y] = 0;
                    }
                }
            }
            return false;
        }
    }

    private static boolean isSafeSudoku(int[][] array, int x, int y, int value){
        int y1 = (y / 3)*3;
        int y2 = (y/3 + 1)*3;
        int x1 = (x/3) * 3;
        int x2 = (x/3 + 1)*3;
        for(int i = 0; i < 9; i++){
            if(array[i][y] == value){
                return false;
            }
            if(array[x][i] == value){
                return false;
            }
        }
        for(int i = x1; i < x2; i++){
            for(int j = y1; j < y2; j++){
                if(array[i][j] == value){
                    return false;
                }
            }
        }
        return true;
    }

//    ====================================================Question 4=============================================================
//    Invalid paranthesis
    private static HashSet<String> hash = new HashSet<>();
    public static ArrayList<String> invalidParanthesis(String str){
        int minRemoval = getRemoval(str);
        int maxLength = str.length()- minRemoval;
        ArrayList<String> ans = solveParantheis(str, maxLength, "", 0);
        return ans;
    }

    private static ArrayList<String> solveParantheis(String str, int maxLength, String current, int index){
        if(current.length() == maxLength && getRemoval(current) == 0){
            ArrayList<String> ans1 = new ArrayList<>();
            if(hash.contains(current) == false){
                ans1.add(current);
                hash.add(current);
            }
            return ans1;
        }
        if(index >= str.length()){
            ArrayList<String> ans1 = new ArrayList<>();
            return ans1;
        }
        String current1 = current + str.charAt(index);
        ArrayList<String> ans = solveParantheis(str, maxLength, current1, index+1);
        ArrayList<String> ans2 = solveParantheis(str, maxLength, current, index+1);
        ArrayList<String> finalAns = new ArrayList<>();
        for(String st: ans){
            finalAns.add(st);
        }
        for(String st1: ans2){
            finalAns.add(st1);
        }
        return finalAns;
    }

    private static int getRemoval(String str){
        Stack<Character> stack = new Stack<>();
        int removal = 0;
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '('){
                stack.push(ch);
            }else if(ch == ')'){
                if(stack.isEmpty()){
                    stack.push(ch);
                }else if(stack.peek() == '('){
                    stack.pop();
                }else{
                    stack.push(ch);
                }
            }
        }
        return stack.size();
    }

//    ===============================================Question 5=============================================================
//word break problem dictionary
    private static HashSet<String> set1 = new HashSet<>();
    public static ArrayList<String> wordBreakDictionary(String str, HashSet<String> hash){
        int n = str.length();
        ArrayList<String> ans = wordBreak(str, 0, "", n, hash, "");
        return ans;
    }
    private static ArrayList<String> wordBreak(String str, int index, String current, int n, HashSet<String> set, String as){
        if(index == str.length()){
            ArrayList<String> ans = new ArrayList<>();
            if(set.contains(current)){
                String a = as + " " + current;
                a = a.trim();
                if(!set1.contains(a)){
                    ans.add(a);
                    set1.add(a);
                }
            }
            String st2 = as + current;
            st2 = st2.trim();
            if(set.contains(st2) && !set1.contains(st2)){
                ans.add(st2);
                set1.add(st2);
            }
            return ans;
        }
        ArrayList<String> ansFinal = new ArrayList<>();
        String st = str.charAt(index) + "";
        if(set.contains(current)){
            ArrayList<String> ans1 = wordBreak(str, index+1, st, n, set, as + " " + current);
            for(String i : ans1){
                ansFinal.add(i);
            }
        }
        ArrayList<String> ans2 = wordBreak(str, index+1, current + st, n, set, as);
        for(String i : ans2){
            ansFinal.add(i);
        }
        return ansFinal;
    }

//    ==================================================Question 6=========================================================
//    Print palindromic partition of strings.............................
//    public static ArrayList<ArrayList<String>> palindromicPartitionStrings(String str){
//        ArrayList<ArrayList<String>> ans = new ArrayList<>();
//        ArrayList<ArrayList<String>> ansFinal = palindromicSubstring(str, " ");
//        for(ArrayList<String> an : ansFinal){
////            Collections.reverse(an);
////            if(isPalindrome(an)){
//                ans.add(an);
////            }
//        }
//        System.out.println(ans.size());
//        return ans;
//    }

//    private static ArrayList<String> palindromicSubstring(String str, ArrayList<String> current) {
//        if(str.length() == 1){
//            for(St: current){
//
//            }
//        }
//    }

    public static boolean isPalindrome(ArrayList<String> ans){
        for(String str : ans){
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                char ch2 = str.charAt(str.length()-i-1);
                if(ch != ch2){
                    return false;
                }
            }
        }
        return true;
    }

//==================================================================================================================================
//    find the shortest safe route in the path full of landmines.
    private static int shortestLength = Integer.MAX_VALUE;
    public static int ShortestPathFullOfLandMines(int[][] array, int n){
        int startRow = 0;
        int startCol = 0;
        modifyPath(array, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }
        findPath(array, n, startRow, startCol, 0, visited);
        int ans = shortestLength;
        if(ans == Integer.MAX_VALUE){
            System.out.println("False");
            return -1;
        }
        return ans;
    }
    private static void modifyPath(int[][] array, int n){
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                if (array[i][j] == 0 && visited[i][j] == false) {
                    if (i > 0 && visited[i - 1][j] == false && array[i-1][j] != 0) {
                        array[i - 1][j] = 0;
                        visited[i - 1][j] = true;
                    }
                    if (i < n - 1 && visited[i + 1][j] == false && array[i+1][j] != 0) {
                        array[i + 1][j] = 0;
                        visited[i + 1][j] = true;
                    }
                    if (j > 0 && visited[i][j - 1] == false && array[i][j-1] != 0) {
                        array[i][j - 1] = 0;
                        visited[i][j - 1] = true;
                    }
                    if (j < array.length - 1 && visited[i][j + 1] == false && array[i][j+1] != 0) {
                        array[i][j + 1] = 0;
                        visited[i][j + 1] = true;
                    }
                    visited[i][j] = true;
                }
            }
        }
        return;
    }

    private static void findPath(int[][] array, int n, int row, int col, int sum, boolean[][] visited){
        if(row == n-1){
            if(sum < shortestLength){
                shortestLength = sum;
                return;
            }
        }
        if(col < n-1){
            if(isSafe(array, row, col+1, visited)){
                visited[row][col+1] = true;
                findPath(array, n, row, col+1, sum+1, visited);
                visited[row][col+1] = false;
            }
        }
        if(col > 0){
            if(isSafe(array, row, col-1, visited)){
                visited[row][col-1] = true;
                findPath(array, n, row, col-1, sum+1, visited);
                visited[row][col-1] = false;
            }
        }
        if(row > 0){
            if(isSafe(array, row-1, col, visited)){
                visited[row-1][col] = true;
                findPath(array, n, row-1, col, sum+1, visited);
                visited[row-1][col] = false;
            }
        }
        if(row < n-1){
            if(isSafe(array, row+1, col, visited)){
                visited[row+1][col] = true;
                findPath(array, n, row+1, col, sum+1, visited);
                visited[row+1][col] = false;
            }
        }
        return;
    }

    public static boolean isSafe(int[][] array, int row, int col, boolean[][] visited){
        if(array[row][col] == 0){
            return false;
        }
        if(visited[row][col] == true){
            return false;
        }
        return true;
    }

//    ===========================================================================================================================
//-=================================================================================================================================
//Combination sum

    private static ArrayList<ArrayList<Integer>> answerList = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> combination(ArrayList<Integer> array, int sum){
        HashSet<Integer> set = new HashSet<>();
        for(int i : array){
            set.add(i);
        }
        array.clear();
        array.addAll(set);
        Collections.sort(array);
        ArrayList<Integer> ans = new ArrayList<>();
        combinationSum1(array, sum, ans, 0);
        return answerList;
    }

    private static void combinationSum1(ArrayList<Integer> array, int sumCurrent, ArrayList<Integer> ans, int index){
        if(sumCurrent == 0){
            ArrayList<Integer> newList = new ArrayList<>();
            for(int i : ans){
                newList.add(i);
            }
            answerList.add(newList);
            return;
        }
        if(sumCurrent < 0){
            return;
        }

        for(int i = index; i < array.size(); i++){
            ans.add(array.get(i));
            sumCurrent -= array.get(i);
            combinationSum1(array, sumCurrent, ans, i);
            sumCurrent += array.get(i);
            ans.remove(ans.size()-1);
        }
        return;
    }

//    K Partition with equal sum
//    =====================================================================================================================
     public static boolean KPartitionSum(int[] array, int k){
         int sum = 0;
          for(int i : array){
              sum += i;
          }
          if(k > array.length){

              return false;
          }
          if(sum % k != 0){
              return false;
          }
          int[] sumInt = new int[k];
          boolean ansFinal = Kpart(array, k, 0, sumInt);
          return ansFinal;
     }
     // private static boolean ansFinal = false;
     public static boolean Kpart(int[] array, int k, int index, int[] sumInt){
         if(index == array.length){
             for(int i = 1; i < sumInt.length; i++){
                 if(sumInt[i] != sumInt[i-1] || sumInt[i] == 0){
                     return false;
                 }
             }
             return true;
         }

         for(int i = 0; i < k; i++){
             sumInt[i] += array[index];
             // answer.get(i).add(array[index]);
             boolean ret = Kpart(array, k, index + 1, sumInt);
             if(ret == true){
                 return true;
             }
             // answer.get(i).remove(answer.get(i).size()-1);
             sumInt[i] -= array[index];
         }
         return false;
     }


//     =======================================================================================================================
//    Longest possible path
    private static int longestpath = Integer.MIN_VALUE;
    public static void longestPossiblePath(int[][] array, int source1, int source2,  int desX, int desY){
        boolean[][] boolArray = new boolean[array.length][array.length];
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                boolArray[i][j] = false;
            }
        }
        int[] xPath = {1, -1, 0, 0};
        int[] yPath = {0, 0, 1, -1};
        boolArray[source1][source2] = true;
        LongestPath(array, desX, desY,  source1, source2, boolArray, 1, xPath, yPath);
        System.out.println(longestpath);
    }
    private static void LongestPath(int[][] array, int desX, int desY, int currentX, int currentY, boolean[][] boolArray, int pathLength, int[] xPath, int[] yPath){
        if(currentX == desX && currentY == desY){
            if(pathLength > longestpath){
                longestpath = pathLength;
                for(boolean[] i : boolArray){
                    for(boolean j : i){
                        System.out.print(j == true ? 1 + " " : 0 + " ");
                    }
                    System.out.println();
                }
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            }
            return;
        }
        for(int i = 0; i < xPath.length; i++){
            int NewX = currentX + xPath[i];
            int NewY = currentY + yPath[i];
            if(isSafeLongest(array, boolArray, NewX, NewY)){
                boolArray[NewX][NewY] = true;
                LongestPath(array, desX, desY, NewX, NewY, boolArray, pathLength+1, xPath, yPath);
                boolArray[NewX][NewY] = false;
            }
        }
        return;
    }

    private static boolean isSafeLongest(int[][] array, boolean[][] boolArray, int x, int y){
        if(x < 0 || x >= array.length || y < 0 || y >= array.length){
            return false;
        }
        if(boolArray[x][y] == true){
            return false;
        }
        if(array[x][y] == 0){
            return false;
        }
        return true;
    }

//    ===============================================================================================================================
//    Crytarhythmic puzzle
    public static void cryptarythmicPuzzle(String str1, String str2, String str3){
        TreeMap<Character, Integer> map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        String[] stringArray = {str1, str2, str3};
        for(String str : stringArray){
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                if(!map.containsKey(ch)){
                    map.put(ch, -1);
                    sb.append(ch);
                }
            }
        }
        boolean[] array = new boolean[10];
        for(int i = 0; i < 10; i++){
            array[i] = false;
        }
        String newString = sb.toString();
        getSumString(newString, map, array, str1, str2, str3, 0);
    }

    public static void getSumString(String newString, TreeMap<Character, Integer> map, boolean[] array, String str1, String str2, String str3, int current){
        if(current == newString.length()){
            int num1 = convertToNumber(str1, map);
            int num2 = convertToNumber(str2, map);
            int num3 = convertToNumber(str3, map);
            if(num1 + num2 == num3){
                for(Map.Entry<Character, Integer> entry : map.entrySet()){
                    System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
                }
                System.out.println();
            }
            return;
        }
        char chPre = newString.charAt(current);
        for(int i = 0; i < 10; i++){
            if(array[i] == false){
                array[i] = true;
                map.put(chPre, i);
                getSumString(newString, map, array, str1, str2, str3, current+1);
                map.put(chPre, -1);
                array[i] = false;
            }
        }
        return;
    }

    public static int convertToNumber(String str, TreeMap<Character, Integer> map){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            int value = map.get(ch);
            sb.append(value);
        }
        String valueString = sb.toString();
        if(valueString.charAt(0) == '0'){
            valueString = valueString.substring(1);
        }
        int answer = Integer.parseInt(valueString);
        return answer;
    }

//    ============================================================================================================================


}

