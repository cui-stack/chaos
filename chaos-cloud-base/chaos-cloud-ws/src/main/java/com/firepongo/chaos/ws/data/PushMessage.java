package com.firepongo.chaos.ws.data;

import cn.hutool.json.JSONObject;
import lombok.*;

/**
 * @author G.G
 * @date 2021/1/19 11:11
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMessage {

    private String type;

    private JSONObject data;

}
