package Controllers.Participant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ParticipantContainerController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private void openBrowse(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/EventUI/Participant/Event_Browse_Participant.fxml"));
            mainPane.getChildren().addAll(vBox);
        }
        catch (IOException e){}
    }

    @FXML
    private void openReservations(){
        mainPane.getChildren().clear();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/ReservationUI/Participant/Reservation_Browse_Participant.fxml"));
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
