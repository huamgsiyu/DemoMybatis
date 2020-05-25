package com.syh.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {

    public static void main(String[] args) {
//        generator("HSY",
//                "jdbc:mysql://ip:port/LibName?useUnicode=true&useSSL=false&characterEncoding=utf8",
//                "com.mysql.jdbc.Driver",
//                "XXX",
//                "XXX",
//                "com.syh.mybatis",
//                "springbootMybatis",
//                "authority",
//                "user");
    }

    /**
     * Mybatis一键生成entity,mapper,mapper.xml,service,serviceImpl,controller
     * @param author            开发人员
     * @param url               驱动连接的URL
     * @param driverName        驱动名称
     * @param username          数据库连接用户名
     * @param password          数据库连接密码
     * @param parent            父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     * @param projectModule     项目模块包名
     * @param functionModule    功能模块包名
     * @param tableName         表名
     */
    public static void generator(String author,
                                 String url,
                                 String driverName,
                                 String username,
                                 String password,
                                 String parent,
                                 String projectModule,
                                 String functionModule,
                                 String tableName) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(globalConfig(author, projectModule));
        mpg.setDataSource(dataSourceConfig(url, driverName, username, password));
        mpg.setPackageInfo(packageConfig(parent, functionModule));
        mpg.setTemplate(templateConfig());
        mpg.setStrategy(strategyConfig(tableName));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 全局配置
     * @param author            开发人员
     * @param projectModule     项目模块包名
     * @return                  GlobalConfig
     */
    private static GlobalConfig globalConfig (String author, String projectModule) {
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/" + projectModule + "/src/main/java");
        globalConfig.setFileOverride(true);
        globalConfig.setAuthor(author);
        globalConfig.setSwagger2(true);
        globalConfig.setOpen(false);
        globalConfig.setEnableCache(false);
        globalConfig.setKotlin(false);
        globalConfig.setActiveRecord(false);
        globalConfig.setBaseResultMap(false);
        globalConfig.setBaseColumnList(false);
        globalConfig.setEntityName("%sVo");
        globalConfig.setMapperName("");
        globalConfig.setXmlName("");
        globalConfig.setServiceName("");
        globalConfig.setServiceImplName("");
        globalConfig.setControllerName("");
        return globalConfig;
    }

    /**
     * 数据源设置
     * @param url           驱动连接的URL
     * @param driverName    驱动名称
     * @param username      数据库连接用户名
     * @param password      数据库连接密码
     * @return              DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig (String url,
                                                      String driverName,
                                                      String username,
                                                      String password) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        return dataSourceConfig;
    }

    /**
     * 包配置
     * @param parent            父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     * @param functionModule    功能模块包名
     * @return                  PackageConfig
     */
    private static PackageConfig packageConfig (String parent, String functionModule) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parent);
        packageConfig.setEntity("entity." + functionModule);
        packageConfig.setService("service." + functionModule);
        packageConfig.setServiceImpl("service.impl." + functionModule);
        packageConfig.setMapper("mapper." + functionModule);
        packageConfig.setXml("mapper.xml." + functionModule);
        packageConfig.setController("controller." + functionModule);
        return packageConfig;
    }

    /**
     * 模板路径配置项
     * @return  TemplateConfig
     */
    private static TemplateConfig templateConfig () {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(ConstVal.TEMPLATE_XML);
        return templateConfig;
    }

    /**
     * 策略配置
     * @param tableName     数据库表名称，多个用英文逗号隔开
     * @return              StrategyConfig
     */
    private static StrategyConfig strategyConfig (String tableName) {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setInclude(tableName);
        strategyConfig.setControllerMappingHyphenStyle(true);
        return strategyConfig;
    }
}