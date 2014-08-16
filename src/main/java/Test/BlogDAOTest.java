//package Test;
//
//import Blog.Blog;
//import DAO.BlogDAOImpl;
//import DAO.HibernateUtil;
//import com.google.gson.Gson;
//import org.junit.Test;
//import org.junit.experimental.runners.Enclosed;
//import org.junit.runner.RunWith;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@RunWith(Enclosed.class)
//public class BlogDAOTest {
//
//    public static class When_save_is_called {
//        private BlogDAOImpl sut;
//
//        @Test
//        public void It_should_save_blog_entry_to_database() {
//            sut = new BlogDAOImpl();
//            sut.setHibernateUtil(new HibernateUtil());
//
//            Blog expectedBlogEntry = new Blog();
//
//            UUID expectedId = UUID.randomUUID();
//            String expectedTitle = "Testing Validate";
//            String expectedAuthor = "Colin";
//            Date expectedCreatedOn = new Date("08/03/14");
//            String expectedPost = "This is a test blog post written using my new restful Blog API that I am working on.";
//
//            expectedBlogEntry.setId(expectedId);
//            expectedBlogEntry.setTitle(expectedTitle);
//            expectedBlogEntry.setAuthor(expectedAuthor);
//            expectedBlogEntry.setCreatedOn(expectedCreatedOn);
//            expectedBlogEntry.setPost(expectedPost);
//
//            UUID id = sut.save(expectedBlogEntry);
//
//        }
//    }
//
//    public static class When_get_is_called_with_an_id {
//        private BlogDAOImpl sut;
//
//        @Test
//        public void It_should_get_the_blog_with_given_id() {
//            sut = new BlogDAOImpl();
//            sut.setHibernateUtil(new HibernateUtil());
//            UUID uuid = UUID.fromString("3CA1C932-CA2F-784B-88F8-AD7F08E34D1D");
//            Blog blog = sut.get(uuid);
//            UUID blogId = blog.getId();
//        }
//    }
//
//    public static class When_get_is_called {
//        private BlogDAOImpl sut;
//
//        @Test
//        public void It_should_get_all_blog_posts() {
//            sut = new BlogDAOImpl();
//            sut.setHibernateUtil(new HibernateUtil());
//            List<Blog> blogs = sut.get();
//        }
//
//        @Test
//        public void It_should_save_blog_entry_to_database() {
//            Blog expectedBlogEntry = new Blog();
//
//            UUID expectedId = UUID.randomUUID();
//            String expectedTitle = "Any Title";
//            String expectedAuthor = "Colin";
//            Date expectedCreatedOn = new Date("08/08/14");
//            String expectedPost = "Testing123.";
//
//            expectedBlogEntry.setId(expectedId);
//            expectedBlogEntry.setTitle(expectedTitle);
//            expectedBlogEntry.setAuthor(expectedAuthor);
//            expectedBlogEntry.setCreatedOn(expectedCreatedOn);
//            expectedBlogEntry.setPost(expectedPost);
//
//            Gson gson = new Gson();
//            String json = gson.toJson(expectedBlogEntry);
//
//
//        }
//
//    }
//}
