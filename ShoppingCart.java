package shopping;

import java.util.Scanner;

public class ShoppingCart {
	
	private Product productA;
    private Product productB;
    private Product productC;

    private double flat10Discount = 10;
    private double bulk5DiscountRate = 0.05;
    private double bulk10DiscountRate = 0.1;
    private double tiered50DiscountRate = 0.5;

    private double giftWrapFee = 1;
    private int itemsPerPackage = 10;
    private double shippingFeePerPackage = 5;

    public ShoppingCart() {
        productA = new Product("Product A", 20);
        productB = new Product("Product B", 40);
        productC = new Product("Product C", 50);
    }

    public void takeUserInput() {
        Scanner scanner = new Scanner(System.in);

        productA.setQuantity(takeQuantityInput("Product A"));
        productA.setGiftWrap(takeGiftWrapInput("Product A"));

        productB.setQuantity(takeQuantityInput("Product B"));
        productB.setGiftWrap(takeGiftWrapInput("Product B"));

        productC.setQuantity(takeQuantityInput("Product C"));
        productC.setGiftWrap(takeGiftWrapInput("Product C"));
    }

    private int takeQuantityInput(String productName) {
        System.out.print("Quantity of " + productName + ": ");
        return new Scanner(System.in).nextInt();
    }

    private boolean takeGiftWrapInput(String productName) {
        System.out.print("Gift wrap? (true/false): ");
        return new Scanner(System.in).nextBoolean();
    }

    public void displayShoppingCart() {
        double totalA = productA.getTotal();
        double totalB = productB.getTotal();
        double totalC = productC.getTotal();

        double subtotal = totalA + totalB + totalC;

        double discountAmount = Discount.applyDiscount(subtotal, productA.getQuantity(), productB.getQuantity(),
                productC.getQuantity(), flat10Discount, bulk5DiscountRate, bulk10DiscountRate, tiered50DiscountRate);

        double shippingFee = calculateShippingFee();
        double giftWrapTotalFee = calculateGiftWrapFee();

        double total = subtotal - discountAmount + shippingFee + giftWrapTotalFee;

        System.out.println("\nProduct Details:");
        displayProductDetails(productA);
        displayProductDetails(productB);
        displayProductDetails(productC);

        System.out.println("\nSubtotal: $" + subtotal);
        System.out.println("Discount Applied: $" + discountAmount);

        System.out.println("\nShipping Fee: $" + shippingFee);
        System.out.println("Gift Wrap Fee: $" + giftWrapTotalFee);

        System.out.println("\nTotal: $" + total);
    }

    private void displayProductDetails(Product product) {
        System.out.println(product.getName() + " - " + product.getQuantity() +
                " - $" + product.getTotal());
    }

    private double calculateShippingFee() {
        int totalItems = productA.getQuantity() + productB.getQuantity() + productC.getQuantity();
        int packages = (int) Math.ceil((double) totalItems / itemsPerPackage);
        return packages * shippingFeePerPackage;
    }

    private double calculateGiftWrapFee() {
        double giftWrapTotalFee = 0;

        if (productA.isGiftWrap()) {
            giftWrapTotalFee += productA.getQuantity() * giftWrapFee;
        }
        if (productB.isGiftWrap()) {
            giftWrapTotalFee += productB.getQuantity() * giftWrapFee;
        }
        if (productC.isGiftWrap()) {
            giftWrapTotalFee += productC.getQuantity() * giftWrapFee;
        }

        return giftWrapTotalFee;
    }
}
