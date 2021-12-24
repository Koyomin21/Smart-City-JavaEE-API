package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Article;
import model.ArticleView;
import service.BusinessService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("business/")
@Produces(MediaType.APPLICATION_JSON)
public class BusinessController {

    @EJB
    private BusinessService businessService;


    @GET
    @PermitAll
    @Path("articles/")
    public Response getBusinessArticles() {
        try {
            ObjectMapper om = new ObjectMapper();
            List<ArticleView> articleViews = new ArrayList<>();
            for(Article article:businessService.getBusinessArticles()) {
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

    @GET
    @PermitAll
    @Path("articles/{id:[0-9]+}")
    public Response getBusinessArticleById(@PathParam("id") String id) {
        try {
            ObjectMapper om = new ObjectMapper();
            ArticleView view = new ArticleView(businessService.getArticleById(Integer.parseInt(id)));
            String jsonList = om.writeValueAsString(view);

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @PermitAll
    @Path("articles/")
    public Response getArticleBySubject(@QueryParam("subject") String subject) {
        try {
            ObjectMapper om = new ObjectMapper();
            ArticleView view = new ArticleView(businessService.getArticleBySubject(subject));
            String jsonList = om.writeValueAsString(view);

            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }




}
