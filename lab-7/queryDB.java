// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class queryDB {
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

            String query;

            // part E
            System.out.println("(e) List details of Departments that offer more than 3 branches : ");
            query = "SELECT * FROM DEPARTMENT D WHERE D.DNO IN (SELECT B.DNO FROM BRANCH B GROUP BY B.DNO HAVING COUNT(B.DNO) > 3)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("DNO.\tDNAME");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

            // part F
            System.out.println("(f) List the details of Departments that offer more than 6 courses : ");
            query = "SELECT * FROM DEPARTMENT D WHERE D.DNO IN (SELECT C.DNO FROM COURSE C GROUP BY C.DNO HAVING COUNT(C.CCODE) > 6)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("DNO.\tDNAME");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
            }
            System.out.print("\n");

            // part G
            System.out.println("(g) List the details of courses that are common for more than 3 branches : ");
            query = "SELECT * FROM COURSE C WHERE C.CCODE IN (SELECT B.CCODE FROM BRANCH_COURSE B GROUP BY B.CCODE HAVING COUNT(B.BCODE) > 3)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("CCODE\tCNAME\tCREDITS\tDNO.");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                        + "\t\t" + resultSet.getInt(4));
            }
            System.out.print("\n");

            // part H
            System.out.println("(h) List students who got ‘S’ in more than 2 courses during single enrollment : ");
            query = "SELECT * FROM STUDENT S WHERE S.ROLLNO IN (SELECT E.ROLLNO FROM ENROLLS E WHERE E.GRADE = 'S' GROUP BY E.ROLLNO HAVING COUNT(E.GRADE) > 2)";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("R201951024\tROLLNO.\tNAME\t\tDOB\t\tGender\tDOA\t\tBcode");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getInt(2) + "\t" + resultSet.getString(3)
                        + "\t\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t"
                        + resultSet.getString(6) + "\t" + resultSet.getInt(7));
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