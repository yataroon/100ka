package dev.yataroon.hyakka.room;

import jakarta.enterprise.context.ConversationScoped;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@ConversationScoped
public class RoomSessionManager implements Serializable {

    /**
     * 現在入室中ルーム
     */
    @Getter
    @Setter
    private RoomModel currentRoom;

    /**
     * ユーザ名
     */
    @Getter
    @Setter
    private String userName;

}
