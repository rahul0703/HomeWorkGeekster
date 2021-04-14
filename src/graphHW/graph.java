package graphHW;
import java.util.*;
public class graph {

    public static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();


    public void addEgde(int src, int des, int len){
        addVertex(src);
        addVertex(des);
        HashMap<Integer, Integer> set1 = map.get(src);
        set1.put(des, len);
        HashMap<Integer, Integer> set2 = map.get(des);
        set2.put(src, len);
        return;
    }

    public void addVertex(int vertex){
        if(map.containsKey(vertex)){
            return;
        }
        map.put(vertex, new HashMap<Integer, Integer>());
    }



}

