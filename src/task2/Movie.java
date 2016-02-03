package task2;

import java.util.HashMap;

public class Movie {
	public Movie(String title, Type type) {
		this.title = title;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public RentalConditions getRentalConditions() {
		return RENTAL_TABLE.get(type);
	}

	public static enum Type {
		CHILDREN, REGULAR, NEW_RELEASE
	}

	public static class RentalConditions {
		public final int fixedPriceDays;
		public final double fixedPrice;
		public final double overtimePrice;

		public RentalConditions(int fixedDays, double fixedPrice, double overtimePrice) {
			this.fixedPriceDays = fixedDays;
			this.fixedPrice = fixedPrice;
			this.overtimePrice = overtimePrice;
		}
	}

	private static HashMap<Type, RentalConditions> RENTAL_TABLE;

	static {
		RENTAL_TABLE = new HashMap<>();
		RENTAL_TABLE.put(Type.CHILDREN, new RentalConditions(3, 1.5, 1.5));
		RENTAL_TABLE.put(Type.REGULAR, new RentalConditions(2, 2, 1.5));
		RENTAL_TABLE.put(Type.NEW_RELEASE, new RentalConditions(0, 0, 3));
	}

	private String title;
	private Type type;
}
