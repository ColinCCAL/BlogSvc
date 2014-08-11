package Test;

import Blog.Blog;
import DAO.MongoBlogDAO;
import DAO.MongoUtil;
import DAO.NullDocumentException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

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

            UUID id = sut.save(blog);
        }
    }

    public static class When_get_is_called_with_a_UUID {
        private MongoBlogDAO sut;

        @Test
        public void it_should_find_the_blog_with_given_UUID() throws UnknownHostException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            Blog blog = sut.get(UUID.fromString("5b636e30-b896-4994-b1bf-6089cf2981eb"));
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
            UUID id = sut.save(blog);
            sut.delete(id);
        }

        @Test(expected=NullDocumentException.class)
        public void it_should_throw_a_null_document_exception_if_document_does_not_exist() throws UnknownHostException, NullDocumentException {
            sut = new MongoBlogDAO();
            sut.setMongoUtil(new MongoUtil());
            UUID id = UUID.randomUUID();
            sut.delete(id);
        }

    }
}
