package dev.yataroon.hyakka.room.message.event;

import dev.yataroon.hyakka.room.constants.MessageTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * * プレイヤー退室通知メッセージ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerLeftEvent {

    /**
     * * メッセージタイプ
     */
    @Builder.Default
    private String type = MessageTypes.PLAYER_LEFT;

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