package com.firepongo.chaos.app.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author G.G
 * @date 2020/5/10 11:44
 */
@Data
public class MuSortModel extends MuModel {
    @TableField(value = "sort", fill = FieldFill.INSERT)
    private Long sort;
}
