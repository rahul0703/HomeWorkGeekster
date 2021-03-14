package PriorityQueueClass;

import java.util.*;

public class medianHeap {
    public PriorityQueue<Integer> maxQ = new PriorityQueue<>();
    public PriorityQueue<Integer> minQ = new PriorityQueue<>(Collections.reverseOrder());

    public medianHeap(){

    }

    public void add(int data){
        if(maxQ.isEmpty()){
            maxQ.add(data);
        }else if(minQ.isEmpty()){
            minQ.add(data);
        }else if(minQ.peek() > data){
            minQ.add(data);
        }else{
            maxQ.add(data);
        }

        if(minQ.size() > maxQ.size() + 1){
            int da = minQ.poll();
            maxQ.add(da);
        }else if(maxQ.size() > minQ.size() + 1){
            int da = maxQ.poll();
            minQ.add(da);
        }
    }

    public int getMedian(){
        return maxQ.size() > minQ.size() ? maxQ.peek() : minQ.peek();
    }

    public int remove(){
        if(maxQ.size() > minQ.size()){
            return maxQ.poll();
        }else{
            return minQ.poll();
        }
    }

    public int size(){
        return minQ.size() + maxQ.size();
    }


}
