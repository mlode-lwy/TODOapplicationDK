package pl.ml.fxControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Data;
import org.hibernate.Session;
import pl.ml.HibernateUtil;
import pl.ml.TaskController.Tasks;
import pl.ml.UserController.Users;

import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Data
public class TasksSceneController implements Initializable {

    @FXML
    public TableView <Tasks> taskTableView;
    @FXML
    public TableColumn <Tasks,String> taskTableColumn;
    @FXML
    public Button doneButton;
    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem showToDoTasksMenuItem;
    @FXML
    private MenuItem showDoneTasksMenuItem;
    @FXML
    private Label descriptionLabel;

    private Users user;

    private ObservableList<Tasks>  tasksObservableList = FXCollections.observableArrayList();

    Session session;


    public void loadTask() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Tasks> tasksList = session.createQuery(
                "FROM Tasks WHERE USER_ID = " +user.getUser_id())
                .getResultList();
        user.setListOfTasks(tasksList);
        tasksObservableList.addAll(tasksList);

        taskTableView.setItems(tasksObservableList);
        taskTableColumn.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTaskName()));
    }

    public void showTaskDescOnClick(MouseEvent mouseEvent) {
        Tasks task = taskTableView.getSelectionModel().getSelectedItem();
        descriptionLabel.setText(task.getTaskDesc());
    }

    public void setCloseMenuItem() {
        Stage stage = (Stage) doneButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
