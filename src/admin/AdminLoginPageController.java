package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tutionfeemanagement.DbAction;
import tutionfeemanagement.FrontPageController;
import tutionfeemanagement.MainMethod;

public class AdminLoginPageController implements Initializable {

    @FXML
    private VBox adminLoginScene;
    @FXML
    private TextField adminIdField;
    @FXML
    private Label warningLabel;
    @FXML
    private PasswordField adminPasswordField;

    static Stage adminStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {

        try {
            DbAction db = new DbAction();
            Connection con = db.getConnection();
            String sql = "select * from admin "
                    + "where admin_id=" + Integer.parseInt(adminIdField.getText()) + " and password='" + adminPasswordField.getText() + "'";
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                Parent pane = FXMLLoader.load(getClass().getResource("/admin/AdminPage.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setTitle("Admin Login");
                stage.show();

                this.adminStage = stage;
                FrontPageController.adminLoginStage.close();
            } else {
                warningLabel.setText("Invalid Id or Password");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        FrontPageController.adminLoginStage.close();
        MainMethod.frontStage.show();

    }
}
