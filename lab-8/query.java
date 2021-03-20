// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class query {
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

            String query;

            // part E
            System.out.println("\n(e) List the details of customers who have placed more than 3 orders : ");
            query = "SELECT * FROM CUSTOMER AS C WHERE C.CUSTOMERNO IN (SELECT O.CUSTOMERNO FROM CUST_ORDER AS O GROUP BY O.CUSTOMERNO HAVING COUNT(O.ORDERNO) > 3)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("CNO.\tCNAME");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

            // part F
            System.out.println(
                    "(f) List details of items whose price is less than the average price of all items in each order : ");
            query = "SELECT * FROM ITEM AS I WHERE I.UNIT_PRICE < (SELECT AVG(UNIT_PRICE) FROM ITEM)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ITEM_NO.\tITEM_NAME\tUNIT_PRICE");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getInt(3));
            }
            System.out.print("\n");
            // part G
            System.out.println("(g) List the orderno and number of items in each order : ");
            query = "SELECT ORDERNO, SUM(QTY) FROM ORDER_ITEM GROUP BY ORDERNO";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ORDER_NO.\tSUM(QTY)");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

            // part H
            System.out.println("(h) List the details of items that are present in 25% of the orders : ");
            query = "SELECT * FROM ITEM WHERE ITEMNO IN (SELECT ITEMNO FROM ORDER_ITEM GROUP BY ITEMNO HAVING COUNT(ITEMNO) >= (SELECT (COUNT(*)/4) FROM CUST_ORDER));";
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