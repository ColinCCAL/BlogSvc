//package DAO;
//
//import Blog.Blog;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javax.enterprise.inject.Alternative;
//import javax.inject.Inject;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@Alternative
//public class BlogDAOImpl implements BlogDAO {
//
//    @Inject private HibernateUtil hibernateUtil;
//
//    public UUID save(Blog blogEntry) {
//        blogEntry.setCreatedOn(new Date());
//        blogEntry.setId(UUID.randomUUID());
//        session = hibernateUtil.openSession();
//        UUID id = null;
//        try {
//            transaction = session.beginTransaction();
//            id = (UUID) session.save(blogEntry);
//            transaction.commit();
//        } finally {
//            session.close();
//        }
//        return id;
//    }
//
//    public Blog get(UUID id) {
//        Blog blog = null;
//        session = hibernateUtil.openSession();
//        try {
//            blog = (Blog)session.get(Blog.class, id);
//        } finally {
//            session.close();
//        }
//        return blog;
//    }
//
//    public List<Blog> get() {
//        List<Blog> blogs = new ArrayList<Blog>();
//        session = hibernateUtil.openSession();
//        try {
//            Query query = session.createQuery("FROM Blog");
//            blogs = (List<Blog>) query.list();
//
//        } finally {
//            session.close();
//        }
//        return blogs;
//    }
//
//    public void delete(UUID id) {
//
//    }
//
//    Transaction transaction = null;
//    Session session = null;
//
//    public void setHibernateUtil(HibernateUtil hibernateUtil) {
//        this.hibernateUtil = hibernateUtil;
//    }
//}
