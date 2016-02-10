/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles;

import cinephiles.data.interfaces.IMediaList;
import cinephiles.data.model.Media;
import cinephiles.data.model.Review;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charliearlie
 */
public class IMediaListDatabase implements IMediaList {

    private final String SQL_GET_HOTTEST_MOVIES = "SELECT * FROM MEDIA "
            + "WHERE HOT_RATING >= 86 AND MEDIA_TYPE = 'movie' "
            + "ORDER BY HOT_RATING desc"
            + " LIMIT 0,3 ";

    private final String SQL_GET_HOTTEST_SERIES = "SELECT * FROM MEDIA "
            + "WHERE HOT_RATING >= 80 AND MEDIA_TYPE = 'series' "
            + "ORDER BY HOT_RATING desc "
            + "LIMIT 0,3 ";

    private final String SQL_GET_HOTTEST_GAMES = "SELECT * FROM MEDIA "
            + "WHERE IMDB_RATING > 8 AND YEAR >= 2014 AND MEDIA_TYPE = 'game' "
            + "LIMIT 0,3";

    private final String SQL_GET_HOTTEST_BY_GENRE = "SELECT * FROM MEDIA "
            + "WHERE HOT_RATING >= 75 AND GENRE LIKE ? "
            + "LIMIT 0,30";

    private final String SQL_GET_MEDIA_BY_TITLE = "SELECT * FROM MEDIA "
            + "WHERE TITLE = ?";

    private final String SQL_GET_MEDIA_BY_ID = "SELECT * FROM MEDIA "
            + "WHERE IMDB_ID = ?";
    
    private final String SQL_SEARCH_MEDIA = " SELECT * FROM MEDIA "
            + "WHERE TITLE LIKE ? "
            + "ORDER BY IMDB_VOTES desc";
    
    private final String SQL_GET_MEDIA_ON_NETFLIX = "SELECT * FROM MEDIA "
            + "WHERE NETFLIX IS NOT NULL";
    
    private final String SQL_DELETE_MEDIA = "DELETE FROM MEDIA WHERE ID = ?";
    
    private final String SQL_GET_USER_RATINGS = "SELECT * FROM MEDIA "
            + "JOIN RATING ON MEDIA.ID = RATING.ID "
            + "JOIN USER ON RATING.USER_ID = USER.USER_ID "
            + "WHERE USER.USER_ID = ?";

    private final String SQL_GET_USER_WATCHLIST = "SELECT * FROM MEDIA "
            + "JOIN WATCHLIST ON MEDIA.ID = WATCHLIST.ID "
            + "JOIN USER ON WATCHLIST.USER_ID = USER.USER_ID "
            + "WHERE USER.USER_ID = ?";

    private final String SQL_RATE_MEDIA = "INSERT INTO RATING " 
            + "(USER_ID, ID, RATING_VALUE)"
            + "VALUES(?, ?, ?)";
    
    private final String SQL_UPDATE_MEDIA = "UPDATE MEDIA "
            + "SET TITLE=?, YEAR=?, AGE_RATING=?, RUNTIME=?, "
            + "GENRE=?, DIRECTOR=?, WRITER=?, CAST=?, METACRITIC=?, "
            + "IMDB_RATING=?, HOT_RATING=?, POSTER=?, "
            + "PLOT=?, FULL_PLOT=?, LANGUAGE=?, COUNTRY=?, AWARDS=?, "
            + "NETFLIX=?, MEDIA_TYPE=? WHERE IMDB_ID =?";

    private final String SQL_ADD_TO_WATCHLIST = "INSERT INTO WATCHLIST "
            + "(USER_ID, ID) VALUES(?, ?)";
    
    private final String SQL_GET_RECOMMENDED = "SELECT * FROM MEDIA "
            + "WHERE IMDB_RATING > 7 AND IMDB_VOTES > 25000 "
            + "AND MEDIA_TYPE = 'movie' AND YEAR > 2000 "
            + "AND GENRE NOT LIKE '%documentary%' "
            + "LIMIT 0, 300";
    
