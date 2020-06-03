package com.cui.tech.chaos.lite.model.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cui.tech.chaos.model.db.Table;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author G.G
 * @date 2019/11/8 14:28
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "isDelete", Integer.class, 0);
        Long sort = (Long) (System.currentTimeMillis() / 1000) - 1589099800;
        this.strictInsertFill(metaObject, "sort", Long.class, sort);
        this.strictInsertFill(metaObject, "version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    }


}
