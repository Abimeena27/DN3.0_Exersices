public class Main {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("001", "Alice", 250.0),
            new Order("002", "Bob", 150.0),
            new Order("003", "Charlie", 200.0),
            new Order("004", "Diana", 300.0)
        };

      
        System.out.println("Bubble Sort:");
        SortingAlgorithms.bubbleSort(orders);
        for (Order order : orders) {
            System.out.println(order);
        }

       
        System.out.println("\nQuick Sort:");
        Order[] orders2 = {
            new Order("001", "Alice", 250.0),
            new Order("002", "Bob", 150.0),
            new Order("003", "Charlie", 200.0),
            new Order("004", "Diana", 300.0)
        };
        SortingAlgorithms.quickSort(orders2, 0, orders2.length - 1);
        for (Order order : orders2) {
            System.out.println(order);
        }
    }
}

