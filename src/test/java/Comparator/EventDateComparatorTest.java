package Comparators;

import Model.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventDateComparatorTest {

    @Test
    void compare() {
        assertAll(
                () -> assertEquals(0, new EventPriceComparator().compare(new Event("", "", "", 1.0, "25 July 2015"),new Event("", "", "", 1.0, "25 July 2015"))),
                () -> assertEquals(1, new EventPriceComparator().compare(new Event("", "", "", 1.0, "25 July 2015"),new Event("", "", "", 0.0, "24 July 2015"))),
                () -> assertEquals(-1, new EventPriceComparator().compare(new Event("", "", "", 1.0, "25 July 2015"),new Event("", "", "", 2.0, "26 July 2015")))
        );
    }
}