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
        if(this.elements.isEmpty()) {
            throw new EmptyMyStackException("Isn't possible peek element from a empty stack");
        }

        int index = this.elements.size() - 1;
        return this.elements.get(index);
    }

    public T pop() {
        if(this.elements.isEmpty()) {
            throw new EmptyMyStackException("Isn't possible pop element from a empty stack");
        }

        int index = this.elements.size() - 1;
        return this.elements.remove(index);
    }
}
