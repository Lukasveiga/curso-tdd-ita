package tdd.ita.semana04.handson;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCustomer {

    Customer client;

    @BeforeEach
    protected void setUp() {
        client = new Customer("John");
    }

    @Test
    public void testNameCreation() {
        var result = client.statement();
        Assertions.assertThat(result).contains("Rental Record for John");
    }

    @Test
    public void testOneRegularOneDay() {
        rentMovie("Indiana Jones", Movie.REGULAR, 1);

        var result = client.statement();
        Assertions.assertThat(result).contains("Rental Record for John");
        Assertions.assertThat(result).contains("Amount owed is 2.0");
        Assertions.assertThat(result).contains("You earned 1 frequent renter points");
    }

    @Test
    public void testOneRegularTreeDays() {
        rentMovie("Indiana Jones", Movie.REGULAR, 3);

        var result = client.statement();
        Assertions.assertThat(result).contains("Rental Record for John");
        Assertions.assertThat(result).contains("Amount owed is 3.5");
        Assertions.assertThat(result).contains("You earned 1 frequent renter points");
    }

    @Test
    public void testOneChildrenOneDay() {
        rentMovie("Procurando nemo", Movie.CHILDRENS, 1);

        var result = client.statement();
        Assertions.assertThat(result).contains("Amount owed is 1.5");
        Assertions.assertThat(result).contains("You earned 1 frequent renter points");
    }

    @Test
    public void testOneChildrenFiveDays() {
        rentMovie("Procurando nemo", Movie.CHILDRENS, 5);

        var result = client.statement();
        Assertions.assertThat(result).contains("Amount owed is 4.5");
        Assertions.assertThat(result).contains("You earned 1 frequent renter points");
    }

    @Test
    public void testOneNewReleaseOneDay() {
        rentMovie("Vingadores 2", Movie.NEW_RELEASE, 1);

        var result = client.statement();
        Assertions.assertThat(result).contains("Amount owed is 3.0");
        Assertions.assertThat(result).contains("You earned 1 frequent renter points");
    }

    @Test
    public void testOneNewReleaseTreeDays() {
        rentMovie("Vingadores 2", Movie.NEW_RELEASE, 3);

        var result = client.statement();
        Assertions.assertThat(result).contains("Amount owed is 9.0");
        Assertions.assertThat(result).contains("You earned 2 frequent renter points");
    }

    @Test
    public void testManyRents() {
        rentMovie("Vingadores 2", Movie.NEW_RELEASE, 2);
        rentMovie("Star Wars - Episodio VII", Movie.NEW_RELEASE, 3);
        rentMovie("Procurando Nemo", Movie.CHILDRENS, 3);
        rentMovie("Indiana Jones", Movie.REGULAR, 2);
        rentMovie("Divertidamente", Movie.CHILDRENS, 4);
        rentMovie("E o ventou levou ...", Movie.REGULAR, 3);

        var result = client.statement();
        Assertions.assertThat(result).contains("Amount owed is 25.0");
        Assertions.assertThat(result).contains("You earned 8 frequent renter points");
    }

    private void rentMovie(String title, int type, int days) {
        Movie movie = Movie.createMovie(title, type);
        Rental rental = new Rental(movie, days);
        client.addRental(rental);
    }
}
