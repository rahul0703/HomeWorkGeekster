package HashMapClass;
import java.util.*;
public class customHashMap<K, V>{
    private class HashNode{
        K key;
        V value;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        public HashNode(){
            this.key = null;
            this.value = null;
        }
    }

    public int size = 0;
    public LinkedList<HashNode>[] array;

    public customHashMap(){
        array = new LinkedList[5];
        for(int i = 0; i < array.length; i++){
            array[i] = new LinkedList<>();
        }
    }


    public int hashFunction(K key){
        int code = key.hashCode();
        int index = Math.abs(code % this.array.length);
        return index;
    }


    public HashNode findwithinBucket(K key){
        int index = hashFunction(key);
        LinkedList<HashNode> list = array[index];
        for(HashNode node : list){
            if(node.key == key){
                return node;
            }
        }
        return null;
    }


    public void put(K key, V value){
        int bucketIndex = hashFunction(key);
        HashNode HMnode = findwithinBucket(key);
        LinkedList<HashNode> list = array[bucketIndex];
        if(HMnode == null){
            list.add(new HashNode(key, value));
            this.size++;
        }else{
            HMnode.value = value;
        }
        if(size >= array.length*2){
            refactor();
        }
        return;
    }
    public int rehash(K key, LinkedList<HashNode>[] array2){
        int code = key.hashCode();
        int index = Math.abs(code % array2.length);
        return index;
    }
    public void refactor(){
        LinkedList<HashNode>[] array2 = new LinkedList[array.length*2];
        for(int i = 0; i < array2.length; i++){
            array2[i] = new LinkedList<>();
        }
        for(LinkedList<HashNode> list: array){
            for(HashNode node : list){
                int index = rehash(node.key, array2);
                array2[index].add(node);
            }
        }
        array = array2;
        return;
    }


    public V get(K key){
        int bucketIndex = hashFunction(key);
        for(HashNode node : array[bucketIndex]){
            if(node.key == key){
                return node.value;
            }
        }
        return null;
    }


    public boolean containsKey(K key){
        V value = get(key);
        if(value == null){
            return false;
        }else{
            return true;
        }
    }


    public void remove(K key){
        int index = hashFunction(key);
        HashNode node = findwithinBucket(key);
        LinkedList<HashNode> list = array[index];
        list.remove(node);
        this.size--;
    }

    public void display(){
        for(LinkedList<HashNode> list : array){
            System.out.print("List => { ");
            for(HashNode node : list){
                System.out.print(" " + node.key + " :" + node.value + ", ");
            }
            System.out.println("}");
        }
        return;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        for(LinkedList<HashNode> list : array){
            if(list.size() > 0){
                return false;
            }
        }
        return true;
    }

}
