package shopping;

public class Discount {

	public static double applyDiscount(double subtotal, int quantityA, int quantityB, int quantityC,
			double flat10Discount, double bulk5DiscountRate, double bulk10DiscountRate, double tiered50DiscountRate) {
		double flat10DiscountAmount = (subtotal > 200) ? flat10Discount : 0;
		double bulk5DiscountAmount = (quantityA > 10) ? productDiscount(quantityA, bulk5DiscountRate)
				: (quantityB > 10) ? productDiscount(quantityB, bulk5DiscountRate)
						: (quantityC > 10) ? productDiscount(quantityC, bulk5DiscountRate) : 0;
		double bulk10DiscountAmount = (quantityA + quantityB + quantityC > 20) ? subtotal * bulk10DiscountRate : 0;
		double tiered50DiscountAmount = (quantityA + quantityB + quantityC > 30 && quantityA > 15)
				? productDiscount(quantityA - 15, tiered50DiscountRate)
				: 0;

		return Math.max(flat10DiscountAmount,
				Math.max(bulk5DiscountAmount, Math.max(bulk10DiscountAmount, tiered50DiscountAmount)));
	}

	private static double productDiscount(int quantity, double discountRate) {
		return quantity * discountRate;
	}
}
