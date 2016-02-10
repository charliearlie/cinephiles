/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package xyz.cinephiles.api;

import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.model.Media;
import cinephiles.data.model.Review;
import cinephiles.data.model.User;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author charliearlie
 */
@Path("/media")
public class MediaResource {
    
    @Context
    private UriInfo context;

    DAOFactory factory;
    
    public MediaResource() {

        factory = new DAOFactoryDatabase();
    }
    /**
     * Retrieves the three hottest movies for display on the home page
     * @return JSON array of those movies
     */
    @GET
    @Path("/hotmovies/")
    @Produces("application/json")
    public Response getHottestMovies() {

        //Check if the factory exists
        if (factory.getConnectionStatus() == true) {
            List<Media> hottestMovies = factory.getMediaList().getHottestMovies();
            Gson gson = new Gson();
            String json = gson.toJson(hottestMovies);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            //If the factory does not exists it must have 
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    /**
     * Retrieves three series marked as hottest to display on home page
     * @return JSON Array of hot series
     */
    @GET
    @Path("/hotseries/")
    @Produces("application/json")
    public Response getHottestSeries() {
        if(factory.getConnectionStatus()) {
            List<Media> hottestSeries = factory.getMediaList().getHottestSeries();
            Gson gson = new Gson();
            String json = gson.toJson(hottestSeries);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    /**
     * Retrieves three games marked as the 'hottest' for display on home page
     * @return JSON array of hot games
     */
    @GET
    @Path("/hotgames/")
    @Produces("application/json")
    public Response getHottestGames() {
        if(factory.getConnectionStatus()) {
            List<Media> hottestGames = factory.getMediaList().getHottestGames();
            Gson gson = new Gson();
            String json = gson.toJson(hottestGames);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/hotbygenre/{genre}/")
    @Produces("application/json")
    public Response getHottestByGenre(@PathParam("genre") String genre) {
        if(factory.getConnectionStatus()) {
            List<Media> hottestByGenre = factory.getMediaList().getHottestByGenre(genre);
            Gson gson = new Gson();
            String json = gson.toJson(hottestByGenre);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/title/{mediaTitle}/")
    @Produces("application/json")
    public Response getMediaByTitle(@PathParam("mediaTitle") String title) {
        if(factory.getConnectionStatus()) {
            title = title.replace('+', ' ');
            Media media = factory.getMediaList().getMediaByTitle(title);
            Gson gson = new Gson();
            String json = gson.toJson(media);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/id/{mediaId}/")
    @Produces("application/json")
    public Response getMediaById(@PathParam("mediaId") String id) {
        if(factory.getConnectionStatus()) {
            id = id.replace('+', ' ');
            Media media = factory.getMediaList().getMediaById(id);
            Gson gson = new Gson();
            String json = gson.toJson(media);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    
    /**
     * List results from a user's search query
     * Have a problem with spaces in the query right now, Jersey doesn't like them
     * @param query
     * @return JSON Array of the results from the search
     */
    @GET
    @Path("/search/{searchParam}/")
    @Produces("application/json")
    public Response searchMedia(@PathParam("searchParam") String query) {
        if (factory.getConnectionStatus()) {
            query = query.replace('+', ' ');
            List<Media> searchResults = factory.getMediaList().searchMedia(query);
            Gson gson = new Gson();
            String json = gson.toJson(searchResults);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/recommended/")
    @Produces("application/json")
    public Response getRecommended() {
        if(factory.getConnectionStatus()) {
            List<Media> recommended = factory.getMediaList().getRecommended();
            Gson gson = new Gson();
            String json = gson.toJson(recommended);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/netflix/")
    @Produces("application/json")
    public Response getMediaOnNetflix() {
        if (factory.getConnectionStatus()) {
            List<Media> netflix = factory.getMediaList().getMediaOnNetflix();
            Gson gson = new Gson();
            String json = gson.toJson(netflix);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/watchlist/{userId}")
    @Produces("application/json")
    public Response getUserWatchlist(@PathParam("userId") int id){
        if(factory.getConnectionStatus()){
            List<Media> watchlist = factory.getMediaList().getWatchlist(id);
            Gson gson = new Gson();
            String json = gson.toJson(watchlist);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/ratings/{userId}")
    @Produces("application/json")
    public Response getUserRatings(@PathParam("userId") int id){
        if(factory.getConnectionStatus()){
            List<Media> ratings = factory.getMediaList().getRatings(id);
            Gson gson = new Gson();
            String json = gson.toJson(ratings);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/reviews/{mediaId}")
    @Produces("application/json")
    public Response getMediaReviews(@PathParam("mediaId") int id){
        if(factory.getConnectionStatus()){
            List<Review> reviews = factory.getMediaList().getMediaReviews(id);
            Gson gson = new Gson();
            String json = gson.toJson(reviews);
            return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
}
