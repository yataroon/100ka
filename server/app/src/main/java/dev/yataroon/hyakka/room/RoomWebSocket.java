package dev.yataroon.hyakka.room;

import java.io.IOException;

import dev.yataroon.hyakka.room.result.PlayerJoinResult;
import jakarta.inject.Inject;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
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
     * * セッションマネージャー
     */
    @Inject
    private RoomSessionManager roomSessionManager;

    /**
     * * コネクション確立時
     * 
     * @param session
     * @param roomId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId) {
        try {
            PlayerJoinResult result = roomManager.handlePlayerJoin(session, roomId);
            roomSessionManager.setCurrentRoom(result.room());

        } catch (IOException e) {
            throw new RuntimeException("Failed to handle player join", e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        try {
            roomManager.handlePlayerLeave(session);
        } catch (IOException e) {
            throw new RuntimeException("Failed to handle player leave", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // エラーログ出力
        System.err.println("WebSocket error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

}
