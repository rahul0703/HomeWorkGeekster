package genricTreeClass;

import java.util.ArrayList;
import java.util.Stack;

class main {
    public static void main(String[] args){
        int[] array = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        GenricTree gt = new GenricTree(array);
//        gt.display();
//        System.out.println(gt.size());
//        System.out.println(gt.height());
//        System.out.println(gt.max());
//        System.out.println(gt.find(120));
//        ArrayList<Integer> ans = gt.nodeToRootPath(120);
//        for(int value : ans){
//            System.out.println(value);
//        }
//        gt.removeLeaf();
        gt.MirrorTree();
        gt.display();

    }
}
