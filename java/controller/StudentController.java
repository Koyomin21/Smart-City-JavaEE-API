package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.StudentService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("student/")
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {

    @EJB
    private StudentService studentService;

    @GET
    @DenyAll
    @Path("deny/")
    public Response deniedMethod() {
        return Response.status(Response.Status.FORBIDDEN).entity("YOU CANNOT ACCESS THIS METHOD!").build();
    }

    @GET
    @RolesAllowed({"STUDENT", "ADMIN"})
    @Path("unis/")
    public Response getUniversities() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(studentService.getUniversities());
            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);


            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "ADMIN"})
    @Path("all/")
    public Response getStudents() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(studentService.getStudents());
            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);


            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "ADMIN"})
    @Path("libraries/")
    public Response getLibraries() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(studentService.getLibraries());
            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);


            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "ADMIN"})
    @Path("locationByUni/{id:[0-9]+}")
    public Response getLocationByUniversityId(@PathParam("id") String id) {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(studentService.getLocationByUniversityId(Integer.parseInt(id)));
            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);


            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @RolesAllowed({"STUDENT", "ADMIN"})
    @Path("filteredUni/")
    public Response getUniversitiesFilteredByName(String id) {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(studentService.getUniversitiesFilteredByName());
            CacheControl cc = new CacheControl();
            cc.setMaxAge(4000);
            cc.setPrivate(false);

            return Response.ok().entity(jsonList).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }



}
