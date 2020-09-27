package tutionfeemanagement;

import java.io.IOException;
import java.net.URL;
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

public class AddStudentController implements Initializable {

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
    @FXML
    private Label warningLabel;

    static ObservableList<Student> stdList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //form to add student...
    @FXML
    private void addButtonAction(ActionEvent event) throws IOException, SQLException {

        if (stdIdField.getText().equals("") || stdNameField.getText().equals("") || stdClassField.getText().equals("") || scholershipAmountField.getText().equals("") || feeAmountField.getText().equals("")) {
            warningLabel.setText("Fill all fields");
            return;
        }

        int stdId = Integer.parseInt(stdIdField.getText());
        String stdName = stdNameField.getText();
        int stdClass = Integer.parseInt(stdClassField.getText());
        int scholershipAmount = Integer.parseInt(scholershipAmountField.getText());
        int feeAmount = Integer.parseInt(feeAmountField.getText());

        int paidAmount = 0;
        int due = 0;

        Student std = new Student(stdId, stdName, stdClass, scholershipAmount, feeAmount, paidAmount, due);

        //database action..
        DbAction db = new DbAction();
        db.insertStudent(std);

        //stdList.add(std);
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
        //PasswordStageController.homeStage.close();
        StudentInformationController.stage1.close();
        stage.close();

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
        StudentInformationController.stage1.close();
    }

    @FXML
    private void idFieldAction(KeyEvent event) {
        warningLabel.setText("");
        String id = stdIdField.getText();
        if (id.equals("")) {
            return;
        }

        for (Student std : stdList) {
            if (std.getId() == Integer.parseInt(id)) {
                warningLabel.setText("This Id already exists");
                break;
            }
        }
    }

}
