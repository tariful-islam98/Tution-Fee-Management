package tutionfeemanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdatePaymentController implements Initializable {

    @FXML
    private Label idField;
    @FXML
    private Label totalAmountField;
    @FXML
    private Label dueField;
    @FXML
    private TextField paidAmountField;
    @FXML
    private TextField dateField;
    @FXML
    private Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idField.setText("" + DefaultCenterController.stdId);
        totalAmountField.setText("" + DefaultCenterController.totalAmount);
        dueField.setText("" + DefaultCenterController.due);
    }

    @FXML
    private void submitButtonAction(ActionEvent event) throws SQLException {
        DbAction db = new DbAction();
        Student std = new Student();

        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        if (paidAmountField.getText().equals("") && dateField.getText().equals("")) {
            warningLabel.setText("Enter value");
            return;
        }

        int stdId = DefaultCenterController.stdId;
        int totalAmount = DefaultCenterController.totalAmount;
        int paidAmount = Integer.parseInt(paidAmountField.getText());
        String date = dateField.getText();

        Payment pay = new Payment(stdId, totalAmount, paidAmount, date);

        int due = DefaultCenterController.due;

        String query1 = "select Paid_Amount from payment where Std_Id = " + stdId;
        ResultSet rs = con.createStatement().executeQuery(query1);
        while (rs.next()) {
            int paidAmount1 = rs.getInt("Paid_Amount");
            String query = "update payment "
                    + "set Paid_Amount = " + (paidAmount1 + paidAmount) + ", Date= " + date + " where Std_Id = " + stdId;

            if (statement.executeUpdate(query) > 0) {
                warningLabel.setText("Paid Successfully");
                warningLabel.setStyle("-fx-text-fill:green;");
            } else {

                warningLabel.setText("Payment Failed");
                warningLabel.setStyle("-fx-text-fill:red;");
            }
        }

        paidAmountField.clear();
        dateField.clear();
        DefaultCenterController.updatePaymentStage.close();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        DefaultCenterController.updatePaymentStage.close();
    }

}
