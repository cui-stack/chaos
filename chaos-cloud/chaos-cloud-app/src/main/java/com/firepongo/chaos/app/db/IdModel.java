package com.firepongo.chaos.app.db;

import com.baomidou.mybatisplus.annotation.*;
import com.firepongo.chaos.app.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class IdModel extends DTO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "create_time", fill = FieldFill.INSERT, update = "NOW()")
    private LocalDateTime createTime;
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE, update = "NOW()")
    private LocalDateTime modifyTime;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_delete", fill = FieldFill.INSERT, update = "0")
    private Integer isDelete;
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE, update = "%s+1")
    private Integer version;

}
