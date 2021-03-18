package MatrixHW;

public class main {
    public static void main(String[] args){
        int[][] array = {{0, 0, 0, 0, 1}, {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}};
//        client.digonalTraversal(array);
//        client.alternateXO(5, 6);
        Character[][] arrayChar = {{'X','X','X','X'}, {'X', 'O','X','X'},{'X','O','O','X'}, {'X', 'O','X','X'},{'X','X','O','O'}};
//        client.replaceOwithX(arrayChar);
//        System.out.println(client.findLargestSqaue(array));

        int[][] array1 = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}};
        int ans = client.largestAreaMatrix(array1);
        System.out.println(ans);
    }
}
