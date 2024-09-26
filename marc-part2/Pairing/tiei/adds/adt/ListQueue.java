package tiei.aads.adt;

//import org.w3c.dom.Node;

/**
 * A class for list-based queue
 */
public class ListQueue<AnyType> implements QueueInterface<AnyType> {

    private Node<AnyType> head;
    private Node<AnyType> tail;
    private int size;

    public ListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void offer(AnyType item) {
        Node<AnyType> newNode = new Node<>(item, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    @Override
    public AnyType poll() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        AnyType data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    @Override
    public AnyType peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return head.getData();
    }

    private static class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }

        public AnyType getData() {
            return data;
        }

        public Node<AnyType> getNext() {
            return next;
        }

        public void setNext(Node<AnyType> next) {
            this.next = next;
        }
    }

}
