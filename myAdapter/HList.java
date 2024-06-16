package myAdapter;

/**
 * HList interface that defines the methods of a list.
 * Extends the HCollection interface to add list-specific operations.
 */
public interface HList extends HCollection {

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the index at which the element is to be inserted
     * @param element the element to be inserted
     */
    void add(int index, Object element);

    /**
     * Inserts all of the elements in the specified collection into this list,
     * starting at the specified position.
     *
     * @param index the index at which to start inserting elements
     * @param c     the collection containing elements to be added
     * @return true if all elements were successfully added, otherwise false
     */
    boolean addAll(int index, HCollection c);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to be returned
     * @return the element at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    Object get(int index) throws ArrayIndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the element, or -1 if the element is not present
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return the index of the last occurrence of the element, or -1 if the element is not present
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    int lastIndexOf(Object o) throws IndexOutOfBoundsException;

    /**
     * Returns a list iterator over the elements in this list.
     *
     * @return a list iterator over the elements in this list
     */
    HListIterator listIterator();

    /**
     * Returns a list iterator over the elements in this list, starting at the specified position.
     *
     * @param index the index to start the iteration from
     * @return a list iterator over the elements in this list, starting at the specified position
     */
    HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    Object remove(int index) throws ArrayIndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   the index of the element to be replaced
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    Object set(int index, Object element) throws ArrayIndexOutOfBoundsException;

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     *
     * @param fromIndex the starting index (inclusive) of the sub-list
     * @param toIndex   the ending index (exclusive) of the sub-list
     * @return a view of the specified range within this list
     * @throws ArrayIndexOutOfBoundsException if the indices are out of range
     */
    HList subList(int fromIndex, int toIndex) throws ArrayIndexOutOfBoundsException;
}
