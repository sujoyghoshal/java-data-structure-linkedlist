class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(int bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.next = this.prev = null;
    }
}

class LibraryManagementSystem {
    private Book head, tail;
    private int bookCount;

    public void addBookAtEnd(int bookId, String title, String author, String genre, boolean isAvailable) {
        Book newBook = new Book(bookId, title, author, genre, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        bookCount++;
    }

    public void addBookAtBeginning(int bookId, String title, String author, String genre, boolean isAvailable) {
        Book newBook = new Book(bookId, title, author, genre, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        bookCount++;
    }

    public void removeBook(int bookId) {
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) return;

        if (temp == head) head = head.next;
        if (temp == tail) tail = tail.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
        bookCount--;
    }

    public void searchBookByTitle(String title) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Book Found: " + temp.title + " by " + temp.author);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void searchBookByAuthor(String author) {
        Book temp = head;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                System.out.println("Book by " + author + ": " + temp.title);
            }
            temp = temp.next;
        }
    }

    public void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                System.out.println("Updated Availability: " + temp.title + " is now " + (status ? "Available" : "Unavailable"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayBooksForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Unavailable"));
            temp = temp.next;
        }
    }

    public void displayBooksReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Unavailable"));
            temp = temp.prev;
        }
    }

    public int getTotalBooks() {
        return bookCount;
    }
}

public class LibraryManagement{
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        library.addBookAtEnd(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", true);
        library.addBookAtEnd(2, "To Kill a Mockingbird", "Harper Lee", "Classic", true);
        library.addBookAtEnd(3, "1984", "George Orwell", "Dystopian", false);
        
        System.out.println("Library Books (Forward):");
        library.displayBooksForward();
        
        System.out.println("\nLibrary Books (Reverse):");
        library.displayBooksReverse();
        
        System.out.println("\nTotal Books: " + library.getTotalBooks());
    }
}
