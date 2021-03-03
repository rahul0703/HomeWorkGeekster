package BinaryTreeHW;

public class elementsModi {
    public int data;
    public int height;
    public elementsModi left;
    public elementsModi right;

    public elementsModi(int data){
        this.height = 0;
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
