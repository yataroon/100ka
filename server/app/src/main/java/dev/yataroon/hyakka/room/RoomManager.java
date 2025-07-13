package dev.yataroon.hyakka.room;

import java.util.concurrent.ConcurrentHashMap;

import dev.yataroon.hyakka.room.constants.SessionKeys;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.websocket.Session;

@Stateless
public class RoomManager {

    @Inject
    private RoomStore roomStore;

    /**
     * * ルームにユーザセッションを追加する
     * 
     * @param roomId
     * @param session
     */
    public void addSessionToRoom(String roomId, Session session) {
        // ルームを取得または作成
        RoomModel room = roomStore.getRoomMap()
                .computeIfAbsent(roomId, k -> createNewRoom(roomId));

        // セッションをルームに追加
        if (room.getSessions() == null) {
            room.setSessions(ConcurrentHashMap.newKeySet());
        }
        room.getSessions().add(session);

        session.getUserProperties().put(SessionKeys.ROOM_ID, roomId);
    }

    /**
     * * ルームからセッションを削除（退出時処理）
     * 
     * @param roomId
     * @param session
     */
    public void removeSessionFromRoom(String roomId, Session session) {
        RoomModel room = roomStore.getRoomMap().get(roomId);

        if (room != null && room.getSessions() != null) {
            room.getSessions().remove(session);

            // ルームが空になった場合、ルーム自体を削除
            if (room.getSessions().isEmpty()) {
                roomStore.getRoomMap().remove(roomId);
            }
        }

        // セッションからルーム情報を削除
        session.getUserProperties().remove(SessionKeys.ROOM_ID);
    }

    /**
     * * ルームを新規作成
     * 
     * @param roomId
     * @return
     */
    private RoomModel createNewRoom(String roomId) {
        RoomModel room = new RoomModel();
        room.setId(roomId);
        room.setName("Room " + roomId);
        room.setMaxPlayers(4); // デフォルト値
        room.setSessions(ConcurrentHashMap.newKeySet());
        return room;
    }
}
