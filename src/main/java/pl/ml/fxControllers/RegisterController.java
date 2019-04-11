package pl.ml.fxControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.ml.UserController.UserController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setApplyButton() {
        if (firstNameField.getText().equals("")||lastNameField.getText().equals("")||userNameField.getText().equals("")||passwordField.getText().equals("")){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error!");
            alert.setHeaderText("blank field!");
            alert.show();
        }
        else if (!UserController.checkPasswordPattern(passwordField.getText())){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error!");
            alert.setHeaderText("WRONG PASSWORD! \n" + "Be between 8 and 40 characters long\n" +
                    "Contain at least one digit.\n" +
                    "Contain at least one lower case character.\n" +
                    "Contain at least one upper case character.\n" +
                    "Contain at least on special character from [ @ # $ % ! . ].");
            alert.show();
        }
        else {
            if (UserController.checkIfLoginExists(userNameField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("registry error");
                alert.setHeaderText("login already exist!");
                alert.show();
            } else {
                UserController.registerUser(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText());
                Stage stage = (Stage) applyButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setCancelButton() {
        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }
}
