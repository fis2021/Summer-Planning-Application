package Services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import Model.Event;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;

import static Services.FileSystemService.getPathToFile;

public class EventService {
    private static ObjectRepository<Event> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Event.class);
    }

    public static void addEvent(String name, String description, BufferedImage image, double price, LocalDate date){
        eventRepository.insert(new Event(name,description, image, price, date));
    }
}
