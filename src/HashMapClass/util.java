package HashMapClass;

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



//    question 2..............................
//    grouping the string (Hashmap of hashmap)
    public static ArrayList<ArrayList<String>> groupStrings(String[] array){
        HashMap<HashMap, ArrayList<String>> mapOuter = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            String str = array[i];
            HashMap<Character, Integer> map1 = new HashMap<>();
            for(int j = 0; j < str.length(); j++){
                if (map1.containsKey(str.charAt(j))){
                    map1.replace(str.charAt(j), map1.get(str.charAt(j)) + 1);
                }else{
                    map1.put(str.charAt(j), 1);
                }
            }
            if(mapOuter.containsKey(map1)){
                ArrayList<String> ans = mapOuter.get(map1);
                ans.add(array[i]);
                mapOuter.put(map1, ans);
            }else{
                ArrayList<String> ans = new ArrayList<>();
                ans.add(array[i]);
                mapOuter.put(map1, ans);
            }
        }
        ArrayList<ArrayList<String>> answerOuter = new ArrayList<>();
        for(ArrayList<String> answer : mapOuter.values()){
            answerOuter.add(answer);
        }
        return answerOuter;
    }



//    question 3.....................................
//    maximum length of unique string (hashmap with start variable)
    public static int maxLengthOfUniqueString(String str){
        HashMap<Character, Integer> set = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(set.containsKey(ch)){
                start = Math.max(start, set.get(ch) + 1);
                set.put(ch, i);
            }else{
                set.put(ch, i);
            }
            maxLength = Math.max(maxLength, i-start+1);
        }
        return maxLength;
    }
}
