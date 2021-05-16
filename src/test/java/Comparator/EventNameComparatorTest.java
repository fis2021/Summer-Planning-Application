package Comparators;

import Model.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventNameComparatorTest {

    @Test
    void compare() {
        assertAll(
                () -> assertEquals(0, new EventPriceComparator().compare(new Event("B", "", "", 1.0, ""),new Event("B", "", "", 1.0, ""))),
                () -> assertEquals(1, new EventPriceComparator().compare(new Event("B", "", "", 1.0, ""),new Event("A", "", "", 0.0, ""))),
                () -> assertEquals(-1, new EventPriceComparator().compare(new Event("B", "", "", 1.0, ""),new Event("C", "", "", 2.0, "")))
        );
    }
}