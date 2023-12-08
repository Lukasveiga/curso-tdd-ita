package tdd.ita.semana03.handson;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> itens = new ArrayList<>();

    public void addItem(Product product) {
        itens.add(product);
    }

    public int total() {
        return itens.stream().mapToInt(Product::value).sum();
    }

}
