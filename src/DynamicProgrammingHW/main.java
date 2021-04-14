package DynamicProgrammingHW;
import java.util.*;

public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        int[] array = {4, 7, 5, 6};
//        int k = 2;
//        int W = 8;
//        int val[] = {10, 40, 50, 70};
//        int wt[]  = {1, 3, 4, 5};
//        System.out.println(util.unboundedKnapScak(W, wt, val));
//        System.out.println(util.largestSumSubarray(array, k));
//        int[][] array1 = {{0, 1, 1, 0, 1}, {1, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};
//        int capacity = 4;
//        System.out.println(util.maximumSizeSquareMatrix(array1));
//        System.out.println(util.uglyNumber(15));
//        System.out.println(util.optimalBST(array, 2));
//        System.out.println(util.subsetSumTest(21));
//        System.out.println(util.booleanParanthesis("TFF", "^|"));
//        System.out.println(util.countWays(45, "T&T|F|F^F^T^T^T&T^F^T&F|F^F^F&F&F|F|F^F^T|T&T"));
//        System.out.println(util.solution("TFFTFFFTTTTFTFFTTF", "|^&|^^^|&^|^^&^|^"));
//        int[] array = {2, 3, 1, 5, 6, 4};
//        System.out.println(util.burstBallon(array));
//        util.wordWrap(array, capacity);
//        System.out.println(util.waterOverflow(10, 4, 1));
//        System.out.println(util.removeMinElements(array));
//        System.out.println(util.countSequence("cccabacabbcbbcbcbcbcbcaacbaccabcbbacccaccacaaabcaacabbacaacbacacbbcbbcbccbbbabbaabacccbacabacbcaccaaaccbbacaccbacbbaaaabbbaaaabbccacbcbcacabbabcaccccbcbaabbcbbabbcbabcaabbcbbcacbbcaacaaabbcabbbbbbbccccabaabbbccbcbacacaccabacbbaacbcbaacbaabccaacbabcbcacacabaabbbbaabbbccacbababacacaacbaacbaaccccbaaacabbcabcbcaccbaccbacccaabcabababcbabbcbcaabaccbcbaaacbababbabababcbaacaaababbabacacacbbcbbaaacabbabbbcabbabccaacbcbcbcaaacccaaccbbbbacbacbcbaabcccabcabcccbcbbaabcacabababbbbabbbbccabcbabccabcaccbbbcccbc"));

//        System.out.println(util.lenLongestValidParenthesis("(((((((()(()))))"));
//        System.out.println(util.digitDecoding("121"));
//        System.out.println(util.longestEvenLenSubstingEqual("123123"));
        int[] array1 = {3, 4, 9, 1, 6, 8};
        int[] array2 = {5, 3, 8, 9, 10, 2, 6, 1, 8};
        System.out.println(util.longestCommonIncreasingSubsequence(array1, array2));
    }
}
