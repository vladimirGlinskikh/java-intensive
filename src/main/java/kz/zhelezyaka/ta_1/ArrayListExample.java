package kz.zhelezyaka.ta_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Не потокобезопасная реализация ArrayList.
 *
 * @param <E> Тип элементов в списке.
 */
public class ArrayListExample<E> {
    private static final int DEFAULT_CAPACITY = 10;
    public static final String INDEX_LABEL = "Index";
    public static final String SIZE_LABEL = "Size";
    private Object[] elements;
    private int size;

    /**
     * Конструктор без параметров.
     */
    public ArrayListExample() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Добавляем элемент в конец списка.
     *
     * @param element Элемент для добавления.
     */
    public void add(E element) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = element;
    }

    /**
     * Добавляем элемент по указанному индексу.
     *
     * @param index   Индекс для вставки элемента.
     * @param element Элемент для добавления.
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    INDEX_LABEL + ": " + index + SIZE_LABEL + ": " + size);
        }

        if (size == elements.length) {
            ensureCapacity();
        }

        // Сдвигаем элементы вправо, начиная с конца списка
        System.arraycopy(elements,
                index,
                elements,
                index + 1,
                size - index);
        // Вставляем новый элемент на освободившееся место
        elements[index] = element;
        // Увеличиваем размер списка
        size++;
    }

    /**
     * Получаем элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент по указанному индексу.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    INDEX_LABEL + ": " + index + SIZE_LABEL + ": " + size);
        }
        return (E) elements[index];
    }

    /**
     * Заменяет элемент по указанному индексу новым элементом.
     *
     * @param index   Индекс элемента для замены.
     * @param element Новый элемент.
     * @return Старый элемент по указанному индексу.
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    INDEX_LABEL + ": " + index + SIZE_LABEL + ": " + size);
        }

        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Сортируем элементы списка в естественном порядке.
     */
    public void sort() {
        sort(null);
    }


    /**
     * Сортируем элементы в списке при помощи компаратора.
     *
     * @param comparator Компаратор для определения порядка элементов.
     */
    public void sort(Comparator<? super E> comparator) {
        if (comparator == null) {
            Arrays.sort(elements, 0, size);
        } else {
            Arrays.sort((E[]) elements, 0, size, comparator);
        }
    }

    /**
     * Удаляем элемент по индексу.
     *
     * @param index Индекс элемента для удаления.
     * @return Удаленный элемент.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    INDEX_LABEL + ": " + index + SIZE_LABEL + ": " + size);
        }

        E removedElement = (E) elements[index];

        // Сдвигаем элементы влево, начиная с элемента после удаляемого
        System.arraycopy(
                elements, index + 1,
                elements, index, size - index - 1);

        // Очищаем последний элемент и уменьшаем размер списка
        elements[--size] = null;

        return removedElement;
    }

    /**
     * Очищаем всю коллекцию.
     */
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    /**
     * Возвращаем размер списка.
     *
     * @return Размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Проверяем, содержится ли указанный элемент в списке.
     *
     * @param element Элемент, наличие которого проверяется в списке.
     * @return {@code true}, если элемент присутствует в списке, в противном случае {@code false}.
     * @throws NullPointerException если указанный элемент равен {@code null} и хотя бы один элемент в списке тоже равен {@code null}.
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Увеличиваем емкость(капасити) списка, если она меньше указанного значения.
     */
    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
