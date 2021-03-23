
public class Book {

    private String bookNo;
    private String title;
    private String author;
    private int numCopies;
    private int remCopies;
    static int currBookIndex;

    public Book() {
    }

    public Book(String bookNo, String title, String author, int numCopies, int remCopies) {
        this.bookNo = bookNo;
        this.title = title;
        this.author = author;
        this.numCopies = numCopies;
        this.remCopies = remCopies;
        currBookIndex++;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public void setRemCopies(int remCopies) {
        this.remCopies = remCopies;
    }

    public String getBookNo() {
        return bookNo;
    }

    public String getTitle() {
        return title;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public int getRemCopies() {
        return remCopies;
    }

    public static int getCurrBookIndex() {
        return currBookIndex;
    }

    @Override
    public String toString() {
        return "Information for Book " + getBookNo()
                + "\r\nTitle:" + getTitle()
                + "\r\n Author:" + getAuthor()
                + "\r\n Number of Copies:" + getNumCopies()
                + "\r\n Number of Remainig Copies:" + getRemCopies() +
                "\r\n\r\n\r\n#####################################";
    }

}
