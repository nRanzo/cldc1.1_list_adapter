package myAdapter;

import java.util.Vector;

/**
 * Adapter for the HList interface that uses a Vector of Object.
 * This class provides an implementation of the HList interface 
 * by adapting a Vector object.
 */
public class ListAdapter implements HList {
    protected Vector<Object> vector;

    /**
     * Constructor for ListAdapter.
     * Initializes a new Vector of Object.
     */
    public ListAdapter() {
        this.vector = new Vector<Object>();
    }

    // methods of HCollection

    /**
     * Adds an element to the list.
     *
     * @param o the element to add
     * @return true if the element was added successfully
     */
    @Override
    public boolean add(Object o) {
        vector.addElement(o);
        return true;
    }

    /**
     * Adds all elements of a collection to the list.
     *
     * @param c the collection containing the elements to add
     * @return true if the list was modified
     */
    @Override
    public boolean addAll(HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (add(it.next())) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * Checks if the list contains a specified element.
     *
     * @param o the element to check
     * @return true if the list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        return vector.contains(o);
    }

    /**
     * Checks if the list contains all elements of a specified collection.
     *
     * @param c the collection to check
     * @return true if the list contains all elements of the collection
     */
    @Override
    public boolean containsAll(HCollection c) {
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares this list with the specified object.
     *
     * @param o the object to compare with this list
     * @return true if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ListAdapter) {
            ListAdapter other = (ListAdapter) o;
            return vector.equals(other.vector);
        }
        return false;
    }

    /**
     * Returns the hash code for this list.
     *
     * @return the hash code for this list
     */
    @Override
    public int hashCode() {
        return vector.hashCode();
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns an iterator for the list.
     *
     * @return an iterator for the list
     */
    @Override
    public HIterator iterator() {
        return new ListAdapterIterator();
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param o the element to remove
     * @return true if the element was removed successfully
     */
    @Override
    public boolean remove(Object o) {
        return vector.removeElement(o);
    }

    /**
     * Removes all elements of the list that are present in a specified collection.
     *
     * @param c the collection containing the elements to remove
     * @return true if the list was modified
     */
    @Override
    public boolean removeAll(HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (remove(it.next())) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements of the list that are present in a specified collection.
     *
     * @param c the collection containing the elements to retain
     * @return true if the list was modified
     * @throws ArrayIndexOutOfBoundsException if an index is out of bounds
     */
    @Override
    public boolean retainAll(HCollection c) throws ArrayIndexOutOfBoundsException {
        boolean modified = false;
        for (int i = 0; i < vector.size(); i++) {
            if (!c.contains(vector.elementAt(i))) {
                vector.removeElementAt(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return vector.size();
    }

    /**
     * Returns an array containing all elements of the list.
     *
     * @return an array containing all elements of the list
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[vector.size()];
        vector.copyInto(array);
        return array;
    }

    /**
     * Returns an array containing all elements of the list;
     * the returned array will be of the same type as the specified array.
     *
     * @param a the array in which to store the elements of the list
     * @return an array containing all elements of the list
     */
    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < vector.size()) {
            return toArray();
        }
        vector.copyInto(a);
        if (a.length > vector.size()) {
            a[vector.size()] = null;
        }
        return a;
    }

    // methods of HList

    /**
     * Adds an element to the list at a specified position.
     *
     * @param index the position to add the element
     * @param element the element to add
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public void add(int index, Object element) {
        vector.insertElementAt(element, index);
    }

    /**
     * Adds all elements of a collection to the list starting from a specified position.
     *
     * @param index the position to start adding the elements
     * @param c the collection containing the elements to add
     * @return true if the list was modified
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public boolean addAll(int index, HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(index++, it.next());
            modified = true;
        }
        return modified;
    }

    /**
     * Returns the element at a specified position.
     *
     * @param index the position of the element to return
     * @return the element at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException {
        return vector.elementAt(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list.
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the element, or -1 if it is not present
     */
    @Override
    public int indexOf(Object o) {
        return vector.indexOf(o);
    }

    /**
     * Returns the index of the last occurrence of the specified element in the list.
     *
     * @param o the element to search for
     * @return the index of the last occurrence of the element, or -1 if it is not present
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public int lastIndexOf(Object o) throws IndexOutOfBoundsException {
        return vector.lastIndexOf(o);
    }

    /**
     * Returns a list iterator for the list.
     *
     * @return a list iterator for the list
     */
    @Override
    public HListIterator listIterator() {
        return new ListAdapterListIterator(0);
    }

    /**
     * Returns a list iterator for the list starting from a specified position.
     *
     * @param index the starting position of the list iterator
     * @return a list iterator for the list
     */
    @Override
    public HListIterator listIterator(int index) {
        return new ListAdapterListIterator(index);
    }

    /**
     * Removes the element at a specified position.
     *
     * @param index the position of the element to remove
     * @return the element removed
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public Object remove(int index) throws ArrayIndexOutOfBoundsException {
        Object element = vector.elementAt(index);
        vector.removeElementAt(index);
        return element;
    }

    /**
     * Replaces the element at a specified position with a new element.
     *
     * @param index the position of the element to replace
     * @param element the new element
     * @return the previous element at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public Object set(int index, Object element) throws ArrayIndexOutOfBoundsException {
        Object oldElement = vector.elementAt(index);
        vector.setElementAt(element, index);
        return oldElement;
    }

    /**
     * Returns a sublist from the specified start index (inclusive) to the end index (exclusive).
     *
     * @param fromIndex the starting index (inclusive)
     * @param toIndex the ending index (exclusive)
     * @return a sublist from the specified range
     */
    @Override
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > vector.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        ListAdapter sublist = new ListAdapter();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(vector.elementAt(i));
        }
        return sublist;
    }

    // ListAdapterIterator class
    private class ListAdapterIterator implements HIterator {
        protected int currentIndex = 0;
        protected int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex < vector.size();
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            lastIndex = currentIndex;
            return vector.elementAt(currentIndex++);
        }

        @Override
        public void remove() {
            if (lastIndex < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.remove(lastIndex);
            currentIndex = lastIndex;
            lastIndex = -1;
        }
    }

    // ListAdapterListIterator class
    private class ListAdapterListIterator extends ListAdapterIterator implements HListIterator {
        public ListAdapterListIterator(int index) {
            this.currentIndex = index;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) {
                throw new java.util.NoSuchElementException();
            }
            lastIndex = --currentIndex;
            return vector.elementAt(currentIndex);
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void set(Object e) {
            if (lastIndex < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.set(lastIndex, e);
        }

        @Override
        public void add(Object e) {
            ListAdapter.this.add(currentIndex++, e);
            lastIndex = -1;
        }
    }
}
