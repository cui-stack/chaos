package ${cfg.p}.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.firepongo.chaos.app.db.MuModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper =false)
@Accessors(chain = true)
@TableName("${table.name}")
@ApiModel(value="${entity}实体", description="${table.comment!}")
public class ${entity} extends MuModel {

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#if field.name !="mu"&& field.name !="create_time"
          && field.name !="modify_time" && field.name !="is_delete"
          && field.name !="version" && field.name !="id">
    <#if field.comment!?length gt 0>

    @ApiModelProperty(value = "${field.comment}")
    </#if>
    @TableField("${field.name}")
    private ${field.propertyType} ${field.propertyName};

    </#if>
    </#list>

}
