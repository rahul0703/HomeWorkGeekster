package BinaryTreeHW;

import com.sun.source.tree.Tree;

import javax.swing.*;
import java.lang.reflect.Array;
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
    public static void printTree(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.data + " ");
        printTree(node.left);
        printTree(node.right);
        return;
    }


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
//    (IMP) boundary traversal.................................................................................
    public static ArrayList <Integer> printBoundary(Node node) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(node.data);
        printLeft(node.left, ans);
        printLeaf(node.left, ans);
        printLeaf(node.right, ans);
        printRight(node.right, ans);
        return ans;
    }

    public static void printLeft(Node node, ArrayList<Integer> ans){
            if(node == null){
                return;
            }
            if(node.left != null){
                ans.add(node.data);
                printLeft(node.left, ans);
            }else if(node.right != null){
                ans.add(node.data);
                printLeft(node.right, ans);
            }
        }

    public static void printLeaf(Node node, ArrayList<Integer> ans){
            if(node == null){
                return;
            }
            printLeaf(node.left, ans);
            if(node.left == null && node.right == null){
                ans.add(node.data);
            }
            printLeaf(node.right, ans);
        }

    public static void printRight(Node node, ArrayList<Integer> ans){
            if(node == null){
                return;
            }
            if(node.right != null){
                printRight(node.right, ans);
                ans.add(node.data);
            }else if(node.left != null){
                printRight(node.left, ans);
                ans.add(node.data);
            }
    }

//    ================================================Question 10==================================================================
//    print binary tree specific level order traversal.......................
    public static void specificLevelOrderTraversal(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);
        ArrayList<Integer> answer = new ArrayList<>();
        Deque<Integer> list = new LinkedList<>();
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            if(poll == null && queue.peek() == null){
                break;
            }else if(poll == null && queue.peek() != null){
                int i = 0;
                int j = list.size()-1;
                while(!list.isEmpty()){
                    answer.add(list.pollFirst());
                    if(!list.isEmpty()){
                        answer.add(list.pollLast());
                    }
                }
                queue.add(null);
            }else{
                list.add(poll.data);
                if(poll.left != null){
                    queue.add(poll.left);
                }
                if(poll.right != null){
                    queue.add(poll.right);
                }
            }
        }
        while(!list.isEmpty()){
            answer.add(list.pollFirst());
            if(!list.isEmpty()){
                answer.add(list.pollLast());
            }
        }
        for(int k : answer){
            System.out.print(k + " ");
        }
    }


//    ======================================================Question 11============================================================
//    Preorder to treeDisplay....................................
    public static  int current = 0;
    public static void preorderToTree(int[] array1, Character[] array2){
        Node root = new Node(array1[current]);
        preorderTree(root, array1, array2);
        preorder(root);
    }

    public static void preorderTree(Node node, int[] array1, Character[] array2){
        if(current + 1>= array1.length){
            return;
        }
        if(array2[current] == 'L'){
            return;
        }
        current += 1;
        node.left = new Node(array1[current]);
        preorderTree(node.left, array1, array2);
        current += 1;
        node.right = new Node(array1[current]);
        preorderTree(node.right, array1, array2);
    }

    public  static void preorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

//    ========================================================Question 12======================================================
//    construct a tree from ancestor matrix


//    ========================================================Question 13=====================================================
//    Construct a ancestor matrix from given binary tree
    public static void constructMatrix(Node root){
        int max = max(root, 0);
        int[][] matrix = new int[max+1][max+1];
        ArrayList<Integer> array = new ArrayList<>();
        fillMatrix(matrix, root, array);
        printMatrix(matrix);
    }
    public static int max(Node root, int max){
        if(root == null){
            return max;
        }
        if(root.data > max){
            max = root.data;
        }
        int max1 = max(root.left, max);
        int max2 = max(root.right, max);
        max = Math.max(max, Math.max(max1, max2));
        return max;
    }

    public static void fillMatrix(int[][] matrix, Node root, ArrayList<Integer> arr){
        if(root == null){
            return;
        }
        for(int i : arr){
            matrix[i][root.data] = 1;
        }
        arr.add(root.data);
        fillMatrix(matrix, root.left, arr);
        fillMatrix(matrix, root.right, arr);
        arr.remove(arr.size()-1);
        return;
    }

    private static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


