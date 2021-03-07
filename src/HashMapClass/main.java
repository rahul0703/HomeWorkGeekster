package HashMapClass;
import  java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        String[] array = new String[N];
//        for(int i = 0; i < N; i++){
//            array[i] = scan.next();
//        }
//        ArrayList<ArrayList<String>> answer = util.groupStrings(array);
//        for(ArrayList<String> ans : answer){
//            System.out.print("[ ");
//            for(String ans1 : ans){
//                System.out.print(ans1 + " ");
//            }
//            System.out.print("];");
//            System.out.println();
//        }
        String str = scan.next();
        System.out.print(util.maxLengthOfUniqueString(str));
    }
}
