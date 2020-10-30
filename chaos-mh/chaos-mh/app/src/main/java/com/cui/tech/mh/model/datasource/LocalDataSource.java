package com.cui.tech.mh.model.datasource;

import me.goldze.mvvmhabit.utils.SPUtils;

public class LocalDataSource {
    private volatile static LocalDataSource INSTANCE = null;

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSource() {
        //数据库Helper构建
    }

    public void save(String field, String value) {
        SPUtils.getInstance().put(field, value);
    }

    public String get(String field) {
        return SPUtils.getInstance().getString(field);
    }


}
