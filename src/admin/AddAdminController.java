package admin;

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

public class AddAdminController implements Initializable {

    @FXML
    private TextField adminIdField;
    @FXML
    private TextField adminNameField;
    @FXML
    private TextField adminPasswordField;
    @FXML
    private Label warningLabel;

    static ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException, IOException {
        if (adminIdField.getText().equals("")) {
            return;
        }

        int adminId = Integer.parseInt(adminIdField.getText());
        String adminName = adminNameField.getText();
        String adminPassword = adminPasswordField.getText();

        if (adminNameField.getText().equals("") && adminPasswordField.getText().equals("")) {
            return;
        }

        Admin admin = new Admin(adminId, adminName, adminPassword);

        //database action
        DbAction2 db = new DbAction2();
        db.insertAdmin(admin);

        adminIdField.clear();
        adminNameField.clear();
        adminPasswordField.clear();

        Parent pane = FXMLLoader.load(getClass().getResource("AdminInfo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Admin Information");
        stage.setScene(new Scene(pane));
        stage.show();

        AdminPageController.adminStage.close();
        AdminInfoController.addStage.close();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {

        AdminInfoController.addStage.close();
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        adminIdField.clear();
        adminNameField.clear();
        adminPasswordField.clear();
    }

    @FXML
    private void idFieldAction(KeyEvent event) {
        warningLabel.setText("");
        String id = adminIdField.getText();
        if (id.equals("")) {
            return;
        }

        for (Admin admin : adminList) {
            if (admin.getAdminId() == Integer.parseInt(id)) {
                warningLabel.setText("This Id already exists");
                break;
            }
        }
    }

}
