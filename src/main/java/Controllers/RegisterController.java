package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class RegisterController {
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
    public void handleRegisterAction(){
        try{
            UserService.addUser(textField.getText(), passwordField.getText(), (String) choiceBox.getValue());
        }catch(UsernameAlreadyExistsException e){
            JOptionPane.showMessageDialog(null, "Username already exists " + UserService.getUser(textField.getText(), passwordField.getText(), (String) choiceBox.getValue()).getUserID(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
