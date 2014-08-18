package Blog;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

    private ObjectId id;
    private String title;
    private String author;
    private Date createdOn;
    private String post;
    private List<Comment> comments;

    public Blog() {}

    public Blog(ObjectId id, String title, String author, Date createdOn, String post) {
        this(id, title, author, createdOn, post, null);
    }

    public Blog(String title, String author, String post) {
        this(null, title, author, null, post, null);
    }

    public Blog(ObjectId id, String title, String author, Date createdOn, String post, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdOn = createdOn;
        this.post = post;
        this.comments = comments;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            this.comments = new ArrayList<Comment>();
            comments.add(comment);
        } else { comments.add(comment); }
    }

    public List<Comment> getComments() { return comments; }
}
