// java program which make an entry for a new item in the item table.
// If any of the check constraint violates, give the message to the user with the information about the constraint.
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class newItemDB {
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

            System.out.println("(b) PROGRAM TO CREATE ENTRY FOR NEW ITEM : ");
            String query, item_no, item_name;
            int unit_price;

            // inputs
            System.out.print("Enter item number(valid) of item to be added : ");
            item_no = sc.nextLine();
            System.out.print("Enter name of the item to be added : ");
            item_name = sc.nextLine();
            System.out.print("Enter price of a unit of item : ");
            unit_price = sc.nextInt();

            // setting query to be executed
            query = "INSERT INTO ITEM VALUES ('" + item_no + "', '" + item_name + "', '" + unit_price + "')";
            System.out.println(query);
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("ERROR CONSTRAINT VIOLATED!!");
                return;
            }
            System.out.println("Item Added!!");

            query = "SELECT * FROM ITEM";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ITEM_NO.\tITEM_NAME\tUNIT_PRICE");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getInt(3));
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