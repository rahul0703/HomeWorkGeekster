package RecursionHW;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        int[][] answer = client.KnightTourProblem(8);
//        for(int[] i : answer){
//            for(int j : i){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }

        int[][] question = { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0} };
        int[][] answer = client.SudokuSolver(question);
        for(int[] i : answer){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
//        HashSet<String> set = new HashSet<>();
//        set.add("i");
//        set.add("like");
//        set.add("sam");
//        set.add("sung");
//        set.add("samsung");
//        set.add("mobile");
//        set.add("ice");
//        set.add("cream");
//        set.add("icecream");
//        ArrayList<String> ans = client.wordBreakDictionary("ilikesamsungmobile", set);
//        for(String st : ans){
//            System.out.println(st);
//        }
//        ArrayList<ArrayList<String>> ans = client.palindromicPartitionStrings("geeks");
//        for(ArrayList<String> ans1 : ans){
//            for(String s : ans1){
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }
//        int[][] question = {{1, 1, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 1},{0, 1, 1, 0, 1, 1}, {0, 1 ,1 ,1, 1, 1}, {1, 0, 1, 0, 1, 1},{0, 1, 0, 1, 0, 1}};
////        int answer = client.ShortestPathFullOfLandMines(question, 6);
////        System.out.println(answer);
//        client.longestPossiblePath(question, 0, 0, 5, 5);
//        int[] array = {2, 4, 6, 8};
//        client.getAllCombinations(array, 8, 4);
//        client.cryptarythmicPuzzle("send", "more", "money");
//        client.patternMatching("geeksforgeeksrahulsurekarahulgeeks");
//        client.PalindromePartitioning("ababbbabbababa");
    }
}
