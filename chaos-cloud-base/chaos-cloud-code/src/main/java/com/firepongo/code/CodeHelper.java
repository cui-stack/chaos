package com.firepongo.code;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author G.G
 * @date 2020/2/25 0:15
 */
public class CodeHelper {
    public static String projectPath = null;
    public static String pcmodel = null;

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static StrategyConfig initStrategyConfig(PackageConfig pc) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.firepongo.chaos.app.db.MuModel");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类

        strategy.setSuperControllerClass("com.firepongo.chaos.web.base.BaseController");
        strategy.setInclude(CodeHelper.scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        return strategy;
    }

    public static PackageConfig initPackageConfig() {
        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(CodeHelper.scanner("模块名"));
        pc.setParent("com.firepongo");
        return pc;
    }

    public static TemplateConfig initTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        templateConfig.setXml(null);
        return templateConfig;

    }


    public static DataSourceConfig initDataSource(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");

        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/chaos?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);
        return dsc;
    }


    public static FileOutConfig FileOutConfig(String tp, String prefix, String suffix) {
        return new FileOutConfig(tp) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + prefix + tableInfo.getEntityName() + suffix;
            }
        };
    }

    public static FileOutConfig FileOutConfig(String tp, String file) {
        return new FileOutConfig(tp) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + file;
            }
        };
    }

    public static List<FileOutConfig> initData() {
        List<FileOutConfig> focList = new ArrayList<>();
        String tp = "templates/src/main/";

        focList.add(FileOutConfig(tp + "resources/mapper/mapper.xml.ftl", "/src/main/resources/mapper/", "Mapper" + StringPool.DOT_XML));

        focList.add(FileOutConfig(tp + "java/api/data/data.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/api/data/", "Data" + StringPool.DOT_JAVA));
        focList.add(FileOutConfig(tp + "java/api/entity/model.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/api/entity/", StringPool.DOT_JAVA));
        focList.add(FileOutConfig(tp + "java/api/service/iservice.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/api/service/I", "Service" + StringPool.DOT_JAVA));
        focList.add(FileOutConfig(tp + "java/service/impl/service_impl.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/service/impl/", "ServiceImpl" + StringPool.DOT_JAVA));
        focList.add(FileOutConfig(tp + "java/service/mapper/mapper.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/service/mapper/", "Mapper" + StringPool.DOT_JAVA));

        focList.add(FileOutConfig(tp + "java/manager/controller/mncontroller.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/manager/controller/", "MnController" + StringPool.DOT_JAVA));
        focList.add(FileOutConfig(tp + "java/client/controller/controller.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/client/controller/", "Controller" + StringPool.DOT_JAVA));
        focList.add(FileOutConfig("templates/pom.xml.ftl", "/pom.xml"));
        focList.add(FileOutConfig(tp + "Application.ftl", "/src/main/java/com/firepongo/" + pcmodel + "/" + pcmodel.substring(0, 1).toUpperCase() + pcmodel.substring(1) + "Application" + StringPool.DOT_JAVA));

        return focList;
    }


}
