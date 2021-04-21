package Controllers;

import Model.Event;
import Model.EventListViewCell;
import Services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EventBrowseController implements Initializable {

    @FXML
    private ListView<Event> eventList;

    private ObservableList<Event> events;

    public EventBrowseController(){
        events = FXCollections.observableArrayList();

        EventService.getEvents(events);
        /*events.addAll(
                new Event("Festive af", "naon jo",
                        "D:/UNI/Sem2/FIS/FIS_Project/src/main/resources/Images/asd.jpg",14.3, "12/24/38"),
                new Event("Festive af szinre", "naon jobb",
                        "D:/UNI/Sem2/FIS/FIS_Project/src/main/resources/Images/dsa.jpg",14.3, "13/24/38"),
                new Event("Festival", "legjobb",
                        "D:/UNI/Sem2/FIS/FIS_Project/src/main/resources/Images/sda.jpg",14.3, "14/24/38")
        );*/
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        eventList.setItems(events);
        eventList.setFixedCellSize(201);
        eventList.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> eventListView) {
                return new EventListViewCell();
            }
        });
    }
}
