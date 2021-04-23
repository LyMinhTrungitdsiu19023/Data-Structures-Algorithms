package Lab5DSA;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SLinkedList<E> implements List<E> {
    public static class Node<E> {
        E element;
        Node<E> next;
        Node(Node<E> next, E element){
            this.next = next;
            this.element = element;
        }
        void update(Node<E> next, E element){
            this.next = next;
            this.element = element;
        }
    }
    public class MyIterator implements Iterator<E>{
        Node<E> pprevNode;
        Node<E> prevNode;
        Node<E> curNode;
        boolean afterMove;
        MoveType moveType;

        MyIterator(){
            pprevNode = null;
            prevNode = SLinkedList.this.head;
            curNode = SLinkedList.this.head.next;
            moveType = MoveType.NEXT;
            afterMove = false;
        }

        @Override
        public boolean hasNext() {
            return curNode != SLinkedList.this.tail;
        }

        @Override
        public E next() {
            E element = curNode.element;
            pprevNode = prevNode;
            curNode = curNode.next;
            afterMove = true;
            moveType = MoveType.NEXT;
            return element;
        }
        @Override
        public void remove(){
            if(!afterMove) return;
            removeAfter(pprevNode);
            prevNode = pprevNode;
            afterMove = false;
        }
    }

    public class MyListIterator extends MyIterator implements ListIterator<E>{
        int index;
        public MyListIterator(){
            super();
            index = 0;
        }
        public MyListIterator(int index){
            super();
            this.index = index;
        }
        public E next(){
            index++;
            return super.next();
        }
        public void remove(){
            if(!afterMove) return;
            if(moveType == MoveType.NEXT){
                super.remove();
            }
            else{
                Node<E> prevNode = getNode(index - 1);
                removeAfter(prevNode);
            }
            afterMove = false;
        }
        @Override
        public boolean hasPrevious() {
            return index != 0;
        }

        @Override
        public E previous() {
            index += 1;
            moveType = MoveType.PREV;
            afterMove = true;
            curNode = SLinkedList.this.getDataNode(index);
            return curNode.element;
        }

        @Override
        public int nextIndex() {
            return this.index;
        }

        @Override
        public int previousIndex() {
            return (index - 1);
        }

        @Override
        public void set(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT)
                prevNode.element = e;
            else curNode.element = e;
        }

        @Override
        public void add(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT){
                Node<E> newNode = new Node<>(prevNode.next, prevNode.element);
                prevNode.update(newNode,e);
                if(curNode.next == tail) tail.next = newNode;
            }
            else{
                Node<E> newNode = new Node<>(curNode.next, curNode.element);
                curNode.update(newNode, e);
                if(curNode.next == tail) tail.next = newNode;
            }
            SLinkedList.this.size += 1;
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
        Node<E> curNode = head.next;
        boolean found = false;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                found = true;
                break;
            }
            curNode = curNode.next;
        }
        if(found) size -= 1;
        return found;    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void clear() {
        Node<E> curNode = head.next;
        while(curNode != tail){
            Node<E> temp = curNode;
            curNode = curNode.next;
            temp.update(null, null); //best for garbage collector
        }
        //to empty condition
        head.next = tail; tail.next = head;
        size = 0;
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
    private Node<E> removeAfter(Node<E> afterThis){
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if(removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        return removedNode;
    }
    @Override
    public int indexOf(Object o) {
        Node<E> curNode = head.next;
        int foundIdx = -1;
        int index = 0;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                foundIdx = index;
                break;
            }
            index += 1;
            curNode = curNode.next;
        }
        return foundIdx;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> curNode = head.next;
        int foundIdx = -1;
        int index = 0;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                foundIdx = index;
                //continue to find, instead of break here
            }
            index += 1;
            curNode = curNode.next;
        }
        return foundIdx;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // largest index = size, instead of (size-1), use with preivous iterator
        if( (index < 0) || (index > size)){
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");    }
}
