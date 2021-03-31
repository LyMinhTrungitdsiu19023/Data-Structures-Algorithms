package list;

class Node<E> {
    E element;
    Node<E> next;

    Node(Node<E> next, E element) {
        this.next = next;
        this.element = element;
    }

    void update(Node<E> next, E element) {
        this.next = next;
        this.element = element;
    }
}
