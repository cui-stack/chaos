package com.cui.tech.chaos.model.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cui.tech.chaos.model.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

@Data
@ApiModel(value = "列表结果对象", description = "")
@RequiredArgsConstructor(staticName = "of")
public class PageList<T> extends DTO {
    @NonNull
    @ApiModelProperty(value = "列表")
    private List<T> list;
    @NonNull
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "当前页")
    @NonNull
    private Long current;

    public PageList() {
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

    public PageList(Page<T> page) {
        this.setCurrent((long) page.getNumber() + 1);
        this.setTotal(page.getTotalElements());
        this.setList(page.getContent());
    }

}
