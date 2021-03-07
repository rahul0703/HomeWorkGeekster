package BinarySearchTree;

import java.util.*;

public class main {
    public static void main(String[] args){
        Node root = new Node(20);
        root.left = new Node(10);
        root.left.left = new Node(5);
        root.left.left.left = new Node(2);
        root.left.left.left.left = new Node(1);
        root.left.left.right = new Node(7);
        root.left.left.right.left = new Node(6);
        root.left.left.right.right = new Node(8);
        root.left.left.right.right.right = new Node(9);
        root.left.right = new Node(15);
        root.left.right.left = new Node(12);
        root.left.right.right = new Node(17);
        root.left.right.left.left = new Node(11);
        root.left.right.left.right = new Node(13);
        root.left.right.right.left = new Node(16);
        root.left.right.right.right = new Node(19);
        root.right = new Node(30);
        root.right.left = new Node(25);
        root.right.left.left = new Node(22);
        root.right.left.right = new Node(28);
        root.right.right = new Node(35);
        root.right.right.left = new Node(32);

        System.out.println("-------------------------------------------------------------------------");
//        client.RemoveElement(root, 15);
//        client.Inorder(root);
//        client.LCA(root, 4, 12);
        client.TargetSunPair(root, 40);
    }
}
