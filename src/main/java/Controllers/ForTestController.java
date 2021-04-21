package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ForTestController {

    @FXML
    private void openEventCreate(){
        try {
            Parent root;
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Event_Create.fxml"));
            root = mLLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create");
            stage.setScene(new Scene(root, 1200, 800));
            stage.show();
        }
        catch (IOException e){}
    }

    @FXML
    private void openEventBrowse(){
        try {
            Parent root;
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/EventUI/Event_Browse.fxml"));
            root = mLLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Browse");
            stage.setScene(new Scene(root, 1200, 800));
            stage.show();
        }
        catch (IOException e){}
    }
}
