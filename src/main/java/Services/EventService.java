package Services;

import Exceptions.EmptyDataBaseException;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import Model.Event;

import java.util.List;
import java.util.Objects;

import static Services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class EventService {
    private static int maxEID = 0;

    private static ObjectRepository<Event> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("hAPPen-Events.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Event.class);

        maxEID = setMaxEID();
    }

    private static int setMaxEID(){
        int m = 0;
        for(Event event : eventRepository.find()){
            if(event.getEventID() != null)
                if(m < Integer.parseInt(event.getEventID())) m = Integer.parseInt(event.getEventID());
        }
        return m;
    }

    public static int getMaxEID() {
        return maxEID;
    }

    public static void getAllEvents(ObservableList<Event> events) throws EmptyDataBaseException{
        try {
            for (Event event : eventRepository.find()) {
                events.add(event);
            }
        }
        catch (NullPointerException e){
            throw new EmptyDataBaseException("DataBase is empty");
        }
    }

    public static void getOrganizatorEvents(ObservableList<Event> events) throws EmptyDataBaseException{
        try {
            for (Event event : eventRepository.find()) {
                if(Objects.equals(event.getOrganizatorID(),UserService.getMainUser().getUserID())) events.add(event);
            }
        }
        catch (NullPointerException e){
            throw new EmptyDataBaseException("DataBase is empty");
        }
    }

    public static void addEvent(Event event){
        event.setEventID(String.valueOf(++maxEID));
        eventRepository.insert(event);
    }

    public static void updateEvent(Event event){
        eventRepository.remove(eq("eventID",event.getEventID()));
        eventRepository.insert(event);
    }

    public static Event getEventByID(String eventID){
        for(Event event : eventRepository.find()){
            if(Objects.equals(event.getEventID(),eventID)) return event;
        }
        return null;
    }

    public static List<Event> getAllEvents(){return eventRepository.find().toList();}

    public static void deleteEvent(Event event){
        eventRepository.remove(event);
    }
}
