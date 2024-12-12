package controller;

import models.Cast;
import models.LoadData;

public class CastQuery4 extends MovieQuery4 {
    // Constructor to initialize CastQuery4 with the LoadData instance
    public CastQuery4(LoadData movieDataBase) {
        super(movieDataBase);
    }

    // Implementation of the abstract method to get the cast details from a Cast object
    @Override
    public String getDetail(Cast cast) {
        return "Cast: " + String.join(", ", cast.getCast());
    }
}
