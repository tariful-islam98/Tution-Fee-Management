package tutionfeemanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentFormController implements Initializable {

    @FXML
    private TextField paidAmountField;
    @FXML
    private TextField paymentDateField;
    @FXML
    private Label stdIdLabel;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Label feeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        stdIdLabel.setText("" + DefaultCenterController.stdId);
        totalAmountLabel.setText("" + DefaultCenterController.totalAmount);

    }

    @FXML
    private void submitButtonAction(ActionEvent event) throws SQLException {
        DbAction db = new DbAction();
        Student std = new Student();

        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        if (paidAmountField.getText().equals("") && paymentDateField.getText().equals("")) {
            feeLabel.setText("Enter value");
            return;
        }

        int stdId = DefaultCenterController.stdId;
        int totalAmount = DefaultCenterController.totalAmount;
        int paidAmount = Integer.parseInt(paidAmountField.getText());
        String date = paymentDateField.getText();

        Payment pay = new Payment(stdId, totalAmount, paidAmount, date);

        String query = "insert into payment "
                + "values(" + pay.getId() + ", " + pay.getTotalAmount() + ", "
                + pay.getPaidAmount() + ",'" + pay.getDate() + "')";

        if (statement.executeUpdate(query) > 0) {
            feeLabel.setText("Paid Successfully");
            feeLabel.setStyle("-fx-text-fill:green;");
        } else {

            feeLabel.setText("Payment Failed");
            feeLabel.setStyle("-fx-text-fill:red;");
        }

        paidAmountField.clear();
        paymentDateField.clear();
        DefaultCenterController.paymentStage.close();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        DefaultCenterController.paymentStage.close();
    }

}
