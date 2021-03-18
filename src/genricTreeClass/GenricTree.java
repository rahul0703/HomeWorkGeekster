package genricTreeClass;
import java.util.*;
public class GenricTree {
    private class Node{
        int data;
        ArrayList<Node> children = new ArrayList<>();

    }
    public Node root;
    public int size;

    public void display(){
        display(root);
    }
    public void display(Node node){
        if(node == null){
            return;
        }
        String s = node.data + "=> ";
        for(Node child: node.children){
            s += child.data + ", ";
        }
        System.out.println(s);
        for(Node child: node.children){
            display(child);
        }
    }

    public GenricTree(int[] array){
        Stack<Node> stack = new Stack<>();
        genricTree(stack, array);
        return;
    }
    public void genricTree(Stack<Node> stack, int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] == -1){
                stack.pop();
            }else{
                if(stack.isEmpty()){
                    Node node = new Node();
                    node.data = array[i];
                    this.root = node;
                    this.size++;
                    stack.push(node);
                }else{
                    Node node = new Node();
                    node.data = array[i];
                    stack.peek().children.add(node);
                    stack.push(node);
                    this.size++;
                }

            }
        }
    }

    public int size(){
        if(root == null){
            return 0;
        }else{
            return size(root);
        }
    }

    public int size(Node root){
        int size = 0;
        for(Node child: root.children){
            size += size(child);
        }
        return size+1;
    }

    public int max(){
        int ans = max(root.data, root);
        return ans;
    }

    public int max(int maxTillNow, Node root){
        int maxV = maxTillNow;
        for(Node child: root.children){
            if(child.data > maxV){
                maxV = child.data;
            }
            maxV = max(maxV, child);
        }
        return maxV;
    }

    public int height(){
        int ans = height(root);
        return ans;
    }
    public int height(Node node){
        if(node.children.size() == 0){
            return 1;
        }
        int ht = 0;
        for(Node child: node.children){
            int cht = height(child);
            ht = Math.max(ht, cht);
        }
        return ht + 1;
    }

    public boolean find(int data){
        return find(data, root);
    }
    public boolean find(int data, Node root){
        if(root.data == data){
            return true;
        }
        for(Node child: root.children){
            boolean ans = find(data, child);
            if(ans == true){
                return true;
            }
        }
        return false;
    }

    public void nodeToRoot(Node node){
        nodeToRoot(node.data, root, "");
        return;
    }
    public void nodeToRoot(int data, Node root, String S){
        if(root.data == data){
            System.out.println(S);
            return;
        }
        for(Node child: root.children){
            nodeToRoot(data, child, S + child.data);
        }
        return;
    }

    public  ArrayList<Integer> nodeToRootPath(int data){
        ArrayList<Integer> ans = nodeToRootPath(data, root);
        return ans;
    }

    public ArrayList<Integer> nodeToRootPath(int data, Node node){
        if(node.data == data){
            ArrayList<Integer> nd = new ArrayList<>();
            nd.add(node.data);
            return nd;
        }
        for(Node child : node.children){
            ArrayList<Integer> cd = nodeToRootPath(data, child);
            if(cd.size() > 0){
                cd.add(node.data);
                return  cd;
            }
        }
        ArrayList<Integer> ne = new ArrayList<>();
        return ne;
    }

    public void removeLeaf(){
        removeLeaf(root);
        return;
    }
    public void removeLeaf(Node root){
        int i = 0;
        while(i < root.children.size()){
            Node child = root.children.get(i);
            if(child.children.size() == 0) {
                root.children.remove(i);
            }else{
                removeLeaf(child);
                i++;
            }
        }
        return;
    }

    public void MirrorTree(){
        MirrorTree(root);
        return;
    }
    public void MirrorTree(Node root){
        if(root.children.size() > 0){
            Collections.reverse(root.children);
        }
        for(Node child: root.children){
            MirrorTree(child);
        }
        return;
    }

//    public void Linearise(){
//        Linearise(Node root);
//        return;
//    }

//    check if the tree is mirror of not......................................................
//    public


    public static int maxPathLength(Node node){
        if(node == null){
            return 0;
        }
        pair answer = maxPath(node);
        return answer.selfSum;
    }

    private static pair maxPath(Node node){
        if(node == null){
            return new pair(0, 0, 0);
        }
        ArrayList<pair> children = new ArrayList<>();
        int childSum = 0;
        int childLeft = 0;
        int childRight = 0;
        for(Node childNode : node.children){
            pair ansChild = maxPath(childNode);
            int leftChildMax = ansChild.leftSum;
            int rightChildMax = ansChild.rightSum;
            childSum = Math.max(childSum, ansChild.selfSum);
            if(childLeft < childRight){
                childLeft = Math.max(childLeft, Math.max( leftChildMax, rightChildMax));
            }else{
                childRight = Math.max(childRight, Math.max(rightChildMax, leftChildMax));
            }
        }
        int SumPresent = Math.max(childSum, childLeft + childRight + node.data);
        int leftPresent = childLeft + node.data;
        int rightPresent = childRight + node.data;
        return new pair(leftPresent, rightPresent, SumPresent);
    }
}
