package dev.yataroon.hyakka.room;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.Session;
import lombok.Getter;
import lombok.Setter;

@ApplicationScoped
public class RoomStore {

    /**
     * * ルーム別のセッション管理
     */
    @Getter
    @Setter
    private Map<String, RoomModel> roomMap;

    @PostConstruct
    private void init() {
        this.roomMap = new ConcurrentHashMap<>();
    }

}
