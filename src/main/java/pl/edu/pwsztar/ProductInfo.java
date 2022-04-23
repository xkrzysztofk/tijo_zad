package pl.edu.pwsztar;

public class ProductInfo {
    private int price;
    private int quantity;

    public ProductInfo(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }

}
