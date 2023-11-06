/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author ivanmjq
 */
public class ActionMovie extends Movie {

    private final String genre = "Action", ageAppropriateness = "PG(Some material may be inappropriate for children.)";
    private String movieDirector, mainCast, plotSummary;

    public ActionMovie(String movieID, String movieTitle, String releaseDate, int movieDuration, double rating, String movieDirector, String mainCast, String plotSummary) {
        super(movieID, movieTitle, releaseDate, movieDuration, rating);
        this.movieDirector = movieDirector;
        this.mainCast = mainCast;
        this.plotSummary = plotSummary;
    }

    public String getGenre() {
        return genre;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String getMainCast() {
        return mainCast;
    }

    public String getPlotSummary() {
        return plotSummary;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void setMainCast(String mainCast) {
        this.mainCast = mainCast;
    }

    public void setPlotSummary(String plotSummary) {
        this.plotSummary = plotSummary;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Movie Genre: " + genre + "\n"
                + "Age Appropriateness: " + ageAppropriateness + "\n"
                + "Movie Director: " + movieDirector + "\n"
                + "Main Cast: " + mainCast + "\n"
                + "Plot Summary: " + plotSummary + "\n";
    }

    public String delete() {
        return String.format("%s:%s:%s:%d:%.2f:%s:%s:%s:%s", super.getMovieID(), super.getMovieTitle(), getReleaseDate(), getMovieDuration(), getRating(), genre, movieDirector, mainCast, plotSummary);
    }
}
