package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import filter.JWTTokenNeeded;
import model.User;
import service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin/")
@Produces(MediaType.APPLICATION_JSON)
public class AdministrationController {

    @EJB
    private UserService userService;


    @GET
    @Path("users/")
    @RolesAllowed("ADMIN")
    public Response getAllUsers() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(userService.getAllUsers());

            return Response.ok().entity(jsonList).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @JWTTokenNeeded
    @RolesAllowed("ADMIN")
    @Path("tourists/")
    public Response getAllTourists() {
        try {
            ObjectMapper om = new ObjectMapper();
            String jsonList = om.writeValueAsString(userService.getAllTourists());

            return Response.ok().entity(jsonList).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @GET
    @Path("user/{id:[0-9]+}")
    public Response getUserById(@PathParam("id") String sId) {
        try {
            Integer id = Integer.valueOf(sId);
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(userService.getUserById(id));

            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.status(Response.Status.OK).entity(json).cacheControl(cc).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("create/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid User user) {
        try {

            int result = userService.createUser(user);

            CacheControl cc = new CacheControl();
            cc.setMaxAge(20000);
            cc.setPrivate(true);

            return Response.status(Response.Status.OK).entity(result).cacheControl(cc).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }

    @POST
    @PermitAll
    @Path("login/")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        try {


            if(userService.login(username, password) == 0) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("FAILED AUTHORIZATION").build();
            }

            String token = userService.generateToken(username, password);

            return Response.status(Response.Status.OK)
                    .header("Authorization", "Bearer: " + token)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage(), e);
        }
    }



}
