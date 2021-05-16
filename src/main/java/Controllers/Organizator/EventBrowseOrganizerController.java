package Controllers.Organizator;

import Controllers.EventListViewCell;
import Exceptions.EmptyDataBaseException;
import Model.Event;
import Services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EventBrowseOrganizerController implements Initializable {

    @FXML
    private ListView<Event> eventList;

    @FXML
    private VBox vBox;

    private ObservableList<Event> events;

    public EventBrowseOrganizerController(){
        events = FXCollections.observableArrayList();
        try {
            EventService.getOrganizatorEvents(events);
        }
        catch (EmptyDataBaseException e){
            events.add(new Event("Empty","Empty","/Images/404.png",0,"Empty"));
        }
    }

    @FXML
    private void handleMouseClick(){
        EventEditController eEController = new EventEditController();
        eEController.showCurrentEventInfo(eventList.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        eventList.setItems(events);
        eventList.setFixedCellSize(230);
        eventList.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> eventListView) {
                return new EventListViewCell();
            }
        });
    }
}
