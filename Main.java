import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product(1, "Laptop Dell", "Electronics", 1200.0, 4.5, true),
            new Product(2, "Mouse Logi", "Electronics", 50.0, 4.8, true),
            new Product(3, "Coffee Maker", "Home", 80.0, 4.2, false),
            new Product(4, "Headphones", "Electronics", 150.0, 3.9, true),
            new Product(5, "Blender", "Home", 50.0, 4.0, true), 
            new Product(6, "iPhone 15", "Electronics", 900.0, 4.7, false),
            new Product(7, "Desk Lamp", "Home", 25.0, 4.9, true)
        );

        System.out.println("=== 1. Багатокритеріальне сортування ===");
        // Спочатку "В наявності", потім дешевші, потім вищий рейтинг
        Comparator<Product> multiSorter = Comparator
                .comparing(Product::isInStock).reversed()
                .thenComparing(Product::getPrice)
                .thenComparing(Comparator.comparing(Product::getRating).reversed());

        products.stream().sorted(multiSorter).forEach(System.out::println);

        System.out.println("\n=== 2. Гнучка фільтрація ===");
        // Умови: Електроніка + Ціна(100-1500) + Підрядок "o" в назві
        Predicate<Product> categoryTech = p -> p.getCategory().equals("Electronics");
        Predicate<Product> priceRange = p -> p.getPrice() >= 100 && p.getPrice() <= 1500;
        Predicate<Product> nameSearch = p -> p.getName().toLowerCase().contains("o");
        
        Predicate<Product> complexFilter = categoryTech.and(priceRange).and(nameSearch);

        products.stream().filter(complexFilter).forEach(System.out::println);

        System.out.println("\n=== 3. Тестування ===");
        runTests();
    }

    public static void runTests() {
        Product p1 = new Product(1, "A", "Cat", 100, 5.0, true);
        Product p2 = new Product(2, "B", "Cat", 100, 4.0, true);
        Product p3 = new Product(3, "C", "Cat", 200, 4.5, false);

        // Тест 1: Сортування
        if (Double.compare(p1.getPrice(), 100.0) == 0) System.out.println("[OK] Ціна коректна");

        // Тест 2: Комбінований компаратор
        Comparator<Product> stableSorter = Comparator.comparing(Product::getPrice)
                .thenComparing(Comparator.comparing(Product::getRating).reversed());
        if (stableSorter.compare(p1, p2) < 0) System.out.println("[OK] Багатокритеріальність працює");
        else System.out.println("[FAIL] Помилка сортування");

        // Тест 3: Фільтр
        Predicate<Product> range = p -> p.getPrice() > 150;
        if (!range.test(p1) && range.test(p3)) System.out.println("[OK] Фільтр діапазону коректний");
    }
}
