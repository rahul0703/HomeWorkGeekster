package LinkedListHW;

public class main {
    public static void main(String[] args){
        int[] array = {12, 13, 14};
        int[] array2 = {5, 7, 9, 10, 11, 15, 19};
        int[] array3 = {1, 2, 4, 5, 6, 8, 9};
        Node head2 = client.makeLinkedList(array2);
        Node head1 = client.makeLinkedList(array);
//        DoublyLinkedListNode doublyHead = client.makeDoublyLinkedList(array3);
//        client.displayDoublyLinkedList(doublyHead);
//        int answer = client.countTriplets(doublyHead, 15);
//        System.out.println(answer);
        Node head = client.merger2SortedLinkedListMostEff(head1, head2);
        client.display(head);
//        Node node = head;
//        while(node.next != null){
//            node = node.next;
//        }
//        node.next = head;
//        int ans = client.JosephCircle(head, 4);
//        System.out.println(ans);




//        Node head = client.merger2SortedLinkedListNaive(head1, head2);
//        Node head = client.merger2SortedLinkedListMostEff(head1, head2);
//        client.display(head);




//        Node head1 = client.reverse(head);
//        client.display(head1);

//        Node head2 = client.reverseInKGroups(head, 3);
//        client.display(head2);

//        Node node = head;
//        while(node.next != null){
//            node = node.next;
//        }
//        node.next = head.next.next;
//
//        Node headFinal = client.DetectAndRemoveLoop(head);
//        boolean ans =  client.detectLoop(head);
//        System.out.println(ans);
//        Node startOfLoop = client.startOfLoop(head);
//        System.out.println(startOfLoop.data);

//
        Node answer = client.RotateLinkedListBlockWise(head, 3);
        System.out.println(answer.data);
        client.display(answer);


    }
}
