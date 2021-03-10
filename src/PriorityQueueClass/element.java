package PriorityQueueClass;

public class element implements Comparable<element>{
    int value;
    int index;
    int listno;

    public int compareTo(element o){
        return this.value - o.value;
    }
}
