/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.testing;

import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.interfaces.IMediaList;
import cinephiles.data.interfaces.IUserList;
import cinephiles.data.model.Media;
import cinephiles.data.model.Review;
import cinephiles.data.model.User;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import cinephiles.data.model.User;
//import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author charliearlie
 */
public class CinephilesModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DAOFactory factory = new DAOFactoryDatabase();
        IUserList userList = factory.getUserList();
        IMediaList mediaList = factory.getMediaList();

        ArrayList<Media> hotMovies;
        ArrayList<Media> hotSeries;
        ArrayList<Media> genreTest;

        
//        User charlie = userList.getUserByEmail("cw5790@gmail.com");
//        charlie.setPassword("password1");
//        userList.updateUser(charlie);
//        
//        System.out.println("Charlie updated");
        hotMovies = mediaList.getHottestMovies();
//        hotSeries = mediaList.getHottestSeries();

        for (Media hotMovie : hotMovies) {
            System.out.println(hotMovie.getTitle());
        }
//
//        for (Media hotSerie : hotSeries) {
//            System.out.println(hotSerie.getTitle());
//        }

//        String genre = "drama";
//        genreTest = mediaList.getHottestByGenre(genre);
//        System.out.println("...................");
//        System.out.println("...................");
//        System.out.println("Hottest movies in the " + genre + " genre");
//        for (Media genreTest1 : genreTest) {
//            System.out.println(genreTest1.getTitle());
//        }
//
        Media inception = mediaList.getMediaByTitle("Inception");

        System.out.println(inception.getTitle());

        Media tlou = mediaList.getMediaById("tt2140553");

        System.out.println(tlou.getTitle());

        //Now testing search
        String searchQuery = "dark knight";
        System.out.println("..............");
        
        ArrayList<Media> shows = mediaList.getHottestSeries();
        
        shows.stream().forEach((show) -> {
            System.out.println(show.getTitle());
        });
        
        ArrayList<Media> recommended = mediaList.getRecommended();
        
        for (Media recommended1 : recommended) {
            System.out.println(recommended1.getTitle());
        }
        
        User lorenzo = userList.getUserById(2);
        
        Media shining = mediaList.getMediaById("tt2096673");
        
        ArrayList<Media> lorenzoWatch = mediaList.getWatchlist(lorenzo.getId());
        
        String id = shining.getImdbId().replace("t", "");
        
        System.out.println(id);
        
        System.out.println("Lorenzo's Watchlist............");
        for (int i = 0; i < lorenzoWatch.size(); i++) {
            if ("tt2096673".equals(lorenzoWatch.get(i).getImdbId())) {
                System.out.println("We found Inside Out in Lorenzo's watchlist");
            }
        }
        
        Media tfa = mediaList.getMediaById("tt2488496");
        ArrayList<Review> tfaReviews = mediaList.getMediaReviews(tfa.getId());
        
        tfaReviews.stream().forEach((review) -> {
            User reviewer = userList.getUserById(review.getUserId());
            System.out.println(review.getReviewText() + "\n Written by:" + reviewer.getFullName());
        });

        Review tfaBanter = new Review("I was waiting for this for a decade. I almost killed myself afterwards from excitement", 
                2488496, 3);
        
        mediaList.reviewMedia(tfaBanter);
        
        tfaReviews = mediaList.getMediaReviews(tfa.getId());
        
        tfaReviews.stream().forEach((review) -> {
            User reviewer = userList.getUserById(review.getUserId());
            System.out.println(review.getReviewText() + "\n Written by:" + reviewer.getFullName());
        });
        
        
