package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tutionfeemanagement.MainMethod;

public class AdminPageController implements Initializable {

    static Stage adminStage;
    static Stage accStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void adminInfoButton(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AdminInfo.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Admin Information");
        stage.show();

        this.adminStage = stage;

    }

    @FXML
    private void accOfInfoButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AccountOfficerInfo.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Accounts Officer Information");
        stage.show();

        this.accStage = stage;
    }

    @FXML
    private void logoutButtonAction(ActionEvent event) throws IOException {

        MainMethod.frontStage.show();
        AdminLoginPageController.adminStage.close();
    }

}
