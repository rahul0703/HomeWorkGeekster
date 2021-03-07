package StacksQueueHW;
import java.lang.reflect.Array;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = scan.nextInt();
        }
//        int[] answer = client.NextGreater(array);
        int[] answer = client.nextSmallerElements(array);
        for(int i : answer){
            System.out.print(i + " ");
        }
    }
}
