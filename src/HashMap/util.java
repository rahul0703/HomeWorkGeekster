package HashMap;

import java.util.*;

public class util {
    public static void longestSequence(int[] array){
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for(int i = 0; i < array.length; i++){
            map.put(array[i], true);
        }
        for(int i = 0; i < array.length; i++){
            if(map.containsKey(array[i] -1)){
                map.put(array[i], false);
            }
        }
        int maxCount = 0;
        int index = -1;
        for(int i = 0; i < array.length; i++){
            if(map.get(array[i]) == true){
                int count = 1;
                while(map.containsKey(array[i] + count)){
                    count++;
                }
                if(count > maxCount){
                    maxCount = count;
                    index = i;
                }
            }
        }
        System.out.print(array[index] + " " + maxCount);
    }
}
