package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Article;
import model.ArticleView;
import service.TourismService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("tourism")
@Produces(MediaType.APPLICATION_JSON)
public class TourismController {

    @EJB
    private TourismService tourismService;

    @GET
    @RolesAllowed({"STUDENT", "TOURIST"})
    @Path("facilities/")
    public Response getTouristFacilities() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(tourismService.getTouristFacilities());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "TOURIST"})
    @Path("atms/")
    public Response getATMs() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(tourismService.getATMs());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "TOURIST"})
    @Path("hotels/")
    public Response getHotels() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(tourismService.getHotels());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "TOURIST"})
    @Path("theatres/")
    public Response getTheatres() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(tourismService.getTheatres());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "TOURIST"})
    @Path("tourArticles/")
    public Response getTouristArticles() {
        try {
            ObjectMapper om = new ObjectMapper();
            List<ArticleView> articleViews = new ArrayList<>();
            for(Article article:tourismService.getTouristArticles()) {
                articleViews.add(new ArticleView(article));
            }
            String jsonList = om.writeValueAsString(articleViews);

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }
}
