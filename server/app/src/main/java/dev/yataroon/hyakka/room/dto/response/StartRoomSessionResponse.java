package dev.yataroon.hyakka.room.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StartRoomSessionResponse {

    /**
     * ユーザ名
     */
    @JsonProperty("userName")
    private String userName;

}
