package task2;

public class Movie
{
    public static final int CHILDRENS   = 2;
    public static final int REGULAR     = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private MovieType type;

    public Movie (String title, MovieType type) {
        this.title      = title;
        this.type  = type;
    }

    public MovieType getMovieType () {
        return type;
    }

    public String getTitle () {
        return title;
    }

}