//        ArrayList<Media> netflix = mediaList.getMediaOnNetflix();
//
//        for (Media netflix1 : netflix) {
//            System.out.println(netflix1.getTitle());
//        }
//        
//        ArrayList<Media> netflix = mediaList.getMediaOnNetflix();
//        for (Media netflix1 : netflix) {
//            System.out.println(netflix1.getTitle() + " ---- URL: " + netflix1.getNetflix());
//        }
//        try {
//            Gson gson = new Gson();
//            URL url = new URL("https://cinephiles90.herokuapp.com/api/media/hotmovies");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP errorcode: " + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//            String input = readAll(br);
//            System.out.println(input);
//            Media[] media = gson.fromJson(input, Media[].class);
////
//            for (int i = 0; i < media.length; i++) {
//                System.out.println(media[i].getTitle());
//            }
//
//            conn.disconnect();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        Media mediaToDelete = mediaList.getMediaById("tt000000");
//        
//        if (mediaList.deleteMedia(mediaToDelete)) {
//            System.out.println("That show or movie was deleted from the database");
//        }
//       User lorenzo = userList.getUserById(2);
//       
//       System.out.println(lorenzo.getFullName() + " - email:" + lorenzo.getEmailAddress());
//       
//       User charlie = userList.getUserByEmail("cw5790@gmail.com");
//       
//       System.out.println(charlie.getFullName());
//       
//       System.out.println("Printing all users");
//
//       for (int i = 0; i < users.size(); i++) {
//           System.out.println(users.get(i).getFullName());
//       }
//       
//       System.out.println("One user will now be deleted completely...");
//       
//       User lastUser = userList.getUserById(users.size());
//       userList.deleteUser(lastUser);
//       users = userList.getAllUsers();
//       
//       for (int i = 0; i < users.size(); i++) {
//           System.out.println(users.get(i).getFullName());
//       }
//       for(int i = 0; i < users.size(); i++) {
//           System.out.println(users.get(i).getFullName());
//       }
//       
//        String[] forenames = {"Isabella", "Katie", "Miranda", "Amanda", "Angelina", "Angelica", "Gemma", "Jemma",
//            "Amelia", "Olivia", "Kate", "Chloe", "Jennifer", "Monica", "Rachael", "Phoebe", "Joey", "Chandler",
//            "Ross", "Charlie", "Charles", "Jodie", "Scott", "William", "Ralph", "Lorenzo", "Emma", "Courtney", "Julia", "Dave",
//            "Tony", "Antonia", "Belle", "Bella", "Lauren", "Louise", "Martha", "Ruby", "Paul", "Susan", "James", 
//            "Billy", "Stephen", "Tom", "Glenn", "Laura", "Luke", "Bob", "Francis", "Bacon", "Lee", "Joseph", "Gary",
//            "Richard", "Rick", "Jimmy", "Skye", "Casey", "Ryan", "Bret", "Sebastien", "Elliot", "Samuel" };
//
//        String[] surnames = {"Smith", "Jones", "Davis", "Hart", "Bellerin", "Terry", "Cahill", "Cole", "Wilshere",
//        "Carrick", "Delph", "Barkley", "Sterling", "Kane", "Shaw", "Baines", "Shelvey", "Walcott", "Chamberlain",
//        "Churchill", "Black", "White", "Rooney", "Gibbs", "Rodwell", "Lingard", "Lambert", "Ramsey", "Sanchez", "Heath", 
//                "Ruddy", "Butland", "Wright", "Dixon", "Pinkman", "Newell", "Messi", "Ferdinand", "Adams", "Bould"};
//        
//       for (int i = 11; i < 1001; i++) {
//        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","y","z" };
//        Random rnd = new Random();
//        String email = alphabet[rnd.nextInt(25)] + alphabet[rnd.nextInt(25)] + alphabet[rnd.nextInt(25)] + alphabet[rnd.nextInt(25)] +
//                alphabet[rnd.nextInt(25)] + alphabet[rnd.nextInt(25)] + alphabet[rnd.nextInt(25)] + 
//                alphabet[rnd.nextInt(25)] + "@gmail.com";
//        String forename = forenames[rnd.nextInt(forenames.length)];
//        String surname = surnames[rnd.nextInt(surnames.length)];
//        int randomMonth = rnd.nextInt((12 - 1) + 1);
//        int randomDay = rnd.nextInt((30 - 1) + 1);
//        int year = rnd.nextInt((1997 - 1920) + 1) + 1920;
//        Calendar cal = Calendar.getInstance();
//        System.out.println(year);
//
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.MONTH, randomMonth);
//        cal.set(Calendar.DAY_OF_MONTH, randomDay);
//        Date dob = cal.getTime();
//        Date join = new Date();
//        User dave = new User(i, forename, surname, "password1", dob, email, join, "Y");
//        dave.setPassword("password123");
//        userList.createUser(dave);
//       }
        
        
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
