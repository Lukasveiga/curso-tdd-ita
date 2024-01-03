package tdd.ita.semana04.handson;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private final String name;
    private final Vector rentals = new Vector();

    private int frequentRenterPoints = 0;

    private double totalAmount = 0.0;

    public Customer (String name){
        this.name = name;
    };
    public void addRental(Rental rental) {
        rentals.addElement(rental);
        frequentRenterPoints += rental.getFrequentRenterPoints();
        totalAmount += rental.getAmount();
    }

    public String getName () {
        return name;
    };

    public String statement() {
        Enumeration rentals = this.rentals.elements();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(each.getAmount()).append("\n");
        }

        result.append("Amount owed is ").append(getTotalAmount()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }

    private int getTotalFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    private double getTotalAmount() {
        return totalAmount;
    }

}
