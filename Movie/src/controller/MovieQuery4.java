package controller;

import models.Cast;
import models.LoadData;
import models.MovieBaseInformation;

import java.util.Collection;

public abstract class MovieQuery4 {
    protected LoadData movieDataBase;

    // Constructor
    public MovieQuery4(LoadData movieDataBase) {
        this.movieDataBase = movieDataBase;
    }

    // Abstract method to be implemented by two subclasses
    public abstract String getDetail(Cast cast);

    // Method to get movie details by rank and type (box office or top rated)
    public String getMovieDetailByRank(int rank, String rankType) {
        Collection<MovieBaseInformation> movies;
        
        // Determine the collection of movies based on rankType
        if ("boxOffice".equalsIgnoreCase(rankType)) {
            movies = movieDataBase.getGrossMovies().values();
        } else if ("topRated".equalsIgnoreCase(rankType)) {
            movies = movieDataBase.getTopRatedMovies().values();
        } else {
            return "Invalid rank type provided.";
        }

        // Iterate through the movies to find the one with the given rank
        for (MovieBaseInformation movie : movies) {
            if (movie.getRank() == rank) {
                // Get the cast details for the movie and return it using getDetail method
                Cast cast = movieDataBase.getCastMovies().get(movie.getTitle());
                return getDetail(cast);
            }
        }
        return "No movie found with the given rank.";
    }
}
