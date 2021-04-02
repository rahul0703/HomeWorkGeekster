package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class graphClass {

    public static HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();


    public void addVertex(String verName){
        if(graph.containsKey(verName)){
            return;
        }
        graph.put(verName, new HashMap<String, Integer>());
    }

    public void addEdge(String source, String des, Integer weight){
        if(graph.containsKey(source) && graph.containsKey(des)){
            graph.get(source).put(des, weight);
            graph.get(des).put(source, weight);
        }else{
            addVertex(source);
            addVertex(des);
            addEdge(source, des, weight);
        }
    }

    public int isNeighbour(String source, String neighbour){
        if(graph.containsKey(source)){
            if(graph.get(source).containsKey(neighbour)){
                return graph.get(source).get(neighbour);
            }
        }
        return -1;
    }

    public int countVertex(){
        return graph.size();
    }

    public int countEdges(){
        int count = 0;
        for(Map.Entry<String, HashMap<String, Integer>> entrySet : graph.entrySet()){
            count += entrySet.getValue().size();
        }
        return count/2;
    }

    public void removeEdge(String source, String des){
        if(graph.containsKey(source) && graph.containsKey(des)){
            graph.get(source).remove(des);
            graph.get(des).remove(source);
        }
        return;
    }
    public void removeVertex(String source){
        if(graph.containsKey(source) == false){
            return;
        }
        for(Map.Entry<String, Integer> entry: graph.get(source).entrySet()){
            String des = entry.getKey();
            graph.get(des).remove(source);
        }
        graph.remove(source);
    }

    public void display(){
        ArrayList<String> verName = new ArrayList<>(graph.keySet());
        for(String vName : verName){
            for(Map.Entry<String, Integer> entry : graph.get(vName).entrySet()){
                System.out.println(vName + "->" + entry.getKey() + " " + entry.getValue());
            }
        }
    }

    public boolean hasPath(String source, String des){
        HashSet<String> visited = new HashSet<>();
        visited.add(source);
        boolean ans = findPath(source, des, visited, source);
        return ans;
    }

    private boolean findPath(String source, String des, HashSet<String> visited, String path){
        if(source == des){
            System.out.println(path);
            return true;
        }
        for(Map.Entry<String, Integer> entry : graph.get(source).entrySet()){
            String ver = entry.getKey();
            if(visited.contains(ver) == false){
                visited.add(ver);
                boolean ans = findPath(ver, des, visited, path + " " + ver);
                if(ans == true){
                    return ans;
                }
            }
        }
        return false;
    }


    public void hasPath1(String source, String des){
        HashSet<String> visited = new HashSet<>();
        visited.add(source);
        findPath1(source, des, visited, source);
        return;
    }

    private void findPath1(String source, String des, HashSet<String> visited, String path){
        if(source == des){
            System.out.println(path);
            return;
        }
        for(Map.Entry<String, Integer> entry : graph.get(source).entrySet()){
            String ver = entry.getKey();
            if(visited.contains(ver) == false){
                visited.add(ver);
                findPath1(ver, des, visited, path + " " + ver);
                visited.remove(ver);
            }
        }
    }

}
