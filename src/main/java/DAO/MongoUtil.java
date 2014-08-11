package DAO;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoUtil {
    private static MongoClient mongoClient;

    public DBCollection getBlogsCollection() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("Colin");
        return db.getCollection("Blogs");
    }

    public void close() {
        if (mongoClient != null) { mongoClient.close(); }
    }
}
