package dev.yataroon.hyakka.room.message.event;

import dev.yataroon.hyakka.room.constants.MessageTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * * プレイヤー入室通知メッセージ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerJoinedEvent {

    /**
     * * メッセージタイプ
     */
    private String type = MessageTypes.PLAYER_JOINED;

    /**
     * * プレイヤーID
     */
    private String playerId;

    /**
     * * ルームID
     */
    private String roomId;

    /**
     * * 現在の参加者数
     */
    private int currentPlayers;

    /**
     * * タイムスタンプ
     */
    private long timestamp;
}