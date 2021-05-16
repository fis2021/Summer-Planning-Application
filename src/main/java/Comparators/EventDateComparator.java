package Comparators;

import Model.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class EventDateComparator implements Comparator<Event> {

    @Override
    public int compare(Event a, Event b)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        LocalDate aLocalDateObj = LocalDate.parse(a.getDate(), dateTimeFormatter);
        LocalDate bLocalDateObj = LocalDate.parse(b.getDate(), dateTimeFormatter);

        return aLocalDateObj.compareTo(bLocalDateObj);
    }
}
