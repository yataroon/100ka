package dev.yataroon.hyakka.room.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.yataroon.hyakka.room.RoomModel;
import dev.yataroon.hyakka.room.dto.RoomResponseDTO;
import jakarta.websocket.Session;

@Mapper(componentModel = "cdi")
public interface RoomMapper {

    @Mapping(target = "currentPlayers", expression = "java(getCurrentPlayersCount(room.getSessions()))")
    RoomResponseDTO toDto(RoomModel room);

    List<RoomResponseDTO> toDtoList(List<RoomModel> rooms);

    default int getCurrentPlayersCount(Set<Session> sessions) {
        return sessions != null ? sessions.size() : 0;
    }
}
