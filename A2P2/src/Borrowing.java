
import java.util.Date;

public class Borrowing {

    private String browNo;
    private Date borrowingDate;
    private Book[] books;
    private Member member;
    int numBooksBorrowing;
    static int currBorrowing = 0;

    public Borrowing() {
    }

    public Borrowing(String browNo, Date borrowingDate, Member member, int numBooksBorrowing) {
        this.browNo = browNo;
        this.borrowingDate = borrowingDate;
        this.books = new Book[numBooksBorrowing];
        this.member = member;
        this.numBooksBorrowing = numBooksBorrowing;
    }

    public int getNumBooksBorrowing() {
        return numBooksBorrowing;
    }

    public void setNumBooksBorrowing(int numBooksBorrowing) {
        this.numBooksBorrowing = numBooksBorrowing;
    }

    public void setBrowNo(String browNo) {
        this.browNo = browNo;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public void setBooks(Book book, int index) {
        this.books[index] = book;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public static void incCurrBorrowing() {
        currBorrowing++;
    }

    public String getBrowNo() {
        return browNo;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public Book[] getAllBooks() {
        return books;
    }

    public Book getBook(int index) {
        return books[index];
    }

    public Member getMember() {
        return member;
    }

    public static int getCurrBorrowing() {
        return currBorrowing;
    }

    @Override
    public String toString() {
        return "Borrowing Information for  BR#  " + getBrowNo() + "\r\n Member Name: "
                + getMember().getName() + "\r\n Borrowing Date: "
                + getBorrowingDate() + "\r\n Number of Borrowing Kooks: "
                + getNumBooksBorrowing() + "\r\nBooks List: ";
    }

}
