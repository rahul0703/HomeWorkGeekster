package BinaryTreeHW;

import com.sun.source.tree.Tree;

import java.util.*;

public class util {

//    =================================================Required Private Classes============================================================
    public class tree{
        Node root;

        public tree(int data){
            this.root = new Node(data);
        }
    }


//    Important functions .......................................................



//    ===============================================Question 1===========================================================================
//    (imp) Clone a binary Tree with random pointers and return head.................................
//    solution: a given tree has 3 pointers left, right, random. we need to clone the tree with random pointer means map[tree].random = mp[tree.random];
    public static NodeClone GetHeadOfCloneTree(NodeClone tree){
        if(tree == null){
            return null;
        }
        HashMap<NodeClone, NodeClone> map = new HashMap<>();
        NodeClone copyTree = NewCopy(tree, map);
        cloneTree(tree, map);
        return copyTree;
    }

    public static NodeClone NewCopy(NodeClone tree, HashMap<NodeClone, NodeClone> map){
        if(tree == null){
            return null;
        }
        NodeClone newTree = new NodeClone(tree.data);
        map.put(tree, newTree);
        newTree.left = NewCopy(tree.left, map);
        newTree.right = NewCopy(tree.right, map);
        return newTree;
    }

    public static void cloneTree(NodeClone tree, HashMap<NodeClone, NodeClone> map){
        if(tree == null){
            return;
        }
        map.get(tree).random = map.get(tree.random);
        cloneTree(tree.left, map);
        cloneTree(tree.right, map);
        return;
    }


//    ===============================================Question 2====================================================================
//    Count Number of SubTrees having given Sum
    public static int countSubtreesWithSumX(Node root, int X) {
        //Add your code here.
        int count = 0;
        elements ans = getanswer(root, X);
        return ans.count;
    }

    public static elements getanswer(Node root, int x){
        if(root == null){
            elements ans = new elements(null);
            ans.count = 0;
            ans.sum = 0;
            return ans;
        }
        elements left = getanswer(root.left, x);
        elements right = getanswer(root.right, x);
        elements answer = new elements(root);
        answer.sum = answer.node.data + left.sum + right.sum;
        if(answer.sum == x){
            answer.count = left.count + right.count + 1;
        }else{
            answer.count = left.count + right.count;
        }
        return answer;
    }


//    ======================================================Question 3===============================================================
//inorder iterative done in class refer class notes........................................................

//    ======================================================Question 4===============================================================
// (IMP) Iterative tree traversal without stack (Morris traversal)....................................................
    public static void MorrisInorder(Node current){
        if(current == null){
            return;
        }
        while(current != null){
            if(current.left == null){
                System.out.print(current.data + "  ");
                current = current.right;
            }else{
                Node predeceser = current.left;
                while(predeceser.right != current && predeceser.right != null){
                    predeceser = predeceser.right;
                }
                if(predeceser.right == null){
                    predeceser.right = current;
                    current = current.left;
                }else{
                    predeceser.right = null;
                    System.out.print(current.data + "  ");
                    current = current.right;
                }
            }
        }
    }


//    ============================================Question 5=============================================================
//    (IMP) Morris traversal for preorder.....................................................
    public static void MorrisPreorder(Node current){
        if(current == null){
            return;
        }
        while(current != null){
            if(current.left == null){
                System.out.print(current.data + "  ");
                current = current.right;
            }else{
//                System.out.print(current.data + "  ");
                Node predeceser = current.left;
                while(predeceser.right != current && predeceser.right != null){
                    predeceser = predeceser.right;
                }
                if(predeceser.right == null){
                    predeceser.right = current;
                    System.out.print(current.data + "  ");
                    current = current.left;
                }else{
                    predeceser.right = null;
                    current = current.right;
                }
            }
        }
    }



//================================================Question 6==============================================================
//    (IMP) PostOrder iterative 2 stack method..............................................................
    public static void postOrderIterative2StackMethod(Node root){
        if(root == null){
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty()){
            Node node = stack1.pop();
            if(node.left != null){
                stack1.push(node.left);
            }
            if(node.right != null){
                stack1.push(node.right);
            }

            stack2.push(node);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().data + "  ");
        }
    }

//===========================================================Question 7=============================================================
//    Diagonal Traversal of a Binary Tree..............................................................
    public static void diagonalTraversal(Node node){
        TreeMap<Integer, ArrayList<Node>> map = new TreeMap<>();
        InorderDiagonal(node, map, 0);
        for(Map.Entry<Integer, ArrayList<Node>> entry : map.entrySet()){
            ArrayList<Node> ans = entry.getValue();
            for(Node nodeAns : ans){
                System.out.print(nodeAns.data + " ");
            }
        }
        return;
    }

    public static void InorderDiagonal(Node node, TreeMap<Integer, ArrayList<Node>> map, int current){
        if(node == null){
            return;
        }
        if(!map.containsKey(current)){
            ArrayList<Node> ans = new ArrayList<>();
            ans.add(node);
            map.put(current, ans);
        }else{
            ArrayList<Node> ans = map.get(current);
            ans.add(ans.size(), node);
            map.put(current, ans);
        }
        InorderDiagonal(node.left, map, current+1);
        InorderDiagonal(node.right, map, current);
        return;
    }


//    ========================================================Question 8======================================================
//    Print the binary tree in vertical order.....................................................
//    here I have changed the property of the Node but if we don't have permission to change it, we can just level order traverse insted of Inorder

    public static void PrintBinaryTreeVerticalOrder(elementsModi node){
        if(node == null){
            return;
        }
        TreeMap<Integer, ArrayList<elementsModi>> map = new TreeMap<>();
        node.height = 0;
        getTheFilledHashMap(node, map, 0);
        for(Map.Entry<Integer, ArrayList<elementsModi>> entry : map.entrySet()){
            ArrayList<elementsModi> ans = entry.getValue();
            Collections.sort(ans, new Comparator<elementsModi>() {
                @Override
                public int compare(elementsModi o1, elementsModi o2) {
                    return o1.height - o2.height;
                }
            });
            for(elementsModi i : ans){
                System.out.print(i.data + " ");
            }
            System.out.println();
        }
    }

    public static void getTheFilledHashMap(elementsModi node, TreeMap<Integer, ArrayList<elementsModi>> map, int current){
        if(node == null){
            return;
        }
        if(!map.containsKey(current)){
            ArrayList<elementsModi> ans = new ArrayList<>();
            ans.add(node);
            map.put(current, ans);
        }else{
            ArrayList<elementsModi> ans = map.get(current);
            ans.add(node);
            map.put(current, ans);
        }
        if(node.left != null){
            node.left.height = node.height + 1;
        }
        if(node.right != null){
            node.right.height = node.height + 1;
        }
        getTheFilledHashMap(node.left, map, current-1);
        getTheFilledHashMap(node.right, map, current+1);

        return;
    }


//    ==========================================================Question 9==========================================================
    
}
