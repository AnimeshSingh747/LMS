import java.util.Date;

public class Transaction {
    private String transactionId;
    private User user;
    private Book book;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private double lateFeeRatePerDay = 0.5; 

    public Transaction(String transactionId, User user, Book book, Date issueDate, Date dueDate) {
        this.transactionId = transactionId;
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double calculateLateFee() {
        if (returnDate.after(dueDate)) {
            long daysLate = (returnDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000);
            return daysLate * lateFeeRatePerDay;
        }
        return 0;
    }
}
