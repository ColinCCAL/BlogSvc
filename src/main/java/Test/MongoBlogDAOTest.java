package Test;

import Blog.Blog;
import Blog.Comment;
import DAO.MongoBlogDAO;
import DAO.MongoUtil;
import DAO.NullDocumentException;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@RunWith(Enclosed.class)
public class MongoBlogDAOTest {
    public static class When_save_is_called {
        private MongoBlogDAO sut;

        @Test
        public void it_should_save_a_blog() throws UnknownHostException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            Blog blog = new Blog();
            blog.setTitle("This is a test title");
            blog.setAuthor("Colin");
            blog.setPost("This is a test post.");

            ObjectId id = sut.save(blog);
        }
    }

    public static class When_get_is_called_with_a_ID {
        private MongoBlogDAO sut;

        @Test
        public void it_should_find_the_blog_with_given_Id() throws UnknownHostException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            Blog blog = sut.get(new ObjectId("53eecd3c818c58a762a253a2"));
        }
    }

    public static class When_get_is_called {
        private MongoBlogDAO sut;

        @Test
        public void it_should_get_all_blog_entries() throws UnknownHostException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            List<Blog> blogs = sut.get();
        }
    }

    public static class When_delete_is_called {
        private MongoBlogDAO sut;

        @Test
        public void it_should_delete_the_blog_with_given_id() throws UnknownHostException, NullDocumentException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            Blog blog = new Blog();
            blog.setTitle("This is a test title");
            blog.setAuthor("Colin");
            blog.setPost("This is a test post.");
            Comment comment = new Comment(ObjectId.get(), "dsfdsfds", "dsfdsfdsf", new Date());
            blog.addComment(comment);
            ObjectId id = sut.save(blog);
            sut.delete(id);
        }

        @Test(expected=NullDocumentException.class)
        public void it_should_throw_a_null_document_exception_if_document_does_not_exist() throws UnknownHostException, NullDocumentException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            ObjectId id = ObjectId.get();
            sut.delete(id);
        }

    }

    public static class When_saveComment_is_called {
        private MongoBlogDAO sut;

        @Test
        public void It_should_add_the_comment_to_the_blog() throws UnknownHostException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            Comment comment = new Comment();
            comment.setAuthor("Colin");
            comment.setPost("Testing comments out!");
            Blog blog = new Blog(null, "expectedTitle", "expectedAuthor", null, "expectedPost", null);
            ObjectId blogId = sut.save(blog);
            ObjectId commentId = sut.saveComment(blogId, comment);
        }

        @Test
        public void deletedb() throws UnknownHostException {
            MongoUtil mongoUtil = new MongoUtil();
            long count = mongoUtil.getBlogsCollection().count();
            mongoUtil.getBlogsCollection().drop();
            long countAfter = mongoUtil.getBlogsCollection().count();

        }
    }
}
