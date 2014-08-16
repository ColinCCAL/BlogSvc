package DAO;

import Blog.Blog;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.List;

public interface BlogDAO {

    ObjectId save(Blog blogEntry) throws UnknownHostException;

    Blog get(ObjectId id) throws UnknownHostException;

    List<Blog> get() throws UnknownHostException;

    void delete(ObjectId id) throws UnknownHostException, NullDocumentException;
}
