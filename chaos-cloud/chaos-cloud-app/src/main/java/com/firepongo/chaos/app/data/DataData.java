package com.firepongo.chaos.app.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/9/1 18:19
 */
@Data
@NoArgsConstructor
public class DataData {
    //历史总计
    private Double total;
    //今日总计
    private Double todayTotal;
    //昨天总计
    private Double yesterdayTotal;
    //本周总计(自然周)
    private Double weekTotal;
    //上周总计(自然周)
    private Double lastWeekTotal;
    //本月总计(自然月)
    private Double monthTotal;
    private Double lastMonthTotal;
    //今年总计(自然年)
    private Double yearTotal;
    private Double lastYearTotal;

    //自定义总计
    private Double xTotal;

    //上周X
    private Double lastWeekToal;
    //环比(年)
    private Float mom;
    //同比(年)
    private Float yoy;
    //日同比
    private Float dyoy;
    //周同比
    private Float wyoy;
    //月同比
    private Float myoy;

}
