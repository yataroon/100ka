package dev.yataroon.hyakka.room.dto.response;

import java.util.List;

import dev.yataroon.hyakka.room.dto.common.RoomDTO;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListRoomResponse {

    /**
     * * ルームリスト
     */
    @JsonbProperty("rooms")
    private List<RoomDTO> rooms;

    /**
     * * ルーム数
     */
    @JsonbProperty("count")
    private int count;

}
