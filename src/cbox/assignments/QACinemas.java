package cbox.assignments;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;

class DataSource {
    private static final DataSource dataSource = new DataSource();

    private DataSource() {

    }

    public static DataSource get() {
        return dataSource;
    }
}

class Logging implements AutoCloseable {
    private static final Logging inst = new Logging();

    private final Logger logger;
    private FileHandler fileHandler;

    // Singleton: Only need one instance of this class.
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
    private static final String greetStr;
    private static final String byeStr;
    private static final String[] optionsStrs;
    private static final Scanner sc;

    static {
        greetStr = "Welcome to QA Cinema's ticket booking system.";
        byeStr = "Thank you for using QA Cinema's ticket booking system.";
        optionsStrs = new String[]{"Enter 1: For help.",
                                   "Enter 2: To add a customer booking.",
                                   "Enter 3: To view movies currently being shown.",
                                   "Enter 4: To view showing times.",
                                   "Enter 5: To view ticket history.",
                                   "Enter 6: To exit."};
        sc = new Scanner(System.in);
    }

    public static void greet() {
        print(greetStr);
    }

    public static void help() {
        for(String option: optionsStrs) {
            print(option);
        }
        System.out.println();
    }

    public static void bye() {
        print(byeStr);
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
            choice = (choice > 0 && choice < optionsStrs.length) ? choice : -1;
        }
        if(choice < 1 || choice > optionsStrs.length) {
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    exit();
                    quit = true;
                    break;
            }
        }
    }

    private void exit() {
        Console.bye();
        Logging.get().close();
    }
}
