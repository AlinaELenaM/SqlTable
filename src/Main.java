import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Students student = new Students();

        try {
            student.createTable();

            student.insertTable("Bianchi","Valentina");
            student.insertTable("Verdi","Paolo");
            student.insertTable("Rossi","Luca");
            student.insertTable("Marroni","Maria");

            student.printAllStudents();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}