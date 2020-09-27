package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tutionfeemanagement.DbAction;
import tutionfeemanagement.FrontPageController;
import tutionfeemanagement.MainMethod;

public class AccLoginPageController implements Initializable {

    public static Stage homeStage;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField accIdFeild;
    @FXML
    private PasswordField accPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Accounts Officer Login...
    @FXML
    private void loginAction(ActionEvent event) throws SQLException {
        DbAction db = new DbAction();
        Connection con = db.getConnection();

        String query = "select * from accounts where Acc_Officer_Id = " + Integer.parseInt(accIdFeild.getText()) + " and Acc_Officer_Password = '" + accPasswordField.getText() + "'";

        ResultSet rs = con.createStatement().executeQuery(query);

        if (rs.next()) {
            try {
                // To enter home page..
                Parent pane = FXMLLoader.load(getClass().getResource("/tutionfeemanagement/FXMLDocument.fxml"));

                Scene scn = new Scene(pane);

                Stage stage = new Stage();
                stage.setScene(scn);
                stage.setTitle("Automation");
                stage.show();

                FrontPageController.accStage.close();

                this.homeStage = stage;

            } catch (IOException ex) {
                Logger.getLogger(AccLoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            loginLabel.setText("Invalid User Id or Password!");;
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        FrontPageController.accStage.close();
        MainMethod.frontStage.show();
    }

}
