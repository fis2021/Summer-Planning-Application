package Services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import Model.Event;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Objects;

import static Services.FileSystemService.getPathToFile;

public class EventService {
    private static ObjectRepository<Event> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Summer-Planning-Application.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Event.class);
    }

    public static void addEvent(String name, String description, String imagePath, double price, String date){
        eventRepository.insert(new Event(name,description, imagePath, price, date));
    }
}
