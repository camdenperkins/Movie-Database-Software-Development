package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import controller.Queries;

public class LoadData {
    // HashMaps to store different types of movie information
    private HashMap<String, MovieBaseInformation> topRatedMovies;
    private HashMap<String, MovieBaseInformation> grossMovies;
    private HashMap<String, Cast> castMovies;

    // Constructor initializes the HashMaps and loads data from files
    public LoadData() {
        topRatedMovies = new HashMap<>();
        grossMovies = new HashMap<>();
        castMovies = new HashMap<>();
        loadMovies("files/imdb_movies_toprated.txt", "topRated");
        loadMovies("files/imdb_movies_gross.txt", "boxOffice");
        loadMovies("files/imdb_movies_cast.txt", "cast");
    }

    // Method to load movie data from a file based on the type
    private void loadMovies(String fileName, String type) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip empty lines and lines not starting with a digit
                if (line.trim().isEmpty() || !line.matches("\\d+.*")) {
                    continue;
                }

                String[] data = line.split("\t");
                int rank = Integer.parseInt(data[0]);
                String title = data[1];
                int year = Integer.parseInt(data[2]);

                // Handle different types of movie data
                if (type.equals("topRated")) {
                	// If the type is "topRated", parse the rating and store in topRatedMovies HashMap
                    double rating = Double.parseDouble(data[3]);
                    topRatedMovies.put(title, new TopRated(rank, title, year, rating));
                } else if (type.equals("boxOffice")) {
                	// If the type is "boxOffice", parse the box office gross and store in grossMovies HashMap
                    long boxOffice = Long.parseLong(data[3]);
                    grossMovies.put(title, new Gross(rank, title, year, boxOffice));
                } else if (type.equals("cast")) {
                	// If the type is "cast", parse the director and cast, and store in castMovies HashMap
                    String director = data[3];
                    String[] cast = new String[data.length - 4];
                    System.arraycopy(data, 4, cast, 0, cast.length);
                    castMovies.put(title, new Cast(rank, title, year, director, cast));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error parsing " + type + " line: Please check the file format.");
        }
    }

    // Getter methods to retrieve the HashMaps
    public HashMap<String, MovieBaseInformation> getTopRatedMovies() {
        return topRatedMovies;
    }

    public HashMap<String, MovieBaseInformation> getGrossMovies() {
        return grossMovies;
    }

    public HashMap<String, Cast> getCastMovies() {
        return castMovies;
    }
    
    // Method to get an instance of Queries class
    public Queries getQueries() {
        return new Queries(this);
    }
}
