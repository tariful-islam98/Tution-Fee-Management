package tutionfeemanagement;

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

public class FrontPageController implements Initializable {

    public static Stage accStage;
    public static Stage adminLoginStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void adminButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/AdminLoginPage.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Admin Login");
        stage.show();
        MainMethod.frontStage.close();

        this.adminLoginStage = stage;
    }

    @FXML
    private void accountsButtonAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/admin/AccLoginPage.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accounts Officer Login");
        stage.show();
        MainMethod.frontStage.close();

        this.accStage = stage;
    }

}
