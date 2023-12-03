package tdd.ita.semana01.handson.firstSteps;

public class MyStack <T> {

    private T element;
    private static int nElements;

    public boolean isEmpty() {
        return element == null;
    }

    public int size() {
        return nElements;
    }

    public void add(T element) {
        this.element = element;
        nElements ++;
    }

    public T peek() {
        return element;
    }
}
