import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // db parameters
            final String url = "jdbc:mysql://localhost:3306/newdb";
            final String user = "developer";
            final String password = "passwordhere";
            // create a connection to the database
            connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            
            String createTableQuery = ""
                    + "CREATE TABLE IF NOT EXISTS `students` ( "
                    + "  `student_id` int NOT NULL AUTO_INCREMENT, "
                    + "  `last_name` varchar(30) DEFAULT NULL, "
                    + "  `first_name` varchar(30) DEFAULT NULL, "
                    + "  PRIMARY KEY (`student_id`) "
                    + ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

            statement.execute(createTableQuery);
            System.out.println("Table created!");

            statement.execute(
                    "INSERT INTO newdb.students "
                    + "(last_name, first_name) "
                    + "VALUES('Bianchi', 'Luigi');");
            statement.execute(
                    "INSERT INTO newdb.students "
                            + "(last_name, first_name) "
                            + "VALUES('Verdi', 'Luisa');");
            statement.execute(
                    "INSERT INTO newdb.students "
                            + "(last_name, first_name) "
                            + "VALUES('Rossi', 'Maria');");
            statement.execute(
                    "INSERT INTO newdb.students "
                            + "(last_name, first_name) "
                            + "VALUES('Azzurri', 'Giovanni');");
            System.out.println("Students inserted!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(connection != null){
                connection.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}