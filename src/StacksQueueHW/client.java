package StacksQueueHW;

import java.util.Stack;

public class client {

//    =========================================Important Functions=========================================================
//    Next Greater element...................................
    public static int[] NextGreater(int[] array){
        int[] arrayAnswer = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = array.length-1; i >= 0; i--){
            if(stack.isEmpty()){
                arrayAnswer[i] = -1;
                stack.push(i);
            } else if(array[stack.peek()] > array[i]){
                arrayAnswer[i] = stack.peek();
                stack.push(i);
            }else{
                while(!stack.isEmpty() && array[stack.peek()] <= array[i]){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    arrayAnswer[i] = -1;
                }else{
                    arrayAnswer[i] = stack.peek();
                }
                stack.push(i);
            }
        }
        return arrayAnswer;
    }


//    Next Smaller element...................................
    public static int[] nextSmallerElements(int[] array){
        int[] answer = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = array.length-1; i >= 0; i--){
            if(stack.isEmpty()){
                answer[i] = -1;
            }else if(array[stack.peek()] < array[i]){
                answer[i] = stack.peek();
            }else{
                while(!stack.isEmpty() && array[stack.peek()] >= array[i]){
                    stack.pop();
                }
                if(stack.isEmpty()){
                    answer[i] = -1;
                }else{
                    answer[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return answer;
    }
//    previous greater element...............................

//    Previous smaller element.................................

//    ============================================================================================================================
//    Largest Histogram......................................
//    solve expression inflix......................................
//    solve expression postflix...................................
//    solve expression preflix.................................
//    inflix to preflix.....................................
//    postflix to inflix...................................
//    inflix to postflix.......................................
//    inflix to postflix.........................................
//    Merge Intervals............................................
//    celebrity Problem............................................

}
