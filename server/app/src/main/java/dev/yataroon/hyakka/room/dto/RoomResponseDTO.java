package dev.yataroon.hyakka.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {

    /**
     * * ルームID
     */
    private String id;

    /**
     * * ルーム名称
     */
    private String name;

    /**
     * * 最大プレイヤー数
     */
    private int maxPlayers;

    /**
     * * 現在の参加者数
     */
    private int currentPlayers;

    /**
     * * ルーム状態
     */
    // private RoomStatus status;

}
