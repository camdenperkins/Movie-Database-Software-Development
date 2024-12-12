package models;

//class extends the base movie information and adds string director and string array cast based off of the cast.txt file
public class Cast extends MovieBaseInformation {
    String director;
    String[] cast;

    public Cast(int rank, String title, int year, String director, String[] cast) {
        super(rank, title, year);
        this.director = director;
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }
    
    public String[] getCast() {
        return cast;
    }


    @Override
    public String toString() {
        String result = super.toString() + " Director: " + director + " Cast: ";
        for (int i = 0; i < cast.length; i++) {
            result += cast[i];
            if (i < cast.length - 1) {
                result += ", ";
            }
        }
        return result;
    }
}
