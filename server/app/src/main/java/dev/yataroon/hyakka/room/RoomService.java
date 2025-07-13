package dev.yataroon.hyakka.room;

import java.util.List;
import java.util.stream.Collectors;

import dev.yataroon.hyakka.room.dto.common.RoomDTO;
import dev.yataroon.hyakka.room.mapper.RoomMapper;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * * ルームのドメインサービスクラス
 */
@Stateless
public class RoomService {

    /**
     * * オブジェクトマッパー
     */
    @Inject
    private RoomMapper roomMapper;

    /**
     * * ルーム情報ストア
     */
    @Inject
    private RoomStore roomStore;

    // @Inject
    // private RoomRepository repository;

    /**
     * * すべてのルームを取得する
     * 
     * @return
     */
    public List<RoomDTO> getAllRooms() {
        return roomStore.getRoomMap().values().stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

}
