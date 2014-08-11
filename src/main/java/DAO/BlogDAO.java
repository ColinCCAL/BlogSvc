package DAO;

import Blog.Blog;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

public interface BlogDAO {

    UUID save(Blog blogEntry) throws UnknownHostException;

    Blog get(UUID id) throws UnknownHostException;

    List<Blog> get() throws UnknownHostException;

    void delete(UUID id) throws UnknownHostException, NullDocumentException;
}
