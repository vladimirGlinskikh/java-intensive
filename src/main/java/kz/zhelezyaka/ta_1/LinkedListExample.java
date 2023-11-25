package kz.zhelezyaka.ta_1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Простая реализация односвязного списка.
 *
 * @param <E> Тип элементов в связном списке.
 */
public class LinkedListExample<E> {

    public static final String INVALID_INDEX_LABEL = "Неверный индекс";

    // Внутренний класс для представления узла связного списка
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    // Головной элемент списка
    private Node<E> head;

    // Количество элементов в списке
    private int size;

    /**
     * Добавляем элемент в конец списка.
     *
     * @param element Элемент для добавления.
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * Добавляем элемент в указанную позицию списка.
     *
     * @param index   Позиция, в которую нужно вставить элемент.
     * @param element Элемент для добавления.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы размера списка.
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_LABEL + ": " + index);
        }

        Node<E> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> previous = getNode(index - 1);
            newNode.next = previous.next;
            previous.next = newNode;
        }
        size++;
    }

    /**
     * Возвращаем элемент по указанному индексу.
     *
     * @param index Индекс элемента в списке.
     * @return Элемент списка.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы размера списка.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_LABEL + ": " + index);
        }

        return getNode(index).data;
    }

    /**
     * Удаляем элемент по указанному индексу.
     *
     * @param index Индекс элемента для удаления.
     * @return Удаленный элемент списка.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы размера списка.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_LABEL + ": " + index);
        }

        Node<E> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
        } else {
            Node<E> previous = getNode(index - 1);
            removedNode = previous.next;
            previous.next = removedNode.next;
        }

        size--;
        return removedNode.data;
    }

    /**
     * Очищаем весь список.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Сортируем список с использованием компаратора.
     *
     * @param comparator Компаратор для сравнения элементов списка.
     */
    public void sort(Comparator<? super E> comparator) {
        // Простая сортировка вставками (Insertion Sort)
        // Внешний цикл пройдет по всем элементам списка
        for (Node<E> i = head; i != null; i = i.next) {
            // Внутренний цикл будет сравнивать текущий элемент с последующими
            for (Node<E> j = i.next; j != null; j = j.next) {
                // Если текущий элемент больше следующего, меняем их местами
                if (comparator.compare(i.data, j.data) > 0) {
                    E temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
    }

    /**
     * Возвращаем итератор для прохода по элементам списка.
     *
     * @return Итератор для прохода по элементам списка.
     */
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("В итерации больше нет элементов");
                }

                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    // Вспомогательный метод для получения узла по индексу
    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Возвращаем количество элементов в списке.
     *
     * @return Количество элементов в списке.
     */
    public int size() {
        return size;
    }
}
