package com.firepongo.chaos.app.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.firepongo.chaos.app.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@ApiModel(value = "列表结果对象", description = "")
public class PageList<T> extends DTO {
    @ApiModelProperty(value = "列表")
    private List<T> list;
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "当前页")
    private Long current;

    public PageList() {
        list = new ArrayList<>();
    }

    public PageList(IPage<T> page, Class c) {
        this.setCurrent(page.getCurrent());
        this.setTotal(page.getTotal());
        List list = page.getRecords().stream().map(entity -> {
            DTO t = null;
            try {
                t = (DTO) c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(entity, t);
            return t;
        }).collect(Collectors.toList());
        this.setList(list);
    }

    public PageList(IPage<T> page) {
        this.setCurrent(page.getCurrent());
        this.setTotal(page.getTotal());
        this.setList(page.getRecords());
    }


}
