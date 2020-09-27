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

public class AddAccountantController implements Initializable {

    @FXML
    private TextField accIdField;
    @FXML
    private TextField accNameField;
    @FXML
    private TextField accPasswordField;
    @FXML
    private Label warningLabel;

    static ObservableList<AccountsOfficer> accList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addButtonAction(ActionEvent event) throws IOException, SQLException {
        if (accIdField.getText().equals("")) {
            return;
        }

        int accId = Integer.parseInt(accIdField.getText());
        String accName = accNameField.getText();
        String accPassword = accPasswordField.getText();

        if (accNameField.getText().equals("") && accPasswordField.getText().equals("")) {
            return;
        }

        AccountsOfficer acc = new AccountsOfficer(accId, accName, accPassword);

        //database action
        DbAction2 db = new DbAction2();
        db.insertAcc(acc);

        accIdField.clear();
        accNameField.clear();
        accPasswordField.clear();

        Parent pane = FXMLLoader.load(getClass().getResource("AccountOfficerInfo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Accounts Officer Information");
        stage.setScene(new Scene(pane));
        stage.show();

        AdminPageController.accStage.close();
        AccountOfficerInfoController.addStage.close();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {

        AccountOfficerInfoController.addStage.close();
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        accIdField.clear();
        accNameField.clear();
        accPasswordField.clear();
    }

    @FXML
    private void idFieldAction(KeyEvent event) {
        warningLabel.setText("");
        String id = accIdField.getText();
        if (id.equals("")) {
            return;
        }

        for (AccountsOfficer acc : accList) {
            if (acc.getAccountantId() == Integer.parseInt(id)) {
                warningLabel.setText("This Id already exists");
                break;
            }
        }
    }

}
