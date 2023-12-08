package tdd.ita.semana03.handson;

public class CartObservableImp implements CartObservable {

    private String nameReceipt;
    private int valueReceipt;

    @Override
    public void addedProduct(String name, int value) {
        this.nameReceipt = name;
        this.valueReceipt = value;
    }
}
