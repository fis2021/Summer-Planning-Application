package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import Managers.MainApplicationManager;
import Model.User;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

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
    private void handleLoginAction() {
        if (UserService.checkUserCredentials(textField.getText(), passwordField.getText(), (String) choiceBox.getValue())){
            MainApplicationManager mAM = new MainApplicationManager();
            UserService.setMainUser(UserService.getUser(textField.getText(), passwordField.getText(), (String) choiceBox.getValue()));
            mAM.startMainApplication(UserService.getUser(textField.getText(), passwordField.getText(), (String) choiceBox.getValue()));
            textField.getScene().getWindow().hide();
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect Credentials", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void handleRegisterAction(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/UserUI/Register.fxml"));
            stage.setTitle("Registration");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e){}
    }
}
