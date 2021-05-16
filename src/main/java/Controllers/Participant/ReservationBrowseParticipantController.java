package Controllers.Participant;

import Exceptions.EmptyDataBaseException;
import Model.Event;
import Model.Reservation;
import Services.EventService;
import Services.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import javax.naming.spi.ResolveResult;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationBrowseParticipantController implements Initializable {

    @FXML
    private ListView<Reservation> reservationList;

    @FXML
    private VBox vbox;

    private ObservableList<Reservation> reservations;

    public ReservationBrowseParticipantController() {
        reservations = FXCollections.observableArrayList();
        try {
            ReservationService.getParticipantReservations(reservations);
        }
        catch (EmptyDataBaseException e){
            System.out.println("oda");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationList.setItems(reservations);
        reservationList.setFixedCellSize(110);
        reservationList.setCellFactory(reservationListView -> new ReservationListViewCellParticipant());
    }
}
