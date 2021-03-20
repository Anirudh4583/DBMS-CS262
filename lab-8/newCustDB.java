// java program which make an entry for a new customer in the customer table.
// If any of the check constraint violates, give the message to the user with the information about the constraint.
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class newCustDB {
    @SuppressWarnings("resource")
    public static void main(String args[])
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        java.sql.Connection connection = null;
        java.sql.Statement statement = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/order_process", "root",
                    "anirudh1234");
            statement = connection.createStatement();
            ResultSet resultSet;
            Scanner sc = new Scanner(System.in);

            System.out.println("(a)PROGRAM TO CREATE ENTRY FOR NEW CUSTOMER : ");
            String query, cust_id, cust_name;

            // inputs
            System.out.print("Enter id of customer to be added : ");
            cust_id = sc.nextLine();
            System.out.print("Enter name of the customer to be added : ");
            cust_name = sc.nextLine();

            // setting query to be executed
            query = "INSERT INTO CUSTOMER VALUES ('" + cust_id + "', '" + cust_name + "')";
            System.out.println(query);
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("ERROR CONSTRAINT VIOLATED!!");
                return;
            }
            System.out.println("Department Added!!");

            query = "SELECT * FROM CUSTOMER";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("CNO.\tCNAME");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }
    }
}