    private final String SQL_GET_MEDIA_REVIEWS = "SELECT REVIEW_TEXT, ID, USER_ID "
            + "FROM REVIEW "
            + "JOIN USER ON REVIEW.USER_ID = USER.USER_ID "
            + "WHERE ID = ?";
    
    private final String SQL_REVIEW_MEDIA = "INSERT INTO REVIEW "
            + "(REVIEW_TEXT, ID, USER_ID) VALUES(?, ?, ?)";
    private Connection connection;

    public IMediaListDatabase(Connection connection) {
        this.connection = connection;
    }

    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Media> getHottestMovies() {
        ArrayList<Media> hottestMovies = new ArrayList<>();
        hottestMovies = listResultsUsingStatement(SQL_GET_HOTTEST_MOVIES);

        return hottestMovies;
    }

    @Override
    public ArrayList<Media> getHottestSeries() {
        ArrayList<Media> hottestSeries = new ArrayList<>();
        hottestSeries = listResultsUsingStatement(SQL_GET_HOTTEST_SERIES);

        return hottestSeries;
    }

    @Override
    public ArrayList<Media> getHottestGames() {
        ArrayList<Media> hottestGames = new ArrayList<>();
        hottestGames = listResultsUsingStatement(SQL_GET_HOTTEST_GAMES);

        return hottestGames;
    }

