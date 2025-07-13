package dev.yataroon.hyakka.room;

import java.util.Set;

import jakarta.websocket.Session;
import lombok.Data;

@Data
public class RoomModel {
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
     * * ルーム状態
     */
    // private RoomStatus status;

    /**
     * * ユーザセッションマップ
     */
    private Set<Session> sessions;
}
