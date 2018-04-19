package cbox.assignments;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.*;

import static cbox.assignments.Console.print;

class Helper {
    public static String padString(String source, int length, char padChar) {
        int num = length - source.length();
        char[] padding = new char[(num > 0) ? num : 0];
        Arrays.fill(padding, padChar);
        return source + String.valueOf(padding);
    }
}

class DataSource implements AutoCloseable {
    private static final DataSource dataSource = new DataSource();

    private final String DB_NAME = "QACinemas.db";
    private final String CONNECTION_STRING = "jdbc:sqlite:data\\databases\\" + DB_NAME;
    private final Connection conn;

    private enum Tables {
        CUSTOMERS,
        MOVIES,
        SALES,
        SHOWINGS
    }
    private enum CustIdx {
        ID(1),
        NAME(2),
        AGE(3),
        GENDER(4);

        int idx;
        CustIdx(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
    }
    private enum MoviesIdx {
        ID(1),
        NAME(2),
        DURATION(3),
        RELEASE(4);

        int idx;
        MoviesIdx(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
    }
    private enum SalesIdx {
        ID(1),
        CUSTOMER_ID(2),
        MOVIE_ID(3),
        DATE(4),
        COST(5),
        SCREEN(6);

        int idx;
        SalesIdx(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
    }

    public static class Customer {
        private int id;
        private String name;
        private int age;
        private String gender;
        @Override
        public String toString() {
            String name = Helper.padString(getName(), 20, '.');
            return String.format("\t[id]:%-3d [name]:%s [age]:%-3d [gender]:%-6s",
                                 getId(), name, getAge(), getGender());
        }
        public Customer(int id, String name, int age, String gender) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public String getGender() {
            return gender;
        }
    }
    public static class Movie {
        private int id;
        private String name;
        private int duration;
        private String release;
        @Override
        public String toString() {
            String name = Helper.padString(getName(), 20, '.');
            return String.format("\t[id]:%-3d [name]:%s [dur]:%-3d [rel]:%-10s",
                                 getId(), name, getDuration(), getRelease());
        }
        public Movie(int id, String name, int duration, String release) {
            this.id = id;
            this.name = name;
            this.duration = duration;
            this.release = release;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getDuration() {
            return duration;
        }
        public String getRelease() {
            return release;
        }
    }
    public static class Sale {
        private int id;
        private int custId;
        private int movieId;
        private String date;
        private double cost;
        private int screen;
        @Override
        public String toString() {
            return null;
        }
        public Sale(int id, int custId, int movieId, String date, double cost, int screen) {
            this.id = id;
            this.custId = custId;
            this.movieId = movieId;
            this.date = date;
            this.cost = cost;
            this.screen = screen;
        }
        public int getId() {
            return id;
        }
        public int getCustId() {
            return custId;
        }
        public int getMovieId() {
            return movieId;
        }
        public String getDate() {
            return date;
        }
        public double getCost() {
            return cost;
        }
        public int getScreen() {
            return screen;
        }
    }

    private static class QueryBuilder {
        private StringBuilder query = new StringBuilder();
        @Override
        public String toString() {
            return query.toString();
        }
        public QueryBuilder select(String select, String from) {
            query.append("SELECT ").append(select).append(" ");
            query.append("FROM ").append(from).append(" ");
            return this;
        }
        public QueryBuilder join(String join, String on) {
            query.append("INNER JOIN ").append(join).append(" ");
            query.append("ON ").append(on).append(" ");
            return this;
        }
    }

    // Singleton: Only need one instance of this class.
    private DataSource() {
        Connection tmpConn = null;
        while(tmpConn == null) {
            try {
                tmpConn = DriverManager.getConnection(CONNECTION_STRING);
            } catch(SQLException e) {
                print("Couldn't connect to " + DB_NAME + " database: " + e.getMessage());
                print("Trying again in three seconds...");
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ie) {}
            }
        }
        conn = tmpConn;
    }

    public static DataSource get() {
        return dataSource;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new LinkedList<>();
        String query = new QueryBuilder().select("*", Tables.CUSTOMERS.toString()).toString();
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(CustIdx.ID.getIdx());
                String name = res.getString(CustIdx.NAME.getIdx());
                int age = res.getInt(CustIdx.AGE.getIdx());
                String gender = res.getString(CustIdx.GENDER.getIdx());
                customers.add(new Customer(id, name, age, gender));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return customers;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new LinkedList<>();
        String query = new QueryBuilder().select("*", Tables.MOVIES.toString()).toString();
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(MoviesIdx.ID.getIdx());
                String name = res.getString(MoviesIdx.NAME.getIdx());
                int duration = res.getInt(MoviesIdx.DURATION.getIdx());
                String release = res.getString(MoviesIdx.RELEASE.getIdx());
                movies.add(new Movie(id, name, duration, release));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return movies;
    }

