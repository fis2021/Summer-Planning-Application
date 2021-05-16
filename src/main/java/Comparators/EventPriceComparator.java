package Comparators;

import Model.Event;

import java.util.Comparator;

public class EventPriceComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
