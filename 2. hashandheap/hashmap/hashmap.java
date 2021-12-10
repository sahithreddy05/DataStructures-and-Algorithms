import java.util.ArrayList;

public class hashmap {

    private class Node {
        Integer key = 0;
        Integer value = 0;
        Node next = null;

        Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private class linkedlist {
        public Node head = null;
        public Node tail = null;
        public int NoOfElements = 0;

        public linkedlist() {
            this.head = this.tail = null;
            this.NoOfElements = 0;
        }

        public int size() {
            return this.NoOfElements;
        }

        public void addLast(Node node) {
            if (this.head == null)
                head = tail = node;
            else {
                this.tail.next = node;
                this.tail = node;
            }
        }

        public int getFirst() {
            return this.head.key;
        }

        public Node removeFirst() {
            Node node = this.head;
            if (this.NoOfElements == 0)
                head = tail = null;
            else {
                head = node.next;
                node.next = null;
            }
            NoOfElements--;
            return node;
        }
    }

    private linkedlist[] containers;
    private int sizeOfHM = 0;

    public void assignValues(int size) {
        containers = new linkedlist[size];
        for (int i = 0; i < size; i++) {
            containers[i] = new linkedlist();
        }
    }

    hashmap() {
        assignValues(10);
    }
    

    private linkedlist group(Integer key) {
        int code = hashCode(key);
        return this.containers[code];
    }

    private int hashCode(Integer key) {
        int value = key.hashCode();
        return value % containers.length;
    }
}