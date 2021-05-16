package Comparators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Model.Event;

class EventPriceComparatorTest {

    @Test
    void compare() {
        assertAll(
                () -> assertEquals(0, new EventPriceComparator().compare(new Event("", "", "", 1.0, ""),new Event("", "", "", 1.0, ""))),
                () -> assertEquals(1, new EventPriceComparator().compare(new Event("", "", "", 1.0, ""),new Event("", "", "", 0.0, ""))),
                () -> assertEquals(-1, new EventPriceComparator().compare(new Event("", "", "", 1.0, ""),new Event("", "", "", 2.0, "")))
                );
    }
}