    public List<Sale> getSales() {
        return null;
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch(SQLException e) {
            print("Couldn't close " + DB_NAME + " database: " + e.getMessage());
        }
    }
}

class Logging implements AutoCloseable {
    private static final Logging inst = new Logging();

    private final Logger logger;
    private FileHandler fileHandler;

    private Logging() {
        logger = Logger.getLogger("QA Cinemas");

        // Stop logging to console.
        final Logger rootLogger = Logger.getLogger("");
        final Handler handler = rootLogger.getHandlers()[0];
        if (handler instanceof ConsoleHandler) {
            rootLogger.removeHandler(handler);
        }

        // Output everything to log.
        logger.setLevel(Level.ALL);

        // Determine date/time for file name.
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        final Date date = new Date();
        final String dt = df.format(date);
        try {
            // Create logging file.
            final String path = "data\\logs\\QA Cinemas (" + dt + ").txt";
            final File file = new File(path);
            file.createNewFile();

            // Complete setup.
            fileHandler = new FileHandler(path);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch(IOException e) {
            Console.print("Couldn't create file or FileHandler: " + e.getMessage());
        }
    }

    public static Logging get() {
        return inst;
    }

    public void print(String str) {
        logger.info(str);
    }

    @Override
    public void close() {
        fileHandler.close();
    }
}

// Manages interaction with program user through the console.
class Console {
    private static final String GREET_STR;
    private static final String BYE_STR;
    private static final String[] OPTIONS_STRS;
    private static final Scanner sc;

    static {
        GREET_STR = "Welcome to QA Cinema's ticket booking system.";
        BYE_STR = "Thank you for using QA Cinema's ticket booking system.";
        OPTIONS_STRS = new String[]{"Enter 1: For help.",
                                   "Enter 2: To add a customer booking.",
                                   "Enter 3: To view customers.",
                                   "Enter 4: To view movies currently being shown.",
                                   "Enter 5: To view showing times.",
                                   "Enter 6: To view ticket history.",
                                   "Enter 7: To exit."};
        sc = new Scanner(System.in);
    }

    public static void greet() {
        print(GREET_STR);
    }

    public static void help() {
        for(String option: OPTIONS_STRS) {
            print(option);
        }
    }

    public static void bye() {
        print(BYE_STR);
    }

    public static void print(String str) {
        print(str, true);
    }

    public static void print(String str, boolean newLine) {
        if (newLine) {
            str += '\n';
        }
        System.out.print(str);
        Logging.get().print(str);
    }

    // Returns -1 if invalid choice.
    public static int getChoice() {
        int choice = -1;
        print("Enter your choice: ", false);
        String input = sc.nextLine();
        print("You entered: " + input + ". ", false);
        if(input.matches("[0-9]+")) {
            choice = Integer.valueOf(input);
            choice = (choice >= 1 && choice <= OPTIONS_STRS.length) ? choice : -1;
        }
        if(choice < 1 || choice > OPTIONS_STRS.length) {
            print("This is invalid.", false);
        }
        System.out.print("\n\n");
        return choice;
    }
}

public class QACinemas {

    public void exec() {
        Console.greet();
        System.out.println();
        Console.help();
        System.out.println();

        loop();
    }

    private void loop() {
        int choice;
        boolean quit = false;
        while(!quit) {
            choice = -1;
            while(choice == -1) {
                choice = Console.getChoice();
            }
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
                    viewShowingTimes();
                    break;
                case 6:
                    viewSales();
                    break;
                case 7:
                    exit();
                    quit = true;
                    break;
            }
            System.out.println();
        }
    }

    private void addCustomer() {

    }

    private void viewCustomers() {
        print("The database contains the following customers: ");

        List<DataSource.Customer> customers = DataSource.get().getCustomers();
        for(DataSource.Customer c: customers) {
            print(c.toString());
        }
    }

    private void viewMovies() {
        print("The database contains the following movies: ");

        List<DataSource.Movie> movies = DataSource.get().getMovies();
        for(DataSource.Movie m: movies) {
            print(m.toString());
        }
    }

    private void viewShowingTimes() {

    }

    private void viewSales() {

    }

    private void exit() {
        Console.bye();
        Logging.get().close();
    }
}
