import java.util.Scanner;
import java.util.concurrent.locks.*;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public void bookTicket(String customerName, int seats) {
        lock.lock();
        try {
            if (availableSeats >= seats) {
                System.out.println(customerName + " successfully booked " + seats + " seat(s). Remaining: " + (availableSeats - seats));
                availableSeats -= seats;
            } else {
                System.out.println("Sorry, " + customerName + "! Not enough seats available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Customer extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerName;
    private int seats;

    public Customer(TicketBookingSystem system, String name, int seats, int priority) {
        this.bookingSystem = system;
        this.customerName = name;
        this.seats = seats;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(customerName, seats);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total number of available seats: ");
        int totalSeats = scanner.nextInt();
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        System.out.print("Enter number of customers: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine();

        Customer[] customers = new Customer[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter number of seats to book: ");
            int seats = scanner.nextInt();
            System.out.print("Enter priority (1 for VIP, 2 for Regular): ");
            int priority = scanner.nextInt();
            scanner.nextLine();

            int threadPriority = (priority == 1) ? Thread.MAX_PRIORITY : Thread.NORM_PRIORITY;
            customers[i] = new Customer(system, name, seats, threadPriority);
        }

        for (Customer customer : customers) {
            customer.start();
        }

        scanner.close();
    }
}
