package Tester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Queries;
import models.LoadData;

import java.util.*;

public class AllTests {
    private LoadData movieDataBase;
    private Queries queries;

    @BeforeEach
    void setUp() {
        movieDataBase = new LoadData();
        queries = new Queries(movieDataBase);
    }

    @Test
    void testGetTotalEarningsInYear() {
        int year = 1993;
        long expectedEarnings = 963772507L; 
        assertEquals(expectedEarnings, queries.getTotalEarningsInYear(year), "test");
    }
    
    @Test
    void testGetSortedDirectorsString() {
        HashMap<String, Integer> directors = new HashMap<>();
        directors.put("Steven Spielberg", 3);
        directors.put("Quentin Tarantino", 2);
        
        ArrayList<Map.Entry<String, Integer>> sortedDirectors = queries.sortDirectors(directors);
        String expectedString = "Directors:\nQuentin Tarantino: 2\nSteven Spielberg: 3\n";
        
        assertEquals(expectedString, queries.getSortedDirectorsString(sortedDirectors, "Directors:"), "test");
    }
    
    @Test
    void testGetTopDirectors() {
        int topNumber = 1;
        ArrayList<Map.Entry<String, Integer>> topDirectors = queries.getTopDirectors(topNumber);
        
        assertEquals(1, topDirectors.size());
        assertEquals("Steven Spielberg", topDirectors.get(0).getKey());
        assertEquals(13, topDirectors.get(0).getValue());
    }
    
//    @Test
//    void testGetHighestRatedInYear() {
//        int year = 1994;
//        String expectedResult = "Highest rated movie in 1994: The Shawshank Redemption with a rating of 9.2";
//        assertEquals(expectedResult, queries.getHighestRatedInYear(year), "test");
//    }
}
