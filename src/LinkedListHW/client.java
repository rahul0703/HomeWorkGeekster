package LinkedListHW;

import javax.naming.NameNotFoundException;
import java.util.*;

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

//    ===============================================================================================================================
    public static Node IntersectionOfLinkedList(Node node1, Node node2){
        HashSet<Node> set = new HashSet<>();
        Node shor = null;
        Node lon = null;
        int size1 = 0;
        Node start1 = node1;
        while(start1 != null){
            size1++;
            start1 = start1.next;
        }
        int size2 = 0;
        Node start2 = node2;
        while(start2 != null){
            start2 = start2.next;
            size2++;
        }
        if(size1 < size2){
            shor = node1;
            lon = node2;
        }else{
            shor = node2;
            lon = node1;
        }

        while(shor != null){
            set.add(shor);
            shor = shor.next;
        }
        while(lon != null){
            if(set.contains(lon)){
                return lon;
            }
            lon = lon.next;
        }
        return null;
    }

//    ===============================================================================================================================
    public static int intersectionOf2LinkedListEfficient(Node head1, Node head2){
         if(head1 == head2){
             return head1.data;
         }
         int count1 = 0;
         int count2 = 0;
         Node node1 = head1;
         Node node2 = head2;
         while(node1 != null){
             node1 = node1.next;
             count1++;
         }
         while(node2 != null){
             node2 = node2.next;
             count2++;
         }
         int diff = Math.abs(count1 - count2);
         if(count1 > count2){
             while(diff > 0){
                 head1 = head1.next;
                 diff--;
             }
         }else if(count2 > count1){
             while(diff > 0){
                 head2 = head2.next;
                 diff--;
             }
         }
         while(head1 != head2){
             head1 = head1.next;
             head2 = head2.next;
         }
         return head1.data;
    }

//remove duplicates ===========================================================================================================
    public static Node removeDuplicates(Node head)
    {
        // Your code here
        HashSet<Integer> set = new HashSet<>();
        Node node = head;
        set.add(node.data);
        while(node.next != null){
            int data = node.next.data;
            if(set.contains(data)){
                node.next = node.next.next;
            }else{
                set.add(data);
                node = node.next;
            }
        }
        return head;
    }

