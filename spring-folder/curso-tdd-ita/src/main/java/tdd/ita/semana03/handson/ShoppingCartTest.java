package tdd.ita.semana03.handson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    public void creatCartInstance() {
        cart = new ShoppingCart();
    }

    @Test
    public void totalCart() {
        cart.addItem(new Product("shoes", 100));
        cart.addItem(new Product("t-shirt", 50));
        cart.addItem(new Product("short", 70));
        assertEquals(220, cart.total());
    }
}
