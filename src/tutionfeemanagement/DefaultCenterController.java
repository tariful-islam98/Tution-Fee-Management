package tutionfeemanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DefaultCenterController implements Initializable {

    static ObservableList<Payment> payList = FXCollections.observableArrayList();

    @FXML
    private TextField stdIdField;
    @FXML
    private Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    static int stdId;
    static int totalAmount;
    static int due;

    static Stage paymentStage;
    static Stage updatePaymentStage;

    ObservableList<Payment> getPaymentInfo() throws SQLException {
        ObservableList<Payment> payList = FXCollections.observableArrayList();
        DbAction db = new DbAction();
        Connection con = db.getConnection();

        String query = "select * from payment";
        ResultSet rs = con.createStatement().executeQuery(query);
        while (rs.next()) {
            int sId = rs.getInt("Std_Id");
            int sTotal = rs.getInt("Total_Amount");
            int sPaid = rs.getInt("Paid_Amount");
            String date = rs.getString("Date");

            Payment pay = new Payment(sId, sTotal, sPaid, date);
            payList.add(pay);
        }
        return payList;
    }

    @FXML
    private void enterButtonAction(ActionEvent event) throws SQLException, IOException {
        warningLabel.setText("");
        if (stdIdField.getText().equals("")) {
            warningLabel.setText("Id field cannot be null!");
            return;
        }
        DbAction db = new DbAction();
        Connection con = db.getConnection();
        this.stdId = Integer.parseInt(stdIdField.getText());

        String query = "select student_id, Total_Amount from student_info where student_id=" + stdId;

        ResultSet rs = con.createStatement().executeQuery(query);
        if (rs.next()) {

            this.totalAmount = rs.getInt("Total_Amount");

            DefaultCenterController.payList = getPaymentInfo();

            warningLabel.setText("");
            String id = stdIdField.getText();

            if (id.equals("")) {
                return;
            }

            for (Payment pay : payList) {
                if (pay.getId() == Integer.parseInt(id)) {
                    warningLabel.setText("This Id already exists, you can update information!");
                    return;
                } else {
                    Parent pane = FXMLLoader.load(getClass().getResource("PaymentForm.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Student Payment Form");
                    stage.setScene(new Scene(pane));
                    stage.show();
                    stdIdField.clear();

                    this.paymentStage = stage;
                }
            }

        } else {
            warningLabel.setText("Record does not exists!");
        }
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws SQLException, IOException {
        warningLabel.setText("");
        if (stdIdField.getText().equals("")) {
            warningLabel.setText("Id field cannot be null!");
            return;
        }
        DbAction db = new DbAction();
        Connection con = db.getConnection();
        this.stdId = Integer.parseInt(stdIdField.getText());

        String query = "select student_id, Total_Amount, Due from student_info where student_id=" + stdId;

        ResultSet rs = con.createStatement().executeQuery(query);
        if (rs.next()) {

            this.totalAmount = rs.getInt("Total_Amount");
            this.due = rs.getInt("Due");

            Parent pane = FXMLLoader.load(getClass().getResource("UpdatePayment.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Student Payment Form");
            stage.setScene(new Scene(pane));
            stage.show();
            stdIdField.clear();

            this.updatePaymentStage = stage;
        } else {
            warningLabel.setText("Record does not exists!");
        }
    }

    @FXML
    private void idFieldAction(KeyEvent event) throws SQLException {
        DefaultCenterController.payList = getPaymentInfo();

        warningLabel.setText("");
        String id = stdIdField.getText();
        if (id.equals("")) {
            return;
        }

        for (Payment pay : payList) {
            if (pay.getId() == Integer.parseInt(id)) {
                warningLabel.setText("This Id already exists, you can update information!");
                break;
            }
        }
    }

}
