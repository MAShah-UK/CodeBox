package cbox.assignments.qacinemas;

import java.util.Scanner;

// Manages interaction with program through the console.
class Console {
    private static final String[] OPTIONS_STRS;
    private static final Scanner sc;

    static {
        OPTIONS_STRS = new String[]{"Enter 1: For help.",
                "Enter 2: To add a customer booking.",
                "Enter 3: To view customers.",
                "Enter 4: To view movies currently being shown.",
                "Enter 5: To view showing times.",
                "Enter 6: To view sales history.",
                "Enter 7: To exit."};
        sc = new Scanner(System.in);
    }

    public static int getOptionsLength() {
        return OPTIONS_STRS.length;
    }

    public static void help() {
        for(String option: OPTIONS_STRS) {
            print(option);
        }
    }

    public static void print() {
        print("", true);
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

    public static int getInt(String msg, int max) {
        int choice = -1;
        while(choice == -1) {
            print(msg, false);
            String input = sc.nextLine();
            print("You entered: " + input + ". ", false);
            if(input.matches("[0-9]+")) {
                choice = Integer.valueOf(input);
                choice = (choice >= 1 && choice <= max) ? choice : -1;
            }
            if(choice < 1 || choice > max) {
                print("This is invalid.", false);
            }
            print();
        }

        return choice;
    }

    public static String getString(String msg) {
        String str = null;
        while(str == null) {
            print(msg, false);
            String input = sc.nextLine().trim().toLowerCase();
            print("You entered: " + input + ". ", false);
            if(!input.isEmpty() && input.matches("[a-z| |-]+")) {
                str = input;
            } else {
                print("This is invalid.", false);
            }
            print();
        }
        return str;
    }

    public static boolean getBoolean(String msg) {
        boolean done = false;
        boolean result = false;
        while(!done) {
            print(msg, false);
            String input = sc.nextLine();
            print("You entered: " + input + ". ", false);
            if(!input.isEmpty() && input.trim().matches("[y|n]")) {
                result = input.matches("y");
                done = true;
            } else {
                print("This is invalid.", false);
            }
            print();
        }
        return result;
    }
}
