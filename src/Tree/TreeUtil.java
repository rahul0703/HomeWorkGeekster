package Tree;
import java.util.*;


public class TreeUtil {


    // 	Display the tree function............................................................
    public static void display(Node node) {
        if (node == null) {
            return;
        }
        String str = "";
        str += node.left != null ? node.left.data + "->" : "null.->";
        str += node.right != null ? node.data + "<-" + node.right.data : node.data + "<-.null";
        System.out.println(str);
        display(node.left);
        display(node.right);
    }



    // 	Remove the last leave of the tree.................................................................................................
    public static void removeLeaf(Node parent, Node child){
        if(child == null){
            return;
        }
        if(child.left == null && child.right == null){
            if(parent.left == child){
                parent.left = null;
                return;
            }else{
                parent.right = null;
                return;
            }
        }
        removeLeaf(child, child.left);
        removeLeaf(child, child.right);
    }


    // 	Print the node with only 1 child.............................................................................................
    public static void singleChild(Node parent, Node child){
        if(child == null){
            return;
        }
        if(parent != null){
            if(parent.right == child && parent.left == null){
                System.out.println(child.data);
            }else if(parent.left == child && parent.right == null){
                System.out.println(child.data);
            }
        }
        singleChild(child, child.left);
        singleChild(child, child.right);
    }


    // 	Print the path from leave to root......................................................................
    public static void rootLeavePath(Node child, String str, int sum){
        if(child == null){
            return;
        }
        if(child.left == null && child.right == null){
            int sum1 = sum + child.data;
            if(sum1 > 5 && sum1 < 100){
                System.out.println(str + " " + child.data);
                return;
            }
        }
        rootLeavePath(child.left, str + " " + child.data, sum+child.data);
        rootLeavePath(child.right, str + " " + child.data, sum+child.data);
        return;
    }


    // 	Print the node K distance downwards.....................................................................
    public static void printKDow(Node child, Node blocker, int K){
        if(child == null || K < 0 || child == blocker){
            return;
        }
        if(K == 0){
            System.out.println(child.data);
            return;
        }
        printKDow(child.left, blocker, K-1);
        printKDow(child.right, blocker, K-1);
        return;
    }


    // 	Peint the node K distance away from the given node...........................................................
    public static void printGuyKDisAway(Node root, int K, Node child){
        ArrayList<Node> list = nodeToRootNode(child, root);
        for(int i = 0; i < list.size(); i++){
            Node blocker = (i == 0? null: list.get(i-1));
            printKDow(list.get(i), blocker, K-i);
        }
        return;
    }



    // 	Return a list containing path from leave to root with integer data.............................................................
    public static ArrayList<Integer> nodeToRoot(Node node1, Node root){
        if(node1 == null){
            ArrayList<Integer> list = new ArrayList<>();
            return list;
        }
        if(root == null){
            ArrayList<Integer> list = new ArrayList<>();
            return list;
        }
        if(root == node1){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(root.data);
            return list;
        }
        ArrayList<Integer> list1 = nodeToRoot(node1, root.left);
        ArrayList<Integer> list2 = nodeToRoot(node1, root.right);
        ArrayList<Integer> list3 = new ArrayList<>();
        if(list1.size() > 0){
            for(int value: list1){
                list3.add(value);
            }
            list3.add(root.data);
            return list3;
        }
        if(list2.size() > 0){
            for(int value: list2){
                list3.add(value);
            }
            list3.add(root.data);
            return list3;
        }
        return list3;
    }



    //  Return a list containing path from leave to root with Node data.............................................................
    public static ArrayList<Node> nodeToRootNode(Node node1, Node root){
        if(node1 == null){
            ArrayList<Node> list = new ArrayList<>();
            return list;
        }
        if(root == null){
            ArrayList<Node> list = new ArrayList<>();
            return list;
        }
        if(root == node1){
            ArrayList<Node> list = new ArrayList<>();
            list.add(root);
            return list;
        }
        ArrayList<Node> list1 = nodeToRootNode(node1, root.left);
        ArrayList<Node> list2 = nodeToRootNode(node1, root.right);
        ArrayList<Node> list3 = new ArrayList<>();
        if(list1.size() > 0){
            for(Node value: list1){
                list3.add(value);
            }
            list3.add(root);
            return list3;
        }
        if(list2.size() > 0){
            for(Node value: list2){
                list3.add(value);
            }
            list3.add(root);
            return list3;
        }
        return list3;
    }


