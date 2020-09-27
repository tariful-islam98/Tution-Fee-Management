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

public class UpdateAdminController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField adminNameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws SQLException, IOException {
        if (idField.getText().equals("") && adminNameField.getText().equals("") && passwordField.getText().equals("")) {
            return;
        }

        int adminId = Integer.parseInt(idField.getText());
        String adminName = adminNameField.getText();
        String password = passwordField.getText();

        Admin admin = new Admin(adminId, adminName, password);

        DbAction2 db = new DbAction2();
        db.updateAdmin(admin);

        idField.clear();
        adminNameField.clear();
        passwordField.clear();

        Parent pane = FXMLLoader.load(getClass().getResource("AdminInfo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Admin Information");
        stage.setScene(new Scene(pane));
        stage.show();

        AdminPageController.adminStage.close();

        AdminInfoController.updateStage.close();
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        idField.clear();
        adminNameField.clear();
        passwordField.clear();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        AdminInfoController.updateStage.close();
    }

}
