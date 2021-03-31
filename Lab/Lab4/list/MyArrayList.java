package list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements java.util.List<E> {
    private static enum MoveType {
        NEXT, PREV
    };

    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    // Internal data fields
    private E[] elements;
    private int size;

    // Constructor
    public MyArrayList(int capacity) throws IllegalArgumentException {
        if ((capacity < 0) || (capacity > MAX_CAPACITY)) {
            String message = String.format("Invalid capacity (=%d)", capacity);
            throw new IllegalArgumentException(message);
        }
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }

    public MyArrayList() throws IllegalArgumentException {
        this(10);
    }

    // Utitility methods (private)
    private void checkValidIndex(int index, int min, int max) {
        if((index < min) || (index > max)){
             String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);} }

    private void checkCapacity(int minCapacity) {
        if((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
            throw new OutOfMemoryError("Not enough memory to store the array");
        if(minCapacity < this.elements.length) return;
        else{
        //grow
            int oldCapacity = this.elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity < 0)
            newCapacity = MAX_CAPACITY;
            this.elements = Arrays.copyOf(this.elements, newCapacity);}}

    // Group-1: read list’s properties
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // Group-2: add elements
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException("Can not add null pointer");
        checkCapacity(this.size + 1);

        this.elements[this.size++] = e;
        return true;
    }

    public void add(int index, E element) {
        checkValidIndex(index, 0, size);
        if (element == null)
            throw new NullPointerException("Can not add null pointer");
        checkCapacity(this.size + 1);
        int copyCount = (this.size - 1) - index + 1;
        System.arraycopy(this.elements, index, this.elements, index + 1, copyCount);
        this.elements[index] = element;
        this.size++;
    }

    // Group-3: remove elements
    public E remove(int index) {
        checkValidIndex(index, 0, size - 1);
        E oldElement = this.elements[index];
        int copyCount = (this.size - 1) - (index + 1) + 1;
        System.arraycopy(this.elements, index + 1, this.elements, index, copyCount);
        this.size--;
        return oldElement;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0)
            return false;
        remove(index);
        return true;
    }

    public void clear() {
        /* here: code */ }

    // Group-4: set and get elements with indices
    public E get(int index) {
        checkValidIndex(index, 0, size - 1);
        return this.elements[index];
    }

    public E set(int index, E element) {
        checkValidIndex(index, 0, size - 1);
        E oldElement = this.elements[index];
        this.elements[index] = element;
        return oldElement;
    }

    // Grpup-5: map an object to its index + check object existing?
    public int indexOf(Object o) {
        int foundIdx = -1;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(o)) { // == not
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    public int lastIndexOf(Object o) {
        int foundIdx = -1;
        for (int idx = this.size - 1; idx >= 0; idx--) {
            if (this.elements[idx].equals(o)) {
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    public boolean contains(Object o) {
        boolean found = false;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(o)) {
                found = true;
                break;
            }
        }
        return found;
    }

    // Group-6: travel on lists 

    public Iterator<E> iterator() {
        return null;
         }

    public ListIterator<E> listIterator() {
        return null;
         }

    public ListIterator<E> listIterator(int index) {
        return null;
        }

    // Supplementary functionalities
    public Object[] toArray() {
        return new Object[0];}

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
         }

    // Inner Classes
    public class MyIterator implements Iterator<E> {
        int cursor = 0;
        MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != MyArrayList.this.size;
        }

        public class MyListIterator extends MyIterator implements ListIterator<E> {
            public MyListIterator(int index) {
                cursor = index;
            }

            @Override
            public boolean hasPrevious() {
                // TODO Auto-generated method stub
                return this.cursor != 0;
            }

            @Override
            public E previous() {
                // TODO Auto-generated method stub
                cursor -= 1;
                moveType = MoveType.PREV;
                afterMove = true;
                return MyArrayList.this.elements[cursor];
            }

            @Override
            public int nextIndex() {
                // TODO Auto-generated method stub
                return cursor;
            }

            @Override
            public int previousIndex() {
                // TODO Auto-generated method stub
                return cursor - 1;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
                if(!afterMove) return;
                if(moveType == MoveType.NEXT) super.remove();
                else{
                MyArrayList.this.remove(cursor);
                afterMove = false;
                } 
            }

            @Override
            public void set(E e) {
                // TODO Auto-generated method stub
                if (!afterMove)
                    return;
                if (moveType == MoveType.NEXT)
                    MyArrayList.this.set(cursor - 1, e);
                else
                    MyArrayList.this.set(cursor, e);
            }

            @Override
            public void add(E e) {
                // TODO Auto-generated method stub
                if (!afterMove)
                    return;
                if (moveType == MoveType.NEXT)
                    MyArrayList.this.add(cursor - 1, e);
                else
                    MyArrayList.this.add(cursor, e);
                cursor += 1;
                afterMove = false;
            }
            /* here: code */ }

        @Override
        public E next() {
            // TODO Auto-generated method stub
            cursor += 1;
            moveType = MoveType.NEXT;
            afterMove = true;
            return MyArrayList.this.elements[cursor - 1];
        }
    } 
    
}
// End of MyArrayList