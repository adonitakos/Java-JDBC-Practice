import java.sql.*;
import java.util.*;

public class JDBC {
    static String URL = "jdbc:mysql://localhost:3306/campus store";
    static String uname;
    static String password;

    static Scanner input = new Scanner(System.in);
    static Connection con;

    public static void CheckSQLDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    } // <--- SQLDriver() method ends here

    User root; // declaring User object

    public static void login() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the authorized credentials");

        System.out.print("Username: ");
        uname = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();

        System.out.println("\nThe credentials are as follows: \n" +
                "URL: " + URL + "\nUsername: " + uname + "\nPassword: " + password + "\n");

        if(!uname.equals("root"))
        {
            System.out.println("Incorrect credentials! Please try again");
            login();
        }
        else
        {
            CheckSQLDriver();
            User root = new User(URL, uname, password);
            con = DriverManager.getConnection(URL, root.getUname(), root.getPassword());
        }

    } // <--- login() method ends here

    public static void prompt() throws SQLSyntaxErrorException {
        System.out.println("You can do one of the following:" +
                "\n(1) Print the entire Inventory Stock table" +
                "\n(2) Print a column from the Inventory Stock table");
        int choice = input.nextInt();

        if(choice == 1) {
            printTable("inventorystock");
        }
        else if(choice == 2) {
            SELECT_Column();
        }
        else {
            System.out.println("Invalid input. Please try again");
            prompt();
        }

    } // <--- prompt() method ends here

    public static void printTable(String table) {
        String query = "SELECT * FROM " + table;
        System.out.println("\t\t\t\t\t\t\t" + table);

        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next())
            {
                System.out.println(result.getString("ItemCode") + "\t\t"
                        + result.getString("ProductName") + " \t\t\t\t\t "
                        + Double.parseDouble(result.getString("Price")) + " \t\t\t\t\t\t"
                        + result.getString("Units"));
            } // <--- while(result.next()) loop ends here
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    } // <--- printTable() method ends here


    public static void SELECT_Column() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat column would you like to output?: ");
        String column = input.nextLine();

        String query = "SELECT " + column + " FROM inventorystock";
        System.out.println("\n\t\t\t\t\t\t\t" + "inventorystock" + "\n\t " + column);

        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next())
            {
                System.out.println(result.getString(column) + "\t\t");
            }
        } // <--- try{} block ends here
        catch(SQLException e) {
            System.out.println("\n\t\tThis is not a valid column within the inventorystock table!");
            e.printStackTrace();
        } // <--- catch{} block ends here

    } // <--- SELECT() method ends here

                                    // ---------*** Main ***--------- \\
    public static void main(String[] args) throws Exception{
        System.out.println("\n------------------------ Welcome to this Java JDBC SQL Connection Program ------------------------ ");
        login();
        prompt();

    } // <--- main() method ends here

} // <--- class ends here