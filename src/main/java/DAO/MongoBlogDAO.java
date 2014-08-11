package DAO;

import Blog.Blog;
import com.mongodb.*;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Default
public class MongoBlogDAO implements BlogDAO {

    @Inject
    private MongoUtil mongoUtil;

    public UUID save(Blog blog) throws UnknownHostException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        UUID id = UUID.randomUUID();
        DBObject doc = new BasicDBObject()
                .append("_id", id)
                .append("title", blog.getTitle())
                .append("author", blog.getAuthor())
                .append("createdOn", new Date())
                .append("post", blog.getPost());
        collection.insert(doc);
        mongoUtil.close();
        return id;
    }

    public Blog get(UUID id) throws UnknownHostException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject dbObject = collection.findOne(query);
        mongoUtil.close();
        return createBlogFromDB(dbObject);
    }



    public List<Blog> get() throws UnknownHostException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        DBCursor cursor = collection.find();
        List<Blog> blogs = new ArrayList<Blog>();
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            blogs.add(createBlogFromDB(dbObject));
        }
        mongoUtil.close();
        return blogs;
    }

    public void delete(UUID id) throws UnknownHostException, NullDocumentException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject dbObject = collection.findOne(query);
        if (dbObject == null) { throw new NullDocumentException("Delete failed, document does not exist"); }
        collection.remove(dbObject);
        mongoUtil.close();
    }

    public void setMongoUtil(MongoUtil mongoUtil) {
        this.mongoUtil = mongoUtil;
    }

    private Blog createBlogFromDB(DBObject dbObject) {
        UUID id = (UUID) dbObject.get("_id");
        String title = (String) dbObject.get("title");
        String author = (String) dbObject.get("author");
        Date createdOn = (Date) dbObject.get("createdOn");
        String post = (String) dbObject.get("post");
        return new Blog(id, title, author, createdOn, post);
    }
}
