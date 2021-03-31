package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SLinkedList<E> implements java.util.List<E> { 
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

    public class MyIterator implements Iterator<E>{
        int cursor = 0;
        MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != SLinkedList.this.size;
        }

        @Override
        public E next() {
            cursor += 1;
            moveType = MoveType.NEXT;
            afterMove = true;
            return SLinkedList.this.getNode(cursor-1).element;
        }

        @Override
        public void remove(){
            if(!afterMove) return;
            SLinkedList.this.remove(cursor - 1);
            cursor -= 1;
            afterMove = false;
        }
    }

    public class MyListIterator extends MyIterator implements ListIterator<E>{
        public MyListIterator(int index){
            cursor = index;
        }
        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }
        @Override
        public void remove(){
            if(!afterMove) return;
            if(moveType == MoveType.NEXT) super.remove();
            else{
                SLinkedList.this.remove(cursor);
                afterMove = false;
            }
        }

        @Override
        public E previous() {
            cursor -= 1;
            moveType = MoveType.PREV;
            afterMove = true;
            return SLinkedList.this.getNode(cursor).element;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT)
                SLinkedList.this.set(cursor-1, e);
            else
                SLinkedList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT)
                SLinkedList.this.add(cursor - 1, e);
            else
                SLinkedList.this.add(cursor,e);
            cursor += 1;
            afterMove = false;
        }
    }
    private static enum MoveType{NEXT, PREV};
    private Node<E> head, tail;
    private int size;

    public SLinkedList(){
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.next = tail; tail.next = head;
        size = 0;
    }

    private void checkValidIndex(int index){
        if((index < 0) || (index >= size)){
            String message = String.format("Invalid Index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }
    private Node<E> getDataNode(int index){
        checkValidIndex(index);
        Node<E> curNode = head.next;
        int runIndex = 0;
        while(curNode != tail){
            if(index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }
    private Node<E> getNode(int index){
        if((index < -1) || (index >= size)){
            String message = String.format("Invalid index (including head) (=%d), index");
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> curNode = head;
        int runIndex = -1;
        while(curNode != tail){
            if(index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }

    private Node<E> removeAfter(Node<E> afterThis){
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if(removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        return removedNode;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void addAfter(Node<E> afterThis, Node<E> newNode){
        newNode.next = afterThis.next;
        afterThis.next = newNode;
        if(newNode.next == tail) tail.next = newNode;
        size += 1;
    }
    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node(null, e);
        Node<E> lastNode = tail.next;
        addAfter(lastNode, newNode);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> prevNode = head;
        Node<E> curNode = head.next;
        boolean found = false;
        while (curNode != tail){
            if(curNode.element.equals(o)){
                found = true;
                removeAfter(prevNode);
                break;
            }
            curNode = curNode.next;
            prevNode = prevNode.next;
        }
        return found;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        checkValidIndex(index);
        return this.getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        checkValidIndex(index);
        E oldElement = this.getNode(index).element;
        this.getNode(index).element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        Node<E> prevNode = getNode(index - 1);
        Node<E> newNode = new Node<>(null, element);
        addAfter(prevNode, newNode);
    }

    @Override
    public E remove(int index) {
        if(size == 0){
            String message = String.format("Remove at %d, but the list is empty", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index - 1);
        Node<E> curNode = prevNode.next;
        removeAfter(prevNode);
        return curNode.element;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
