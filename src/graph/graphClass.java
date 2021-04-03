package graph;

import com.sun.jdi.event.StepEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

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


//    =====================================================================================================================================
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

//=======================================================================================================================================
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


//    ==================================================================================================================================
    public int shortestPath(String source, String des){
        HashSet<String> visited = new HashSet<>();
        visited.add(source);
        findShortPath(source, des, visited, 0);
        return minimum;
    }

    private int minimum = Integer.MAX_VALUE;

    private void findShortPath(String current, String des, HashSet<String> visited, int curr){
        if(current == des){
            if(minimum > curr){
                minimum = curr;
            }
            return;
        }
        for(Map.Entry<String, Integer> entry : graph.get(current).entrySet()){
            String ver = entry.getKey();
            if(visited.contains(ver) == false){
                visited.add(ver);
                curr += graph.get(current).get(ver);
                findShortPath(ver, des, visited, curr);
                visited.remove(ver);
                curr -= graph.get(current).get(ver);
            }
        }
    }

    private class Tpair {
        String v;
        String p;
        int w;

        public Tpair(String v,String p,int w){
            this.v = v;
            this.p = p;
            this .w = w;
        }
    }
    public boolean bfs(String s,String d){
        Tpair pair = new Tpair(s,s,0);

        HashSet<String> visited = new HashSet<>();
        LinkedList<Tpair> q = new LinkedList<>();
        q.addLast(pair);
        while(q.size()>0){
            Tpair rem = q.removeFirst();
            visited.add(rem.v);
            System.out.println(rem.v+"@"+rem.p);
            if(rem.v.equals(d)){
                return true;
            }
            for(String n:graph.get(rem.v).keySet()){
                if(visited.contains(n)==false){
                    Tpair npair = new Tpair(n,rem.p+n,rem.w+ graph.get(rem.v).get(n));
                    q.addLast(npair);
                }
            }
        }

        return false;
    }


    public boolean dfs(String source, String des){
        HashSet<String> set = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.add(source);
        while(!stack.isEmpty()){
            String popEle = stack.pop();
            if(set.contains(popEle) == false){
                set.add(popEle);
                for(String neighbour : graph.get(popEle).keySet()){
                    if(neighbour.equals(des)){
                        return true;
                    }
                    stack.push(neighbour);
                }
            }
        }
        return false;
    }


    public ArrayList<HashSet<String>> connectedComponents(){
        HashSet<String> visited = new HashSet<>();
        ArrayList<HashSet<String>> ans = new ArrayList<>();
        for(String vertex : graph.keySet()){
            if(visited.contains(vertex) == false){
                HashSet<String> set = new HashSet<>();
                Stack<String> stack = new Stack<>();
                stack.add(vertex);
                set.add(vertex);
                while(!stack.isEmpty()){
                    String popEle = stack.pop();
                    if(visited.contains(popEle) == false){
                        visited.add(popEle);
                        for(String neighbour : graph.get(popEle).keySet()){
                            if(visited.contains(neighbour) == false){
                                stack.add(neighbour);
                                set.add(neighbour);
                            }
                        }
                    }
                }
                ans.add(set);
            }
        }
        return ans;
    }


    public boolean isCyclic(){
        HashSet<String> visited = new HashSet<>();
        for (String vertex : graph.keySet()) {
            if(visited.contains(vertex)){
                continue;
            }
            Tpair pair = new Tpair(vertex, vertex, 0);

            LinkedList<Tpair> q = new LinkedList<>();
            q.addLast(pair);
            while (q.size() > 0) {
                Tpair rem = q.removeFirst();
                if (visited.contains(rem.v)) {
                    return true;
                }
                visited.add(rem.v);
                for (String n : graph.get(rem.v).keySet()) {
                    if (visited.contains(n) == false) {
                        Tpair npair = new Tpair(n, rem.p + n, rem.w + graph.get(rem.v).get(n));
                        q.addLast(npair);
                    }
                }
            }
        }
        return false;
    }
}
