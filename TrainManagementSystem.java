
import java.util.*;

class Train {
    int trainNo;
    String trainName;
    String source;
    String destination;
    int seats;
    double fare;

    Train(int trainNo, String trainName, String source,
          String destination, int seats, double fare) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    void display() {
        System.out.println("Train Number: " + trainNo);
        System.out.println("Train Name: " + trainName);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Available Seats: " + seats);
        System.out.println("Fare: Rs. " + fare);
        System.out.println("--------------------------------");
    }
}

class Passenger {
    int passengerId;
    String name;
    int age;
    int trainNo;
    int seatsBooked;
    double totalFare;

    Passenger(int passengerId, String name, int age,
              int trainNo, int seatsBooked, double totalFare) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.trainNo = trainNo;
        this.seatsBooked = seatsBooked;
        this.totalFare = totalFare;
    }

    void display() {
        System.out.println("Passenger ID: " + passengerId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Train Number: " + trainNo);
        System.out.println("Seats Booked: " + seatsBooked);
        System.out.println("Total Fare: Rs. " + totalFare);
        System.out.println("--------------------------------");
    }
}

public class TrainManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<Passenger> passengers = new ArrayList<>();

    static int passengerCounter = 1001;

    static {
        trains.add(new Train(101, "Rajdhani Express",
                "Delhi", "Mumbai", 100, 1500));

        trains.add(new Train(102, "Shatabdi Express",
                "Bhopal", "Delhi", 80, 1200));

        trains.add(new Train(103, "Duronto Express",
                "Mumbai", "Kolkata", 90, 1800));
    }

    static Train findTrain(int trainNo) {
        for (Train t : trains) {
            if (t.trainNo == trainNo) {
                return t;
            }
        }
        return null;
    }

    static Passenger findPassenger(int id) {
        for (Passenger p : passengers) {
            if (p.passengerId == id) {
                return p;
            }
        }
        return null;
    }

    // Add New Train
    static void addTrain() {
        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();
        sc.nextLine();

        if (findTrain(no) != null) {
            System.out.println("Train Number Already Exists!");
            return;
        }

        System.out.print("Enter Train Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Source: ");
        String source = sc.nextLine();

        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();

        System.out.print("Enter Available Seats: ");
        int seats = sc.nextInt();

        System.out.print("Enter Ticket Fare: ");
        double fare = sc.nextDouble();

        if (seats <= 0 || fare < 0) {
            System.out.println("Invalid seat count or fare!");
            return;
        }

        trains.add(new Train(no, name, source,
                destination, seats, fare));

        System.out.println("Train Added Successfully!");
    }

    // View All Trains
    static void viewTrains() {
        if (trains.isEmpty()) {
            System.out.println("No Trains Available!");
            return;
        }

        System.out.println("\n===== AVAILABLE TRAINS =====");

        for (Train t : trains) {
            t.display();
        }
    }

    // Search Train
    static void searchTrain() {
        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();

        Train t = findTrain(no);

        if (t != null) {
            System.out.println("\nTrain Found!");
            t.display();
        } else {
            System.out.println("Train Not Found!");
        }
    }

    // Book Ticket
    static void bookTicket() {
        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();

        Train t = findTrain(no);

        if (t == null) {
            System.out.println("Train Not Found!");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Passenger Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Number of Seats: ");
        int seats = sc.nextInt();

        if (age <= 0 || seats <= 0) {
            System.out.println("Invalid passenger details!");
            return;
        }

        if (t.seats < seats) {
            System.out.println("Not Enough Seats Available!");
            return;
        }

        double totalFare = t.fare * seats;

        t.seats -= seats;

        Passenger p = new Passenger(
                passengerCounter++, name, age,
                no, seats, totalFare);

        passengers.add(p);

        System.out.println("\n===== TICKET BOOKED SUCCESSFULLY =====");
        System.out.println("Passenger ID: " + p.passengerId);
        System.out.println("Passenger Name: " + p.name);
        System.out.println("Train Name: " + t.trainName);
        System.out.println("Train Number: " + t.trainNo);
        System.out.println("Seats Booked: " + seats);
        System.out.println("Total Fare: Rs. " + totalFare);
        System.out.println("======================================");
    }

    // View Passengers
    static void viewPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("No Passenger Records Found!");
            return;
        }

        System.out.println("\n===== PASSENGER DETAILS =====");

        for (Passenger p : passengers) {
            p.display();
        }
    }

    // Cancel Ticket
    static void cancelTicket() {
        System.out.print("Enter Passenger ID: ");
        int id = sc.nextInt();

        Passenger p = findPassenger(id);

        if (p == null) {
            System.out.println("Passenger Record Not Found!");
            return;
        }

        Train t = findTrain(p.trainNo);

        if (t != null) {
            t.seats += p.seatsBooked;
        }

        passengers.remove(p);

        System.out.println("Ticket Cancelled Successfully!");
        System.out.println("Cancelled Passenger: " + p.name);
        System.out.println("Refund Amount: Rs. " + p.totalFare);
    }

    // Main Menu
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("    TRAIN MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add New Train");
            System.out.println("2. View All Trains");
            System.out.println("3. Search Train");
            System.out.println("4. Book Train Ticket");
            System.out.println("5. View Passengers");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addTrain();
                    break;

                case 2:
                    viewTrains();
                    break;

                case 3:
                    searchTrain();
                    break;

                case 4:
                    bookTicket();
                    break;

                case 5:
                    viewPassengers();
                    break;

                case 6:
                    cancelTicket();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    System.out.println("Train Management System Closed.");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
