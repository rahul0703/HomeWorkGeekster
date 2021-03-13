package StacksQueueHW;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
    public static int SolveInflixOperation(String str){
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operator = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                operands.push(ch - '0');
            }else if(ch == '('){
                operator.push(ch);
            }else if(ch == ')'){
                while(operator.peek() != '('){
                    int opar2 = operands.pop();
                    int opar1 = operands.pop();
                    char oper = operator.pop();
                    int ans = solveExpression(opar1, opar2, oper);
                    operands.push(ans);
                }
                operator.pop();
            }else if(operator.isEmpty()){
                operator.push(ch);
            }else if(ch == '/' || ch == '*' || ch == '+' || ch == '-'){
                while(!operator.isEmpty() && operator.peek() != '(' && precdence(operator.peek()) >= precdence(ch)){
                    int opar2 = operands.pop();
                    int opar1 = operands.pop();
                    char oper = operator.pop();
                    int ans = solveExpression(opar1, opar2, oper);
                    operands.push(ans);
                }
                operator.push(ch);
            }
        }
        while (!operator.isEmpty()){
            int opar2 = operands.pop();
            int opar1 = operands.pop();
            char oper = operator.pop();
            int ans = solveExpression(opar1, opar2, oper);
            operands.push(ans);
        }
        return operands.pop();
    }

    private static int solveExpression(int a, int b, char ch){
        if(ch == '+')
            return a + b;
        else if(ch == '-')
            return a - b;
        else if(ch == '/')
            return a / b;
        else
            return a * b;
    }

    private static int precdence(char ch){
        if(ch == '+' || ch == '-'){
            return 1;
        }else if(ch == '/' || ch == '*'){
            return 2;
        }
        return 0;
    }
////    solve expression postflix...................................

    public static int solvePostFlixExpression(String str){
        Stack<Integer> operands = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                operands.push(ch - '0');
            }else if(ch == '+' || ch == '-' || ch == '/' || ch == '*'){
                int b = operands.pop();
                int a = operands.pop();
                int ans = solveExpression(a, b, ch);
                operands.push(ans);
            }
        }
        return operands.pop();
    }


//    solve expression preflix.................................
    public static int solvePreflixOperation(String str){
        Stack<Integer> operands = new Stack<>();
        for(int i = str.length()-1; i >= 0; i--){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                operands.push(ch - '0');
            }else if(ch == '-' || ch == '+' || ch == '/' || ch == '*'){
                int oper1 = operands.pop();
                int oper2 = operands.pop();
                int ans = solveExpression(oper1, oper2, ch);
                operands.push(ans);
            }
        }
        return operands.pop();
    }


//    inflix to preflix.....................................


//    postflix to inflix...................................
//    inflix to postflix.......................................
//    inflix to postflix.........................................
//    Merge Intervals............................................
//    celebrity Problem............................................

/*
    Home work questions.....................................................
*/
//    ======================================Question 1=========================================================================
//Queue based approach for first non-repeating character in a stream
    public static String[] firstNonRepeatingChar(String str){
        Queue<Character> queue = new LinkedList<>();
        int[] arrayCount = new int[27];
        String[] answerArray = new String[str.length()];
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            arrayCount[ch - 'a']++;
            if(queue.isEmpty()){
                if(arrayCount[ch - 'a'] > 1){
                    answerArray[i] = "-1";
                }else{
                    answerArray[i] = ch + "";
                    queue.add(ch);
                }
            }else{
                while(!queue.isEmpty() && arrayCount[queue.peek() - 'a'] > 1){
                    queue.poll();
                }
                if(queue.isEmpty()){
                    if(arrayCount[ch - 'a'] > 1){
                        answerArray[i] = "-1";
                    }else{
                        answerArray[i] = ch + "";
                        queue.add(ch);
                    }
                }else{
                    answerArray[i] = queue.peek() + "";
                    queue.add(ch);
                }
            }
        }
        return answerArray;
    }


//    ===========================================Question 2 last=========================================================
//    Sort the stack using recursion...........................................................
    public static void SortStack(Stack<Integer> stack){
        sort(stack);
        return;
    }
    private static void sort(Stack<Integer> stack){
        if(stack.size() == 1 || stack.isEmpty()){
            return;
        }
        int x = stack.pop();
        sort(stack);
        insert(stack, x);
        return;
    }

    private static void insert(Stack<Integer> stack, int x){
        if(stack.isEmpty()){
            stack.push(x);
            return;
        }
        if(stack.peek() < x){
            int y = stack.pop();
            insert(stack, x);
            stack.push(y);
        }else{
            stack.push(x);
        }
        return;
    }

//    ==============================================Question 4last===================================================
//Tower hanoi
    public static void TowerOfHanoi(int k){
        tower("A", "B", "C", k);
    }

    private static void tower(String source,  String des, String helper, int k){
        if(k == 0){
            return;
        }
        tower(source, helper, des, k-1);
        System.out.println("Move disk number " + k + "from " + source + " to " + des);
        tower(helper, des, source, k-1);
    }
}