    @Override
    public ArrayList<Media> getHottestByGenre(String genre) {
        ArrayList<Media> hottestByGenre = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_HOTTEST_BY_GENRE)) {
            preparedStatement.setString(1, "%" + genre + "%");

            hottestByGenre = listResultsUsingPreparedStatement(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex); 
        }

        return hottestByGenre;
    }

    @Override
    public Media getMediaByTitle(String title) {
        Media media = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MEDIA_BY_TITLE)) {
            preparedStatement.setString(1, title);
            
            media = getMediaUsingPreparedStatement(preparedStatement);

        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return media;
    }

    @Override
    public Media getMediaById(String id) {
        Media media = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MEDIA_BY_ID)) {
            preparedStatement.setString(1, id);
            
            media = getMediaUsingPreparedStatement(preparedStatement);

        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return media;
    }

    @Override
    public ArrayList<Media> searchMedia(String query) {
        ArrayList<Media> searchResults = new ArrayList<>();
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_MEDIA)) {
            preparedStatement.setString(1, "%" + query + "%");
            
            searchResults = listResultsUsingPreparedStatement(preparedStatement);
            
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return searchResults;
    }

    /**
     * Delete media from database. Only accessible to administrator
     * @param media
     * @return 
     */
    @Override
    public boolean deleteMedia(Media media) {
        boolean deleted = false;
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_MEDIA)) {
            preparedStatement.setInt(1, media.getId());
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deleted;
    }

    @Override
    public Media updateMedia(Media media) {
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MEDIA)){
            preparedStatement.setString(1, media.getTitle());
            preparedStatement.setInt(2, media.getYear());
            preparedStatement.setString(3, media.getAgeRating());
            preparedStatement.setString(4, media.getRuntime());
            preparedStatement.setString(5, media.getGenre());
            preparedStatement.setString(6, media.getDirector());
            preparedStatement.setString(7, media.getWriter());
            preparedStatement.setString(8, media.getCast());
            preparedStatement.setInt(9, media.getMetacriticRating());
            preparedStatement.setDouble(10, media.getImdbRating());
            preparedStatement.setInt(11, media.getHotRating());
            preparedStatement.setString(12, media.getPoster());
            preparedStatement.setString(13, media.getPlot());
            preparedStatement.setString(14, media.getFullPlot());
            preparedStatement.setString(15, media.getLanguage());
            preparedStatement.setString(16, media.getCountry());
            preparedStatement.setString(17, media.getAwards());
            preparedStatement.setString(18, media.getNetflix());
            preparedStatement.setString(19, media.getType());
            preparedStatement.setString(20, media.getImdbId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return media;
    }

    @Override
    public ArrayList<Media> getRecommended() {
        ArrayList<Media> temp = new ArrayList<>();
        ArrayList<Media> recommended = new ArrayList<>();
        temp = listResultsUsingStatement(SQL_GET_RECOMMENDED);
        Random rnd = new Random();
        int one = rnd.nextInt(temp.size());
        int two = 2;
        int three = 3;
        int four = 4;
        int five = 5;
        int six = 6;
        boolean complete = false;
        while (!complete) {
            two = rnd.nextInt(temp.size());
            three = rnd.nextInt(temp.size());
            four = rnd.nextInt(temp.size());
            five = rnd.nextInt(temp.size());
            six = rnd.nextInt(temp.size());
            if (two != one) {
                if (three != two) {
                    if (four != three) {
                        if (five != four) {
                            if (six != five) {
                                recommended.add(temp.get(one));
                                recommended.add(temp.get(two));
                                recommended.add(temp.get(three));
                                recommended.add(temp.get(four));
                                recommended.add(temp.get(five));
                                recommended.add(temp.get(six));
                                complete = true;
                            }
                        }
                    }
                }
            }
        
        
        }
        

        return recommended;
    }

    @Override
    public ArrayList<Media> getLatest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Media> getMediaInCinema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<Media> getMediaOnNetflix() {
        ArrayList<Media> temp = new ArrayList<>();
        ArrayList<Media> netflixMedia = new ArrayList<>();
        temp = listResultsUsingStatement(SQL_GET_MEDIA_ON_NETFLIX);
        Random rnd = new Random();
        int one = rnd.nextInt(temp.size());
        int two = 2;
        int three = 3;
        boolean complete = false;
        while (!complete) {
            two = rnd.nextInt(temp.size());
            three = rnd.nextInt(temp.size());
            if (two != one) {
                if (three != two) { 
                    netflixMedia.add(temp.get(one));
                    netflixMedia.add(temp.get(two));
                    netflixMedia.add(temp.get(three));
                    complete = true; 

                }
            }
        }
        
        return netflixMedia;
    }
    
    @Override
    public boolean rateMedia(int userId, int mediaId, int ratingValue) {
        //Media media = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_RATE_MEDIA)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mediaId);
            preparedStatement.setInt(3, ratingValue);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
    
    
    @Override
    public boolean addToWatchlist(int userId, int mediaId) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_TO_WATCHLIST)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mediaId);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public ArrayList<Media> getWatchlist(int userId) {
        ArrayList<Media> watchlist = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_WATCHLIST)) {
            preparedStatement.setInt(1, userId);
            
            watchlist = listResultsUsingPreparedStatement(preparedStatement);
            
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return watchlist;
    }

    @Override
    public ArrayList<Media> getRatings(int userId) {
        ArrayList<Media> ratings = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_RATINGS)) {
            preparedStatement.setInt(1, userId);
            
            ratings = listResultsUsingPreparedStatement(preparedStatement);
            
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ratings;
    }
    
    @Override
    public ArrayList<Review> getMediaReviews(int mediaId) {
        ArrayList<Review> reviews = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MEDIA_REVIEWS)) {
            preparedStatement.setInt(1, mediaId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String reviewText = resultSet.getString("REVIEW_TEXT");
                int userId = resultSet.getInt("USER_ID");
                
                Review review = new Review(reviewText, mediaId, userId);
                
                reviews.add(review);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reviews;
    }

    

    @Override
    public boolean reviewMedia(Review review) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REVIEW_MEDIA)) {
            preparedStatement.setString(1, review.getReviewText());
            preparedStatement.setInt(2, review.getMediaId());
            preparedStatement.setInt(3, review.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    private ArrayList<Media> listResultsUsingPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        ArrayList<Media> results = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String imdbId = resultSet.getString("IMDB_ID");
            String title = resultSet.getString("TITLE");
            int year = resultSet.getInt("YEAR");
            String ageRating = resultSet.getString("AGE_RATING");
            String runtime = resultSet.getString("RUNTIME");
            String genre = resultSet.getString("GENRE");
            String released = resultSet.getString("RELEASED");
            String director = resultSet.getString("DIRECTOR");
            String writer = resultSet.getString("WRITER");
            String cast = resultSet.getString("CAST");
            int metacriticRating = resultSet.getInt("METACRITIC");
            double imdbRating = resultSet.getDouble("IMDB_RATING");
            int hotRating = resultSet.getInt("HOT_RATING");
            String poster = resultSet.getString("POSTER");
            String plot = resultSet.getString("PLOT");
            String fullPlot = resultSet.getString("FULL_PLOT");
            String language = resultSet.getString("LANGUAGE");
            String country = resultSet.getString("COUNTRY");
            String awards = resultSet.getString("AWARDS");
            String netflix = resultSet.getString("NETFLIX");
            String type = resultSet.getString("MEDIA_TYPE");

            Media media = new Media(id, imdbId, title, year, ageRating, runtime,
                    genre, released, director, writer, cast, metacriticRating, imdbRating,
                    hotRating, poster, plot, fullPlot, language, country, awards, netflix, type);

            results.add(media);

        }

        return results;

    }

    private ArrayList<Media> listResultsUsingStatement(String query) {
        ArrayList<Media> results = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String imdbId = resultSet.getString("IMDB_ID");
                String title = resultSet.getString("TITLE");
                int year = resultSet.getInt("YEAR");
                String ageRating = resultSet.getString("AGE_RATING");
                String runtime = resultSet.getString("RUNTIME");
                String genre = resultSet.getString("GENRE");
                String released = resultSet.getString("RELEASED");
                String director = resultSet.getString("DIRECTOR");
                String writer = resultSet.getString("WRITER");
                String cast = resultSet.getString("CAST");
                int metacriticRating = resultSet.getInt("METACRITIC");
                double imdbRating = resultSet.getDouble("IMDB_RATING");
                int hotRating = resultSet.getInt("HOT_RATING");
                String poster = resultSet.getString("POSTER");
                String plot = resultSet.getString("PLOT");
                String fullPlot = resultSet.getString("FULL_PLOT");
                String language = resultSet.getString("LANGUAGE");
                String country = resultSet.getString("COUNTRY");
                String awards = resultSet.getString("AWARDS");
                String netflix = resultSet.getString("NETFLIX");
                String type = resultSet.getString("MEDIA_TYPE");

                Media media = new Media(id, imdbId, title, year, ageRating, runtime,
                        genre, released, director, writer, cast, metacriticRating, imdbRating,
                        hotRating, poster, plot, fullPlot, language, country, awards, netflix, type);

                results.add(media);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IMediaListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;

    }

    private Media getMediaUsingPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        Media media = null;

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String imdbId = resultSet.getString("IMDB_ID");
            String title = resultSet.getString("TITLE");
            int year = resultSet.getInt("YEAR");
            String ageRating = resultSet.getString("AGE_RATING");
            String runtime = resultSet.getString("RUNTIME");
            String genre = resultSet.getString("GENRE");
            String released = resultSet.getString("RELEASED");
            String director = resultSet.getString("DIRECTOR");
            String writer = resultSet.getString("WRITER");
            String cast = resultSet.getString("CAST");
            int metacriticRating = resultSet.getInt("METACRITIC");
            double imdbRating = resultSet.getDouble("IMDB_RATING");
            int hotRating = resultSet.getInt("HOT_RATING");
            String poster = resultSet.getString("POSTER");
            String plot = resultSet.getString("PLOT");
            String fullPlot = resultSet.getString("FULL_PLOT");
            String language = resultSet.getString("LANGUAGE");
            String country = resultSet.getString("COUNTRY");
            String awards = resultSet.getString("AWARDS");
            String netflix = resultSet.getString("NETFLIX");
            String type = resultSet.getString("MEDIA_TYPE");

            media = new Media(id, imdbId, title, year, ageRating, runtime,
                    genre, released, director, writer, cast, metacriticRating, imdbRating,
                    hotRating, poster, plot, fullPlot, language, country, awards, netflix, type);

        }
        
        return media;
    }

    

    
}
