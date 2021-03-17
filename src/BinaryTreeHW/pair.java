package BinaryTreeHW;

public class pair {
    int leftSubTreeSum= 0;
    int rightSubTreeSum = 0;
    int sumSelf = 0;
    public pair(int left, int right, int data){
        this.leftSubTreeSum = left;
        this.rightSubTreeSum = right;
        this.sumSelf = data;
    }
}
