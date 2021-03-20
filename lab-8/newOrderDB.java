// java program which make an entry for a new order in the order_item and cust_order tables.
// If any of the check constraint violates, give the message to the user with
// the information about the constraint.
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class newOrderDB {
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

            System.out.println("(c) PROGRAM TO CREATE ENTRY FOR NEW ORDER : ");
            // variables
            String query, order_no, item_no, cust_id, order_date;
            int unit_price = 0, order_qty, order_amnt; // order_amnt = unit_price * order_qty
            // unit_price is taken from table of items

            // inputs
            System.out.print("Enter customer id(valid) : ");
            cust_id = sc.nextLine();
            System.out.print("Enter order number of order to be placed : ");
            order_no = sc.nextLine();
            System.out.print("Enter date on which order is placed : ");
            order_date = sc.nextLine();

            // show list of available items
            System.out.println("Available items to order :");
            query = "SELECT * FROM ITEM";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ITEM_NO.\tITEM_NAME\tUNIT_PRICE");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getInt(3));
            }
            System.out.print("\n");

            // more inputs
            System.out.print("Enter item number(valid) from above list : ");
            item_no = sc.nextLine();

            query = "SELECT * FROM ITEM WHERE ITEMNO='" + item_no + "'";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("Selected item : ");
            System.out.println("ITEM_NO.\tITEM_NAME\tUNIT_PRICE");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getInt(3));
                unit_price = resultSet.getInt("UNIT_PRICE");
            }
            // System.out.println(unit_price);

            // some more inputs
            System.out.println("Enter quantity of the item to be ordered : ");
            order_qty = sc.nextInt();
            // calculate order amount
            order_amnt = unit_price * order_qty;
            System.out.println("Total amount of order = " + order_amnt);

            // setting query add in CUST_ORDER
            query = "INSERT INTO CUST_ORDER VALUES ('" + order_no + "', STR_TO_DATE('" + order_date
                    + "', '%M %d %Y'), '" + cust_id + "', '" + order_amnt + "')";
            System.out.println(query);
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("ERROR CONSTRAINT VIOLATED!!");
                return;
            }

            // setting query add in CUST_ORDER
            query = "INSERT INTO ORDER_ITEM VALUES('" + order_no + "', '" + item_no + "', " + order_qty + ")";
            System.out.println(query);
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("ERROR CONSTRAINT VIOLATED!!");
                return;
            }
            System.out.println("Order Added!!");

            // cust_order table
            query = "SELECT * FROM CUST_ORDER";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tORDER_DATE\tCUST_ID\tORDER_AMNT");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t" + resultSet.getInt(4));
            }
            System.out.print("\n");

            // order_item table
            query = "SELECT * FROM ORDER_ITEM";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tITEM_NO.\tQTY");
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