//    ===================================================Question 14================================================================
//    construct a binary tree from parent array......................................................
    public static void parentArrayToBinaryTree(int[] array){
        HashMap<Integer, Node> set = new HashMap<>();
        int root = -1;
        for(int i = 0; i < array.length; i++){
            set.put(i, new Node(i));
            if(array[i] == -1){
                root = i;
            }
        }
        for(int i = 0; i < array.length; i++){
            if(set.containsKey(array[i])){
                Node node = set.get(array[i]);
                Node child = set.get(i);
                if(node.left == null){
                    node.left = child;
                }else{
                    node.right = child;
                }
            }
        }
        inorder(set.get(root));
    }

    private static void inorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        inorder(root.left);
        inorder(root.right);
    }

//    =================================================Question 15=============================================================
//    Create a doubly linkedList from a ternary tree................................
    public static DoublyLinkedList doublyLinkedListFromTernaryTree(ternaryNode root){
        ArrayList<ternaryNode> list = new ArrayList<>();
        InorderTernary(root, list);
        ArrayList<DoublyLinkedList> list2 = new ArrayList<>();
        for(ternaryNode node : list){
            list2.add(new DoublyLinkedList(node));
        }
        for(int i = 1; i < list2.size(); i++){
            list2.get(i-1).next = list2.get(i);
            list2.get(i).prev = list2.get(i-1);
        }
        return list2.get(0);
    }

    private static void InorderTernary(ternaryNode node, ArrayList<ternaryNode> list){
        if(node == null){
            return;
        }
        list.add(node);
        InorderTernary(node.left, list);
        InorderTernary(node.middle, list);
        InorderTernary(node.right, list);
    }

//    ==================================================Question 16===========================================================
    public static Node bToDLL(Node root) {
	//  Your code here
	    ArrayList<Node> list = new ArrayList<>();
	    InOrder(root, list);
	    list.get(0).left = null;
	    list.get(list.size() -1).right = null;
	    for(int i = 1; i < list.size(); i++){
	        list.get(i-1).right = list.get(i);
	        list.get(i).left = list.get(i-1);
	    }
	    return list.get(0);
    }

    private static void InOrder(Node node, ArrayList<Node> list){
        if(node == null){
            return;
        }
        InOrder(node.left, list);
        list.add(node);
        InOrder(node.right, list);
    }


//    ==============================================Question 17============================================================
//    transform tree to sumTree.......................
    public static void toSumTree(Node root){
        //add code here.
        int finaldata = postOrder(root);
        return;
    }

    private static int postOrder(Node node){
        if(node == null){
            return 0;
        }
        int data1 = postOrder(node.left);
        int data2 = postOrder(node.right);
        int data = node.data;
        node.data = data1 + data2;
        return node.data + data;
    }

//    =================================================Question 18==========================================================
//    Transform the tree that is store sum of all left sub tree and self...........................................
    public static void leftSubTreeSum(Node node){
        int dataFinal = preOrderLeftSubtree(node);
        printTree(node);
        return;
    }
    private static int preOrderLeftSubtree(Node node){
        if(node == null){
            return 0;
        }
        int data = preOrderLeftSubtree(node.left);
        int value = node.data;
        node.data = value + data;
        int data1 = preOrderLeftSubtree(node.right);
        return node.data + data1;
    }

