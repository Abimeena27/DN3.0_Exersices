import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem {
    private Map<String, Product> inventory;

    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(String productId, Product updatedProduct) {
        if (inventory.containsKey(productId)) {
            inventory.put(productId, updatedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public void displayInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Adding products
        Product p1 = new Product("001", "Laptop", 10, 999.99);
        Product p2 = new Product("002", "Smartphone", 20, 499.99);
        ims.addProduct(p1);
        ims.addProduct(p2);

        // Displaying inventory
        System.out.println("Inventory:");
        ims.displayInventory();

        // Updating a product
        Product p1Updated = new Product("001", "Laptop", 15, 949.99);
        ims.updateProduct("001", p1Updated);
        System.out.println("Inventory after update:");
        ims.displayInventory();

        // Deleting a product
        ims.deleteProduct("002");
        System.out.println("Inventory after deletion:");
        ims.displayInventory();
    }
}
