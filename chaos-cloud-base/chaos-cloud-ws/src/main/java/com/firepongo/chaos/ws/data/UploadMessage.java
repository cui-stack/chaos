package com.firepongo.chaos.ws.data;

import cn.hutool.json.JSONObject;
import lombok.*;

/**
 * 上行消息
 *
 * @author G.G
 * @date 2021/1/19 11:11
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadMessage {

    private String domain;
    private String method;
    private JSONObject data;

}
