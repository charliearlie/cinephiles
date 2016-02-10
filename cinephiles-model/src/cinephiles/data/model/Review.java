/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data.model;

/**
 *
 * @author charliearlie
 */
public class Review {

    public Review() {
    }

    public Review(String reviewText, int mediaId, int userId) {
        this.reviewText = reviewText;
        this.mediaId = mediaId;
        this.userId = userId;
    }
    
    
    private String reviewText = "UNKNOWN";
    private int mediaId = -1;
    private int userId = -1;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }
    

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
