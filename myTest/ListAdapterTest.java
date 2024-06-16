package myTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import myAdapter.ListAdapter;
import myAdapter.HList;
import myAdapter.HIterator;
import myAdapter.HListIterator;

import static org.junit.Assert.*;

/**
 * The ListAdapterTest class contains unit tests for the ListAdapter class.
 * It utilizes the JUnit framework for automated testing.
 */
public class ListAdapterTest {

    private ListAdapter list;
    private ListAdapter sublist;

    /**
     * Sets up the test environment. Initializes a ListAdapter with some elements
     * and creates a sublist.
     */
    @Before
    public void setUp() {
        list = new ListAdapter();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");
        list.add("Element4");
        list.add("Element5");

        // sublist contains elements "Element2", "Element3", "Element4"
        sublist = (ListAdapter) list.subList(1, 4);
    }

    /**
     * Cleans up the test environment. Resets lists to ensure independence between tests.
     */
    @After
    public void tearDown() {
        list = null;
        sublist = null;
    }

    /**
     * Test for the add(Object o) method.
     * Verifies that the element is correctly added to both the list and the sublist.
     */
    @Test
    public void testAdd() {
        list.add("NewElement");
        assertEquals(6, list.size());
        assertTrue(list.contains("NewElement"));

        sublist.add("SubNewElement");
        assertEquals(4, sublist.size());
        assertTrue(sublist.contains("SubNewElement"));
    }

    /**
     * Test for the add(int index, Object element) method.
     * Verifies that the element is correctly added at the specified position
     * in both the list and the sublist.
     */
    @Test
    public void testAddAtIndex() {
        list.add(2, "IndexedElement");
        assertEquals(6, list.size());
        assertEquals("IndexedElement", list.get(2));

        sublist.add(1, "SubIndexedElement");
        assertEquals(4, sublist.size());
        assertEquals("SubIndexedElement", sublist.get(1));
    }

    /**
     * Test for the addAll(HCollection c) method.
     * Verifies that all elements of the collection are correctly added
     * to both the list and the sublist.
     */
    @Test
    public void testAddAll() {
        ListAdapter otherList = new ListAdapter();
        otherList.add("ElementA");
        otherList.add("ElementB");
        
        list.addAll(otherList);
        assertEquals(7, list.size());
        assertTrue(list.contains("ElementA"));
        assertTrue(list.contains("ElementB"));

        sublist.addAll(otherList);
        assertEquals(7, sublist.size());
        assertTrue(sublist.contains("ElementA"));
        assertTrue(sublist.contains("ElementB"));
    }

    /**
     * Test for the clear() method.
     * Verifies that all elements are removed from both the list and the sublist.
     */
    @Test
    public void testClear() {
        list.clear();
        assertTrue(list.isEmpty());

        sublist.clear();
        assertTrue(sublist.isEmpty());
    }

    /**
     * Test for the contains(Object o) method.
     * Verifies whether the list and the sublist contain the specified elements.
     */
    @Test
    public void testContains() {
        assertTrue(list.contains("Element1"));
        assertFalse(list.contains("NonExistentElement"));

        assertTrue(sublist.contains("Element2"));
        assertFalse(sublist.contains("Element5"));
    }

    /**
     * Test for the containsAll(HCollection c) method.
     * Verifies whether the list contains all elements from the collection.
     */
    @Test
    public void testContainsAll() {
        ListAdapter otherList = new ListAdapter();
        otherList.add("Element1");
        otherList.add("Element2");

        assertTrue(list.containsAll(otherList));

        otherList.add("NonExistentElement"); 
        assertFalse(list.containsAll(otherList));
    }

    /**
     * Test for the equals(Object o) method.
     * Verifies whether two lists are equal.
     */
    @Test
    public void testEquals() {
        ListAdapter otherList = new ListAdapter();
        otherList.add("Element1");
        otherList.add("Element2");
        otherList.add("Element3");
        otherList.add("Element4");
        otherList.add("Element5");

        assertTrue(list.equals(otherList));
    }

    /**
     * Test for the isEmpty() method.
     * Verifies whether the list is empty or not empty.
     */
    @Test
    public void testIsEmpty() {
        ListAdapter emptyList = new ListAdapter();
        assertTrue(emptyList.isEmpty());

        assertFalse(list.isEmpty());
    }

    /**
     * Test for the iterator() method.
     * Verifies that the iterator can correctly iterate through all elements
     * of the list and the sublist.
     */
    @Test
    public void testIterator() {
        HIterator it = list.iterator();
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assertEquals(5, count);

        it = sublist.iterator();
        count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assertEquals(3, count);
    }

    /**
     * Test for the remove(Object o) method.
     * Verifies that the specified element is removed from both the list and the sublist.
     */
    @Test
    public void testRemove() {
        assertTrue(list.remove("Element3"));
        assertEquals(4, list.size());

        assertTrue(sublist.remove("Element2"));
        assertEquals(2, sublist.size());
    }

