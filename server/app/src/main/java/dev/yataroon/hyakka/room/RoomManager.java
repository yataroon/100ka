package dev.yataroon.hyakka.room;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import dev.yataroon.hyakka.common.JSONConverter;
import dev.yataroon.hyakka.room.constants.MessageTypes;
import dev.yataroon.hyakka.room.constants.SessionKeys;
import dev.yataroon.hyakka.room.message.event.PlayerJoinedEvent;
import dev.yataroon.hyakka.room.message.event.PlayerLeftEvent;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.websocket.Session;

@Stateless
public class RoomManager {

    @Inject
    private RoomStore roomStore;

    @Inject
    private JSONConverter jsonConverter;

    /**
     * * プレイヤー入室処理
     *
     * @param session
     * @param roomId
     */
    public void handlePlayerJoin(Session session, String roomId) throws IOException {
        // ルームに参加
        addSessionToRoom(roomId, session);

        // プレイヤーIDを生成・設定
        String playerId = generatePlayerId(session);
        session.getUserProperties().put(SessionKeys.PLAYER_ID, playerId);

        // 他の参加者に入室通知
        PlayerJoinedEvent event = PlayerJoinedEvent.builder()
                .type(MessageTypes.PLAYER_JOINED)
                .playerId(playerId)
                .roomId(roomId)
                .currentPlayers(getCurrentPlayerCount(roomId))
                .timestamp(System.currentTimeMillis())
                .build();

        broadcastToRoom(roomId, event, session);
    }

    /**
     * * プレイヤー退室処理
     *
     * @param session
     */
    public void handlePlayerLeave(Session session) throws IOException {

        String roomId = (String) session.getUserProperties().get(SessionKeys.ROOM_ID);
        String playerId = (String) session.getUserProperties().get(SessionKeys.PLAYER_ID);

        if (roomId != null) {
            // ルームから退出
            removeSessionFromRoom(roomId, session);

            // 他の参加者に退室通知
            if (playerId != null) {
                PlayerLeftEvent event = PlayerLeftEvent.builder()
                        .type(MessageTypes.PLAYER_LEFT)
                        .playerId(playerId)
                        .roomId(roomId)
                        .currentPlayers(getCurrentPlayerCount(roomId))
                        .timestamp(System.currentTimeMillis())
                        .build();

                broadcastToRoom(roomId, event, null);
            }
        }
    }

    /**
     * * ルーム内の他の参加者にメッセージをブロードキャスト
     */
    public void broadcastToRoom(String roomId, Object message, Session excludeSession) throws IOException {
        RoomModel room = getRoom(roomId);
        if (room != null && room.getSessions() != null) {
            for (Session session : room.getSessions()) {
                if (!session.equals(excludeSession)) {
                    sendToSession(session, message);
                }
            }
        }
    }

    /**
     * * 特定のセッションにメッセージを送信
     */
    private void sendToSession(Session session, Object message) throws IOException {
        String json = jsonConverter.toJSON(message);
        session.getBasicRemote().sendText(json);
    }

    /**
     * * プレイヤーIDを生成
     */
    private String generatePlayerId(Session session) {
        return "player_" + session.getId().substring(0, 8);
    }

    /**
     * * 現在の参加者数を取得
     */
    private int getCurrentPlayerCount(String roomId) {
        RoomModel room = getRoom(roomId);
        return room != null && room.getSessions() != null ? room.getSessions().size() : 0;
    }

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

    /**
     * * ルームIDをキーにRoomModelを取得
     * 
     * @param roomId
     * @return
     */
    public RoomModel getRoom(String roomId) {
        return roomStore.getRoomMap().get(roomId);
    }
}
