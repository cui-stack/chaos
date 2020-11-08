package com.firepongo.chaos.app.tencent.weapp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author G.G
 * @date 2020/2/25 20:02
 */
@Data
@NoArgsConstructor
public class WeappMessage {
    private String touser;
    private String template_id;
    private String page = "pages/index/index";
    private Map<String, WxminiTemplateData> data;
}
