package tutionfeemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DbAction {

    //connecting database...
    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
        return con;
    }

    ObservableList<Student> getAllStudents() throws SQLException {
        ObservableList<Student> stdList = FXCollections.observableArrayList();

        Connection con = getConnection();
        Statement statement = con.createStatement();

        String query = "select * from student_info s left join payment p on s.student_id = p.Std_Id";

        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {

            int id = rs.getInt("student_id");
            String name = rs.getString("student_name");
            int stdClass = rs.getInt("class");
            int scholarship = rs.getInt("Scholership");
            int amount = rs.getInt("Total_Amount");
            int paidAmount = rs.getInt("Paid_Amount");

            int due = amount - paidAmount;

            String query1 = "update student_info set Due = " + due + " where student_id= " + id;
            con.createStatement().executeUpdate(query1);

            Student student = new Student(id, name, stdClass, scholarship, amount, paidAmount, due);
            stdList.add(student);

        }
        return stdList;
    }

    String insertStudent(Student std) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        //insert into student_info values(id, 'name', class, scholership, amount, 'Payment_status');
        String query = "insert into student_info "
                + "values(" + std.getId() + ",'" + std.getName() + "'," + std.getStdClass() + ","
                + std.getScholership() + "," + std.getTotalAmount() + "," + std.getDue() + ")";

        if (statement.executeUpdate(query) > 0) {
            return "Student Added Successfully";
        } else {
            return "Adding Student Failed";
        }

    }

    void updateStudents(Student std) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        String query = "update student_info "
                + "set student_name = '" + std.getName() + "', class=" + std.getStdClass() + ", Scholership= "
                + std.getScholership() + ", Total_Amount= " + std.getTotalAmount() + " where student_id = " + std.getId();

        statement.executeUpdate(query);
    }

    void deleteStudents(ObservableList<Student> selectedStd) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();

        for (Student std : selectedStd) {
            String query = "delete from student_info where student_id =" + std.getId();
            statement.executeUpdate(query);
        }
    }
}
