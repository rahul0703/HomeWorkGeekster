package BinaryTreeHW;
import java.lang.reflect.Array;
import java.util.*;
public class main {
    public static void main(String[] args){
//        Scanner scan = new Scanner(System.in);
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(5);
        root.left.right = new Node(6);
        root.right.left = new Node(7);
        root.right.right = new Node(8);
        root.left.left.left = new Node(4);
        root.left.left.right = new Node(9);
        root.right.right.right = new Node(11);
        root.left.right.right = new Node(12);
        root.left.left.left.left = new Node(2);
//        util.MorrisInorder(root);
//        util.postOrderIterative2StackMethod(root);
//        util.MorrisPreorder(root);
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//
//        root.left.left.left = new Node(8);
//        root.left.left.right = new Node(9);
//        root.left.right.left = new Node(10);
//        root.left.right.right = new Node(11);
//        root.right.left.left = new Node(12);
//        root.right.left.right = new Node(13);
//        root.right.right.left = new Node(14);
//        root.right.right.right = new Node(15);
//
//        root.left.left.left.left = new Node(16);
//        root.left.left.left.right = new Node(17);
//        root.left.left.right.left = new Node(18);
//        root.left.left.right.right = new Node(19);
//        root.left.right.left.left = new Node(20);
//        root.left.right.left.right = new Node(21);
//        root.left.right.right.left = new Node(22);
//        root.left.right.right.right = new Node(23);
//        root.right.left.left.left = new Node(24);
//        root.right.left.left.right = new Node(25);
//        root.right.left.right.left = new Node(26);
//        root.right.left.right.right = new Node(27);
//        root.right.right.left.left = new Node(28);
//        root.right.right.left.right = new Node(29);
//        root.right.right.right.left = new Node(30);
//        root.right.right.right.right = new Node(31);
//        util.specificLevelOrderTraversal(root);
//        int[] array1 = {10, 20, 5, 4, 9, 6, 30, 7, 8};
//        Character[] array2 = {'N', 'N', 'N', 'L', 'L', 'L', 'N', 'L', 'L'};
//        util.preorderToTree(array1, array2);
//        int[] parent = {1, 5, 5, 2, 2, -1, 3};
//        util.parentArrayToBinaryTree(parent);
//        System.out.print(util.lowestCommonAncestor(root, root.left.right,root.left.left ));
//        int mat[][] = {
//                { 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 }};
//        Node root1 = util.ancestorMatrixToTree(mat);
//        util.printTree(root1);

//        System.out.println(util.pathWithGivenSum(root, 10));
//        util.LeftClone(root);
//        int ans = util.verticalSum(root);
//        System.out.println(ans);
        util.deepestLeftLeafNode(root);
    }
}
