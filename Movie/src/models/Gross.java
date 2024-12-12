package models;

//class extends the base movie information and adds long boxOffice $ based off of the gross.txt file
public class Gross extends MovieBaseInformation {
    long boxOffice;

    public Gross(int rank, String title, int year, long boxOffice) {
        super(rank, title, year);
        this.boxOffice = boxOffice;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    @Override
    public String toString() {
        return super.toString() + " Box Office: $" + boxOffice;
    }
}
