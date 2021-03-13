// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class userStud {
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

            System.out.println("(c) PROGRAM TO ADD NEW STUDENT INPUT FROM USER : ");
            String query;

            int roll, bCode;
            String name, dob, gender, doa;

            System.out.print("Enter RollNo : ");
            roll = sc.nextInt();
            System.out.print("Enter Name : ");
            name = sc.next();
            System.out.print("Enter Date of Birth : ");
            dob = sc.nextLine();
            dob += sc.nextLine();
            System.out.print("Enter Gender : ");
            gender = sc.next();
            System.out.print("Enter Date of admission : ");
            doa = sc.nextLine();
            doa += sc.nextLine();
            System.out.print("Enter Branch Code : ");
            bCode = sc.nextInt();

            // STUDENT
            query = "INSERT INTO STUDENT VALUES (NULL, " + roll + ", '" + name + "',STR_TO_DATE('" + dob
                    + "', '%M %D %Y'), '" + gender + "', STR_TO_DATE('" + doa + "', '%M %D %Y'), " + bCode + ")";
            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Student Added!!");

            query = "SELECT * FROM STUDENT";
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
            sc.close();
        }
    }
}