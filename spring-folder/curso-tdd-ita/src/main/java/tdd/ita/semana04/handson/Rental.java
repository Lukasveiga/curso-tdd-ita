package tdd.ita.semana04.handson;

public class Rental {

    final Movie movie;
    private final int daysRented;
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getFrequentRenterPoints() {
        return this.movie.getFrequentRenterPoints(this.daysRented);
    }

    public double getAmount() {
        return this.movie.getAmount(this.daysRented);
    }

    public int getDaysRented() {
        return daysRented;
    }
    public Movie getMovie() {
        return movie;
    }
}
