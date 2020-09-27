package tutionfeemanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateInfoController implements Initializable {

    @FXML
    private TextField stdIdField;
    @FXML
    private TextField stdNameField;
    @FXML
    private TextField stdClassField;
    @FXML
    private TextField scholershipAmountField;
    @FXML
    private TextField feeAmountField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException, SQLException {
        if (stdIdField.getText().equals("") && stdClassField.getText().equals("") && scholershipAmountField.getText().equals("") && feeAmountField.getText().equals("")) {
            return;
        }

        int stdId = Integer.parseInt(stdIdField.getText());
        String stdName = stdNameField.getText();
        int stdClass = Integer.parseInt(stdClassField.getText());
        int scholershipAmount = Integer.parseInt(scholershipAmountField.getText());
        int feeAmount = Integer.parseInt(feeAmountField.getText());

        int paidAmount = 0;
        int due = 0;

        if (stdName.equals("")) {
            return;
        }

        Student std = new Student(stdId, stdName, stdClass, scholershipAmount, feeAmount, paidAmount, due);

        //database action..
        DbAction db = new DbAction();
        db.updateStudents(std);

        stdIdField.clear();
        stdNameField.clear();
        stdClassField.clear();
        scholershipAmountField.clear();
        feeAmountField.clear();

        Parent pane = FXMLLoader.load(getClass().getResource("StudentInformation.fxml"));

        Scene scn = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scn);
        stage.setTitle("Automation");
        stage.show();
        stage.close();
        //PasswordStageController.homeStage.close();
        StudentInformationController.stage2.close();
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        stdIdField.clear();
        stdNameField.clear();
        stdClassField.clear();
        scholershipAmountField.clear();
        feeAmountField.clear();
    }

    @FXML
    private void cancelButtonActon(ActionEvent event) {
        StudentInformationController.stage2.close();
    }

}
