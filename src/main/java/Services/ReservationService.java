package Services;

import Exceptions.EmptyDataBaseException;
import Model.Event;
import Model.Reservation;
import Model.User;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static Services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ReservationService {
    private static int maxRID = 0;

    private static ObjectRepository<Reservation> reservationRepository;

    public static void initDatabase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Summer-Planning-Application-Rsrv.db").toFile())
                .openOrCreate("test", "test");

        reservationRepository = database.getRepository(Reservation.class);

        maxRID = setMaxRID();
    }

    private static int setMaxRID(){
        int m = 0;
        for(Reservation reservation : reservationRepository.find()){
            if(reservation.getReservationID() != null){
                if(Integer.parseInt(reservation.getReservationID()) > m ) m = Integer.parseInt(reservation.getReservationID());
            }
        }
        return m;
    }

    public static void addReservation(Reservation reservation){
        reservation.setReservationID(String.valueOf(++maxRID));
        reservationRepository.insert(reservation);
    }

    public static void getParticipantReservations(ObservableList<Reservation> reservations) throws EmptyDataBaseException{
        try {
            for(Reservation reservation : reservationRepository.find()){
                if(reservation != null){
                    //System.out.println(reservation.getReservationID() + " " + reservation.getEventID() + " " + reservation.getParticipantID() + " " + UserService.getMainUser().getUserID());
                    if(Objects.equals(reservation.getParticipantID(), UserService.getMainUser().getUserID())){
                        System.out.println("asd");
                        reservations.add(reservation);
                    }
                }
            }
        }
        catch (NullPointerException e){
            throw new EmptyDataBaseException("DataBase is empty");
        }
    }

    public static void getOrganizatorReservations(ObservableList<Reservation> reservations) throws EmptyDataBaseException{
        try {
            for(Reservation reservation : reservationRepository.find()){
                if(reservation != null){
                    if(Objects.equals(EventService.getEventByID(reservation.getEventID()).getOrganizatorID(), UserService.getMainUser().getUserID())){
                        System.out.println("asd");
                        reservations.add(reservation);
                    }
                }
            }
        }
        catch (NullPointerException e){
            throw new EmptyDataBaseException("DataBase is empty");
        }
    }

    public static void updateReservation(Reservation reservation){
        reservationRepository.remove(eq("reservationID",reservation.getReservationID()));
        reservationRepository.insert(reservation);
    }
}
