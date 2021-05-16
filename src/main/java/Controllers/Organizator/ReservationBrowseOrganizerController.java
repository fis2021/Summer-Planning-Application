package Controllers.Organizator;

import Exceptions.EmptyDataBaseException;
import Model.Reservation;
import Services.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationBrowseOrganizerController implements Initializable {

    @FXML
    private ListView<Reservation> reservationList;

    @FXML
    private VBox vbox;

    private ObservableList<Reservation> reservations;

    public ReservationBrowseOrganizerController() {
        reservations = FXCollections.observableArrayList();
        try {
            ReservationService.getOrganizatorReservations(reservations);
        }
        catch (EmptyDataBaseException e){
            System.out.println("oda");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationList.setItems(reservations);
        reservationList.setFixedCellSize(110);
        reservationList.setCellFactory(reservationListView -> new ReservationListViewCellOrganizer());
    }
}