//=================================================Question 19================================================================
//    (IMP) Convert binary tree to doubly linked list
    public static Node prev = null;
    public static Node Head = null;
    public static void TreeToDoublyLinkedListInPlace(Node root){
        InorderInPlace(root);
        printLinkedList(Head);
    }
    private static void InorderInPlace(Node node){
        if(node == null){
            return;
        }
        InorderInPlace(node.left);
        if(prev == null){
            prev = node;
            Head = node;
        }else{
            node.left = prev;
            prev.right = node;
            prev = node;
        }
        InorderInPlace(node.right);
        return;
    }
    private static void printLinkedList(Node root){
        while (root != null){
            System.out.print(root.data + " ");
            root = root.right;
        }
    }


//    ==============================================Question 20===============================================================
//    convert tree to doubly linked list circular
    public static Node head2 = null;
    public static Node prev2 = null;
    public static Node bTreeToClist(Node root)
    {
        //your code here
        if(root == null){
            return null;
        }
        Inorder2(root);
        prev2.right = head2;
        head2.left = prev2;

        return head2;

    }

    private static void Inorder2(Node root){
        if(root == null){
            return;
        }
        bTreeToClist(root.left);
        if(prev2 == null){
            prev2 = root;
            head2 = root;
        }else{
            root.left = prev2;
            prev2.right = root;
            prev2 = root;
        }
        bTreeToClist(root.right);
    }

//    ==============================================Question 21==============================================================
//convert a tree to forest of even nodes..........................................

//    ==============================================Question 22==============================================================
//    lowest common ancestor in binary tree............................................................
    public static Integer lowestCommonAncestor(Node root, Node node1, Node node2){
        if(node1 == null || node2 == null){
            return null;
        }
        ArrayList<Integer> path = nodeToRootPath(root, node1);
        ArrayList<Integer> path2 = nodeToRootPath(root, node2);
//        System.out.print(path.size() + " " + path2.size());
        Collections.reverse(path);
        Collections.reverse(path2);
        int i = 0;
        while (i < path.size() && i < path2.size()){
            if(path.get(i) == path2.get(i)){
                i++;
            }else{
                return path.get(i-1);
            }
        }
        return path.get(i-2);
    }

    private static ArrayList<Integer> nodeToRootPath(Node root, Node node){
        if(node == null || root == null){
            ArrayList<Integer> ans = new ArrayList<>();
            return ans;
        }
        if(root == node){
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(root.data);
            return ans;
        }
        ArrayList<Integer> ans1 = nodeToRootPath(root.left, node);
        if(ans1.size() > 0){
            ans1.add(root.data);
            return ans1;
        }
        ArrayList<Integer> ans2 = nodeToRootPath(root.right, node);
        if(ans2.size() > 0){
            ans2.add(root.data);
            return ans2;
        }
        ArrayList<Integer> blank = new ArrayList<>();
        return blank;
    }


//    ============================================question =========================================================
//   (IMP) make tree from ancestor matrix.........................
    public static Node ancestorMatrixToTree(int[][] array){
        int[] parent = new int[array.length];
        HashMap<Integer, Integer> IndexCount = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            int sum = 0;
            for(int j = 0; j < array[0].length; j++){
                if(array[i][j] == 1){
                    sum++;
                }
            }
            IndexCount.put(i, sum);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(IndexCount.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Node root = null;
        Node[] node = new Node[array.length];
        for(Map.Entry<Integer, Integer> set : IndexCount.entrySet()) {
            int key = set.getKey();
            int val = set.getValue();
            node[key] = new Node(key);
        }
        for(Map.Entry<Integer, Integer> set : IndexCount.entrySet()){
            int key = set.getKey();
            int val = set.getValue();
            node[key] = new Node(key);
            if(val != 0){
                for(int i = 0; i < parent.length; i++){
                    if(parent[i] == 0 && array[key][i] != 0){
                        if(node[key].left == null){
                            node[key].left = node[i];
                        }else{
                            node[key].right = node[i];
                        }
                        parent[i] = 1;
                    }
                    root = node[key];
                }
            }
        }
        return root;
    }

    public static void DisplayTree(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        DisplayTree(root.left);
        DisplayTree(root.right);
    }
}
