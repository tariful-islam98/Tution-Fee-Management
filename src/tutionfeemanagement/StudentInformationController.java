package tutionfeemanagement;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentInformationController implements Initializable {

    @FXML
    private BorderPane studentInfoScene;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> studentId;
    @FXML
    private TableColumn<Student, String> studentName;
    @FXML
    private TableColumn<Student, Integer> studentClass;
    @FXML
    private TableColumn<Student, Integer> scholership;
    @FXML
    private TableColumn<Student, Integer> due;
    @FXML
    private TableColumn<Student, Integer> totalAmountCol;
    @FXML
    private TableColumn<Student, Integer> paidAmountCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            AddStudentController.stdList.clear();

            //database actions
            DbAction db = new DbAction();
            AddStudentController.stdList = db.getAllStudents();

            studentId.setCellValueFactory(new PropertyValueFactory<>("id"));
            studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
            studentClass.setCellValueFactory(new PropertyValueFactory<>("stdClass"));
            scholership.setCellValueFactory(new PropertyValueFactory<>("scholership"));
            totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
            paidAmountCol.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
            due.setCellValueFactory(new PropertyValueFactory<>("due"));

            studentTable.setItems(AddStudentController.stdList);
        } catch (SQLException ex) {
            Logger.getLogger(StudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static Stage stage1;
    static Stage stage2;

    @FXML
    private void addInfoButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Add Student Information");
        stage.show();

        this.stage1 = stage;

    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("UpdateInfo.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Update Student Information");
        stage.show();

        this.stage2 = stage;
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException {

        ObservableList<Student> selectedStd = studentTable.getSelectionModel().getSelectedItems();
        if (selectedStd.isEmpty()) {
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

        DbAction db = new DbAction();
        db.deleteStudents(selectedStd);

        AddStudentController.stdList.removeAll(selectedStd);

    }

}
