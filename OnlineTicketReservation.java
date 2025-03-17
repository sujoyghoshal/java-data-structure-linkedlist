class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket last;
    private int ticketCount;

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (last == null) {
            last = newTicket;
            last.next = last;
        } else {
            newTicket.next = last.next;
            last.next = newTicket;
            last = newTicket;
        }
        ticketCount++;
    }

    public void removeTicket(int ticketId) {
        if (last == null) return;
        Ticket current = last.next, prev = last;
        do {
            if (current.ticketId == ticketId) {
                if (current == last && current.next == last) {
                    last = null;
                } else {
                    prev.next = current.next;
                    if (current == last) last = prev;
                }
                ticketCount--;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != last.next);
    }

    public void displayTickets() {
        if (last == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = last.next;
        do {
            System.out.println(temp.ticketId + " | " + temp.customerName + " | " + temp.movieName + " | " + temp.seatNumber + " | " + temp.bookingTime);
            temp = temp.next;
        } while (temp != last.next);
    }

    public void searchByCustomer(String customerName) {
        if (last == null) return;
        Ticket temp = last.next;
        do {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket Found: " + temp.movieName + " | " + temp.seatNumber);
            }
            temp = temp.next;
        } while (temp != last.next);
    }

    public void searchByMovie(String movieName) {
        if (last == null) return;
        Ticket temp = last.next;
        do {
            if (temp.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket Found: " + temp.customerName + " | " + temp.seatNumber);
            }
            temp = temp.next;
        } while (temp != last.next);
    }

    public int getTotalTickets() {
        return ticketCount;
    }
}

public class OnlineTicketReservation {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        
        system.addTicket(1, "Alice", "Inception", "A10", "12:00 PM");
        system.addTicket(2, "Bob", "Interstellar", "B5", "3:00 PM");
        system.addTicket(3, "Charlie", "Dunkirk", "C7", "6:00 PM");
        
        System.out.println("All Tickets:");
        system.displayTickets();
        
        System.out.println("\nTotal Tickets: " + system.getTotalTickets());
    }
}
