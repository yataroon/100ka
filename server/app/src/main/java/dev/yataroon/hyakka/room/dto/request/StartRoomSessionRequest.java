package dev.yataroon.hyakka.room.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ルームセッション開始リクエスト
 */
@Data
public class StartRoomSessionRequest {

    /**
     * ユーザ名
     */
    @NotNull
    @JsonProperty("userName")
    private String userName;

}
