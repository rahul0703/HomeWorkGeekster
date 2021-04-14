package graphHW;

import java.util.*;

public class client {

//    =============================================Problem 1 =====================================================================

    public static boolean printPath(int src, int des, graph gf) {
        HashSet<Integer> visited = new HashSet<>();
        boolean ans = getPath(src, des, gf, visited, src + " ");
        return ans;
    }

    private static boolean getPath(int src, int des, graph gf, HashSet<Integer> visited, String pathSoFar) {
        if (src == des) {
            System.out.println(pathSoFar);
            return true;
        }
        visited.add(src);
        for (int ngr : gf.map.get(src).keySet()) {
            if (visited.contains(ngr) == false) {
                boolean ans = getPath(ngr, des, gf, visited, pathSoFar + ngr + " ");
                if (ans == true) {
                    return true;
                }
            }
        }
        return false;
    }

//    ======================================================================================================================

    public static void printAllPath(int src, int des, graph gf) {
        HashSet<Integer> visited = new HashSet<>();
        getAllPath(src, des, gf, visited, src + " ");
        return;
    }


    private static void getAllPath(int src, int des, graph gf, HashSet<Integer> visited, String pathSoFar) {
        if (src == des) {
            System.out.println(pathSoFar);
            return;
        }
        visited.add(src);
        for (int ngr : gf.map.get(src).keySet()) {
            if (visited.contains(ngr) == false) {
                getAllPath(ngr, des, gf, visited, pathSoFar + ngr + " ");
            }
        }
        visited.remove(src);
    }


    //    ================================================Problem 3==================================================================
    public static int shortestPath(int src, int des, graph gf) {
        HashSet<Integer> visited = new HashSet<>();
        ans = Integer.MAX_VALUE;
        getShotestPath(src, des, gf, visited, 0);
        if (ans == Integer.MAX_VALUE) {
            System.out.println("NO Path Found");
            return -1;
        }
        return ans;
    }

    private static int ans = Integer.MAX_VALUE;

    private static void getShotestPath(int src, int des, graph gf, HashSet<Integer> visited, int disTravelled) {
        if (src == des) {
            if (disTravelled < ans) {
                ans = disTravelled;
            }
            return;
        }

        visited.add(src);
        for (int ngr : gf.map.get(src).keySet()) {
            if (visited.contains(ngr) == false) {
                getShotestPath(ngr, des, gf, visited, disTravelled + gf.map.get(src).get(ngr));
            }
        }
        visited.remove(src);
    }


