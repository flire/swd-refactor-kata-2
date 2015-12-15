package task2;

public class Rental
{
    public Rental (Movie movie, int daysRented) {
        this.movie      = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented () {
        return daysRented;
    }

    public Movie getMovie () {
        return movie;
    }

    private final Movie movie;
    private int daysRented;
}
