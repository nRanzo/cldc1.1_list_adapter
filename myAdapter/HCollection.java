package myAdapter;

/**
 * Interface that defines the basic methods of a collection.
 */
public interface HCollection {

    /**
     * Adds an element to the collection.
     *
     * @param o the element to be added
     * @return true if the element was successfully added, otherwise false
     */
    boolean add(Object o);

    /**
     * Adds all elements of the specified collection to this collection.
     *
     * @param c the collection containing elements to be added
     * @return true if all elements were successfully added, otherwise false
     */
    boolean addAll(HCollection c);

    /**
     * Removes all elements from the collection.
     */
    void clear();

    /**
     * Checks if the collection contains the specified element.
     *
     * @param o the element to be checked for in the collection
     * @return true if the collection contains the element, otherwise false
     */
    boolean contains(Object o);

    /**
     * Checks if the collection contains all elements of the specified collection.
     *
     * @param c the collection containing elements to be checked
     * @return true if the collection contains all elements, otherwise false
     */
    boolean containsAll(HCollection c);

    /**
     * Compares this collection with the specified object for equality.
     *
     * @param o the object to be compared
     * @return true if the collections are equal, otherwise false
     */
    boolean equals(Object o);

    /**
     * Returns the hash code of the collection.
     *
     * @return the hash code of the collection
     */
    int hashCode();

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, otherwise false
     */
    boolean isEmpty();

    /**
     * Returns an iterator over the collection.
     *
     * @return an iterator over the collection
     */
    HIterator iterator();

    /**
     * Removes the specified element from the collection.
     *
     * @param o the element to be removed
     * @return true if the element was successfully removed, otherwise false
     */
    boolean remove(Object o);

    /**
     * Removes all elements of the specified collection from this collection.
     *
     * @param c the collection containing elements to be removed
     * @return true if all elements were successfully removed, otherwise false
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     *
     * @param c the collection containing elements to be retained
     * @return true if the collection was modified, otherwise false
     * @throws ArrayIndexOutOfBoundsException if an exception occurs during the operation
     */
    boolean retainAll(HCollection c) throws ArrayIndexOutOfBoundsException;

    /**
     * Returns the number of elements in the collection.
     *
     * @return the number of elements in the collection
     */
    int size();

    /**
     * Returns an array containing all elements of the collection.
     *
     * @return an array containing all elements of the collection
     */
    Object[] toArray();

    /**
     * Returns an array containing all elements of the collection, 
     * using the specified array if it is large enough; 
     * otherwise, a new array of type Object[] is created.
     *
     * @param a the array into which the elements of the collection are to be stored
     * @return an array containing all elements of the collection
     */
    Object[] toArray(Object[] a);
}