//    =================================================================================================================
//Count triplets in sorted doubly linkedlist
    public static int countTriplets(DoublyLinkedListNode node, int x){
        int MaxSum = 0;
        int size = 0;
        DoublyLinkedListNode node1 = node;
        while(node1.next != null){
            size++;
            node1 = node1.next;
        }
        DoublyLinkedListNode nodeLast = node1;
//        node1 = node1.prev;
        DoublyLinkedListNode stopNode = node1.prev.prev;
        while(node != stopNode.next){
            DoublyLinkedListNode pointer1 = node.next;
            node1 = nodeLast;
            while(pointer1 != null && node1 != null && pointer1 != node1){
                if(node.data + pointer1.data + node1.data == x){
                    MaxSum++;
                    pointer1 = pointer1.next;
                }else if(node.data + pointer1.data + node1.data < x){
                    pointer1 = pointer1.next;
                }else{
                    node1 = node1.prev;
                }
            }
            node = node.next;
        }
        return MaxSum;
    }


    public static DoublyLinkedListNode makeDoublyLinkedList(int[] array){
        if(array.length == 0){
            return null;
        }
        if(array.length == 1){
            return new DoublyLinkedListNode(array[0]);
        }
        DoublyLinkedListNode head = new DoublyLinkedListNode(array[0]);
        DoublyLinkedListNode pointer = head;
        for(int i = 1; i < array.length; i++){
            DoublyLinkedListNode newPointer = new DoublyLinkedListNode(array[i]);
            pointer.next = newPointer;
            newPointer.prev = pointer;
            pointer = newPointer;
        }
        return head;
    }

    public static void displayDoublyLinkedList(DoublyLinkedListNode node){
        StringBuilder sb = new StringBuilder();
        sb.append("null");
        while(node != null){
            sb.append("<=" + node.data + "=>");
            node = node.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
        return;
    }

    public static int JosephCircle(Node node, int m){
        while(node.next != node){
            int j = m-1;
            while(j > 0){
                node = node.next;
                j--;
            }
            Node nodeNext = node.next.next;
            node.next = nodeNext;
        }
        return node.data;
    }

//    Rotate LinkedList block wise
    public static Node RotateLinkedListBlockWise(Node node, int k){
        if(node == null){
            return null;
        }
        Node head = node;
        int l = k-2;
        while(node != null && l > 0){
            node = node.next;
            l--;
        }
        if(node == null || node.next == null){
            return head;
        }
        Node next = node.next.next;
        Node headFinal = node.next;
        Node head2 = RotateLinkedListBlockWise(next, k);
        node.next = head2;
        headFinal.next = head;
        return headFinal;
    }

//
    public static Node subtractNumberAsLinkedList(Node node1, Node node2){
        int x1 = 0;
        int x2 = 0;
        while(node1 != null){
            x1 = x1 *10 + node1.data;
            node1 = node1.next;
        }
        while (node2 != null){
            x2 = x2 * 10 + node2.data;
            node2 = node2.next;
        }
        int ans = Math.abs(x1 - x2);
        Node head = new Node(ans % 10);
        Node node = head;
        ans = ans/10;
        while(ans > 0){
            node.next = new Node(ans % 10);
            ans = ans/10;
            node = node.next;
        }
        Node headFinal = reverse(head);
        return headFinal;
    }


//subtract linkedList
    public static ArrayList<Node> getGreaterAndSmallerLinkedList(Node node1, Node node2){
        int size1 = size(node1);
        int size2 = size(node2);
        ArrayList<Node> ans = new ArrayList<>();
        if(size1 > size2){
            ans.add(node1);
            ans.add(node2);
        }else if(size2 > size1){
            ans.add(node2);
            ans.add(node1);
        }else{
            Node node11 = node1;
            Node node22 = node2;
            while(node1 != null && node1.data == node2.data){
                node1 = node1.next;
                node2 = node2.next;
            }
            if(node1 != null && node1.data >= node2.data){
                ans.add(node11);
                ans.add(node22);
            }else{
                ans.add(node22);
                ans.add(node11);
            }
        }
        return ans;
    }

    public static int size(Node node){
        int siz = 0;
        while(node != null){
            siz++;
            node = node.next;
        }
        return siz;
    }

    public static Node SubtractLinkedList(Node node1, Node node2){
        ArrayList<Node> a = getGreaterAndSmallerLinkedList(node1, node2);
        node1 = a.get(0);
        node2 = a.get(1);
        node1 = reverse(node1);
        node2 = reverse(node2);
        ArrayList<Integer> list = new ArrayList<>();
        while(node1 != null && node2 != null){
            int x1 = node1.data;
            int x2 = node2.data;
            int ans = 0;
            if(x1 >= x2){
                ans = x1 - x2;
            }else{
                ans = x1 + 10 - x2;
                Node pointer = node1.next;
                while(pointer.data == 0){
                    pointer.data = 9;
                    pointer = pointer.next;
                }
                pointer.data = pointer.data-1;
            }
            list.add(ans);
            node1 = node1.next;
            node2 = node2.next;
        }
        while(node1 != null){
            list.add(node1.data);
            node1 = node1.next;
        }
        while(node2 != null){
            list.add(node2.data);
            node2 = node2.next;
        }
        Collections.reverse(list);
        while(list.size() > 0 && list.get(0) == 0){
            list.remove(0);
        }
        if(list.size() == 0){
            list.add(0);
        }
        int[] array = new int[list.size()];
        int j = 0;
        for(int i : list){
            array[j] = i;
            j++;
        }
        Node head = makeLinkedList(array);
        return head;
    }

    public static Node mergerKSortedLinkedListEasy(Node[] array){
        Node head1 = array[0];
        int i = 1;
        while(i < array.length){
            Node head = merger2SortedLinkedListMostEff(head1, array[i]);
            head1 = head;
            i++;
        }
        return head1;
    }


}
