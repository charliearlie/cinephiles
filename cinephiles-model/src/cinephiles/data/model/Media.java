/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data.model;

import java.util.Date;

/**
 *
 * @author charliearlie
 */
public class Media {
    private int id = -1;
    private String imdbId = "UNKNOWN";
    private String title = "UNKNOWN";
    private int year = -1;
    private String ageRating = "UNKNOWN";
    private String runtime = "UNKNOWN";
    private String genre = "UNKNOWN";
    private String released = "UNKNOWN";
    private String director = "UNKNOWN";
    private String writer = "UNKNOWN";
    private String cast = "UNKNOWN";
    private int metacriticRating = -1;
    private double imdbRating = -1;
    private String poster = "UNKNOWN";
    private String plot = "UNKNOWN";
    private String fullPlot = "UNKNOWN";
    private String language = "UNKNOWN";
    private String country = "UNKNOWN";
    private String awards = "UNKNOWN";
    private String type = "UNKNOWN";
    private int hotRating = -1;
    private String netflix = "UNKNOWN";
    
    
    
    public Media(int id, String imdbId, String title, int year, String ageRating,
            String runtime, String genre, String released, String director, String writer, String cast,
            int metacriticRating, double imdbRating, int hotRating, String poster, String plot,
            String fullPlot, String language, String country, String awards, String netflix,
            String type) {
        
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.ageRating = ageRating;
        this.runtime = runtime;
        this.genre = genre;
        this.released = released;
        this.director = director;
        this.writer = writer;
        this.cast = cast;
        this.metacriticRating = metacriticRating;
        this.imdbRating = imdbRating;
        this.hotRating = hotRating;
        this.poster = poster;
        this.plot = plot;
        this.fullPlot = fullPlot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.netflix = netflix;
        this.type = type;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(int metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getFullPlot() {
        return fullPlot;
    }

    public void setFullPlot(String fullPlot) {
        this.fullPlot = fullPlot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotRating() {
        return hotRating;
    }

    public void setHotRating(int hotRating) {
        this.hotRating = hotRating;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getNetflix() {
        return netflix;
    }

    public void setNetflix(String netflix) {
        this.netflix = netflix;
    }
    
    
}
