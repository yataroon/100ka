package dev.yataroon.hyakka.room;

import java.util.List;

import dev.yataroon.hyakka.room.dto.common.RoomDTO;
import dev.yataroon.hyakka.room.dto.response.ListRoomResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("room")
@RequestScoped
public class RoomResource {

    @Inject
    private RoomService roomService;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms() {
        List<RoomDTO> roomList = roomService.getAllRooms();

        ListRoomResponse response = ListRoomResponse.builder()
                .rooms(roomList)
                .count(roomList.size())
                .build();

        return Response.ok(response).build();
    }

}
