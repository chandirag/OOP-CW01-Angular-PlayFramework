package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballClubTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCompareTo() {
        // Test method to test the compareTo method to test the comparison of two FootballClub objects

        // When club points are not equal
        FootballClub club1 = new FootballClub("Club1", "Location1", new Date(2020, 1, 1),
                "HeadCoach1", 0, 5, 0, 0, 0, 5, 10);
        FootballClub club2 = new FootballClub("Club2", "Location2", new Date(2020, 1, 1),
                "HeadCoach1", 0, 3, 0, 0, 0, 4, 6);

        assertEquals(2, club1.compareTo(club2));


        // When club points are equal (comparing goal difference of both clubs)
        FootballClub club3 = new FootballClub("Club1", "Location1", new Date(2020, 1, 1),
                "HeadCoach1", 0, 1, 0, 0, 0, 3, 5);
        FootballClub club4 = new FootballClub("Club2", "Location2", new Date(2020, 1, 1),
                "HeadCoach1", 0, 1, 0, 0, 0, 5, 3);

        assertEquals(4, club3.compareTo(club4));
    }
}