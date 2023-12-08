package tdd.ita.semana03.handson;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final CartObservable cartObservable;

    private final List<Product> itens = new ArrayList<>();

    public ShoppingCart(CartObservable cartObservable) {
        this.cartObservable = cartObservable;
    }

    public void addItem(Product product) {
        try{
            itens.add(product);
            cartObservable.addedProduct(product.name(), product.value());
        } catch (IllegalArgumentException ignored) {
        }
    }

    public int total() {
        return itens.stream().mapToInt(Product::value).sum();
    }

}
