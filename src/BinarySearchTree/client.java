package BinarySearchTree;

import java.util.ArrayList;
import java.util.HashSet;

public class client {
//    =====================================================================================================
    public static int max(Node root){
        while(root.right != null){
            root = root.right;
        }
        return root.data;
    }

//    ============================================================================================
    public static int min(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }
//========================================================================================================
    public static void PrintRange(Node root, int low, int high){
        inorder(root, low, high);
        return;
    }
    public static void inorder(Node root, int low, int high){
        if(root == null){
            return;
        }
        if(root.data >= low && root.data <= high){
            System.out.println(root.data);
            inorder(root.left, low, high);
            inorder(root.right, low, high);
        }
        if(root.data < low){
            inorder(root.right, low, high);
        }
        if (root.data > high){
            inorder(root.left, low, high);
        }
        return;
    }

//    (IMP)====================================================================================================================
    public static int sum = 0;
    public static void ReplaceWithSumOfLargerNodes(Node root){
        if(root == null){
            return;
        }

        ReplaceWithSumOfLargerNodes(root.right);
        int x = root.data;
        root.data = sum;
        sum += x;
        ReplaceWithSumOfLargerNodes(root.left);
    }

    public static void Inorder(Node root){
        if(root == null){
            return;
        }
        Inorder(root.left);
        System.out.println(root.data);
        Inorder(root.right);
    }

//===============================add a given element===========================================================
    public static void RemoveElement(Node root, int data){
        remove(root, data);
    }

    public static void remove(Node root, int data){
        if(root == null){
            return;
        }
        if(root.data == data){
            replace(root);
        }
        if(root.data > data){
            remove(root.left, data);
        }else {
            remove(root.right, data);
        }
    }

    public static void replace(Node root){
        if(root.left == null && root.right == null){
            root = null;
        }else{
            if(root.left == null){
                root.data = root.right.data;
                root.right = null;
            }else{
                Node node = root.left;
                if(node.right == null){
                    root.data = node.data;
                    root.left = node.left;
                }else{
                    while (node.right.right != null){
                        node = node.right;
                    }
                    root.data = node.right.data;
                    node.right = null;
                }

            }
        }
    }

    public static void LCA(Node root, int x, int y){
        LCAxy(root, x, y);

    }
    public static void LCAxy(Node root, int x, int y){
        if(root == null){
            return;
        }
        if(root.data >= x && root.data <= y){
            System.out.println(root.data);
            return;
        }else if(root.data > x && root.data > y){
            LCAxy(root.left, x, y);
        }else{
            LCAxy(root.right, x, y);
        }
        return;
    }

    public static void TargetSunPair(Node root, int sum){
        targetSumPair(root, root, sum);
        return;
    }
    private static HashSet<Integer> set = new HashSet<>();
    public static void targetSumPair(Node node, Node root, int sum){
        if(root == null){
            return;
        }
        if(root.data >= sum){
            return;
        }
        int x = root.data;
        int y = sum - x;
        if(!set.contains(x)){
            if(x != y){
                boolean ans = find(node, y);
                if(ans == true){
                    System.out.println(x + " " + y);
                }
            }
            set.add(x);
            set.add(y);
        }

        targetSumPair(node, root.left, sum);
        targetSumPair(node, root.right, sum);
    }

    public static boolean find(Node root, int y){
        if(root == null){
            return false;
        }
        if(root.data == y){
            return true;
        }
        if(root.data > y){
            boolean ans1 = find(root.left, y);
            if(ans1 == true){
                return true;
            }
        }else{
            boolean ans2 = find(root.right, y);
            if(ans2 == true){
                return true;
            }
        }
        return false;
    }
}
