package tdd.ita.semana01.handson.firstSteps;

public class MyStack <T> {

    private T element;

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public void add(T element) {
        this.element = element;
    }

    public T peek() {
        return element;
    }
}
