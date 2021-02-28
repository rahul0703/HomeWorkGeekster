package Tree;

class main {
    public static void main(String[] args){
            Node root = new Node(5);
            root.left = new Node(4);
            root.right = new Node(6);
            root.left.left = new Node(3);
            root.left.right = new Node(7);
            root.right.left = new Node(8);
            root.right.right = new Node(9);
            root.right.right.right = new Node(10);
            root.right.right.right.left = new Node(11);
            TreeUtil.display(root);
            System.out.println("-------------------------------------------------------------------------");
    }
}
