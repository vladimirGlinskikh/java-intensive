package kz.zhelezyaka.дз_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListExampleTest {
    private ArrayListExample<String> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new ArrayListExample<>();
    }

    @Test
    void shouldAddedElements() {
        addElements("a", "b");
        assertEquals(2, arrayList.size());
    }

    @Test
    void shouldAddedElementsByIndex() {
        arrayList.add(0, "a");
        arrayList.add(1, "b");
        arrayList.add(2, "c");
        arrayList.add(3, "d");
        assertEquals("b", arrayList.get(1));
        assertEquals("d", arrayList.get(3));
    }

    @Test
    void shouldGetElementByIndex() {
        addElements("a", "b", "c", "d", "e");
        assertEquals("e", arrayList.get(4));
        assertEquals("a", arrayList.get(0));
    }

    @Test
    void shouldSetElementByIndex() {
        addElements("a", "b", "c", "d", "e");

        String oldElement = arrayList.set(2, "new B");

        assertEquals("c", oldElement);
        assertEquals("new B", arrayList.get(2));
    }

    @Test
    void shouldSortByNaturalOrder() {
        addElements("f", "b", "i", "d", "y", "u", "a");

        assertEquals("f", arrayList.get(0));
        assertEquals("b", arrayList.get(1));
        assertEquals("i", arrayList.get(2));
        assertEquals("d", arrayList.get(3));
        assertEquals("y", arrayList.get(4));
        assertEquals("u", arrayList.get(5));
        assertEquals("a", arrayList.get(6));

        arrayList.sort();

        assertEquals("a", arrayList.get(0));
        assertEquals("b", arrayList.get(1));
        assertEquals("d", arrayList.get(2));
        assertEquals("f", arrayList.get(3));
        assertEquals("i", arrayList.get(4));
        assertEquals("u", arrayList.get(5));
        assertEquals("y", arrayList.get(6));
    }

    @Test
    void shouldSortWithComparatorByLength() {
        addElements("one", "two", "three", "four", "five");
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        arrayList.sort(lengthComparator);

        assertEquals("one", arrayList.get(0));
        assertEquals("two", arrayList.get(1));
        assertEquals("four", arrayList.get(2));
        assertEquals("five", arrayList.get(3));
        assertEquals("three", arrayList.get(4));
    }

    @Test
    void shouldSortWithComparatorByLetter() {
        addElements("one", "two", "three", "four", "five");

        Comparator<String> alphabetComparator = Comparator.naturalOrder();
        arrayList.sort(alphabetComparator);

        assertEquals("five", arrayList.get(0));
        assertEquals("four", arrayList.get(1));
        assertEquals("one", arrayList.get(2));
        assertEquals("three", arrayList.get(3));
        assertEquals("two", arrayList.get(4));
    }

    @Test
    void shouldRemoveElement() {
        addElements("a", "b", "c", "d");

        String removedElement = arrayList.remove(2);

        assertEquals(3, arrayList.size());
        assertEquals("c", removedElement);
    }

    @Test
    void shouldClearsArray() {
        addElements("a", "b", "c", "d");
        arrayList.clear();

        assertEquals(0, arrayList.size());
    }

    @Test
    void shouldContainsWhenPresent() {
        addElements("one", "two", "three", "four", "five");

        assertTrue(arrayList.contains("three"));
    }

    @Test
    void shouldNotContainsWhenNotPresent() {
        addElements("one", "two", "three", "four", "five");

        assertFalse(arrayList.contains("six"));
    }

    @Test
    void shouldNotContainsWithNullElement() {
        assertFalse(arrayList.contains(null));
    }

    @Test
    void shouldContainsWithNullElementInList() {
        addElements(null, null, null);
        assertTrue(arrayList.contains(null));
    }

    private void addElements(String... elements) {
        for (String element : elements) {
            arrayList.add(element);
        }
    }
}