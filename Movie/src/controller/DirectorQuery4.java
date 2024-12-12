package controller;

import models.Cast;
import models.LoadData;

public class DirectorQuery4 extends MovieQuery4 {
    // Constructor to initialize DirectorQuery4 with the LoadData instance
    public DirectorQuery4(LoadData movieDataBase) {
        super(movieDataBase);
    }

    // Implementation of the abstract method to get the director's details from a Cast object
    @Override
    public String getDetail(Cast cast) {
        return "Director: " + cast.getDirector();
    }
}
