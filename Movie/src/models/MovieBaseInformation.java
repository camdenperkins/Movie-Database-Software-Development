package models;


// class provides base data that all movies have
public abstract class MovieBaseInformation {
    int rank;
    String title;
    int year;

    public MovieBaseInformation(int rank, String title, int year) {
        this.rank = rank;
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " " + title + " " + year;
    }
}
