package DynamicProgClass;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        util.fibonaci(10);
//        System.out.println(util.maxePathBottomUp(0, 0, 3, 3));
//        int[][] array = {{10, 33, 13, 15}, {22, 21, 4, 1}, {5, 0, 2, 3}, {0, 6, 14, 2}};
//        System.out.println(util.maxGoldWithPath(array));
//        int[] array = {3, 2, 0, 2, 3, 1, 0, 1, 2, 0, 1};
//        int[] array = {2, 3, 5, 8, 2, 1, 4, 6,7, 9, 1};
//        System.out.println(util.LongestIncreasingSubsequence(array));
//            int T = scan.nextInt();
//            for(int c = 0; c < T; c++){
//                int N = scan.nextInt();
//                int K = scan.nextInt();
//                String str = scan.next();
//
//                int score = countScore(str);
//                int ans = K - score;
//                if(score > K){
//                    ans = score - K;
//                }else if(score == K){
//                    ans = 0;
//                }
//                int index = c+1;
//                System.out.println("Case #"+ index + ": " + ans);
//            }
//        }
//
//        public static int countScore(String str){
//            int count = 0;
//            for(int i = 0; i < str.length()/2; i++){
//                if(str.charAt(i) != str.charAt(str.length() -1 -i)){
//                    count++;
//                }
//            }
//            return count;
        int[] array = {2, 3, 1, 5, 6};
        int n = 11;
        System.out.println(util.targetSumPair(n, array));
        }
    }
