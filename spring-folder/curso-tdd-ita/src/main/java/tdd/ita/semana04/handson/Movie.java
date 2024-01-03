package tdd.ita.semana04.handson;

public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private final String title;

    public static Movie createMovie(String title, int priceCode) {
        if (priceCode == REGULAR)
            return new Regular(title);

        if (priceCode == NEW_RELEASE)
            return new NewRelease(title);

        if (priceCode == CHILDRENS)
            return new Childrens(title);

        throw new RuntimeException("This type of film does not exist.");
    }

    protected Movie(String title) {
        this.title = title;
    }

    public String getTitle (){
        return title;
    };

    public abstract double getAmount(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
