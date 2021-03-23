package MatrixHW;

public class pair3 implements Comparable<pair3>{
    int value;
    int row;
    int col;

    public pair3(int value, int row, int col){
        this.value = value;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(pair3 newPair){
        return this.value - newPair.value;
    }
}
