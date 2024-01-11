package si.fri.rso.location.version.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import si.fri.rso.location.version.lib.Location;
import si.fri.rso.location.version.services.beans.LocationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;



@ApplicationScoped
@Path("/Locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {

    private Logger log = Logger.getLogger(LocationResource.class.getName());

    @Inject
    private LocationBean LocationBean;


    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all location metadata.", summary = "Get all metadata")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of location metadata",
                    content = @Content(schema = @Schema(implementation = Location.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getLocation() {

        List<Location> Location = LocationBean.getLocationFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(Location).build();
    }


    @Operation(description = "Get metadata for an location.", summary = "Get metadata for an location")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "location metadata",
                    content = @Content(
                            schema = @Schema(implementation = Location.class))
            )})
    @GET
    @Path("/{LocationId}")
    public Response getLocation(@Parameter(description = "Metadata ID.", required = true)
                                     @PathParam("LocationId") Integer LocationId) {

        Location Location = LocationBean.getLocation(LocationId);

        if (Location == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(Location).build();
    }

    @Operation(description = "Add location metadata.", summary = "Add metadata")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Metadata successfully added."
            ),
            @APIResponse(responseCode = "405", description = "Validation error .")
    })
    @POST
    public Response createLocation(@RequestBody(
            description = "DTO object with location metadata.",
            required = true, content = @Content(
            schema = @Schema(implementation = Location.class))) Location Location) {

        if ((Location.getname() == null || Location.getdescription() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            Location = LocationBean.createLocation(Location);
        }

        return Response.status(Response.Status.CONFLICT).entity(Location).build();

    }


    @Operation(description = "Update metadata for an location.", summary = "Update metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Metadata successfully updated."
            )
    })
    @PUT
    @Path("{LocationId}")
    public Response putLocation(@Parameter(description = "Metadata ID.", required = true)
                                     @PathParam("LocationId") Integer LocationId,
                                     @RequestBody(
                                             description = "DTO object with location metadata.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Location.class)))
                                             Location Location){

        Location = LocationBean.putLocation(LocationId, Location);

        if (Location == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @Operation(description = "Delete metadata for an location.", summary = "Delete metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Metadata successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{LocationId}")
    public Response deleteLocation(@Parameter(description = "Metadata ID.", required = true)
                                        @PathParam("LocationId") Integer LocationId){

        boolean deleted = LocationBean.deleteLocation(LocationId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }





}
