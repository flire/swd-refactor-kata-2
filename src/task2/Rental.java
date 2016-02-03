package task2;

import task2.Movie.RentalConditions;

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
    
	public double getCost() {
		RentalConditions movieRentalConditions = movie.getRentalConditions();
		int overtime = Math.max(daysRented - movieRentalConditions.fixedPriceDays, 0);
		return movieRentalConditions.fixedPrice + overtime * movieRentalConditions.overtimePrice;
	}
	
	public int getRenterPoints() {
		int result = FIXED_RENTER_POINTS;
		if (movie.getType() == Movie.Type.NEW_RELEASE && daysRented >= NEW_RELEASE_DAYS_FOR_BONUS) {
			result += NEW_RELEASE_BONUS;
		}
		return result;
	}
	
	private static int FIXED_RENTER_POINTS = 1;
	private static int NEW_RELEASE_DAYS_FOR_BONUS = 2;
	private static int NEW_RELEASE_BONUS = 1;

    private final Movie movie;
    private int daysRented;
}
