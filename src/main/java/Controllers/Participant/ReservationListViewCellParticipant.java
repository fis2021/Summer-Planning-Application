package Controllers.Participant;

import Model.Reservation;
import Services.EventService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ReservationListViewCellParticipant extends ListCell<Reservation> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label statusLabel;

    @FXML
    private Label titleLabel;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Reservation reservation, boolean empty){
        super.updateItem(reservation, empty);

        if (empty || reservation == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/ReservationUI/Participant/Reservation_Cell_Participant.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            titleLabel.setText(EventService.getEventByID(reservation.getEventID()).getName());
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
}
