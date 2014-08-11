package Blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Blog")
public class Blog {

    private UUID id;
    private String title;
    private String author;
    private Date createdOn;
    private String post;

    public Blog() {}

    public Blog(UUID id, String title, String author, Date createdOn, String post) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdOn = createdOn;
        this.post = post;
    }

    @Id
    @Column(name="Id",columnDefinition="uniqueidentifier")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name="Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="Author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name="CreatedOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="Post",columnDefinition="varchar(max)")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Blog [Id=" + id + ", Title=" + title + ", Author=" + author + ", CreatedOn=" + createdOn + ", Post=" + post + "]";
    }
}
