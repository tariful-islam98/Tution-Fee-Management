package admin;

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

public class UpdateAcountantController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField accNameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws SQLException, IOException {
        if (idField.getText().equals("") && passwordField.getText().equals("")) {
            return;
        }

        int accId = Integer.parseInt(idField.getText());
        String accName = accNameField.getText();
        String password = passwordField.getText();

        AccountsOfficer acc = new AccountsOfficer(accId, accName, password);

        DbAction2 db = new DbAction2();
        db.updateAcc(acc);

        idField.clear();
        accNameField.clear();
        passwordField.clear();

        Parent pane = FXMLLoader.load(getClass().getResource("AccountOfficerInfo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Accounts Officer Information");
        stage.setScene(new Scene(pane));
        stage.show();

        AdminPageController.accStage.close();
        AccountOfficerInfoController.updateStage.close();
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        idField.clear();
        accNameField.clear();
        passwordField.clear();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        AccountOfficerInfoController.updateStage.close();
    }

}
