package com.cui.tech.mh.model.datasource.impl;


import com.cui.tech.mh.model.datasource.LoginLocalDataSource;

import me.goldze.mvvmhabit.utils.SPUtils;

public class LoginLocalDataSourceImpl implements LoginLocalDataSource {
    private volatile static LoginLocalDataSourceImpl INSTANCE = null;

    public static LoginLocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginLocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginLocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LoginLocalDataSourceImpl() {
        //数据库Helper构建
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put("UserName", userName);
    }


    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString("UserName");
    }

}
