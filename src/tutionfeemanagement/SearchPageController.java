package tutionfeemanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SearchPageController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private Label idLabel;
    @FXML
    private Label dueLabel;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Label scholarshipLabel;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void searchButtonAction(ActionEvent event) throws SQLException {

        DbAction db = new DbAction();
        Connection con = db.getConnection();

        if (idField.getText().equals("")) {
            idLabel.setText("Enter Id");
            return;
        }

        idLabel.setText("");
        nameLabel.setText("");
        classLabel.setText("");
        scholarshipLabel.setText("");
        totalAmountLabel.setText("");
        dueLabel.setText("");

        String query = "select * from student_info s left join payment p"
                + " on s.student_id= p.Std_Id where s.student_id=" + Integer.parseInt(idField.getText());

        ResultSet rs = con.createStatement().executeQuery(query);

        if (rs.next()) {
            int id = rs.getInt("student_id");
            String name = rs.getString("student_name");
            int stdClass = rs.getInt("class");
            int totalAmount = rs.getInt("Total_Amount");
            int schloarship = rs.getInt("Scholership");
            int due = rs.getInt("Due");

            idLabel.setText("" + id);
            nameLabel.setText(name);
            classLabel.setText("" + stdClass);
            scholarshipLabel.setText("" + schloarship);
            totalAmountLabel.setText("" + totalAmount);
            dueLabel.setText("" + due);

            idField.clear();
        } else {
            idLabel.setText("Invalid Id");
            nameLabel.setText("");
            classLabel.setText("");
            scholarshipLabel.setText("");
            totalAmountLabel.setText("");
            dueLabel.setText("");
        }
    }

    @FXML
    private void idFieldAction(KeyEvent event) {
        idLabel.setText("");
        nameLabel.setText("");
        classLabel.setText("");
        scholarshipLabel.setText("");
        totalAmountLabel.setText("");
        dueLabel.setText("");
    }

}
