package LinkedListHW;

public class main {
    public static void main(String[] args){
        int[] array = {12, 13, 14};
        int[] array2 = {5, 7, 9, 10, 11, 15, 19};
        Node head2 = client.makeLinkedList(array2);
        Node head1 = client.makeLinkedList(array);




//        Node head = client.merger2SortedLinkedListNaive(head1, head2);
        Node head = client.merger2SortedLinkedListMostEff(head1, head2);
//        client.display(head);




//        Node head1 = client.reverse(head);
//        client.display(head1);

//        Node head2 = client.reverseInKGroups(head, 3);
//        client.display(head2);

        Node node = head;
        while(node.next != null){
            node = node.next;
        }
        node.next = head.next.next;

        Node headFinal = client.DetectAndRemoveLoop(head);
//        boolean ans =  client.detectLoop(head);
//        System.out.println(ans);
//        Node startOfLoop = client.startOfLoop(head);
//        System.out.println(startOfLoop.data);
    }
}
