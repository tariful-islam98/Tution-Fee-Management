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

public class AccountOfficerInfoController implements Initializable {

    @FXML
    private TableView<AccountsOfficer> accOficerTable;
    @FXML
    private TableColumn<AccountsOfficer, Integer> accOfficerIdCol;
    @FXML
    private TableColumn<AccountsOfficer, String> nameCol;
    @FXML
    private TableColumn<AccountsOfficer, String> accOfPasswordCol;

    static Stage addStage;
    static Stage updateStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            accOficerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            AddAccountantController.accList.clear();

            //Database action
            DbAction2 db = new DbAction2();
            AddAccountantController.accList = db.getAccountsOfficer();

            accOfficerIdCol.setCellValueFactory(new PropertyValueFactory<AccountsOfficer, Integer>("accountantId"));
            accOfPasswordCol.setCellValueFactory(new PropertyValueFactory<AccountsOfficer, String>("accountantPassword"));
            nameCol.setCellValueFactory(new PropertyValueFactory<AccountsOfficer, String>("accountantName"));

            accOficerTable.setItems(AddAccountantController.accList);

        } catch (SQLException ex) {
            Logger.getLogger(AdminInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addOfficerButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AddAccountant.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Add Accounts Offficer");
        stage.show();

        this.addStage = stage;
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("UpdateAcountant.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Update Accounts Officer Information");
        stage.show();

        this.updateStage = stage;
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException {
        ObservableList<AccountsOfficer> selectedList = accOficerTable.getSelectionModel().getSelectedItems();
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
        db.deleteAcc(selectedList);

        AddAccountantController.accList.removeAll(selectedList);
    }

}
