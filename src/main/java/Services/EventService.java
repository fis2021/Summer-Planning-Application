package Services;

import Exceptions.EmptyDataBaseException;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import Model.Event;

import java.util.Objects;

import static Services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class EventService {
    private static int maxEID = 0;

    private static ObjectRepository<Event> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Summer-Planning-Application-Events.db").toFile())
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

    public static void getEvents(ObservableList<Event> events) throws EmptyDataBaseException{
        try {
            for (Event event : eventRepository.find()) {
                events.add(event);
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
}
