package dev.yataroon.hyakka.room;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.inject.Inject;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/room/{roomId}")
public class RoomWebSocket {
    // ルーム別のセッション管理
    private static final Map<String, Set<Session>> roomSessions = new ConcurrentHashMap<>();

    // @Inject
    // private RoomManager roomManager;

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId) {
        // セッションをルームに関連付け
        roomSessions.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(session);
        session.getUserProperties().put("roomId", roomId);
        // session.getUserProperties().put("playerId", extractPlayerId(session));

        // // 現在のルーム状態を新規接続者に送信
        // GameRoomState currentState = roomManager.getRoomState(roomId);
        // sendToSession(session, new StateUpdateMessage(currentState));

        // // 他の参加者に新規参加を通知
        // broadcastToRoom(roomId, new PlayerJoinedMessage(playerId), session);
    }
}
