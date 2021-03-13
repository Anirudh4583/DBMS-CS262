// 
// 4N1RU0H M17R4 201951024

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class interactDB {
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
            query = "SELECT * FROM STUDENT";
            resultSet = ((java.sql.Statement) statement).executeQuery(query);
            System.out.println("(a) PROGRAM TO ITERACT WITH THE DATABASE : ");
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