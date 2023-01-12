import java.sql.*;

public class Main {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        try {
//             studentService.createTable();
//             studentService.insertTable("Bianchi","Valentina");
//             studentService.insertTable("Verdi","Paolo");
//             studentService.insertTable("Rossi","Luca");
//             studentService.insertTable("Marroni","Maria");
//             studentService.printAllStudents();
//             studentService.addNewColumn();
               //studentService.createItalianStudentsView();
               //studentService.createGermanStudentsView();
                studentService.getAllItalianStudents();
                studentService.getAllGermanStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}