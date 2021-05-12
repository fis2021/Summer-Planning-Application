package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField textField;
    @FXML
    private Label label;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    public void initialize(){
        choiceBox.getItems().addAll("Organizator","Participant");
    }
    @FXML
    public void handleLoginAction() {
        if (UserService.checkUserCredentials(textField.getText(), passwordField.getText(), (String) choiceBox.getValue()))
            label.setText("Succes");
        else label.setText("Failure");
    }
}
