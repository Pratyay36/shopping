package shopping;

public class Product {
	
	private String name;
    private double price;
    private int quantity;
    private boolean giftWrap;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.giftWrap = false;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setGiftWrap(boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    public double getTotal() {
        return price * quantity;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public String getName() {
        return name;
    }
}
