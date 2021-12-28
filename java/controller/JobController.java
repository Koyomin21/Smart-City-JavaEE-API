package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.JobPost;
import service.JmsJobService;
import service.JobService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("job/")
@Produces(MediaType.APPLICATION_JSON)
public class JobController {

    @EJB
    private JobService jobService;

    @EJB
    private JmsJobService jmsJobService;

    @GET
    @PermitAll
    @Path("vacancies/")
    public Response getVacancies() {
        try {

            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(jobService.getAllVacancies());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @PermitAll
    @Path("jobPosts/")
    public Response getJobPosts() {
        try {

            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(jobService.getAllJobPosts());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @PermitAll
    @Path("relevantVacancies/")
    public Response getRelevantVacancies() {
        try {

            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(jobService.getVacanciesFilteredByRelevance());

            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @PermitAll
    @Path("updRelevance/")
    public Response updateVacancyRelevance(@QueryParam("id") int id,
                                         @QueryParam("relevance") Boolean relevance) {
        try {

            System.out.println(relevance);
            ObjectMapper om = new ObjectMapper();
            int res = jobService.updateVacancyRelevance(id, relevance);
            String result = "";
            if(res == 1) {
                result = "Successfully updated!";
            } else {
                result = "Not updated!";
            }


            return Response.ok().entity(result).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("delJobPost/{id:[0-9-]}")
    public Response deleteJobPost(@PathParam("id") int id) {
        try {

            ObjectMapper om = new ObjectMapper();
            int res = jobService.deleteJobPost(id);
            String result = "";
            if(res == 1) {
                result = "Successfully deleted!";
            } else {
                result = "Not deleted!";
            }


            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.ok().entity(result).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @POST
    @PermitAll
    @Path("sendJobPost/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendJmsJobPost(JobPost jobPost) {
        try {
            String res = jmsJobService.sendJmsJobPost(jobPost);
            return Response.ok().entity(res).build();

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());

        }
    }


    @GET
    @RolesAllowed("ADMIN")
    @Path("getJobPost/")
    public Response getJmsJobPost() {
        try {
            JobPost post = jmsJobService.getJmsJobPost();
            String result = "";

            if(post != null && jobService.isVerified(post)) {
                int res = jobService.createJobPost(post);

                if(res == 1) {
                    result = "Successfully verified and created!";
                } else {
                    result = "Could not be created";
                }
            } else {
                result = "Not verified";
            }

            return  Response.ok().entity(result).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());

        }
    }


}
