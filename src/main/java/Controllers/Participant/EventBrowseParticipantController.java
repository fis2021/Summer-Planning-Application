package Controllers.Participant;

import Comparators.EventDateComparator;
import Comparators.EventNameComparator;
import Comparators.EventPriceComparator;
import Controllers.EventListViewCell;
import Exceptions.EmptyDataBaseException;
import Model.Event;
import Services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;

public class EventBrowseParticipantController implements Initializable {
    @FXML
    private ListView<Event> eventList;

    @FXML
    private VBox vBox;

    @FXML
    private ChoiceBox choiceBox;

    private ObservableList<Event> events;

    public EventBrowseParticipantController(){
        events = FXCollections.observableArrayList();
        try {
            EventService.getAllEvents(events);
        }
        catch (EmptyDataBaseException e){
            events.add(new Event("Empty","Empty","/Images/404.png",0,"Empty"));
        }
    }

    @FXML
    private void handleMouseClick(){

        EventDetailViewController eDVController = new EventDetailViewController();
        eDVController.showEvent(eventList.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void sort(){
        Comparator<Event> comparator;
        switch (String.valueOf(choiceBox.getValue())){
            case "Name":
                comparator = new EventNameComparator();
                break;
            case "Date":
                comparator = new EventDateComparator();
                break;
            case "Price":
                comparator = new EventPriceComparator();
                break;
            default:
                comparator = new EventNameComparator();
                break;
        }
        FXCollections.sort(events, comparator);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        choiceBox.getItems().addAll("Name", "Date", "Price");
        eventList.setItems(events);
        eventList.setFixedCellSize(230);
        eventList.setCellFactory(eventListView -> new EventListViewCell());
    }
}
