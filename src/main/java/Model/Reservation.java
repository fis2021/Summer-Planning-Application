package Model;

public class Reservation {
    String reservationID;
    String eventID;
    String participantID;
    String status;


    public Reservation(String eventID, String participantID) {
        this.eventID = eventID;
        this.participantID = participantID;
        this.status = "Pending";
    }
    public String getReservationID() {
        return reservationID;
    }

    public Reservation() {
    }

    public String getEventID() {
        return eventID;
    }

    public String getParticipantID() {
        return participantID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
