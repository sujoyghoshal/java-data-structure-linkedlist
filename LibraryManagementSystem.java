class Book {
    String title, author, genre;
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

class Library {
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

    public void removeBook(int bookId) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                bookCount--;
                return;
            }
            temp = temp.next;
        }
    }

    public void searchBook(String query) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(query) || temp.author.equalsIgnoreCase(query)) {
                System.out.println("Found: " + temp.title + " by " + temp.author);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.prev;
        }
    }

    public int getTotalBooks() {
        return bookCount;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBookAtEnd(1, "1984", "George Orwell", "Dystopian", true);
        library.addBookAtEnd(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", true);
        library.addBookAtEnd(3, "Moby Dick", "Herman Melville", "Adventure", false);
        
        System.out.println("Library Books (Forward Order):");
        library.displayForward();
        
        System.out.println("\nLibrary Books (Reverse Order):");
        library.displayReverse();
    }
}
