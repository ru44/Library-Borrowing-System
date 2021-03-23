/*
Telegram: @DemonMissedMatch
 */
import java.io.*;
import java.util.*;

public class A2P2 {

    static String uniqueCode = "BR";
    static int uniqueNumber = 1000;

    public static void main(String[] args) throws Exception {
        File input = new File("input.txt");
        File output = new File("output.txt");
        if (!input.exists()) {
            System.out.println("File does not exist!");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        PrintWriter out = new PrintWriter(output);
        Book[] books = new Book[in.nextInt()];
        Member[] members = new Member[in.nextInt()];
        int numBookAllowed = in.nextInt();
        int maxBorrowing = numBookAllowed * members.length;
        Borrowing[] borrowing = new Borrowing[maxBorrowing];
        out.println("###########################################\r\n"
                + "***** Welcome to Library Borrowing System *****\r\n"
                + "###########################################");
        String command = in.next();
        do {
            if (command.equalsIgnoreCase("Add_Book")) {
                addBook(in, out, books);
            } else if (command.equalsIgnoreCase("Add_Member")) {
                addMember(in, out, members);
            } else if (command.equalsIgnoreCase("Borrow")) {
                borrowingBook(out, in, books, members, borrowing, numBookAllowed);

            } else if (command.equalsIgnoreCase("Search_Borrowing")) {

                String browNo = in.next();

                borrowingdetails(out, borrowing, browNo);

            } else if (command.equalsIgnoreCase("Book_Status")) {
                String booksNo2Search = in.next();
                bookStatus(out, books, booksNo2Search, borrowing);

            }
            command = in.next();
        } while (!command.equalsIgnoreCase("Quit"));
        out.println("Thank you for using the books booking System, Good Bye!\r\n"
                + "\n#####################################");
        out.flush();
        out.close();
        in.close();

    }

    public static void addBook(Scanner in, PrintWriter out, Book[] books) {
        if (Book.getCurrBookIndex() < books.length) {
            String bookNo = in.next();
            String title = in.next();
            String author = in.next();
            int numCopies = in.nextInt();
            int remCopies = numCopies;
            books[Book.getCurrBookIndex()] = new Book(bookNo, title, author, numCopies, remCopies);
            out.println("Book " + books[Book.getCurrBookIndex() - 1].getBookNo()
                    + " Successfully Added "
                    + "\r\n###########################################");
        } else {
            out.println("Book " + in.next() + " Was not Added"
                    + "\r\n You exceed the maximum number of Books"
                    + "\r\n###########################################");
        }

    }

    public static void addMember(Scanner in, PrintWriter out, Member[] members) {
        if (Member.getCurrMemberIndex() < members.length) {
            String name = in.next();
            int id = in.nextInt();
            String type = in.next();
            members[Member.getCurrMemberIndex()] = new Member(name, id, type);
            out.println("Member " + members[Member.getCurrMemberIndex() - 1].getName()
                    + " Successfully Added "
                    + "\r\n###########################################");
        } else {
            out.println("Member " + in.next() + " Was not Added"
                    + "\r\n You exceed the maximum number of Members"
                    + "\r\n###########################################");
        }
    }

    public static void borrowingBook(PrintWriter out, Scanner in, Book[] books, Member[] members, Borrowing[] borrowing, int numBookAllowed) {
        if (Borrowing.getCurrBorrowing() < borrowing.length) {
            int memberId = in.nextInt();
            int reqNumOfBorrowing = in.nextInt();
            String[] bookNos = in.nextLine().trim().split(" ");
            int mIndex = -1;
            for (int i = 0; i < Member.getCurrMemberIndex(); i++) {

                if (members[i].getId() == memberId) {
                    mIndex = i;
                    break;
                }

            }
            if (mIndex == -1) {
                out.println("Member " + memberId + " was not regeistered in the System"
                        + "\r\n###########################################");
                return;
            }

            int alreadyNumBorrowing = 0;
            for (int i = 0; i < Borrowing.getCurrBorrowing(); i++) {
                Member tempMember = borrowing[i].getMember();
                if (tempMember.getId() == memberId) {
                    alreadyNumBorrowing += borrowing[i].getNumBooksBorrowing();
                }
            }

            if (alreadyNumBorrowing >= numBookAllowed) { // already borrowed all
                out.println("---Already borrowed all Allowed books"
                        + "\r\n###########################################");
                return;
            } else if (alreadyNumBorrowing + reqNumOfBorrowing > numBookAllowed) {
                out.println("---The required plus the previous borrowed books is "
                        + "excedded the maximium of Allowed"
                        + "\r\n###########################################");
                return;
            }

            int[] bTIndex = new int[reqNumOfBorrowing];
            int numBooksWillBorrowed = 0;

            for (int i = 0; i < bookNos.length; i++) {
                bTIndex[i] = -1;
                for (int j = 0; j < Book.getCurrBookIndex(); j++) {
                    if (bookNos[i].equalsIgnoreCase(books[j].getBookNo())) {
                        bTIndex[i] = j;
                        numBooksWillBorrowed++;
                        break;
                    }
                }
            }

            String brNo = uniqueCode + uniqueNumber;
            uniqueNumber++;
            Borrowing tempBorrowing = new Borrowing(brNo, new Date(), members[mIndex], numBooksWillBorrowed);
            int indexCurrBookToAdd = 0;
            int numBorrowed = 0;
            for (int i = 0; i < bTIndex.length; i++) {
                if (bTIndex[i] == -1) {
                    out.println(bookNos[i] + " was not a registered book");
                } else if (books[bTIndex[i]].getRemCopies() < 1) {
                    out.println("Number of remaining copies for " + bookNos[i] + " is not enough");
                } else {
                    tempBorrowing.setBooks(books[bTIndex[i]], indexCurrBookToAdd++);
                    books[bTIndex[i]].setRemCopies(books[bTIndex[i]].getRemCopies() - 1);
                    numBorrowed++;
                }
            }
            if (numBorrowed > 0) {
                borrowing[Borrowing.getCurrBorrowing()] = tempBorrowing;
                Borrowing.incCurrBorrowing();
                out.println("Member with Id   " + memberId + " Successfully Borrowed  " + numBorrowed + " Books"
                        + "\r\n###########################################");
            } else {
                out.println("Member with Id   " + memberId + " could not borrow  any Books"
                        + "\r\n###########################################");

            }

        } else {
            System.out.println("You exceeded the number of borrowing requests");
        }

    }

    public static void borrowingdetails(PrintWriter out, Borrowing[] borrowing, String browNo2Search) {
        int borroNo = -1;
        for (int i = 0; i < Borrowing.getCurrBorrowing(); i++) {
            if (borrowing[i].getBrowNo().equalsIgnoreCase(browNo2Search)) {
                borroNo = i;
            }
        }
        if (borroNo != -1) {

            out.println(borrowing[borroNo].toString());
            listBooksDetails(out, borrowing[borroNo].getAllBooks());
            out.println("\r\n###########################################");
        } else {
            out.println("No Record found with the BR " + browNo2Search + "\r\n"
                    + "\r\n#####################################");
        }

    }

    public static void bookStatus(PrintWriter out, Book[] books, String book2Search, Borrowing[] borrowing) {
        int bIndex = -1;
        for (int i = 0; i < Book.getCurrBookIndex(); i++) {
            if (books[i].getBookNo().equalsIgnoreCase(book2Search)) {
                bIndex = i;
            }
        }
        if (bIndex != -1) {
            out.println(books[bIndex].toString());
        } else {
            out.println("No Record found with the Book Code " + book2Search
                    + "\r\n\r\n#####################################");
        }

    }

    public static void listBooksDetails(PrintWriter out, Book[] books) {
        for (int i = 0; i < books.length; i++) {
            out.println("Title: " + books[i].getTitle() + "  Author:  " + books[i].getAuthor()
                    + "# of Copies: " + books[i].getNumCopies() + " # of Remaining Copies: " + books[i].getRemCopies());
        }
    }
}
