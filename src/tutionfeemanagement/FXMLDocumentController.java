package tutionfeemanagement;

import admin.AccLoginPageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane homeStagePane;

    static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("DefaultCenter.fxml"));
            homeStagePane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void infoAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("StudentInformation.fxml"));
        homeStagePane.setCenter(pane);

    }

    @FXML
    private void logOutAction(ActionEvent event) {
        try {

            AccLoginPageController.homeStage.close();
            MainMethod.frontStage.show();

        } catch (Exception ex) {
            Logger.getLogger(AccLoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("DefaultCenter.fxml"));
            homeStagePane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void paymentButtonAction(ActionEvent event) {

        Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("DefaultCenter.fxml"));
            homeStagePane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void searchButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        homeStagePane.setCenter(pane);
    }
}
