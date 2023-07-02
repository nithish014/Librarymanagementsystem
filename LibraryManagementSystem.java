package library;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ")";
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book);
                }
            }
        }
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found book: " + book);
                return book;
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("You have borrowed '" + book.getTitle() + "'");
        } else {
            System.out.println("'" + book.getTitle() + "' is already borrowed by someone else.");
        }
    }

    public void returnBook(Book book) {
        if (!book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("You have returned '" + book.getTitle() + "'");
        } else {
            System.out.println("'" + book.getTitle() + "' is already available in the library.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Book1", "Author1", "ISBN001");
        Book book2 = new Book("Book2", "Author2", "ISBN002");
        Book book3 = new Book("Book3", "Author3", "ISBN003");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display available books");
            System.out.println("2. Search book");
            System.out.println("3. Borrow book");
            System.out.println("4. Return book");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    library.searchBook(title);
                    break;
                case 3:
                    System.out.print("Enter the title of the book to borrow: ");
                    scanner.nextLine();
                    String borrowTitle = scanner.nextLine();
                    Book borrowBook = library.searchBook(borrowTitle);
                    if (borrowBook != null) {
                        library.borrowBook(borrowBook);
                    }
                    break;
                case 4:
                    System.out.print("Enter the title of the book to return: ");
                    scanner.nextLine();
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.searchBook(returnTitle);
                    if (returnBook != null) {
                        library.returnBook(returnBook);
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}