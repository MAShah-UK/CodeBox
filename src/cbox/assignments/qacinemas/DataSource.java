package cbox.assignments.qacinemas;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static cbox.assignments.qacinemas.Console.print;
import static cbox.assignments.qacinemas.SQLBuilder.sqlStr;

// Manages interaction with database.
class DataSource implements AutoCloseable {
    private static final DataSource dataSource = new DataSource();

    private final String DB_NAME = "QACinemas.db";
    private final String CONNECTION_STRING = "jdbc:sqlite:data\\databases\\" + DB_NAME;
    private final Connection conn;

    private enum Tables {
        CUSTOMERS,
        MOVIES,
        SALES,
        SHOWS
    }
    private enum CustTable {
        _ID(1),
        NAME(2),
        AGE(3),
        GENDER(4);

        private int idx;
        CustTable(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
        public String fullyQual() {
            return "CUSTOMERS" + '.' + toString();
        }
    }
    private enum MoviesTable {
        _ID(1),
        NAME(2),
        DURATION(3),
        RELEASE(4);

        private int idx;
        MoviesTable(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
        public String fullyQual() {
            return "MOVIES" + '.' + toString();
        }
    }
    private enum SalesTable {
        _ID(1),
        CUSTOMER_ID(2),
        MOVIE_ID(3),
        DATE(4),
        TIME(5),
        COST(6),
        SCREEN(7);

        private int idx;
        SalesTable(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
        public String fullyQual() {
            return "SALES" + '.' + toString();
        }
    }
    private enum ShowsTable {
        _ID(1),
        MOVIE_ID(2),
        TIME(3),
        SCREEN(4);

        private int idx;
        ShowsTable(int idx) {
            this.idx = idx;
        }
        public int getIdx() {
            return idx;
        }
        public String fullyQual() {
            return "SHOWS" + '.' + toString();
        }
    }

