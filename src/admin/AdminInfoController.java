package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminInfoController implements Initializable {

    @FXML
    private TableView<Admin> adminTable;
    @FXML
    private TableColumn<Admin, Integer> adminIdColl;
    @FXML
    private TableColumn<Admin, String> nameCol;
    @FXML
    private TableColumn<Admin, String> adminPasswordCol;

    static Stage addStage;
    static Stage updateStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            adminTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            AddAdminController.adminList.clear();

            //Database action
            DbAction2 db = new DbAction2();
            AddAdminController.adminList = db.getAdmin();

            adminIdColl.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("adminId"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Admin, String>("adminName"));
            adminPasswordCol.setCellValueFactory(new PropertyValueFactory<Admin, String>("adminPassword"));

            adminTable.setItems(AddAdminController.adminList);

        } catch (SQLException ex) {
            Logger.getLogger(AdminInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addAdminButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AddAdmin.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Add Admin");
        stage.show();

        this.addStage = stage;
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("UpdateAdmin.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Update Admin");
        stage.show();

        this.updateStage = stage;
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException {
        ObservableList<Admin> selectedList = adminTable.getSelectionModel().getSelectedItems();
        if (selectedList.isEmpty()) {
            VBox vbx = new VBox();
            vbx.setMinHeight(250);
            vbx.setSpacing(30);
            vbx.setMinWidth(300);
            vbx.setAlignment(Pos.CENTER);
            Label label = new Label("Entity not Selected!");
            Button btn = new Button("Close");
            vbx.getChildren().addAll(label, btn);

            Stage stg = new Stage();
            stg.setScene(new Scene(vbx));
            stg.setTitle("Warning!");
            stg.show();

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stg.close();
                }
            });
        }

        DbAction2 db = new DbAction2();
        db.deleteAdmin(selectedList);

        AddAdminController.adminList.removeAll(selectedList);

    }

}
