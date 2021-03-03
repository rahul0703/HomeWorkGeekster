package BinaryTreeHW;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        elementsModi root = new elementsModi(10);
        root.left = new elementsModi(20);
        root.right = new elementsModi(30);
        root.left.left = new elementsModi(5);
        root.left.right = new elementsModi(6);
        root.right.left = new elementsModi(7);
        root.right.right = new elementsModi(8);
        root.left.left.left = new elementsModi(4);
        root.left.left.right = new elementsModi(9);
        root.right.right.right = new elementsModi(23);
        root.left.right.right = new elementsModi(21);
        root.left.left.left.left = new elementsModi(2);
//        util.MorrisInorder(root);
//        util.postOrderIterative2StackMethod(root);
//        util.MorrisPreorder(root);
        util.PrintBinaryTreeVerticalOrder(root);
    }
}
