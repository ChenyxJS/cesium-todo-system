package com.chenyx.create.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.chenyx.util.StringUtil;

import java.util.*;

public class CreateCode {
    public static void main(String[] args) {
        CreateCode createCode = new CreateCode("jdbc:mysql://localhost:3306/todo_system?useSSL=false&serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true",
                "com.mysql.cj.jdbc.Driver",
                "root",
                "12345678",
                "/TodoSystem-web/src/main",
                "chenyx");

        createCode.buildCode("user_info");
    }

    private AutoGenerator autoGenerator;
    private String projectPath;
    private StrategyConfig strategy;
    private List<String> entityNames;
    private static final String parentBKG = "com.chenyx";
    private static final String controllerBKG = "controller.admin";
    private static final String entityBKG = "entity";
    private static final String mapperBKG = "mapper";
    private static final String serviceBKG = "service";
    private static final String serviceImplBKG = "service.impl";
    private static Map<String,Object> mapBKG = new HashMap<>();
    // 自定义controller模板地址
    private static final String controllerUrl = "/templates/controllerMy.java.ftl";
    // 自定义ExtXXXMapwe.xml模板地址
    private static final String extXMLUrl = "/templates/mapperMy.xml.ftl";
    private static final String extJAVAUrl = "/templates/mapperMy.java.ftl";
    private static final String entityDtoUrl = "/templates/entityDto.java.ftl";
    private static final String entityEnumUrl = "/templates/entityEnum.java.ftl";
    private static final String entityQueryUrl = "/templates/entityQuery.java.ftl";

    /**
     * @param dataBaseUrl        数据库连接url，例如："jdbc:mysql://192.168.123.168:3306/yat_o?useSSL=false&serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true"
     * @param dataBaseDriverName 数据库连接驱动，例如："com.mysql.cj.jdbc.Driver"
     * @param dataBaseUsername   数据库用户名，例如："ROOT"
     * @param dataBasePassword   数据库密码，例如："ROOT"
     * @param fileOutPutUrl      生成文件输出地址，例如："/Main/src/main"，指生成在Main模块下的src/main/java文件夹下的各对应包下，xml则生成在src/main/resources/sqlmap/下
     */
    private CreateCode(String dataBaseUrl, String dataBaseDriverName, String dataBaseUsername, String dataBasePassword, String fileOutPutUrl, String author) {
        if (StringUtil.isEmpty(dataBaseUrl)) {
            System.out.println("dataBaseUrl" + "不可为空");
        }
        if (StringUtil.isEmpty(dataBaseDriverName)) {
            System.out.println("dataBaseDriverName" + "不可为空");
        }
        if (StringUtil.isEmpty(dataBaseUsername)) {
            System.out.println("dataBaseUsername" + "不可为空");
        }
        if (StringUtil.isEmpty(dataBasePassword)) {
            dataBasePassword = "";
        }
        if (StringUtil.isEmpty(fileOutPutUrl)) {
            System.out.println("fileOutPutUrl" + "不可为空");
        }
        entityNames = new ArrayList<>();
        // 配置模板，用于定义模板类型和设置模板地址（模板文件）
        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator = new AutoGenerator();
        // 获取项目地址：到项目最上层地址，比如该项目会到/CodeStandard
        projectPath = System.getProperty("user.dir");
        // 设置公用配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置文件输出地址
        globalConfig.setOutputDir(projectPath + fileOutPutUrl + "/java");
        // 设置作者名称，用于类文件中的类注释的 @author 标签
        globalConfig.setAuthor(author);
        // 未明
        globalConfig.setOpen(false);
        // 设置xml中是否生成<resultMap id="BaseResultMap" type="com.chenyx.entity.Exam">标签
        globalConfig.setBaseResultMap(true);
        // 设置xml中是否生成<sql id="Base_Column_List">标签
        globalConfig.setBaseColumnList(true);

        autoGenerator.setGlobalConfig(globalConfig);

        // 设置数据库连接信息
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dataBaseUrl);
        dataSourceConfig.setDriverName(dataBaseDriverName);
        dataSourceConfig.setUsername(dataBaseUsername);
        dataSourceConfig.setPassword(dataBasePassword);
        autoGenerator.setDataSource(dataSourceConfig);

