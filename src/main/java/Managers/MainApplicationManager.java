package Managers;

import Model.User;
import Services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplicationManager {
    public void startMainApplication(User user){
        switch (user.getRole()){
            case "Organizator":
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ContainerWindows/Organizator_Container.fxml"));
                    stage.setTitle("hAPPening ");
                    stage.setScene(new Scene(root, 1440, 800));
                    stage.show();
                }
                catch (IOException e){}
                break;
            case "Participant":
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ContainerWindows/User_Container.fxml"));
                    stage.setTitle("hAPPening ");
                    stage.setScene(new Scene(root, 1440, 800));
                    stage.show();
                }
                catch (IOException e){}
                break;
        }
    }
}
