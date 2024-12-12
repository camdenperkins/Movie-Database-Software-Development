package models;

// class extends the base movie information and adds double rating based off of the toprated.txt file
public class TopRated extends MovieBaseInformation {
    double rating;

    public TopRated(int rank, String title, int year, double rating) {
        super(rank, title, year);
        this.rating = rating;
    }
    
    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return super.toString() + " Rating: " + rating;
    }
}
