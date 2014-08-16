package Blog;

import DAO.BlogDAO;
import DAO.NullDocumentException;
import com.google.gson.Gson;
import org.bson.types.ObjectId;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.util.List;

@Path("/blog")
@RequestScoped
public class BlogResource {

    private static final int STATUS_OK = 200;
    private static final int STATUS_CREATED = 201;
    public static final int STATUS_NOT_FOUND = 404;

    @Inject private BlogDAO blogDAO;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response get() throws UnknownHostException {
        List<Blog> blogList = blogDAO.get();
        String json = new Gson().toJson(blogList);
        return Response.status(STATUS_OK).entity(json).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") ObjectId id) throws UnknownHostException {
        Blog blog = blogDAO.get(id);
        if (blog == null) { return Response.status(STATUS_NOT_FOUND).build(); }
        String blogJson = new Gson().toJson(blog);
        return Response.status(STATUS_OK).entity(blogJson).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response post(Blog blog) throws UnknownHostException {
        ObjectId id = blogDAO.save(blog);
        return Response.status(STATUS_CREATED).entity(id.toString()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") ObjectId id) throws UnknownHostException {
        try {
            blogDAO.delete(id);
            return Response.status(STATUS_OK).build();
        } catch(NullDocumentException e) {
            return Response.status(STATUS_NOT_FOUND).build();
        }
    }
    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
}