        // 设置各class文件输出包
        PackageConfig pc = new PackageConfig();
        // 设置父包，下面设置的其他包将自动加上该项设置
        pc.setParent(parentBKG);
        // controller类的包，会自动加上com.chenyx
        pc.setController(controllerBKG);
        // entity类的包，会自动加上com.chenyx
        pc.setEntity(entityBKG);
        // mapper类的包，会自动加上com.chenyx
        pc.setMapper(mapperBKG);
        // service类的包，会自动加上com.chenyx
        pc.setService(serviceBKG);
        // service.impl类的包，会自动加上com.chenyx
        pc.setServiceImpl(serviceImplBKG);
        // 注意，因为xml要生成的地址不在java（globalConfig.setOutputDir的设置）文件中，因此在这里无法设置，需要使用InjectionConfig单独设置
        autoGenerator.setPackageInfo(pc);

        // 自定义配置，用于设置需要单独设置输出路径和名称有特殊前后缀的类
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        mapBKG.put("parentBKG",parentBKG);
        cfg.setMap(mapBKG);
        // 自定义配置列表，在初始化FileOutConfig的方法中可以进行TableInfo（模板中${table.xx}中的table）信息的获取和修改
        List<FileOutConfig> focList = new ArrayList<>();
        // 首先是controller，这里的参数值是自己做的模板文件
        focList.add(new FileOutConfig(controllerUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 获取列信息
                List<TableField> tableFields = tableInfo.getFields();
                // 由于mybatis中的datetime类的列会自动设置成LocalDateTime类型，但是这个类型在查询时会出现java.lang.AbstractMethodError: Method org/apache/commons/dbcp/DelegatingResultSet.getObject(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object; is abstract
                // 因此，这里将LocalDateTime类型改为Date类型
                tableFields.forEach(v -> {
                    if ("LocalDateTime".equals(v.getPropertyType())) {
                        Set<String> packages = tableInfo.getImportPackages();
                        packages.remove("java.time.LocalDateTime");
                        IColumnType iColumnType = new IColumnType() {
                            @Override
                            public String getType() {
                                return "Date";
                            }

                            @Override
                            public String getPkg() {
                                return "java.util.Date";
                            }
                        };
                        v.setColumnType(iColumnType);
                    }
                });
                // 重新将列数据导入该类用于重新设置需要导入的包
                tableInfo.setFields(tableFields);
                // 设置controller类的类名的前后缀
                tableInfo.setControllerName("Admin" + tableInfo.getEntityName() + "Controller");
                entityNames.add(parentBKG + "." + entityBKG + "." + tableInfo.getEntityName());
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/java/com/chenyx/controller/admin/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        // 设置了controller的自定义配置之后，就必须将配置类中的该项设置为null，即使前文没有设置该项，也不可遗漏，否则，将还会生成一个默认名称的类
        templateConfig.setController(null);
        // 然后是mapper.xml，这里的配置值是默认的模板文件，该文件在自己的项目文件夹下没有，但是运行之后，在target文件夹下是存在的（是引的包中自带的）
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        // 然后是ExtXXXmapper.xml，这里的配置值是设定的模板文件
        focList.add(new FileOutConfig(extXMLUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/resources/mapper/Ext" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // 然后是ExtXXXmapper.java，这里的配置值是设定的模板文件
        focList.add(new FileOutConfig(extJAVAUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/java/com/chenyx/mapper/Ext" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        // 然后是XXXDTO.java，这里的配置值是设定的模板文件
        focList.add(new FileOutConfig(entityDtoUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/java/com/chenyx/entity/dto/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });
        // 然后是XXXEnum.java，这里的配置值是设定的模板文件
        focList.add(new FileOutConfig(entityEnumUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/java/com/chenyx/entity/enums/" + tableInfo.getEntityName() + "Enum" + StringPool.DOT_JAVA;
            }
        });
        // 然后是XXXQuery.java，这里的配置值是设定的模板文件
        focList.add(new FileOutConfig(entityQueryUrl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + fileOutPutUrl + "/java/com/chenyx/entity/query/" + tableInfo.getEntityName() + "Query" + StringPool.DOT_JAVA;
            }
        });

        // 设置模板类型是freemarker
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 将列表放进InjectionConfig类对象中
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        // 策略配置
        strategy = new StrategyConfig();
        // 未明
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 未明
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 未明
        strategy.setEntityLombokModel(true);
        // 未明
        strategy.setRestControllerStyle(true);
        // 表名，多个表的话可以使用英文逗号隔开，如：strategy.setInclude("exam","user");
        // 未明
        strategy.setControllerMappingHyphenStyle(true);

    }



    /**
     * @param tableName 表名，多个表的话可以使用英文逗号隔开，如：("exam,user");
     * @return 是否生成成功
     */
    public boolean buildCode(String tableName) {
        String[] tableNames = tableName.split(",");
        strategy.setInclude(tableNames);
        autoGenerator.setStrategy(strategy);
        // 运行
        autoGenerator.execute();
//        ClazzUtils.buildByClassName(entityNames);
        return true;
    }


}
