package cbox.assignments.qacinemas;

import java.util.List;

import static cbox.assignments.qacinemas.Console.print;
import static cbox.assignments.qacinemas.Helper.currDate;
import static cbox.assignments.qacinemas.Helper.currDayOfWeek;

public class CustomerManager {
    private int customerCount;
    private DataSource.Customer customer;
    private DataSource.Sale sale;

    public int getCustomerCount() {
        return customerCount;
    }

    public DataSource.Customer getCustomer() {
        return customer;
    }

    public DataSource.Sale getSale() {
        return sale;
    }

    private DataSource.Customer getCustomerDetails() {
        String name = Console.getString("Enter customer name: ");
        int age = Console.getInt("Enter customer age: ", 130);
        char gender = Console.getString("Enter customer gender (m/f): ").toCharArray()[0];
        return new DataSource.Customer(0, name, age, gender);
    }

    private String getMovieChoice() {
        print("The following movies are available: ");
        List<DataSource.Movie> movies = DataSource.get().getMovies();
        int count = 1;
        for(DataSource.Movie movie: movies) {
            print(count + ") " + movie.getName() + " ", false);
            count++;
        }
        print();
        int choice = Console.getInt("Enter the index of the movie: ", movies.size());
        return movies.get(choice-1).getName();
    }

    private DataSource.Show getShowTimeChoice(String movie) {
        print("The following show times are available: ");

        List<DataSource.Show> shows = DataSource.get().getShows();
        int count = 1;
        for(DataSource.Show show: shows) {
            if(show.getMovie().equals(movie)) {
                print(count + ") [Time]:" + show.getTime() + " [Screen]:" +
                                show.getScreen() + " ", false);
                count++;
            }
        }
        print();
        int choice = Console.getInt("Enter the index for the show time: ", shows.size());
        return shows.get(choice-1);
    }

    private double getCost(int customerCount) {
        double cost = 0;
        for(int i = 1; i <= customerCount; i++) {
            String ticketStr = null;
            //TODO: Use index based selection. Less typing required.
            print("The following ticket types are available: ");
            print(QACinemas.TicketType.allToString());
            while(!QACinemas.TicketType.contains(ticketStr)) {
                ticketStr = Console.getString("What ticket type does customer "
                        + i + "/" + customerCount + " require? ")
                        .toUpperCase();
            }
            cost += QACinemas.TicketType.valueOf(ticketStr).getCost();
        }
        if (currDayOfWeek().equals("Wed")) {
            cost -= 2*customerCount;
        }
        return cost;
    }

    public void addCustomer() {
        customerCount = Console.getInt("Enter number of customers: ", 10);
        print();

        customer = Console.getBoolean("Create membership for customer (y/n)? ") ?
                getCustomerDetails() : null;
        print();

        String movieName = getMovieChoice();
        print();

        DataSource.Show show = getShowTimeChoice(movieName);
        print();
        String time = show.getTime();
        int screen = show.getScreen();

        String date = currDate("dd/mm/yyyy");

        double cost = getCost(customerCount);

        sale = new DataSource.Sale(0, customer.getName(), movieName, date, time, cost, screen);
    }
}
