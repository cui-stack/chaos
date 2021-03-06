package com.firepongo.chaos.app.db;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author G.G
 */
@Data
@NoArgsConstructor
public class MuModel extends Model {
    @TableField(value = "id")
    private Integer id;
    @TableId(value = "mu", type = IdType.ASSIGN_ID)
    private String mu;
    @JsonFormat
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE, select = false)
    private LocalDateTime modifyTime;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_delete", fill = FieldFill.INSERT, select = false)
    private Integer isDelete;
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE, update = "%s+1", select = false)
    private Integer version;

}
