package LinkedListHW;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class client {

    public static void display(Node node){
        String s = "";
        while(node != null){
            s +=  node.data + "->";
            node = node.next;
        }
        s += "null";
        System.out.println(s);
    }

    public static Node reverse(Node node){
        Node prev = null;
        Node next = null;
        Node current = node;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static Node reverseInKGroups(Node root, int k){
        Node node = root;
        int size = 0;
        while(node != null){
            node = node.next;
            size++;
        }

        int x = size/k;
        Node start = reverseInKGroupsHelper(root, k, x, 0);
        return start;
    }

    public static Node reverseInKGroupsHelper(Node node, int k, int x, int curr){
        if(curr >= x || node == null){
            return node;
        }
        Node current = node;
        Node prev = null;
        Node next = null;
        int count = 0;
        while(current != null && count < k){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if(next != null){
            node.next = reverseInKGroupsHelper(current, k, x, curr+1);
        }
        return prev;
    }

    public static Node makeLinkedList(int[] array){
        if(array.length == 0){
            return null;
        }
        Node head = new Node(array[0]);
        Node node = head;
        for(int i = 1; i < array.length; i++){
            node.next = new Node(array[i]);
            node = node.next;
        }

        return head;
    }

    public static boolean detectLoop(Node node){
        Node start = node;
        Node end = node;
        while(end != null && end.next != null){
            start = start.next;
            end = end.next.next;
            if(start == end){
                return true;
            }
        }
        return false;
    }

    public static Node startOfLoop(Node node){
        Node start = node;
        Node fast = node;
        Node PointerLoop = null;
        while(fast != null && fast.next != null) {
            start = start.next;
            fast = fast.next.next;
            if(start == fast){
                PointerLoop = start;
                break;
            }
        }
        Node PointerStart = node;
        while(PointerStart != PointerLoop){
            PointerLoop = PointerLoop.next;
            PointerStart = PointerStart.next;
        }
        return PointerStart;
    }


//    ===========================================================================================================================
    public static Node merger2SortedLinkedListNaive(Node node1, Node node2){
        ArrayList<Node> array = new ArrayList<>();
        while(node1 != null){
            array.add(node1);
            node1 = node1.next;
        }
        while(node2 != null){
            array.add(node2);
            node2 = node2.next;
        }
        Collections.sort(array, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.data - o2.data;
            }
        });
        Node newHead = array.get(0);
        Node start = newHead;
        int i = 1;
        while(i < array.size()){
            start.next = array.get(i);
            start = start.next;
            i++;
        }
        return newHead;
    }

    public static Node merger2SortedLinkedListTimeEfficient(Node node1, Node node2){
        ArrayList<Node> array = new ArrayList<>();
        while(node1 != null && node2 != null){
            if(node1.data < node2.data){
                array.add(node1);
                node1 = node1.next;
            }else{
                array.add(node2);
                node2 = node2.next;
            }
        }
        if(node1 != null){
            while(node1 != null){
                array.add(node1);
                node1 = node1.next;
            }
        }
        if(node2 != null){
            while(node2 != null){
                array.add(node2);
                node2 = node2.next;
            }
        }
        Node head = array.get(0);
        Node start = head;
        for(int i = 1; i < array.size(); i++){
            start.next = array.get(i);
            start = start.next;
        }
        return head;
    }

    public static Node merger2SortedLinkedListMostEff(Node node1, Node node2){
        Node start = null;
        if(node1.data < node2.data){
            start = node1;
            mergeHelper(node2, node1);
            return start;
        }else{
            start = node2;
            mergeHelper(node1, node2);
            return start;
        }
    }

    public static void mergeHelper(Node child, Node parent){
        Node next = parent.next;
        while(next != null && child != null){
            if(next.data <= child.data){
                next = next.next;
                parent = parent.next;
            }else{
                Node merger = child;
                child = child.next;
                parent.next = merger;
                merger.next = next;
                parent = parent.next;
            }
        }
        if(next == null && child != null){
            parent.next = child;
        }
        return;
    }


//    ==========================================================================================================================
//    detect and remove loop from linkedList
    public static Node DetectAndRemoveLoop(Node head) {
        if(head == null){
            return null;
        }
        Node slowPointer = head;
        Node fastPointer = head;
        boolean isLoop = false;
        while(fastPointer != null && fastPointer.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if(slowPointer == fastPointer){
                isLoop = true;
                break;
            }
        }
        if(isLoop == false){
//            throw new Exception("Loop not detected");
            System.out.println("No loop detected");
            return head;
        }
        System.out.println("Loop Detected");
        Node pointer1 = head.next;
        Node pointer2 = slowPointer;
        while(pointer2.next != pointer1){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        pointer2.next = null;
        display(head);
        return head;
//        ....
    }


//    find subsequence of length5
    public static int subsequence(String str){
        if(str.length() < 5){
            return 0;
        }

        int ans = 0;
        ArrayList<String> answer = subseq(str,"");
//        System.out.println(value);
        return answer.size();
    }
//    public static int value = 0;
    public static ArrayList<String> subseq(String ques, String ans){
        if(ans.length() == 5 && isPalindrome(ans) == true){
            ArrayList<String> ansswers = new ArrayList<>();
            ansswers.add(ans);
//            value++;
            return ansswers;
        }
        if(ques.length() == 0){
            return new ArrayList<>();
        }
        String sb = ques.substring(0, 1);
        String rq = ques.substring(1);
        ArrayList<String> answer1 = subseq(rq, ans + sb);
        ArrayList<String> answer2 = subseq(rq, ans);
        ArrayList<String> answerFinal = new ArrayList<>();
        for(String s : answer1){
            answerFinal.add(s);
        }
        for(String s1 : answer2){
            answerFinal.add(s1);
        }
        return answerFinal;
    }

    public static boolean isPalindrome(String str){
        if(str.length() == 0){
            return true;
        }
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != str.charAt(str.length() -1 -i)){
                return false;
            }
        }
        return true;
    }

//    ===============================================================================================================================

}
