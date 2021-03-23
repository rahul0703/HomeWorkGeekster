package DynamicProgClass;

public class box implements Comparable<box>{
    int height;
    int length;
    int bredth;
    int area = length*bredth;


    public box(int a, int b, int c){
        this.height = a;
        this.length = b;
        this.bredth = c;
    }

    public int compareTo(box next){
        return this.area - next.area;
    }


}
