package Controllers.Organizator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerContainerController {

    @FXML
    private AnchorPane mainPane;


    @FXML
    private void openBrowse(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/EventUI/Organizator/Event_Browse_Organizer.fxml"));
            mainPane.getChildren().addAll(vBox);
        }
        catch (IOException e){}
    }

    @FXML
    private void openCreate(){
        mainPane.getChildren().clear();
        try{
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/EventUI/Organizator/Event_Create_Organizer.fxml"));
            mainPane.getChildren().addAll(anchorPane);
        }
        catch (IOException e) {}
    }

    @FXML
    private void openReservations(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/ReservationUI/Organizator/Reservation_Browse_Organizer.fxml"));
            mainPane.getChildren().addAll(vBox);
        }
        catch (IOException e){}
    }

    @FXML
    private void logOut(){
        mainPane.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/UserUI/Login.fxml"));
            stage.setTitle("hAPPening ");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e){}
    }
}
