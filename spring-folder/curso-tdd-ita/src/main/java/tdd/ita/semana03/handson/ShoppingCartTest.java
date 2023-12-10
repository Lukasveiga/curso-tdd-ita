package tdd.ita.semana03.handson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartTest {

    @InjectMocks
    private ShoppingCart cart;

    @Mock
    private CartObservable cartObservable;

    @Test
    public void totalCart() {
        cart.addItem(new Product("shoes", 100));
        cart.addItem(new Product("t-shirt", 50));
        cart.addItem(new Product("short", 70));
        assertEquals(220, cart.total());
    }

    @Test
    public void observeAddOneProductToCart() {
        cart.addItem(new Product("shoes", 100));
        verify(cartObservable, times(1)).addedProduct("shoes", 100);
    }

    @Test
    public void observeAddTwoProductsToCart() {
        cart.addItem(new Product("shoes", 100));
        verify(cartObservable, times(1)).addedProduct("shoes", 100);

        cart.addItem(new Product("t-shirt", 50));
        verify(cartObservable, times(1)).addedProduct("t-shirt", 50);
    }

    @Test
    public void continueEvenObservableThrows() {
        doThrow(IllegalArgumentException.class).when(cartObservable).addedProduct("shoes", 100);
        cart.addItem(new Product("shoes", 100));

        cart.addItem(new Product("t-shirt", 50));
        verify(cartObservable, times(1)).addedProduct("t-shirt", 50);
    }
}
