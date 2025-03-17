class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieLinkedList {
    private Movie head;
    private Movie tail;

    MovieLinkedList() {
        this.head = null;
        this.tail = null;
    }

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            newMovie.prev = tail;
            tail.next = newMovie;
            tail = newMovie;
        }
    }

    void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid position");
            return;
        }
        newMovie.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = newMovie;
        }
        temp.next = newMovie;
        newMovie.prev = temp;
    }

    void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.title.equals(title)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return;
        }
        Movie temp = head;
        while (temp != null && !temp.title.equals(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie not found");
            return;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
    }

    Movie searchByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equals(director)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    Movie searchByRating(double rating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    void updateRatingByTitle(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found");
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieLinkedList list = new MovieLinkedList();

        list.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        list.addAtBeginning("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        list.addAtPosition(2, "Interstellar", "Christopher Nolan", 2014, 8.6);

        System.out.println("Movies in Forward Order:");
        list.displayForward();

        System.out.println("\nMovies in Reverse Order:");
        list.displayReverse();

        list.removeByTitle("Inception");
        System.out.println("\nAfter Removing 'Inception':");
        list.displayForward();

        Movie movieByDirector = list.searchByDirector("Christopher Nolan");
        if (movieByDirector != null) {
            System.out.println("\nFound by Director: " + movieByDirector.title);
        } else {
            System.out.println("\nMovie not found by Director");
        }


        Movie movieByRating = list.searchByRating(9.0);
        if (movieByRating != null) {
            System.out.println("\nFound by Rating: " + movieByRating.title);
        } else {
            System.out.println("\nMovie not found by Rating");
        }


        list.updateRatingByTitle("Interstellar", 8.9);
        System.out.println("\nAfter Updating Rating for 'Interstellar':");
        list.displayForward();
    }
}