package CarRentalSystem;
public class Feedback {
    private int feedbackNo;
    private Customer customer;
    private int rating;
    private String subjectMatter;
    private String comment;

    public Feedback(int feedbackNo, Customer customer, int rating, String subjectMatter, String comment) {
        this.feedbackNo = feedbackNo;
        this.customer = customer;
        this.rating = rating;
        this.subjectMatter = subjectMatter;
        this.comment = comment;
    }

    public int getFeedbackNo() {
        return feedbackNo;
    }

    public void setFeedbackNo(int feedbackNo) {
        this.feedbackNo = feedbackNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSubjectMatter() {
        return subjectMatter;
    }

    public void setSubjectMatter(String subjectMatter) {
        this.subjectMatter = subjectMatter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
