package lab3;

class Node {
    int value;
    Node next;

    public void printNode() {
        IOUtil.writeInt(value);
    }
}


public class LinkedList {
    public static void main(String[] args) {
        Node tmp = new Node();
        tmp.value = 0;
        Node head = tmp;
        for (int i = 1; i < 10; i++) {
            tmp.next = new Node();
            tmp = tmp.next;
            tmp.value = i;
        }

        tmp = head;
        while (tmp != null) {
            tmp.value = tmp.value * tmp.value;
            tmp = tmp.next;
        }

        tmp = head;
        while (tmp != null) {
            tmp.printNode();
            tmp = tmp.next;
        }
    }
}
