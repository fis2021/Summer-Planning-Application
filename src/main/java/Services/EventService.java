package Services;

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
    }

    public static void getEvents(ObservableList<Event> events){
        try {
            for (Event event : eventRepository.find()) {
                events.add(event);
            }
        }
        catch (NullPointerException e){ System.out.println("Asd");}
    }

    public static void addEvent(Event event){
        if(maxEID == 0) {
            try {
                for (Event e : eventRepository.find()) {
                    if (Integer.getInteger(e.getEventID()) > maxEID) maxEID = Integer.getInteger(e.getEventID());
                }
            } catch (NullPointerException e) {
                System.out.println("Asd");
            }
        }
        event.setEventID(String.valueOf(++maxEID));
        eventRepository.insert(event);
    }

    public static void updateEvent(Event event){
        eventRepository.remove(eq("eventID",event.getEventID()));
        eventRepository.insert(event);
    }
}