    /**
     * Test for the remove(Object o) method.
     * Verifies that the specified element cannot be removed from both the list and the sublist.
     * In such case the contains method should return false
     */
    @Test
    public void testRemoveNull() {
        assertFalse(list.remove("Element300"));
        assertEquals(5, list.size());
        assertFalse(list.contains("Element300"));

        assertFalse(sublist.remove("Element200"));
        assertEquals(3, sublist.size());
        assertFalse(sublist.contains("Element200"));
    }

    /**
     * Test for the remove(int index) method.
     * Verifies that the element at the specified position is removed
     * from both the list and the sublist.
     */
    @Test
    public void testRemoveAtIndex() {
        list.remove(2);
        assertEquals(4, list.size());
        assertFalse(list.contains("Element3"));

        sublist.remove(1);
        assertEquals(2, sublist.size());
        assertFalse(sublist.contains("Element3"));
    }

    /**
     * Test for the removeAll(HCollection c) method.
     * Verifies that all elements from the specified collection
     * are removed from both the list and the sublist.
     */
    @Test
    public void testRemoveAll() {
        ListAdapter otherList = new ListAdapter();
        otherList.add("Element2");
        otherList.add("Element3");

        list.removeAll(otherList);
        assertEquals(3, list.size());
        assertFalse(list.contains("Element2"));
        assertFalse(list.contains("Element3"));

        sublist.removeAll(otherList);
        assertEquals(1, sublist.size());
        assertFalse(sublist.contains("Element2"));
    }

    /**
     * Test for the retainAll(HCollection c) method.
     * Verifies that only the elements present in the specified collection
     * are retained in both the list and the sublist.
     */
    @Test
    public void testRetainAll() {
        ListAdapter otherList = new ListAdapter();
        otherList.add("Element2");
        otherList.add("Element3");
        otherList.add("Element4");

        list.retainAll(otherList);
        assertEquals(3, list.size());
        assertTrue(list.contains("Element2"));
        assertTrue(list.contains("Element3"));
        assertTrue(list.contains("Element4"));

        sublist.retainAll(otherList);
        assertEquals(2, sublist.size());
        assertTrue(sublist.contains("Element2"));
        assertTrue(sublist.contains("Element3"));
    }

    /**
     * Test for the size() method.
     * Verifies that the size of the list and the sublist is correct.
     */
    @Test
    public void testSize() {
        assertEquals(5, list.size());
        assertEquals(3, sublist.size());
    }

    /**
     * Test for the toArray() method.
     * Verifies that the resulting array contains all elements from the list
     * and the sublist.
     */
    @Test
    public void testToArray() {
        Object[] array = list.toArray();
        assertEquals(5, array.length);
        assertEquals("Element1", array[0]);

        array = sublist.toArray();
        assertEquals(3, array.length);
        assertEquals("Element2", array[0]);
    }

    /**
     * Test for the toArray(Object[] a) method.
     * Verifies that the specified array is correctly filled
     * with elements from the list and the sublist.
     */
    @Test
    public void testToArrayWithParameter() {
        Object[] array = new Object[5];
        list.toArray(array);
        assertEquals("Element1", array[0]);

        array = new Object[3];
        sublist.toArray(array);
        assertEquals("Element2", array[0]);
    }

    /**
     * Test for the get(int index) method.
     * Verifies that elements at the specified position are correctly
     * retrieved from the list and the sublist.
     */
    @Test
    public void testGet() {
        assertEquals("Element1", list.get(0));
        assertEquals
        assertEquals("Element3", list.get(2));

        assertEquals("Element2", sublist.get(0));
        assertEquals("Element4", sublist.get(2));
    }

    /**
     * Test for the indexOf(Object o) method.
     * Verifies that the index of the specified element is correctly
     * retrieved from the list and the sublist.
     */
    @Test
    public void testIndexOf() {
        assertEquals(0, list.indexOf("Element1"));
        assertEquals(2, list.indexOf("Element3"));
        assertEquals(-1, list.indexOf("NonExistentElement"));

        assertEquals(2, sublist.indexOf("Element3"));
        assertEquals(-1, sublist.indexOf("Element5"));
    }

    /**
     * Test for the lastIndexOf(Object o) method.
     * Verifies that the last index of the specified element is correctly
     * retrieved from the list and the sublist.
     */
    @Test
    public void testLastIndexOf() {
        list.add("Element3");
        assertEquals(5, list.lastIndexOf("Element3"));

        sublist.add("Element4");
        assertEquals(4, sublist.lastIndexOf("Element4"));
    }

    /**
     * Test for the listIterator() method.
     * Verifies that the list iterator can correctly iterate through all
     * elements of the list and the sublist.
     */
    @Test
    public void testListIterator() {
        HListIterator it = list.listIterator();
        assertTrue(it.hasNext());
        assertEquals("Element1", it.next());

        it = sublist.listIterator();
        assertTrue(it.hasNext());
        assertEquals("Element2", it.next());
    }

