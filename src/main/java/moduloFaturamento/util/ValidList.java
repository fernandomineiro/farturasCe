package moduloFaturamento.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.validation.Valid;

public class ValidList<E> implements List<E>{
    
    @Valid
    private List<E> list;

    public ValidList() {
        this.list = new ArrayList<E>();
    }

    public ValidList(List<E> list) {
        this.list = list;
    }

    // Bean-like methods, used by javax.validation but ignored by JSON parsing

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    // List-like methods, used by JSON parsing but ignored by javax.validation

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        
        return false;
    }

    @Override
    public void clear() {
        
        
    }

    @Override
    public E get(int index) {
        
        return null;
    }

    @Override
    public E set(int index, E element) {
        
        return null;
    }

    @Override
    public void add(int index, E element) {
        
        
    }

    @Override
    public E remove(int index) {
        
        return null;
    }

    @Override
    public int indexOf(Object o) {
        
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        
        return null;
    }

    // Other list methods ...
}

