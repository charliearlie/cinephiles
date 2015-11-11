/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package connecttodatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charliearlie
 */
public class MovieList {
    
    private final String SQL_GET_SOME_MOVIES = "SELECT * FROM OMDb WHERE imdbRating >= 9 AND type = 'series' ORDER BY imdbVotes DESC";
    
    private Connection connection;

    public MovieList(Connection connection) {
        this.connection = connection;
    }

    MovieList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<String> getSomeMovies() {
        ArrayList<String> movieList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_SOME_MOVIES);

            while (resultSet.next()) {
                String id = resultSet.getString("imdbId");
                String title = resultSet.getString("title");

                movieList.add(title);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movieList;
    }
}


