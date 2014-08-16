package DAO;

import Blog.Blog;
import Blog.Comment;
import com.mongodb.*;
import org.bson.types.ObjectId;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Default
public class MongoBlogDAO implements BlogDAO {

    @Inject
    private MongoUtil mongoUtil;

    public ObjectId save(Blog blog) throws UnknownHostException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        ObjectId id = ObjectId.get();
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

    public Blog get(ObjectId id) throws UnknownHostException {
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

    public ObjectId saveComment(ObjectId blogId, Comment comment) throws UnknownHostException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        DBObject query = new BasicDBObject().append("_id", blogId);
        BasicDBObject blog = (BasicDBObject) collection.findOne(query);
        ObjectId id = new ObjectId();
        BasicDBObject dbComment = new BasicDBObject()
                .append("_id", id)
                .append("author", comment.getAuthor())
                .append("post", comment.getPost())
                .append("createdOn", new Date());
        BasicDBList commentList = new BasicDBList();
        commentList.add(dbComment);
        blog.append("comments", commentList);
        collection.update(query, blog);
        return id;
    }

    public void delete(ObjectId id) throws UnknownHostException, NullDocumentException {
        DBCollection collection = mongoUtil.getBlogsCollection();
        DBObject query = new BasicDBObject().append("_id", id);
        DBObject dbObject = collection.findOne(query);
        if (dbObject == null) { throw new NullDocumentException("Delete failed, document does not exist"); }
        collection.remove(dbObject);
        mongoUtil.close();
    }

    private Blog createBlogFromDB(DBObject dbObject) {
        ObjectId id = (ObjectId) dbObject.get("_id");
        String title = (String) dbObject.get("title");
        String author = (String) dbObject.get("author");
        Date createdOn = (Date) dbObject.get("createdOn");
        String post = (String) dbObject.get("post");
        BasicDBList commentsList = (BasicDBList) dbObject.get("comments");
        if (commentsList != null && commentsList.size() > 0) {
            List<Comment> comments = new ArrayList<Comment>();
            for (Object comment : commentsList) {
                BasicDBObject com = (BasicDBObject) comment;
                ObjectId comId = (ObjectId) com.get("_id");
                String comAuthor = (String) com.get("author");
                String comPost = (String) com.get("post");
                Date comCreatedOn = (Date) com.get("createdOn");
                Comment blogComment = new Comment(comId, comAuthor, comPost, comCreatedOn);
                comments.add(blogComment);
            }
            return new Blog(id, title, author, createdOn, post, comments);
        }
        return new Blog(id, title, author, createdOn, post);
    }

    public void setMongoUtil(MongoUtil mongoUtil) {
        this.mongoUtil = mongoUtil;
    }
}
