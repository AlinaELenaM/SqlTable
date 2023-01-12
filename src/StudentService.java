import java.sql.*;
import java.util.ArrayList;

public class StudentService {

    // db parameters
    private static final String URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "passwordhere";

//SQL - 02 - Table
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

//SQL - 03 - Select
    public void addNewColumn() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String alterQuery = ""
                + "ALTER TABLE newdb.students "
                + "ADD country varchar(30);";
        statement.executeUpdate(alterQuery);

        statement.executeUpdate("UPDATE students SET country = 'Italy' WHERE student_id = 1;" );
        statement.executeUpdate("UPDATE students SET country = 'Italy' WHERE student_id = 2;" );
        statement.executeUpdate("UPDATE students SET country = 'Germany' WHERE student_id = 3;" );
        statement.executeUpdate("UPDATE students SET country = 'Germany' WHERE student_id = 4;" );

        statement.close();
        System.out.println("Country added!");
    }

//SQL - 04 - View
    public void createItalianStudentsView() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String italianStudentsSelectQuery = ""
                + "select * from students where country = 'Italy'";

        statement.executeUpdate("create view italian_students as (" +
                 italianStudentsSelectQuery + ");");

        statement.close();
        System.out.println("Italian students View created!");
    }
    public void createGermanStudentsView() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String germanStudentsSelectQuery = ""
                + "select * from students where country = 'Germany'";

        statement.executeUpdate("create view german_students as (" +
                germanStudentsSelectQuery + ");");

        statement.close();
        System.out.println("German students View created!");
    }

    public void getAllItalianStudents() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        ArrayList<Student> italianStudents = new ArrayList<>();

        String getItalianStudents = "SELECT * FROM italian_students";

        ResultSet resultSet = statement.executeQuery(getItalianStudents);
        while(resultSet.next()){
            italianStudents.add(new Student(resultSet.getString("last_name"),
                    resultSet.getString("first_name")));
        }

        statement.close();
        System.out.println("Italian students: ");
        System.out.println(italianStudents);
    }

    public void getAllGermanStudents() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        ArrayList<Student> germanStudents = new ArrayList<>();

        String getGermanStudents = "SELECT * FROM german_students";

        ResultSet resultSet = statement.executeQuery(getGermanStudents);
        while(resultSet.next()){
            germanStudents.add(new Student(resultSet.getString("last_name"),
                    resultSet.getString("first_name")));
        }

        statement.close();
        System.out.println("German students: ");
        System.out.println(germanStudents);
    }
}
