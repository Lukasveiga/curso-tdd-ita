package tdd.ita.semana04.handson;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private final String name;
    private final Vector rentals = new Vector();
    public Customer (String name){
        this.name = name;
    };
    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName () {
        return name;
    };

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            double thisAmount = each.getAmount();
            frequentRenterPoints += each.getFrequentRenterPoints();
            //show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}
