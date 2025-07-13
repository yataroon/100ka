package dev.yataroon.hyakka.room;

import dev.yataroon.hyakka.room.constants.SessionKeys;
import jakarta.inject.Inject;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/room/{roomId}")
public class RoomWebSocket {

    /**
     * * ルームマネージャー
     */
    @Inject
    private RoomManager roomManager;

    /**
     * * コネクション確立時
     * 
     * @param session
     * @param roomId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId) {

        roomManager.addSessionToRoom(roomId, session);

        // session.getUserProperties().put("playerId", extractPlayerId(session));

        // // 現在のルーム状態を新規接続者に送信
        // GameRoomState currentState = roomManager.getRoomState(roomId);
        // sendToSession(session, new StateUpdateMessage(currentState));

        // // 他の参加者に新規参加を通知
        // broadcastToRoom(roomId, new PlayerJoinedMessage(playerId), session);
    }

    @OnClose
    public void OnClose(Session session, CloseReason closeReason) {
        String roomId = (String) session.getUserProperties().get(SessionKeys.ROOM_ID);

        if (roomId != null) {
            roomManager.removeSessionFromRoom(roomId, session);
        }
    }
}