    //    ==================================================Problem 4===============================================================
    public static ArrayList<ArrayList<Integer>> getConnctedComponents(graph gf) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<ArrayList<Integer>> finalAnswer = new ArrayList<>();
        for (int vertex : gf.map.keySet()) {
            if (visited.contains(vertex) == false) {
                ArrayList<Integer> ans = new ArrayList<>();
                fillList(vertex, gf, visited, ans);
                finalAnswer.add(ans);
            }
        }
        return finalAnswer;
    }

    private static void fillList(int src, graph gf, HashSet<Integer> visited, ArrayList<Integer> ans) {
        visited.add(src);
        ans.add(src);
        for (int ngr : gf.map.get(src).keySet()) {
            if (visited.contains(ngr) == false) {
                fillList(ngr, gf, visited, ans);
            }
        }
        return;
    }


    //    ===========================================problem 5=======================================================================
    public static boolean isGraphConnected(graph gf) {
        ArrayList<ArrayList<Integer>> connected = getConnctedComponents(gf);
        if (connected.size() == 1) {
            return true;
        }
        return false;
    }


    public static int numberOfIslands(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        for (boolean[] arr : visited) {
            Arrays.fill(arr, false);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1 && visited[i][j] == false) {
                    ans++;
                    exploreIsland(matrix, i, j, visited);
                }
            }
        }
        return ans;
    }

    private static void exploreIsland(int[][] planet, int posX, int posY, boolean[][] visited) {
        visited[posX][posY] = true;
        int[] allPosX = {1, -1, 0, 0};
        int[] allPosY = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            if (isSafeMove(allPosX[i] + posX, allPosY[i] + posY, planet, visited)) {
                exploreIsland(planet, allPosX[i] + posX, allPosY[i] + posY, visited);
            }
        }
        return;
    }

    private static boolean isSafeMove(int x, int y, int[][] mat, boolean[][] visited) {
        if (x < 0 || x >= mat.length || y < 0 || y >= mat.length || visited[x][y] == true || mat[x][y] == 0) {
            return false;
        }
        return true;
    }


    //    =================================================Problem 6=====================================================
    public static void hamiltonPathAndCycle(int src, graph gf) {
        HashSet<Integer> visited = new HashSet<>();
        getHamilton(src, src, gf, visited, src + " ");
        return;
    }

    private static void getHamilton(int src, int curr, graph gf, HashSet<Integer> visited, String path) {
        visited.add(curr);
        if (visited.size() == gf.map.size()) {
            if (gf.map.get(src).containsKey(curr)) {
                path = path + "*";
            } else {
                path += ".";
            }
            System.out.println(path);
            visited.remove(curr);
            return;
        }
        for (int ngr : gf.map.get(curr).keySet()) {
            if (visited.contains(ngr) == false) {
                getHamilton(src, ngr, gf, visited, path + ngr + " ");
            }
        }
        visited.remove(curr);
    }


    //    =====================================================Problem 7=============================================================
    public static void knightTour(int srcX, int srcY, int[][] matrix) {
        int[] arrX = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] arrY = {2, -2, 1, -1, 2, -2, 1, -1};
        knightTourHelper(matrix, srcX, srcY, 1, arrX, arrY);
        return;
    }

    private static void knightTourHelper(int[][] matrix, int x, int y, int count, int[] arrX, int[] arrY) {
        if(x < 0 || y < 0 || x >= matrix.length || y >= matrix.length || matrix[x][y] != 0){
            return;
        }else if(count == matrix.length * matrix[0].length){
            matrix[x][y] = count;
            displayBoard(matrix);
            matrix[x][y] = 0;
            return;
        }
        matrix[x][y] = count;
        for(int i = 0; i < 8; i++){
            int nextX = x + arrX[i];
            int nextY = y + arrY[i];
            knightTourHelper(matrix, nextX, nextY, count+1, arrX, arrY);
        }
        matrix[x][y] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

//    ==================================================problem 8==========================================================
//    cyclic graph detection
    private static class pair{
        int node;
        int radius;
        String path;

        public pair(int node, int radius, String path){
            this.node = node;
            this.radius = radius;
            this.path = path;
        }
}
    public static int bfs(int src, int des, graph gf){
        if(src == des){
            return 0;
        }
        HashSet<Integer> visited = new HashSet<>();
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(src, 1, src + " "));
        while(!queue.isEmpty()){
            pair rem = queue.poll();
            int ver = rem.node;
            int count = rem.radius;
            if(des == ver){
                System.out.println(rem.path);
                return count;
            }
            visited.add(ver);
            for(int ngr : gf.map.get(ver).keySet()){
                if(visited.contains(ngr) == false){
                    queue.add(new pair(ngr, count+1, rem.path + ngr + " "));
                }
            }
        }
        return -1;

    }


