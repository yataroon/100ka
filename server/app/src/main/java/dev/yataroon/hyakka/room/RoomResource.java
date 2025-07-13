package dev.yataroon.hyakka.room;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("room")
@RequestScoped
public class RoomResource {

    @GET
    @Path("list")
    public Response ping() {
        return Response
                .ok("ping Jakarta EE on Container")
                .build();
    }

}
