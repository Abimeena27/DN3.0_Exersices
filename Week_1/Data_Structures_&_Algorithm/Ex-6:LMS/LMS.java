import java.util.ArrayList;
import java.util.List;

public class LMS {
    public static void main(String[] args) {
        // Setup example books
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(3, "1984", "George Orwell"));

        // Linear Search
        String titleToSearch = "To Kill a Mockingbird";
        Book foundBookLinear = SearchUtils.linearSearchByTitle(books, titleToSearch);
        System.out.println("Linear Search Result: " + (foundBookLinear != null ? foundBookLinear : "Not Found"));

        // Binary Search (requires sorted list)
        Book foundBookBinary = SearchUtils.binarySearchByTitle(books, titleToSearch);
        System.out.println("Binary Search Result: " + (foundBookBinary != null ? foundBookBinary : "Not Found"));
    }
}
