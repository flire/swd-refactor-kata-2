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
			double rentalCost = rental.getCost();
			frequentRenterPoints += rental.getRenterPoints();
			resultBuffer.append(String.format(RENTAL_FORMAT_STRING, rental.getMovie().getTitle(), rentalCost));
			totalAmount += rentalCost;
		}

		resultBuffer.append(String.format(CUSTOMER_OWED_FORMAT_STRING, totalAmount));
		resultBuffer.append(String.format(CUSTOMER_POINTS_FORMAT_STRING, frequentRenterPoints));

		return resultBuffer.toString();
	}

	private String HEADER_FORMAT_STRING = "Rental Record for %s\n";
	private String RENTAL_FORMAT_STRING = "\t%s\t%.1f\n";
	private String CUSTOMER_OWED_FORMAT_STRING = "You owed %.1f\n";
	private String CUSTOMER_POINTS_FORMAT_STRING = "You earned %d frequent renter points\n";

	private String name;
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
}
