import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product("001", "Laptop", "Electronics"),
            new Product("002", "Smartphone", "Electronics"),
            new Product("003", "Desk", "Furniture"),
            new Product("004", "Chair", "Furniture")
        };

   
        Arrays.sort(products, Comparator.comparing(Product::getProductName));

        
        int linearIndex = SearchAlgorithms.linearSearch(products, "Smartphone");
        System.out.println("Linear Search: Product found at index " + linearIndex);

        int binaryIndex = SearchAlgorithms.binarySearch(products, "Smartphone");
        System.out.println("Binary Search: Product found at index " + binaryIndex);
    }
}
