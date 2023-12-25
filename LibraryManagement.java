public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books and users to the library
        library.addBook("B001", "GITA");
        library.addBook("B002", "Brahmasashtra");
        library.addUser("U001", "Animesh Singh");
        library.addUser("U002", "Aayush Singh");

        // Issue books to users
        library.issueBook("U001", "B001");
        library.issueBook("U002", "B002");

        // Return books
        library.returnBook("T1"); // Specify the transaction ID for the book issued to U001
        library.returnBook("T2"); // Specify the transaction ID for the book issued to U002

        // Calculate fees
        double feeAnimesh = library.calculateFee("U001");
        double feeAayush = library.calculateFee("U002");

        System.out.println("Fee for Animesh: ₹" + feeAnimesh);
        System.out.println("Fee for Aayush: ₹" + feeAayush);
    }
}
