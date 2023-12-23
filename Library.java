import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, Book> books;
    private Map<String, User> users;
    private List<Transaction> transactions;
    private int transactionCount = 0;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(String bookId, String title) {
        Book book = new Book(bookId, title);
        books.put(bookId, book);
    }

    public void addUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
    }

    public void issueBook(String userId, String bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);

        if (user != null && book != null && book.isAvailable()) {
            Date currentDate = new Date();
            Date dueDate = new Date(currentDate.getTime() + 14 * 24 * 60 * 60 * 1000); // 14 days due date
            Transaction transaction = new Transaction("T" + ++transactionCount, user, book, currentDate, dueDate);
            transactions.add(transaction);
            book.setAvailable(false);
            System.out.println("Book '" + book.getTitle() + "' borrowed successfully. Due date: " + dueDate);
        } else {
            System.out.println("Book cannot be borrowed. Invalid user or book, or book is not available.");
        }
    }

    public void returnBook(String transactionId) {
        Transaction transaction = findTransaction(transactionId);

        if (transaction != null && transaction.getReturnDate() == null) {
            transaction.setReturnDate(new Date());
            Book book = transaction.getBook();
            book.setAvailable(true);

            double lateFee = transaction.calculateLateFee();
            if (lateFee > 0) {
                System.out.println("Book '" + book.getTitle() + "' returned successfully. Late fee: ₹" + lateFee);
            } else {
                System.out.println("Book '" + book.getTitle() + "' returned successfully.");
            }
        } else {
            System.out.println("Invalid transaction or book already returned.");
        }
    }

    public double calculateFee(String userId) {
        double totalFee = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getUser().getUserId().equals(userId) && transaction.getReturnDate() != null) {
                totalFee += transaction.calculateLateFee();
            }
        }
        return totalFee;
    }

    private Transaction findTransaction(String transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }
}
