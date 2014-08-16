package Blog;

import org.bson.types.ObjectId;

import java.util.Date;

public class Comment {
    private ObjectId id;
    private String author;
    private String post;
    private Date createdOn;

    public Comment() {}

    public Comment(String author, String post) {
        this(null, author, post, null);
    }

    public Comment(ObjectId id, String author, String post, Date date) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.createdOn = date;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
