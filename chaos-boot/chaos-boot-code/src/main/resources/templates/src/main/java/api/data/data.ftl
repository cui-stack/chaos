package ${cfg.p}.api.data;

import com.cui.tech.chaos.model.db.DATA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="${entity}Data", description="${table.comment!}")
public class ${entity}Data extends DATA{

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.name !="mu"&& field.name !="create_time"
    && field.name !="modify_time" && field.name !="is_delete"
    && field.name !="version" && field.name !="id">
        <#if field.comment!?length gt 0>

    @ApiModelProperty(value = "${field.comment}")
        </#if>
    private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>

}
