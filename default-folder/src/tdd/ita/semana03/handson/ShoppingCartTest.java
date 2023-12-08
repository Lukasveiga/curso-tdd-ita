package tdd.ita.semana03.handson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    public void totalCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("shoes", 100));
        cart.addProduct(new Product("t-shirt ", 50));
        cart.addProduct(new Product("short", 70));
        assertEquals(220, cart.total());
    }
}
