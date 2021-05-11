import java.util.*;

public class DLinkedList<E> extends AbstractSequentialList<E> implements java.util.List<E> {
    public static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        Node(E data) {
            this(null, data, null);
        }
    }
    public class DLinkedListIterator implements ListIterator<E>{
        private Node<E> lastReturned = null;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        DLinkedListIterator(int index){
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }
        final void checkForCountModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForCountModification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForCountModification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? tail : next.prev;
            nextIndex--;
            return lastReturned.element;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex-1;
        }

        @Override
        public void remove() {
            checkForCountModification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForCountModification();
            lastReturned.element = e;
        }

        @Override
        public void add(E e) {
            checkForCountModification();
            lastReturned = null;
            if (next == null)
                linkTail(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }
    }

    private Node<E> head, tail;
    private int size;

    public DLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.next = tail;
        tail.next = head;
        size = 0;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

//    public void addHead(E e) {
//        if (!isEmpty())
//            head = new Node<E>(e);
//        else {
//            Node<E> temp = head;
//            head = new Node<E>(null, e, temp);
//            head.next.prev = head;
//        }
//        size++;
//    }
//
//    public void addTail(E x) {
//        if (isEmpty())
//            head = new Node<E>(x);
//        else {
//            Node<E> temp = head;
//            while (temp.next != null) {
//                temp = temp.next;
//            }
//            temp.next = new Node<E>(temp, x, null);
//        }
//        size++;
//    }
//
//    public void addBefore(E x, E y) {
//        //List is empty => cannot add
//        if (isEmpty())
//            throw new NoSuchElementException("Element " + x.toString() + "not found");
//        Node<E> current = head;
//        //Looping through until found
//        while (current != null & !current.element.equals(x))
//            current = current.next;
//        //If null, not found
//        if (current == null)
//            throw new NoSuchElementException("Element " + x.toString() + " not found");
//        Node<E> newNode = new Node<E>(current.prev, y, current);
//        if (current.prev != null)
//            current.prev.next = newNode;
//        else
//            head = newNode;
//        current.prev = newNode;
//        size++;
//    }
//
//    /**
//     * Adding node after another node
//     *
//     * @param x Value to look for, adding after x if found
//     * @param y Value to add
//     */
//    public void addAfter(E x, E y) {
//        if (isEmpty())
//            throw new NoSuchElementException("Element " + x.toString() + "not found");
//        Node<E> current = head;
//        //Looping through until found
//        while (current != null && !current.element.equals(x))
//            current = current.next;
//        //If null, not found
//        if (current == null)
//            throw new NoSuchElementException("Element " + x.toString() + " not found");
//        //Not null, value found
//        Node<E> newNode = new Node<E>(current, y, current.next);
//        if (current.next != null)
//            current.next.prev = newNode;
//        current.next = newNode;
//        size++;
//    }
    private void linkHead(E e){
        final Node<E> f = head;
        final Node<E> newNode = new Node<>(null, e, f);
        head = newNode;
        if(f == null)
            tail = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }
    public void linkTail(E e){
        final Node<E> l = tail;
        final Node<E> newNode = new Node<>(l,e,null);
        tail = newNode;
        if(l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkHead(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.element;
        final Node<E> next = f.next;
        f.element = null;
        f.next = null; // help GC
        head = next;
        if (next == null)
            tail = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null last node l.
     */
    private E unlinkTail(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.element;
        final Node<E> prev = l.prev;
        l.element = null;
        l.prev = null; // help GC
        tail = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    public E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.element;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.element = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getHead() {
        final Node<E> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return f.element;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getTail() {
        final Node<E> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.element;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeHead() {
        final Node<E> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkHead(f);
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeTail() {
        final Node<E> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkTail(l);
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    public void addFirst(E e) {
        linkHead(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    public void addTail(E e) {
        linkTail(e);
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLinkedListIterator(0);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.element;
        return result;    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");    }

    @Override
    public boolean add(E e) {
        linkTail(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");    }

    @Override
    public void clear() {
    // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (DLinkedList.Node<E> x = head; x != null; ) {
            DLinkedList.Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).element;    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.element;
        x.element = element;
        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        if (index > size)
            throw new IndexOutOfBoundsException("Out of Bounds Exception");
        Node<E> node = new Node<E>(element);
        if (size == 0) {
            head = tail = node;
        } else {
            if (index == 0) {
                node.next = head;
                head.prev = node;
                head = node;
            } else if (index == size) {
                node.prev = tail;
                tail.next = node;
                tail = node;
            } else {
                Node<E> current = this.head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                node.next = current;
                node.prev = current.prev;
                current.prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int x) {
        checkElementIndex(x);
        return unlink(node(x));
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    DLinkedList.Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            DLinkedList.Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            DLinkedList.Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (DLinkedList.Node<E> x = head; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (DLinkedList.Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (DLinkedList.Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (x.element == null)
                    return index;
            }
        } else {
            for (DLinkedList.Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (o.equals(x.element))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DLinkedListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new DLinkedListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



