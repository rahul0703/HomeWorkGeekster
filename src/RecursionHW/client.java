package RecursionHW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

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
}
