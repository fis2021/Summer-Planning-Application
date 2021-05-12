package Controllers.Organizator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OrganizatorContainerController {

    @FXML
    private AnchorPane mainPane;


    @FXML
    private void openBrowse(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/EventUI/Event_Browse_Organizator.fxml"));
            mainPane.getChildren().addAll(vBox);
        }
        catch (IOException e){}
    }

    @FXML
    private void openCreate(){
        mainPane.getChildren().clear();
        try{
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/EventUI/Event_Create.fxml"));
            mainPane.getChildren().addAll(anchorPane);
        }
        catch (IOException e) {}
    }
}
