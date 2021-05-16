package Controllers.Organizator;

import Model.Reservation;
import Services.EventService;
import Services.ReservationService;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ReservationListViewCellOrganizer extends ListCell<Reservation> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label statusLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label participantLabel;

    @FXML
    private ChoiceBox choiceBox;

    private Reservation thisReservation;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Reservation reservation, boolean empty){
        thisReservation = reservation;
        super.updateItem(reservation, empty);

        if (empty || reservation == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/ReservationUI/Organizator/Reservation_Cell_Organizer.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            choiceBox.getItems().addAll("Pending", "Accepted", "Rejected");
            titleLabel.setText(EventService.getEventByID(reservation.getEventID()).getName());
            participantLabel.setText(UserService.getUserByID(reservation.getParticipantID()).getUsername());
            choiceBox.setValue(reservation.getStatus());
            statusLabel.setText(reservation.getStatus());
            switch (reservation.getStatus()){
                case "Pending":
                    statusLabel.setStyle("-fx-background-color: Orange");
                    break;
                case "Accepted":
                    statusLabel.setStyle("-fx-background-color: Green");
                    break;
                case "Rejected":
                    statusLabel.setStyle("-fx-background-color: Red");
                    break;
            }

            setText(null);
            setGraphic(gridPane);
        }
    }

    @FXML
    private void changeStatus(){
        String newStatus = String.valueOf(choiceBox.getValue());
        thisReservation.setStatus(newStatus);
        statusLabel.setText(newStatus);
        switch (newStatus){
            case "Pending":
                statusLabel.setStyle("-fx-background-color: Orange");
                break;
            case "Accepted":
                statusLabel.setStyle("-fx-background-color: Green");
                break;
            case "Rejected":
                statusLabel.setStyle("-fx-background-color: Red");
                break;
        }
        ReservationService.updateReservation(thisReservation);
    }
}
