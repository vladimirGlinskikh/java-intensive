package kz.zhelezyaka.ta_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListExampleTest {
    private LinkedListExample<String> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedListExample<>();
    }

    @Test
    void shouldAddElement() {
        addElements("a", "b");
        assertEquals(2, linkedList.size());
    }

    @Test
    void shouldAddElementByIndex() {
        linkedList.add(0, "f");
        linkedList.add(1, "u");
        linkedList.add(2, "f");
        linkedList.add(3, "o");
        linkedList.add(4, "q");
        assertEquals("o", linkedList.get(3));
        assertEquals("f", linkedList.get(0));
    }

    @Test
    void shouldGetElementByIndex() {
        addElements("a", "b", "c", "d", "e");
        assertEquals("e", linkedList.get(4));
        assertEquals("a", linkedList.get(0));
    }

    @Test
    void shouldRemoveElement() {
        addElements("a", "b", "c", "d", "e");
        String removedElement = linkedList.remove(3);
        assertEquals(4, linkedList.size());
        assertEquals("d", removedElement);
    }

    @Test
    void shouldClearList() {
        addElements("a", "b", "c", "d", "e");
        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    void shouldSortListWithComparator() {
        addElements("aaaaa", "aaa", "aa", "a", "aaaa");
        Comparator<String> lengthComparator = Comparator.comparing(String::length);

        linkedList.sort(lengthComparator);

        Iterator<String> iterator = linkedList.iterator();

        assertEquals("a", iterator.next());
        assertEquals("aa", iterator.next());
        assertEquals("aaa", iterator.next());
        assertEquals("aaaa", iterator.next());
        assertEquals("aaaaa", iterator.next());
    }

    @Test
    void shouldIterateOverElements() {
        addElements("a", "b", "c");

        Iterator<String> iterator = linkedList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldGetSize() {
        addElements();
        assertEquals(0, linkedList.size());

        addElements("a", "b", "c");
        assertEquals(3, linkedList.size());
    }

    private void addElements(String... elements) {
        for (String element : elements) {
            linkedList.add(element);
        }
    }
}