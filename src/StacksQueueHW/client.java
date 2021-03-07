package StacksQueueHW;

import java.util.ArrayList;
import java.util.Stack;

public class client {

//    =========================================Important Functions=========================================================
//    Next Greater element...................................
    public static int[] NextGreater(int[] array){
        int[] arrayAnswer = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = array.length-1; i >= 0; i--){
            if(stack.isEmpty()){
                arrayAnswer[i] = array.length;
                stack.push(i);
            } else if(array[stack.peek()] > array[i]){
                arrayAnswer[i] = stack.peek();
                stack.push(i);
            }else{
                while(!stack.isEmpty() && array[stack.peek()] <= array[i]){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    arrayAnswer[i] = array.length;
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
    public static int[] previousGreaterElements(int[] array){
        int[] answer = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < array.length; i++){
            if(stack.isEmpty()){
                answer[i] = array.length;
            }else if(array[stack.peek()] > array[i]){
                answer[i] = stack.peek();
            }else{
                while (!stack.isEmpty() && array[stack.peek()] <= array[i]){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    answer[i] = array.length;
                }else{
                    answer[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return answer;
    }


//    Previous smaller element.................................
    public static int[] previousSmallerElements(int[] array){
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[array.length];
        for(int i = 0; i < array.length; i++){
            if(stack.isEmpty()){
                answer[i] = -1;
            }else if(array[stack.peek()] < array[i]){
                answer[i] = stack.peek();
            }else{
                while (!stack.isEmpty() && array[stack.peek()] >= array[i]){
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


//    ============================================================================================================================


//    Largest Histogram......................................
    public static int largestHistogramArea(int[] array){
        int[] leftSmaller = previousSmallerElements(array);
        int[] nextSmaller = nextSmallerElements(array);
        int maxArea = 0;
        for(int i = 0; i < array.length; i++){
            int area = array[i] * (nextSmaller[i] - leftSmaller[i] - 1);
            maxArea = Math.max(area, maxArea);
//            System.out.println(leftSmaller[i] + " " + nextSmaller[i] + " " + area);
        }
        return maxArea;
    }
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
