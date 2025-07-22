package dev.yataroon.hyakka.room;

import dev.yataroon.hyakka.room.dto.common.RoomDTO;
import dev.yataroon.hyakka.room.dto.request.StartRoomSessionRequest;
import dev.yataroon.hyakka.room.dto.response.ListRoomResponse;
import dev.yataroon.hyakka.room.dto.response.StartRoomSessionResponse;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("room")
@RequestScoped
public class RoomResource {

    @Inject
    private RoomService roomService;

    @Inject
    private Conversation conversation;

    @Inject
    private RoomSessionManager roomSessionManager;

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

    @POST
    @Path("start")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response startSession(@Valid StartRoomSessionRequest request) {

        conversation.begin();

        roomSessionManager.setUserName(request.getUserName());

        StartRoomSessionResponse response = StartRoomSessionResponse.builder()
                .userName(roomSessionManager.getUserName())
                .build();

        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response currentRoom() {

        return Response.ok(roomSessionManager.getCurrentRoom().getId()).build();
    }

}
