package Controllers.Participant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserContainerController {

    @FXML
    private AnchorPane mainPane;

    public void openBrowse(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/EventUI/Event_Browse_User.fxml"));
            mainPane.getChildren().addAll(vBox);
        }
        catch (IOException e){}
    }

    public void openReservations(){

    }
}
