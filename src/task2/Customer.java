package task2;

import java.util.ArrayList;

public class Customer {
	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuffer resultBuffer = new StringBuffer(String.format(HEADER_FORMAT_STRING, name));

		for (Rental rental : rentals) {
			double thisAmount = getRentalCost(rental);
			frequentRenterPoints += getRenterPoints(rental);
			resultBuffer.append(String.format(RENTAL_FORMAT_STRING, rental.getMovie().getTitle(), thisAmount));
			totalAmount += thisAmount;
		}

		resultBuffer.append(String.format(CUSTOMER_OWED_FORMAT_STRING, totalAmount));
		resultBuffer.append(String.format(CUSTOMER_POINTS_FORMAT_STRING, frequentRenterPoints));

		return resultBuffer.toString();
	}

	private double getRentalCost(Rental rental) {
		MoviePrice moviePrice = getMoviePrice(rental.getMovie().getMovieType());
		return getRentalCost(moviePrice, rental.getDaysRented());
	}

	private int getRenterPoints(Rental rental) {
		return getMovieRenterPoints(rental.getMovie().getMovieType(), rental.getDaysRented()) + FIXED_RENTER_POINTS;
	}

	private int getMovieRenterPoints(MovieType movieType, int daysRented) {
		if (movieType == MovieType.NEW_RELEASE && daysRented >= NEW_RELEASE_DAYS_FOR_BONUS) {
			return 1;
		}
		return 0;
	}

	private double getRentalCost(MoviePrice moviePrice, int daysRented) {
		int overtime = Math.max(daysRented - moviePrice.fixedPriceDays, 0);
		return moviePrice.fixedPrice + overtime * moviePrice.overtimePrice;
	}

	private MoviePrice getMoviePrice(MovieType movie) {
		switch (movie) {
		case REGULAR:
			return new MoviePrice(2, 2, 1.5);
		case NEW_RELEASE:
			return new MoviePrice(0, 0, 3);
		case CHILDRENS:
			return new MoviePrice(3, 1.5, 1.5);
		default:
			throw new IllegalArgumentException("Unknown movie type");
		}
	}

	private int FIXED_RENTER_POINTS = 1;
	private int NEW_RELEASE_DAYS_FOR_BONUS = 2;

	private String HEADER_FORMAT_STRING = "Rental Record for %s\n";
	private String RENTAL_FORMAT_STRING = "\t%s\t%.1f\n";
	private String CUSTOMER_OWED_FORMAT_STRING = "You owed %.1f\n";
	private String CUSTOMER_POINTS_FORMAT_STRING = "You earned %d frequent renter points\n";

	private String name;
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
}
