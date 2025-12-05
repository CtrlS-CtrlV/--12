public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private double rating;
    private boolean inStock;

    public Product(int id, String name, String category, double price, double rating, boolean inStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public boolean isInStock() { return inStock; }

    @Override
    public String toString() {
        return String.format("%-15s | %-10s | %7.2f$ | ★ %.1f | %s", 
            name, category, price, rating, (inStock ? "В наявності" : "Немає"));
    }
}