    public static class Customer {
        private int id;
        private String name;
        private int age;
        private char gender;
        @Override
        public String toString() {
            String name = Helper.padStr(getName(), 25, '.');
            return String.format("\t[id]:%-3d [name]:%s [age]:%-3d [gender]:%-6s",
                    getId(), name, getAge(), getGender());
        }
        public Customer(int id, String name, int age, char gender) {
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
        public char getGender() {
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
            String name = Helper.padStr(getName(), 20, '.');
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
        private String custName;
        private String movieName;
        private String date;
        private String time;
        private double cost;
        private int screen;
        @Override
        public String toString() {
            String name =  Helper.padStr(getCustName(), 25, '.');
            String movie = Helper.padStr(getMovieName(), 25, '.');
            return String.format("\t[id]:%-3d [name]:%s [movie]:%s [date]:%-10s [time]:%-5s [cost]:£%-5.2f [screen]:%d",
                    getId(), name, movie, getDate(), getTime(), getCost(), getScreen());
        }
        public Sale(int id, String custName, String movieName, String date, String time, double cost, int screen) {
            this.id = id;
            this.custName = custName;
            this.movieName = movieName;
            this.date = date;
            this.time = time;
            this.cost = cost;
            this.screen = screen;
        }
        public int getId() {
            return id;
        }
        public String getCustName() {
            return custName;
        }
        public String getMovieName() {
            return movieName;
        }
        public String getDate() {
            return date;
        }
        public String getTime() {
            return time;
        }
        public double getCost() {
            return cost;
        }
        public int getScreen() {
            return screen;
        }
    }
    public static class Show {
        private int id;
        private String movie;
        private String time;
        private int screen;
        @Override
        public String toString() {
            String name = Helper.padStr(getMovie(), 25, '.');
            return String.format("\t[id]:%-3d [name]:%s [dur]:%-5s [screen]:%d",
                    getId(), name, getTime(), getScreen());
        }
        public Show(int id, String movie, String time, int screen) {
            this.id = id;
            this.movie = movie;
            this.time = time;
            this.screen = screen;
        }
        public int getId() {
            return id;
        }
        public String getMovie() {
            return movie;
        }
        public String getTime() {
            return time;
        }
        public int getScreen() {
            return screen;
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch(SQLException e) {
            print("Couldn't close " + DB_NAME + " database: " + e.getMessage());
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
        String query = new SQLBuilder().select("*", Tables.CUSTOMERS.toString()).toString();
        Logging.get().print(query);
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(CustTable._ID.getIdx());
                String name = res.getString(CustTable.NAME.getIdx());
                int age = res.getInt(CustTable.AGE.getIdx());
                char gender = res.getString(CustTable.GENDER.getIdx()).toCharArray()[0];
                customers.add(new Customer(id, name, age, gender));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return customers;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new LinkedList<>();
        String query = new SQLBuilder().select("*", Tables.MOVIES.toString()).toString();
        Logging.get().print(query);
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(MoviesTable._ID.getIdx());
                String name = res.getString(MoviesTable.NAME.getIdx());
                int duration = res.getInt(MoviesTable.DURATION.getIdx());
                String release = res.getString(MoviesTable.RELEASE.getIdx());
                movies.add(new Movie(id, name, duration, release));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return movies;
    }

    public List<Sale> getSales() {
        List<Sale> sales = new LinkedList<>();
        String[] cols = new String[]{SalesTable._ID.fullyQual(), CustTable.NAME.fullyQual(),
                MoviesTable.NAME.fullyQual(), SalesTable.DATE.fullyQual(),
                SalesTable.TIME.fullyQual(), SalesTable.COST.fullyQual(),
                SalesTable.SCREEN.fullyQual()};
        String query = new SQLBuilder()
                .select(cols, Tables.SALES.toString())
                .join(Tables.CUSTOMERS.toString(),
                        SalesTable.CUSTOMER_ID.fullyQual(), CustTable._ID.fullyQual())
                .join(Tables.MOVIES.toString(),
                        SalesTable.MOVIE_ID.fullyQual(), MoviesTable._ID.fullyQual())
                .toString();
        Logging.get().print(query);
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(1);
                String custName = res.getString(2);
                String movieName = res.getString(3);
                String date = res.getString(4);
                String time = res.getString(5);
                double cost = res.getDouble(6);
                int screen = res.getInt(7);
                sales.add(new Sale(id, custName, movieName, date, time, cost, screen));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return sales;
    }

    public List<Show> getShows() {
        List<Show> shows = new LinkedList<>();
        String[] cols = new String[]{ShowsTable._ID.fullyQual(), MoviesTable.NAME.fullyQual(),
                ShowsTable.TIME.fullyQual(), ShowsTable.SCREEN.fullyQual()};
        String query = new SQLBuilder()
                .select(cols, Tables.SHOWS.toString())
                .join(Tables.MOVIES.toString(),
                        ShowsTable.MOVIE_ID.fullyQual(), MoviesTable._ID.fullyQual())
                .toString();
        Logging.get().print(query);
        try(Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {
            while(res.next()) {
                int id = res.getInt(1);
                String movie = res.getString(2);
                String time = res.getString(3);
                int screen = res.getInt(4);
                shows.add(new Show(id, movie, time, screen));
            }
        } catch(SQLException e) {
            print("Couldn't create statement/results: " + e.getMessage());
        }
        return shows;
    }

    public int getCustomerID(String name) {
        List<Customer> customers = getCustomers();
        for(Customer cust: customers) {
            if(cust.getName().equals(name)) {
                return cust.getId();
            }
        }
        return -1;
    }

    public int getMovieID(String name) {
        List<Movie> movies = getMovies();
        for(Movie movie: movies) {
            if(movie.getName().equals(name)) {
                return movie.getId();
            }
        }
        return -1;
    }

    public boolean addCustomer(Customer cust) {
        String[] cols = {CustTable.NAME.toString(), CustTable.AGE.toString(),
                CustTable.GENDER.toString()};
        String[] values = {sqlStr(cust.getName()), String.valueOf(cust.getAge()),
                sqlStr(String.valueOf(cust.getGender()))};
        String exec = new SQLBuilder().insert(Tables.CUSTOMERS.toString(), cols, values)
                .toString();
        Logging.get().print(exec);
        try(Statement statement = conn.createStatement()) {
            statement.execute(exec);
        } catch(SQLException e) {
            print("Couldn't add customer to database: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addSale(Sale sale) {
        List<Movie> movies = getMovies();
        String[] cols = {SalesTable.CUSTOMER_ID.toString(), SalesTable.MOVIE_ID.toString(),
                SalesTable.DATE.toString(), SalesTable.TIME.toString(),
                SalesTable.COST.toString(), SalesTable.SCREEN.toString()};
        Integer custID = getCustomerID(sale.getCustName());
        Integer movieID = getMovieID(sale.getMovieName());
        String[] values = {custID.toString(), movieID.toString(),
                sqlStr(sale.getDate()), sqlStr(sale.getTime()),
                String.valueOf(sale.getCost()), String.valueOf(sale.getScreen())};
        String exec = new SQLBuilder().insert(Tables.SALES.toString(), cols, values).toString();
        Logging.get().print(exec);
        try(Statement statement = conn.createStatement()) {
            statement.execute(exec);
        } catch(SQLException e) {
            print("Couldn't add sale to database: " + e.getMessage());
            return false;
        }
        return true;
    }
}