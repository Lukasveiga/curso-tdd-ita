package tdd.ita.semana01.handson.firstSteps;

import java.util.ArrayList;

public class MyStack <T> {

    private final ArrayList<T> elements;

    public MyStack() {
        this.elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return this.elements.size();
    }

    public void add(T element) {
        this.elements.add(element);
    }

    public T peek() {
        int index = this.elements.size() - 1;
        return this.elements.get(index);
    }

    public T pop() {
        int index = this.elements.size() - 1;
        return this.elements.remove(index);
    }
}
