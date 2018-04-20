package cbox.assignments.qac;

import java.util.*;

import static cbox.assignments.qac.Console.print;
import static cbox.assignments.qac.Helper.currDate;
import static cbox.assignments.qac.Helper.currDayOfWeek;

public class QACinemas {
    public enum TicketType {
        STANDARD(8),
        OAP(6),
        STUDENT(6),
        CHILD(4);

        private double cost;
        TicketType(double cost) {
            this.cost = cost;
        }
        public double getCost() {
            return cost;
        }
        public static boolean contains(String str) {
            for(TicketType type: values()) {
                if(type.toString().equals(str)) {
                    return true;
                }
            }
            return false;
        }
        public static String allToString() {
            StringBuilder sb = new StringBuilder();
            for(TicketType tt: TicketType.values()) {
                sb.append(tt.toString()).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }
    }

    public void exec() {
        print("Welcome to QA Cinema's ticket booking system.");
        print();
        Console.help();
        print();

        loop();
    }

    private void loop() {
        boolean quit = false;
        while(!quit) {
            int choice = Console.getInt("Enter an option: ", Console.getOptionsLength());
            print();
            switch(choice) {
                case 1:
                    Console.help();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    viewCustomers();
                    break;
                case 4:
                    viewMovies();
                    break;
                case 5:
                    viewShows();
                    break;
                case 6:
                    viewSales();
                    break;
                case 7:
                    exit();
                    quit = true;
                    break;
            }
            print();
        }
    }

    private boolean isValidSale(int custCount, DataSource.Sale sale) {
        print("The sales details are as follows: ");
        print("[customers]:" + custCount + " " + sale.toString());
        print();
        return Console.getBoolean("Is this correct (y/n)? ");
    }

    private boolean processTransaction(DataSource.Customer cust, DataSource.Sale sale) {
        DataSource ds = DataSource.get();
        boolean isProcessed = true;
        if(cust != null) {
            isProcessed = ds.addCustomer(cust);
        }
        isProcessed = isProcessed && ds.addSale(sale);
        return isProcessed;
    }

    private void addCustomer() {
        CustomerManager customerManager = new CustomerManager();
        customerManager.addCustomer();

        int customerCount = customerManager.getCustomerCount();
        DataSource.Sale sale = customerManager.getSale();
        DataSource.Customer customer = customerManager.getCustomer();

        if(isValidSale(customerCount, sale) && processTransaction(customer, sale)) {
            print("Sale is complete, printing tickets...");
        } else {
            print("Error: Something went wrong with the sale. Please try again.");
        }
    }

    private void viewCustomers() {
        print("The following customers have previously bought tickets: ");

        List<DataSource.Customer> customers = DataSource.get().getCustomers();
        for(DataSource.Customer c: customers) {
            print(c.toString());
        }
    }

    private void viewMovies() {
        print("The following movies are being shown: ");

        List<DataSource.Movie> movies = DataSource.get().getMovies();
        for(DataSource.Movie m: movies) {
            print(m.toString());
        }
    }

    private void viewShows() {
        print("The show times are as follows: ");

        List<DataSource.Show> shows = DataSource.get().getShows();
        for(DataSource.Show s: shows) {
            print(s.toString());
        }
    }

    private void viewSales() {
        print("The sales history is as follows: ");

        List<DataSource.Sale> sales = DataSource.get().getSales();
        for(DataSource.Sale s: sales) {
            print(s.toString());
        }
    }

    private void exit() {
        print("Thank you for using QA Cinema's ticket booking system.");
    }
}
