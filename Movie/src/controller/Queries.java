package controller;

import java.util.*;

import models.Cast;
import models.Gross;
import models.LoadData;
import models.MovieBaseInformation;
import models.TopRated;

public class Queries {
    // Reference to the LoadData instance
    private LoadData movieDataBase;

    // Constructor to initialize the Queries class with the LoadData instance
    public Queries(LoadData movieDataBase) {
        this.movieDataBase = movieDataBase;
    }

    // Prompt 1: Calculate total earnings for movies in a given year
    public long getTotalEarningsInYear(int year) {  
        long totalEarnings = 0;  
        // Iterate through all gross movies
        for (MovieBaseInformation movie : movieDataBase.getGrossMovies().values()) {
            // Check if the movie was released in the given year
            if (movie.getYear() == year) {
                // Add the movie's box office earnings to the total
                totalEarnings += ((Gross) movie).getBoxOffice();
            }
        }
        return totalEarnings;
    }

    // Prompt 2: Count the number of movies directed by each director
    public HashMap<String, Integer> getDirectors() {
        HashMap<String, Integer> directorCounts = new HashMap<>();
        // Iterate through all cast movies
        for (Cast movie : movieDataBase.getCastMovies().values()) {
            String director = movie.getDirector();
            // Update the count for each director
            directorCounts.put(director, directorCounts.getOrDefault(director, 0) + 1);
        }
        return directorCounts;
    } 

    // Prompt 2 Part 2: Sort directors alphabetically by name
    public ArrayList<Map.Entry<String, Integer>> sortDirectors(HashMap<String, Integer> directors) {
        // Convert the HashMap to a List of Map Entries
        ArrayList<Map.Entry<String, Integer>> sortedDirectors = new ArrayList<>(directors.entrySet());
        // Sort the list by director's name (key)
        sortedDirectors.sort(Map.Entry.comparingByKey());
        return sortedDirectors;
    }

    // Prompt 3: Get top directors by the number of movies directed, limited to the inputted amount
    public ArrayList<Map.Entry<String, Integer>> getTopDirectors(int topNumber) {
        HashMap<String, Integer> directorCounts = getDirectors();
        // Convert the HashMap to a List of Map Entries
        List<Map.Entry<String, Integer>> list = new ArrayList<>(directorCounts.entrySet());
        // Sort the list by the number of movies directed (value) in descending order
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        // Return the top directors up to the specified number
        return new ArrayList<>(list.subList(0, Math.min(topNumber, list.size())));
    }

    // Method to get sorted directors as a formatted string, including a message
    public String getSortedDirectorsString(ArrayList<Map.Entry<String, Integer>> sortedDirectors, String message) {
        StringBuilder result = new StringBuilder(message + "\n");
        // Append each director and their movie count to the result string
        sortedDirectors.forEach(entry -> result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));
        return result.toString();
    }

    // Prompt 4
    // Get director details by rank
    public String getDirectorByRank(int rank, String rankType) {
        return new DirectorQuery4(movieDataBase).getMovieDetailByRank(rank, rankType);
    }

    // Get cast details by rank
    public String getCastByRank(int rank, String rankType) {
        return new CastQuery4(movieDataBase).getMovieDetailByRank(rank, rankType);
    }

    // Method to get the highest-rated movie in a given year
    public String getHighestRatedInYear(int year) {
        double highestRating = 0;
        TopRated highestRatedMovie = null;
        // Iterate through all top-rated movies
        for (MovieBaseInformation movie : movieDataBase.getTopRatedMovies().values()) {
            // Check if the movie was released in the given year
            if (movie.getYear() == year) {
                TopRated topRatedMovie = (TopRated) movie;
                // Update the highest rated movie if the current one has a higher rating
                if (topRatedMovie.getRating() > highestRating) {
                    highestRating = topRatedMovie.getRating();
                    highestRatedMovie = topRatedMovie;
                }
            }
        }
        // Return the details of the highest-rated movie or a message if no movies found
        if (highestRatedMovie != null) {
            return "Highest rated movie in " + year + ": " + highestRatedMovie.getTitle() + " with a rating of " + highestRatedMovie.getRating();
        } else {
            return "No movies found for the given year.";
        }
    }
}