    // 	Preorder Tree Traversal...................................................................................
    public static void Preorder(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        Preorder(root.left);
        Preorder(root.right);
        return;
    }


    //  Inorder Tree Traversal...................................................................................
    public static void Inorder(Node root){
        if(root == null){
            return;
        }
        Inorder(root.left);
        System.out.println(root.data);
        Inorder(root.right);
        return;
    }


    // PostOrder Tree Traversal..................................................................................
    public static void PostOrder(Node root){
        if(root == null){
            return;
        }
        PostOrder(root.left);
        PostOrder(root.right);
        System.out.println(root.data);
        return;
    }


    // Level Order Traversal....................................................................................
    public static void LevelOrderTraversal(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        Queue<Node> queue = new LinkedList<Node>();
        if(root.left != null){
            queue.add(root.left);
        }
        if(root.right != null){
            queue.add(root.right);
        }
        queue.add(null);
        LevelOrderTraversalrecursive(queue);
        return;
    }


    // Continue function level order traversal.....................................................................
    public static void LevelOrderTraversalrecursive(Queue<Node> queue){
        if(queue.isEmpty()){
            return;
        }
        while(!queue.isEmpty()){
            Node ans = queue.poll();
            if(ans == null && queue.peek() != null){
                System.out.println();
                queue.add(null);
            }else if(ans == null && queue.peek() == null){
                return;
            }else{
                System.out.print(ans.data + "  ");
                if(ans.left != null){
                    queue.add(ans.left);
                }
                if(ans.right != null){
                    queue.add(ans.right);
                }
            }
        }
        return;
    }


    //  Print left view of tree non recursive..................................
    public static void leftViewNonRecursive(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(null);
        queue.add(node);
        printLeftView(queue);
        return;
    }

    // following function...............................
    public static void printLeftView(Queue<Node> queue){
        if(queue.isEmpty()){
            return;
        }
        Node ele = queue.poll();
        if(queue.isEmpty()){
            return;
        }
        if(ele == null && queue.peek() == null){
            return;
        }else if(ele == null && queue.peek() != null){
            // System.out.println();
            Node next = queue.poll();
            queue.add(null);
            System.out.println(next.data);
            if(next.left != null){
                queue.add(next.left);
            }
            if(next.right != null){
                queue.add(next.right);
            }
            printLeftView(queue);
        }else{
            if(ele.left != null){
                queue.add(ele.left);
            }
            if(ele.right != null){
                queue.add(ele.right);
            }
            printLeftView(queue);
        }
        return;
    }


    // Print the left view of the tree recursive..................................
    static int maxLevel = 0;
    public static void leftView(Node node, int currLevel){
        if(node == null){
            return;
        }
        if(currLevel > maxLevel){
            System.out.println(node.data);
            maxLevel = currLevel;
        }
        leftView(node.left, currLevel+1);
        leftView(node.right, currLevel+1);
        return;
    }

    //  Print right view recursive of the tree...................................
    static int maxLevel1 = 0;
    public static void rightView(Node node, int currLevel){
        if(node == null){
            return;
        }
        if(currLevel > maxLevel1){
            System.out.println(node.data);
            maxLevel1 = currLevel;
        }
        rightView(node.right, currLevel+1);
        rightView(node.left, currLevel+1);
        return;
    }


    public static void rightViewWithQueue(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root); // root = 1
        while (!queue.isEmpty()) {

            int n = queue.size();
            while (n != 0) {
                Node temp = queue.poll();
                if (n == 1) {
                    System.out.println(temp.data); // 1
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                n--;
            }
        }
    }

// Print least common ancester.....................................................


// Print least common ancester recursive..........................................



// Preorder Iterative.............................................................

    public static void PreorderIterative(Node root){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.data);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }

        }
        return;
    }


// PostOrderIterative................................................................

