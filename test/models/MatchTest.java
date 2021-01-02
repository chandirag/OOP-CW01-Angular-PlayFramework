package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSetTeam1Score() {
        // Test method to check whether an IllegalArgumentException is thrown when entering
        // a negative integer as the team 1 score
        Match match = new Match();
        Assertions.assertThrows(IllegalArgumentException.class, () -> match.setTeam1Score(-3));
    }

    @Test
    void testSetTeam2Score() {
        // Test method to check whether an IllegalArgumentException is thrown when entering
        // a negative integer as the team 2 score
        Match match = new Match();
        Assertions.assertThrows(IllegalArgumentException.class, () -> match.setTeam2Score(-5));
    }

    @Test
    void testCompareTo() {
        Match match1 = new Match();
        Match match2 = new Match();

        match1.setDatePlayed(new Date(2020, 3, 5));
        match2.setDatePlayed(new Date(2020, 3, 10));

        assertEquals(5,  match2.compareTo(match1));
    }
}