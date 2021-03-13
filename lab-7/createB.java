// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createB {
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination", "root", "anirudh1234");
            statement = connection.createStatement();
            ResultSet resultSet;

            System.out.println("(b) PROGRAM TO CREATE NEW BRANCH, DEP, BRANCH_COURSE : ");
            String query;

            // DEPARTMENT
            query = "INSERT INTO DEPARTMENT VALUES (6, 'AA')";
            statement.executeUpdate(query);
            System.out.println("Department Added!!");

            query = "SELECT * FROM DEPARTMENT";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("DNO.\tDNAME");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

            // COURSE
            query = "INSERT INTO COURSE VALUE (4201, 'WRITING', 2, 4)";
            statement.executeUpdate(query);
            System.out.println("Course Added!!");

            query = "SELECT * FROM COURSE";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("CCODE\tCNAME\tCREDITS\tDNO.");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                        + "\t\t" + resultSet.getInt(4));
            }
            System.out.print("\n");

            // BRANCH
            query = "INSERT INTO BRANCH VALUE (302, 'MM', 4)"; // both course and brance are related to same department
                                                               // DNO. = 4
            statement.executeUpdate(query);
            System.out.println("Branch Added!!");

            query = "SELECT * FROM BRANCH";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("BCODE\tBNAME\tDNO.");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3));
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