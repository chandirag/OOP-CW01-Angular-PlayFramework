package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testSetYear() {
        // Test method to check whether an IllegalArgumentException is thrown when entering
        // a negative integer as the year
        Date date = new Date();
        Assertions.assertThrows(IllegalArgumentException.class, () -> date.setYear(-1000));
    }

    @Test
    void testSetMonth() {
        // Test method to check whether an IllegalArgumentException is thrown when entering
        // a integer outside of the range 1-12 as the month
        Date date1 = new Date();
        Date date2 = new Date();
        Assertions.assertThrows(IllegalArgumentException.class, () -> date1.setMonth(13));
        Assertions.assertThrows(IllegalArgumentException.class, () -> date2.setMonth(0));
    }

    @Test
    void testSetDay() {
        // Test method to check whether an IllegalArgumentException is thrown when entering
        // 29 as the day for the month of February for a year that is not a leap year.
        Date date = new Date();
        date.setYear(2019);
        date.setMonth(2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> date.setDay(29));
    }

    @Test
    void testCompareTo() {
        // Test method to test the compareTo method to test the comparison of
        // two date objects
        Date date1 = new Date(2020, 4, 13);
        Date date2 = new Date(2020, 4,  30);

        Date date3 = new Date(2020, 2, 13);
        Date date4 = new Date(2020, 6,  30);

        assertEquals(17, date2.compareTo(date1));
        assertEquals(4, date4.compareTo(date3));
    }
}