//    ============================================problem 9==============================================================
//    cycle detection
    public static boolean cycleDetection(graph gf){
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int vertex : gf.map.keySet()){
            if(!visited.contains(vertex)){
                queue.add(vertex);
                while(!queue.isEmpty()){
                    int rem = queue.poll();
                    if(visited.contains(rem)){
                        return true;
                    }else{
                        visited.add(rem);
                        for(int ngr : gf.map.get(rem).keySet()){
                            if(!visited.contains(ngr)){
                                queue.add(ngr);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


//    ==================================================problem 10==========================================================
//    bipartite graph

    public static class pair2{
        int ver;
        int level;

        public pair2(int ver, int lev){
            this.ver = ver;
            this.level = lev;
        }
    }
    public static boolean bipartite(graph gf){
        HashMap<Integer, Integer> visited = new HashMap<>();
        Queue<pair2> queue = new LinkedList<>();
        for(int vertx : gf.map.keySet()){
            if(!visited.containsKey(vertx)){
                queue.add(new pair2(vertx, 0));
                while(!queue.isEmpty()){
                    pair2 rem = queue.poll();
                    int ver = rem.ver;
                    int lev = rem.level;
                    if(visited.containsKey(ver)){
                        if(visited.get(ver) != lev){
                            return false;
                        }else{
                            continue;
                        }
                    }else{
                        visited.put(ver, lev);
                        for(int ngr : gf.map.get(ver).keySet()){
                            if(!visited.containsKey(ngr)){
                                queue.add(new pair2(ngr, lev+1));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

//    =======================================================problem 11========================================================
//    dijkstra algorithm
    public static class pair3 implements Comparable<pair3>{
        int ver;
        String path;
        int cost;

        public pair3(int vertex, int cost, String path){
            this.ver = vertex;
            this.cost = cost;
            this.path = path;
        }

        public int compareTo(pair3 o){
            return this.cost - o.cost;
        }
}
    public static void dijkstraAlgorithm(int src, graph gf){
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<pair3> queue = new PriorityQueue<>();
        queue.add(new pair3(src, 0, src + " "));
        while(!queue.isEmpty()){
            pair3 rem = queue.poll();
            visited.add(rem.ver);
            System.out.println(rem.path + "@" + rem.cost);
            for(int ngr : gf.map.get(rem.ver).keySet()){
                if(!visited.contains(ngr)){
                    queue.add(new pair3(ngr, gf.map.get(rem.ver).get(ngr) + rem.cost, rem.path + ngr + " "));
                }
            }
        }
        return;
    }

//    ===========================================problem 12==================================================================
    public static void primsAlgorithm(int src, graph gf){
        String finalPath = "";
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<pair3> queue= new PriorityQueue<>();
        queue.add(new pair3(src, 0, " "));
        while(!queue.isEmpty()){
            pair3 remove = queue.poll();
            int vertex = remove.ver;
            int cost = remove.cost;
            if(visited.contains(vertex)){
                continue;
            }else{
                finalPath += remove.path;
                visited.add(vertex);
                for(int ngr : gf.map.get(vertex).keySet()){
                    if(!visited.contains(ngr)){
                        queue.add(new pair3(ngr, gf.map.get(vertex).get(ngr), vertex + "@" + ngr + " "));
                    }
                }
            }
        }
        System.out.println(finalPath);
        return;
    }

//    ===============================================Problem 13============================================================
//        bellmanford algorithm

    public static class pair4{
        int src;
        int wt;

        public pair4(int src, int wt){
            this.src = src;
            this.wt = wt;
        }
    }
    public static void bellmanfordAlgorithm(int src, graph gf){
        int x = gf.map.size();
        pair4[] array = new pair4[x];
        for(int vertex : gf.map.keySet()){
            if(vertex == src){
                array[vertex] = new pair4(vertex, 0);
            }else{
                array[vertex] = new pair4(vertex, Integer.MAX_VALUE);
            }
        }
        for(int i = 0; i < x-1; i++){
            for(pair4 pair : array){
                int vertex = pair.src;
                int wt = pair.wt;
                for(int ngr : gf.map.get(vertex).keySet()){
                    int minWt = array[ngr].wt;
                    int newWt = wt + gf.map.get(vertex).get(ngr);
                    if(newWt < minWt){
                        minWt = newWt;
                        array[ngr].wt = newWt;
                    }
                }
            }
        }
        for(pair4 pair : array){
            System.out.print(src + "@" + pair.src + "=" + pair.wt + " ");
        }
        return;
    }

//    =============================================Problem 14===============================================================

    public static class edge implements Comparable<edge>{
        int src;
        int ngr;
        int len;

        public edge(int src, int ngr, int len){
            this.src = src;
            this.len = len;
            this.ngr = ngr;
        }

        public int compareTo(edge o){
            return this.len - o.len;
        }
    }

    public static class DSNode {
        int data;
        DSNode parent;
        int rank;

        public DSNode(int data){
            this.data = data;
            this.parent = new DSNode(data);
            this.rank = 0;
        }
    }
    public Integer[][] kruskal(Integer[][] matrix) {
        PriorityQueue<edge> queue = new PriorityQueue<>();
        HashMap<Integer, DSNode> dset = new HashMap<>();
        Integer[][] mst = new Integer[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            dset.put(i, new DSNode(i));
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != null) {
                    queue.add(new edge(i, j, matrix[i][j]));
                }
            }
        }

        while(!queue.isEmpty()){
            edge rem = queue.poll();
            DSNode leader1 = find(dset.get(rem.src));
            DSNode leader2 = find(dset.get(rem.ngr));
            if(leader1 != leader2){
                mst[rem.src][rem.ngr] = rem.len;
                mst[rem.ngr][rem.src] = rem.len;
                union(leader1, leader2);
            }
        }
        return mst;

    }


    private DSNode find(DSNode child){
        if(child.parent == child){
            return child;
        }else{
            DSNode par = find(child.parent);
            child.parent = par;
            return par;
        }
    }


    private void union(DSNode leader1, DSNode leader2){
        if(leader1.rank < leader2.rank){
            leader1.parent = leader2;
        }else if(leader1.rank > leader2.rank){
            leader2.parent = leader1;
        }else{
            leader1.parent = leader2;
            leader2.rank++;
        }
    }

//    ==============================================Problem 15===========================================================
//    topological sort
    public static void topologicalSort(int[][] mat) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        int n = mat.length;
        for(int i = 0; i < n; i++){
            if(!visited.contains(i)){
                topoSort(visited, i, mat, stack);
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

    private static void topoSort(HashSet<Integer> visited, int src, int[][] mat, Stack<Integer> stack){
        visited.add(src);
        for(int i = 0; i < mat[0].length; i++){
            if(mat[src][i] == 1 && visited.contains(i) == false){
                topoSort(visited, i, mat, stack);
            }
        }
        stack.push(src);
        return;
    }


}
