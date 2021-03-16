package com.example.demo.mylists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class MyList<T> implements AdvancedList<T>, AuthorHolder {

    private Object[] array = new Object[10];

    private int size = 0;

    @Override
    public AdvancedList<T> shuffle() {
        for(int i = 0; i < size; i++){
            int randomNumber = i + (int)(Math.random() * (size - i));
            Object item = array[randomNumber];
            array[randomNumber] = array[i];
            array[i] = item;
        }
        return null;
    }

    @Override
    public AdvancedList<T> sort(Comparator<T> comparator) {

        MyList<T> temporaryObject = new MyList<>();

        Object[] temporaryArray = Arrays.copyOf(array,size);
        temporaryObject.setArray(quickSort(temporaryArray,0, size-1, comparator));


        return temporaryObject;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public Object[] getArray() {
        return array;
    }


    private Object[] quickSort(Object[] temporaryArray, int leftBorder, int rightBorder, Comparator<T> comparator){
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        Object pivot = temporaryArray[(leftMarker + rightMarker) / 2];
        do {
            while (comparator.compare((T)pivot, (T) temporaryArray[leftMarker]) > 0) {
                leftMarker++;
            }
            while (comparator.compare((T)temporaryArray[rightMarker], (T)pivot)  > 0) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    Object tmp = temporaryArray[leftMarker];
                    temporaryArray[leftMarker] = temporaryArray[rightMarker];
                    temporaryArray[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(temporaryArray ,leftMarker, rightBorder, comparator);
        }
        if (leftBorder < rightMarker) {
            quickSort(temporaryArray ,leftBorder, rightMarker, comparator);
        }
        return temporaryArray;
    }


    @Override
    public String author() {
        return "Liza";
    }


    @Override
    public void add(T item) {
        if(size >= array.length) array = Arrays.copyOf(array, array.length * 2);
        array[size] = item;
        size++;

    }


    @Override
    public void insert(int index, T item) throws Exception {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;

    }

    @Override
    public void remove(int index) throws Exception {
        if (index >= size) throw new IndexOutOfBoundsException();
        for(int i = index; i < size; i++){
            array[i] = array[i+1];
        }
        size--;


    }

    @Override
    public Optional<T> get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
        return Optional.of(((T) array[index]));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(SimpleList<T> list) {
        for (int i = size; i < list.size(); i++){
            add((T)list.get(i).get());
        }

    }

    @Override
    public int first(T item) {
        for(int i = 0; i < size; i++){
            if(array[i] == item) return i;
        }
        return -1;
    }

    @Override
    public int last(T item) {
        for(int i = size - 1; i > 0; i--){
            if(array[i] == item) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T item) {
        for(int i = 0; i < size; i++){
            if(array[i] == item) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
