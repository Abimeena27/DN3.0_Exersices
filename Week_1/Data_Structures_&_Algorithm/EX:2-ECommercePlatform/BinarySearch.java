import java.util.Arrays;
import java.util.Comparator;

public class SearchAlgorithms {

   
    public static int binarySearch(Product[] products, String targetProductName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareTo(targetProductName);

            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                left = mid + 1; 
            } else {
                right = mid - 1;  
            }
        }
        return -1;  
    }
}
