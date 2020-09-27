package tutionfeemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMethod extends Application {

    public static Stage frontStage;

    @Override
    public void start(Stage stage) throws Exception {

        this.frontStage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("FrontPage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Automation");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
