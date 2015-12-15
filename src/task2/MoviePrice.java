package task2;

public class MoviePrice {
	public final int fixedPriceDays;
	public final double fixedPrice;
	public final double overtimePrice;
	public MoviePrice(int fixedDays, double fixedPrice, double overtimePrice) {
		this.fixedPriceDays = fixedDays;
		this.fixedPrice = fixedPrice;
		this.overtimePrice = overtimePrice;
	}
}
