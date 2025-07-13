package dev.yataroon.hyakka.room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

/**
 * * ルームの状態をインメモリで保持するストアクラス
 */
@ApplicationScoped
public class RoomStore {

    /**
     * * ルーム情報マップ
     */
    @Getter
    @Setter
    private Map<String, RoomModel> roomMap;

    /**
     * * 初期化処理
     */
    @PostConstruct
    private void init() {
        this.roomMap = new ConcurrentHashMap<>();
    }

    /**
     * * 終了処理
     */
    @PreDestroy
    private void close() {

    }

}
