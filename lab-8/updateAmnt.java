// java program to edit value of ORDER_AMNT of an existing order
// If any of the check constraint violates, give the message to the user
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class updateAmnt {
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

            System.out.println("(d) PROGRAM TO EDIT ORDER_AMNT OF AN EXISTING ORDER : ");
            // variables
            String query, order_no;
            int order_amnt;

            // show list of orders
            System.out.println("list of existing orders :");
            query = "SELECT * FROM CUST_ORDER";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tORDER_DATE\tCUST_ID\tORDER_AMNT");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t" + resultSet.getInt(4));
            }
            System.out.print("\n");

            // inputs
            System.out.print("Enter order number of order to be edited : ");
            order_no = sc.nextLine();

            System.out.println("selected order : ");
            query = "SELECT * FROM CUST_ORDER WHERE ORDERNO='" + order_no + "'";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tORDER_DATE\tCUST_ID\tORDER_AMNT");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t" + resultSet.getInt(4));
            }
            System.out.print("\n");

            System.out.print("Enter the new amount : ");
            order_amnt = sc.nextInt();

            // setting query to edit ORDER_AMNT in CUST_ORDER
            query = "UPDATE CUST_ORDER SET ORD_AMT = " + order_amnt + " WHERE ORDERNO='" + order_no + "'";
            statement.executeUpdate(query);
            System.out.println("Order Updated!!");

            query = "SELECT * FROM CUST_ORDER";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tORDER_DATE\tCUST_ID\tORDER_AMNT");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t" + resultSet.getInt(4));
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