// Preorder Iterative sir method.............................................................

    public static void PreorderIterativeA(Node root){
        Stack<NodeModi> stack = new Stack<>();
        NodeModi root1 = new NodeModi(root, 0);
        stack.push(root1);
        while(!stack.isEmpty()){
            NodeModi node = stack.peek();
            if(node.work == 0){
                System.out.println(node.node.data);
                node.work++;
            }else if(node.work == 1){
                if(node.node.left!= null){
                    stack.push(new NodeModi(node.node.left, 0));
                }
                node.work++;
            }else if(node.work == 2){
                if(node.node.right != null){
                    stack.push(new NodeModi(node.node.right, 0));
                }
                node.work++;
            }else{
                stack.pop();
            }
        }
        return;
    }


    // Post order iterative sir method........................................................................
    public static void postIter(Node node) {
        Stack<NodeModi> stack = new Stack<>();
        NodeModi NodeModi = new NodeModi(node, 0);
        stack.push(NodeModi);
        while (stack.size() > 0) {
            NodeModi tNodeModi = stack.peek();
            if (tNodeModi.work == 0) {
                if (tNodeModi.node.left != null) {
                    NodeModi left = new NodeModi(tNodeModi.node.left, 0);
                    stack.push(left);
                }
                tNodeModi.work++;
            } else if (tNodeModi.work == 1) {
                if (tNodeModi.node.right != null) {
                    NodeModi right = new NodeModi(tNodeModi.node.right, 0);
                    stack.push(right);
                }
                tNodeModi.work++;
            } else if (tNodeModi.work == 2) {

                System.out.print(tNodeModi.node.data + " ");
                tNodeModi.work++;
            } else {
                stack.pop();
            }
        }
        System.out.println();
    }


    // 	inorder iterative..........................................................................................
    public static void inIter(Node node) {
        Stack<NodeModi> stack = new Stack<>();
        NodeModi NodeModi = new NodeModi(node, 0);
        stack.push(NodeModi);
        while (stack.size() > 0) {
            NodeModi tNodeModi = stack.peek();
            if (tNodeModi.work == 0) {
                if (tNodeModi.node.left != null) {
                    NodeModi left = new NodeModi(tNodeModi.node.left, 0);
                    stack.push(left);
                }
                tNodeModi.work++;
            } else if (tNodeModi.work == 1) {

                System.out.print(tNodeModi.node.data + " ");
                tNodeModi.work++;

            } else if (tNodeModi.work == 2) {
                if (tNodeModi.node.right != null) {
                    NodeModi right = new NodeModi(tNodeModi.node.right, 0);
                    stack.push(right);
                }
                tNodeModi.work++;

            } else {
                stack.pop();
            }
        }
        System.out.println();
    }


    // 	===================================================================================================
    static class NodeModi1 {
        int height;
        boolean balance;

        NodeModi1(int h, boolean bal) {
            this.height = h;
            this.balance = bal;
        }
    }
// ======================================================================================================


    // Is the tree balanced ? ...............................................................
    public static boolean isBalancedImprove(Node node) {
        return isBalancedImprovehelper(node).balance;
    }

    private static NodeModi1 isBalancedImprovehelper(Node node) {
        if (node == null) {
            return new NodeModi1(0, true);
        }
        NodeModi1 leftp = isBalancedImprovehelper(node.left);
        NodeModi1 rightp = isBalancedImprovehelper(node.right);
        if (leftp.balance == false || rightp.balance == false) {
            return new NodeModi1(Math.max(leftp.height, rightp.height) + 1, false);
        }

        if (leftp.height - rightp.height >= -1 && leftp.height - rightp.height <= 1) {
            return new NodeModi1(Math.max(leftp.height, rightp.height) + 1, true);
        }

        return new NodeModi1(Math.max(leftp.height, rightp.height) + 1, false);
    }



// find height

    public static int height(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // is the tree balanced in O(n^2).........................................................
    public static boolean isBalanced(Node root){
        if(root == null){
            return true;
        }
        int h1 = height(root.left);
        int h2 = height(root.right);
        if(Math.abs(h1-h2) <= 1){
            isBalanced(root.left);
            isBalanced(root.right);
        }else{
            return false;
        }
        return true;
    }


// is the tree balanced in O(n)...............................................................


// Diameter of the tree........................................................................

    static class NodeModi2 {
        int height;
        int maxDiameter;

        NodeModi2(int h, int d) {
            this.height = h;
            this.maxDiameter = d;
        }
    }


    public static int diameter(Node node) {
        return diameterHelper(node).maxDiameter;
    }



    private static NodeModi2 diameterHelper(Node node) {
        if (node == null) {
            return new NodeModi2(0, 0);
        }
        NodeModi2 left = diameterHelper(node.left);
        NodeModi2 right = diameterHelper(node.right);
        int myDia = left.height + right.height + 1;

        int max = Math.max(myDia, Math.max(left.maxDiameter, right.maxDiameter));

        return new NodeModi2(Math.max(left.height, right.height) + 1, max);
    }


// Deep level sum...............................................................................



    // convert tree to linkedlist......................................................................

//convert tree to linkedlist vamsi approach..................................................



// check if one tree is subtree of other...............................................





}