    /**
     * Test for the listIterator(int index) method.
     * Verifies that the list iterator starts correctly from the specified position
     * in the list and the sublist.
     */
    @Test
    public void testListIteratorWithIndex() {
        HListIterator it = list.listIterator(2);
        assertEquals("Element3", it.next());

        it = sublist.listIterator(1);
        assertEquals("Element3", it.next());
    }

    /**
     * Test for the set(int index, Object element) method.
     * Verifies that the element at the specified position is correctly
     * replaced in the list and the sublist.
     */
    @Test
    public void testSet() {
        list.set(1, "NewElement");
        assertEquals("NewElement", list.get(1));

        sublist.set(1, "SubNewElement");
        assertEquals("SubNewElement", sublist.get(1));
    }

    /**
     * Test for the subList(int fromIndex, int toIndex) method.
     * Verifies that the created sublist correctly contains the specified
     * elements.
     */
    @Test
    public void testSubList() {
        HList newSublist = list.subList(1, 3);
        assertEquals(2, newSublist.size());
        assertEquals("Element2", newSublist.get(0));
    }

    /**
     * Edge Cases Test: Verifies that methods handle edge cases correctly,
     * such as out-of-bounds indices.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexOutOfBounds() {
        list.add(10, "OutOfBounds");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds() {
        list.get(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBounds() {
        list.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListOutOfBounds() {
        list.subList(0, 6);
    }

    /**
     * Integrity Test: Verifies that modifications to the main list
     * are correctly reflected.
     */
    @Test
    public void testMainListModification() {
        list.set(2, "ModifiedElement");
        assertEquals("ModifiedElement", list.get(2));
    }

    /**
     * Integrity Test: Verifies that modifications to the sublist are
     * correctly reflected in the main list.
     */
    @Test
    public void testSublistModification() {
        sublist.set(0, "ModifiedElement");
        assertEquals("ModifiedElement", list.get(1));
    }

    /**
     * Invariance Test: Verifies that the size of the list remains
     * unchanged after additions and removals.
     */
    @Test
    public void testInvariantSize() {
        int initialSize = list.size();
        list.add("NewElement");
        list.remove("NewElement");
        assertEquals(initialSize, list.size());
    }

    /**
     * Invariance Test: Verifies that the order of elements remains
     * unchanged after additions and removals.
     */
    @Test
    public void testInvariantOrder() {
        list.add("NewElement");
        list.remove("NewElement");
        assertEquals("Element1", list.get(0));
        assertEquals("Element2", list.get(1));
    }

        /**
     * Test per la creazione di una sottolista da un'altra sottolista esistente.
     * Verifica che la nuova sottolista contenga gli elementi corretti.
     */
    @Test
    public void testSublistFromSublist() {
        // Crea una sottolista dalla sottolista esistente
        ListAdapter subsublist = (ListAdapter) sublist.subList(1, 3);

        assertEquals(2, subsublist.size());
        assertEquals("Element3", subsublist.get(0));
        assertEquals("Element4", subsublist.get(1));
    }

    /**
     * Test per l'aggiunta di elementi a una sottolista creata da un'altra sottolista.
     * Verifica che le modifiche siano riflesse nelle liste originali.
     */
    @Test
    public void testAddToSublistFromSublist() {
        ListAdapter subsublist = (ListAdapter) sublist.subList(0, 2);

        // Aggiunge un elemento alla subsublist
        subsublist.add("NewElement");

        // Verifica dimensione e contenuto nella subsublist
        assertEquals(3, subsublist.size());
        assertEquals("NewElement", subsublist.get(2));

        // Verifica dimensione e contenuto nella sublist originale
        assertEquals(3, sublist.size());
        assertEquals("NewElement", sublist.get(0));
    }

    /**
     * Test per la rimozione di elementi da una sottolista creata da un'altra sottolista.
     * Verifica che le modifiche siano riflesse nelle liste originali.
     */
    @Test
    public void testRemoveFromSublistFromSublist() {
        ListAdapter subsublist = (ListAdapter) sublist.subList(0, 2);

        // Rimuove un elemento dalla subsublist
        subsublist.remove("Element2");

        // Verifica dimensione e contenuto nella subsublist
        assertEquals(1, subsublist.size());
        assertEquals("Element3", subsublist.get(0));

        // Verifica dimensione e contenuto nella sublist originale
        assertEquals(2, sublist.size());
        assertEquals("Element3", sublist.get(0));
    }

    /**
     * Test per la modifica di elementi in una sottolista creata da un'altra sottolista.
     * Verifica che le modifiche siano riflesse nelle liste originali.
     */
    @Test
    public void testModifyInSublistFromSublist() {
        ListAdapter subsublist = (ListAdapter) sublist.subList(0, 2);

        // Modifica un elemento nella subsublist
        subsublist.set(1, "ModifiedElement");

        // Verifica contenuto nella subsublist
        assertEquals("ModifiedElement", subsublist.get(1));

        // Verifica contenuto nella sublist originale
        assertEquals("ModifiedElement", sublist.get(1));
    }
}
