// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class enrollStud {
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
            Scanner sc = new Scanner(System.in);

            System.out.println("(d) PROGRAM TO ENROLL EXISTING STUDENT IN EXISTING COURSE : \n");
            String query;
            int roll, cCode;

            // taking inputs
            System.out.print("Enter RollNo of Student to be enrolled : ");
            roll = sc.nextInt();
            System.out.print("Enter Course Code of the course to be enrolled in: ");
            cCode = sc.nextInt();

            boolean isStudent = false, isCourse = false;

            query = "select * from student where RollNo=" + roll;
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            if (resultSet.next())
                isStudent = true;
            query = "select * from course where Ccode=" + cCode;
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            if (resultSet.next())
                isCourse = true;

            query = "INSERT INTO ENROLLS VALUE (" + roll + ", " + cCode
                    + ", STR_TO_DATE('APRIL 01 2020', '%M %d %Y'), 'A')";

            if (isStudent && isCourse) {
                statement.executeUpdate(query);
                System.out.println("Student enrolled succesfully!!");
            } else {
                System.out.println("Student/Course doesn't exist!!");
                return;
            }

            query = "SELECT * FROM ENROLLS";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("ROLLNO.\tCCODE\tSESS\t\tGRADE");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getInt(2) + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4));
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
