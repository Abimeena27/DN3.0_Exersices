public class LinearSearch {

    // Linear search
    public static int linearSearch(Product[] products, String targetProductName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equals(targetProductName)) {
                return i;
            }
        }
        return -1;
    }
}
