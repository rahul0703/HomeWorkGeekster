package PriorityQueueClass;

import java.util.*;

class heap{
    public ArrayList<Integer> list = new ArrayList<>();

    public heap(){

    }
    public heap(int[] array){
        for(int i : array){
            this.list.add(i);
        }
        for(int i = (size()/2)-1; i >= 0; i--){
            downheapify(i);
        }
    }
    public void add(int data){
        this.list.add(data);
        upheapify(this.list.size()-1);
    }
    public void upheapify(int index){
        if(index == 0){
            return;
        }
        int parentIndex = (index - 1)/2;
        if(this.list.get(parentIndex) < this.list.get(index)){
            swap(index, parentIndex);
            upheapify(parentIndex);
        }
    }

    public void swap(int indexChild, int indexParent){
        int temp = this.list.get(indexChild);
        int temp2 = this.list.get(indexParent);
        this.list.set(indexChild, temp2);
        this.list.set(indexParent, temp);
        return;
    }

    public void display(){
        for(int i = 0; i < this.list.size(); i++){
            System.out.print(this.list.get(i) + " ");
        }
    }

    public int remove(){
        int removeEle = this.list.get(0);
        swap(0, this.list.size() -1);
        this.list.remove(this.list.size()-1);
        downheapify(0);
        return  removeEle;
    }

    public void downheapify(int index){
        int max = index;
        int leftChild = index*2 + 1;
        int rightChild = index*2 + 2;

        if(this.list.size() > leftChild && this.list.get(leftChild) > this.list.get(max)){
            max = leftChild;
        }
        if(this.list.size() > rightChild && this.list.get(rightChild) > this.list.get(max)){
            max = rightChild;
        }
        if(max != index){
            swap(max, index);
            downheapify(max);
        }
        return;
    }

    public int size(){
        return this.list.size();
    }

    public boolean isEmpty(){
        if(this.list.size() == 0){
            return true;
        }
        return false;
    }

    public int peek(){
        return this.list.get(0);
    }

    public int poll(){
        return remove();
    }
}


