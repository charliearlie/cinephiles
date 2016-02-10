/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data.interfaces;

import cinephiles.data.model.Media;
import cinephiles.data.model.Review;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author charliearlie
 */
public interface IMediaList {
    
    public ArrayList<Media> getHottestMovies();
    public ArrayList<Media> getHottestSeries();
    public ArrayList<Media> getHottestGames();
    public ArrayList<Media> getHottestByGenre(String genre);
    public Media getMediaByTitle(String title);
    public Media getMediaById(String id);
    public ArrayList<Media> searchMedia(String query);
    public boolean deleteMedia(Media media);
    public Media updateMedia(Media media);
    public ArrayList<Media> getRecommended();
    public ArrayList<Media> getLatest();
    public ArrayList<Media> getMediaInCinema();
    public ArrayList<Media> getMediaOnNetflix();
    public boolean rateMedia(int userId, int mediaId, int ratingValue);
    public ArrayList<Media> getWatchlist(int userId);
    public ArrayList<Media> getRatings(int userId);
    public boolean addToWatchlist(int userId, int mediaId);
    public ArrayList<Review> getMediaReviews(int mediaId);
    public boolean reviewMedia(Review review);
    
}
