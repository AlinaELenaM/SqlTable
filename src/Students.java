import java.sql.*;
import java.util.ArrayList;

public class Students {

    // db parameters
    private static final String URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "passwordhere";

    public void createTable() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String createTableQuery = ""
                + "CREATE TABLE IF NOT EXISTS `students` ( "
                + "  `student_id` int NOT NULL AUTO_INCREMENT, "
                + "  `last_name` varchar(30) DEFAULT NULL, "
                + "  `first_name` varchar(30) DEFAULT NULL, "
                + "  CONSTRAINT student_pk PRIMARY KEY (student_id) "
                + ");";

        statement.execute(createTableQuery);
        statement.close();
        System.out.println("Table created!");
    }

    public void insertTable(String lastName, String firstName) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String insertQuery =
                """ 
                 INSERT INTO newdb.students (last_name, first_name)
                 VALUES ('""" + lastName + "', '" + firstName + "');";

        statement.executeUpdate(insertQuery);
        statement.close();
        System.out.println("Student inserted!");
    }

    public void printAllStudents() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String selectQuery = """
                SELECT * from newdb.students;
                """;

        ResultSet resultSet = statement.executeQuery(selectQuery);
        ArrayList<String> surnames = new ArrayList<>();
        int i = 0;

        while (resultSet.next()) {
            i = i + 1;
            System.out.println(i + " First name: " + resultSet.getString("first_name") );
            surnames.add(resultSet.getString("last_name"));
        }
        connection.close();
        System.out.println("Surname Array: ");
        System.out.println(surnames.toString());
    }
}
