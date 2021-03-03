package BinaryTreeHW;
import java.util.*;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(6);
        root.right.left = new Node(7);
        root.right.right = new Node(8);
        root.left.left.left = new Node(4);
        root.left.left.right = new Node(9);
        root.right.right.right = new Node(23);
        root.left.right.right = new Node(21);
        root.left.left.left.left = new Node(2);
//        util.MorrisInorder(root);
//        util.postOrderIterative2StackMethod(root);
        util.MorrisPreorder(root);
    }
}
