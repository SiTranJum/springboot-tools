package springboottools.MPG;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HEMINGLIANG823
 * @create 2019-04-26 10:49
 */
public class MPGenerator {

    /**
     * table前缀
     */
    private static final String DEFAULT_PREFIX = "t_";


    /**
     * 实体父类
     */
    private static final String DEFAULT_ENTITY_SUPERE_CLASS = "com.gzstrong.cloud.common.data.entity.SnowFlakeEntity";//""com.gzstrong.cloud.bus.jdbc.pojo.BaseModel";

    /**
     * 实体和父类公用字段
     */
    private static final String[] DEFAULT_ENTITY_COMMON_COLUMNS = new String[]{"id", "del_flag", "create_user_id", "create_time", "update_user_id", "update_time","dept_id","tenant_id","create_user_name","update_user_name"};

    private static final String DEFAULT_URL = "jdbc:mysql://192.168.197.62:3308/sc_cloud_maas?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "Gzsc@601";


    private static boolean DEFAULT_IS_NEED_ENTITY_SUPER_CLASS = true;

    /**
     * 数据库配置四要素
     */
    private static String DEFAULT_DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * 数据库类型
     */
    private static DbType DEFAULT_DBTYPE = DbType.MYSQL;


    /**
     * 作者
     */
    private static String DEFAULT_AUTHOR_NAME = "MPGenerator";

    /**
     * 配置
     */
    private MPGeneratorConfig config;

    /**
     * 生成工具类
     */
    private AutoGenerator autoGenerator;

    public MPGenerator(MPGeneratorConfig config) {
        autoGenerator = new AutoGenerator();
        final String[] tables = config.getTables();
        final String projectPath = config.getProjectPath();
        final String basePackage = config.getBasePackage();
        Objects.requireNonNull(tables);
        Objects.requireNonNull(projectPath);
        Objects.requireNonNull(basePackage);
        initDefaultConfig();
        initConfig(config);
        settingGeneratorConfig();
    }

    private void initDefaultConfig() {
        config = new MPGeneratorConfig();
        config.setPrefix(DEFAULT_PREFIX);
        config.setEntitySuperClass(DEFAULT_ENTITY_SUPERE_CLASS);
        config.setEntityCommonColumns(DEFAULT_ENTITY_COMMON_COLUMNS);
        config.setUrl(DEFAULT_URL);
        config.setUsername(DEFAULT_USERNAME);
        config.setPassword(DEFAULT_PASSWORD);
        config.setNeedEntitySuperClass(DEFAULT_IS_NEED_ENTITY_SUPER_CLASS);
        config.setDriverName(DEFAULT_DRIVER_NAME);
        config.setDbType(DEFAULT_DBTYPE);
        config.setAuthorName(DEFAULT_AUTHOR_NAME);

    }

    private void settingGeneratorConfig() {
        /**
         * 数据库配置
         */
        autoGenerator.setDataSource(new DataSourceConfig()
                .setDbType(config.getDbType())
                .setDriverName(config.getDriverName())
                .setUrl(config.getUrl())
                .setUsername(config.getUsername())
                .setPassword(config.getPassword())
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    //@Override
                    //public DbColumnType processTypeConvert(String fieldType) {
                    //System.out.println("转换类型：" + fieldType);
                    // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                    //    return DbColumnType.BOOLEAN;
                    // }
                    //return super.processTypeConvert(fieldType);
                    //}
                }));

        /**
         * 全局配置
         */
        autoGenerator.setGlobalConfig(new GlobalConfig()
                .setOutputDir(config.getProjectPath() + "/src/main/java")//输出目录
                .setFileOverride(false)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setOpen(false)//生成后打开文件夹
                .setAuthor(config.getAuthorName())
                .setSwagger2(true)//自动上生成swagger2注解
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

        /**
         * 策略配置
         */
        autoGenerator.setStrategy(new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{config.getPrefix()})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(config.getTables()) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        .setSuperEntityClass(config.getEntitySuperClass())
                        // 自定义实体，公共字段
                        .setSuperEntityColumns(config.getEntityCommonColumns())
                        //.setTableFillList(tableFillList)
                        // 自定义 mapper 父类 默认BaseMapper
                        //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
                        // 自定义 service 父类 默认IService
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类 默认ServiceImpl
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        // .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        );

        /**
         * 包配置
         */
        autoGenerator.setPackageInfo(new PackageConfig()
                //.setModuleName("User")
                .setParent(config.getBasePackage())// 自定义包路径
                .setController("controller")// 这里是控制器包名，默认 web
                .setEntity("pojo") // 设置Entity包名，默认entity
                .setMapper("dao") // 设置Mapper包名，默认mapper
                .setService("service") // 设置Service包名，默认service
                .setServiceImpl("service.impl") // 设置Service Impl包名，默认service.impl
                .setXml("mapper") // 设置Mapper XML包名，默认mapper.xml
        );

        /**
         * 注入自定义配置
         */
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig abc = new InjectionConfig() {
            @Override
            public void initMap() {
//                Map<String, Object> orderMap = new HashMap<String, Object>();
//                orderMap.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(orderMap);
            }
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<FileOutConfig>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return config.getProjectPath() + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        abc.setFileOutConfigList(fileOutList);
        autoGenerator.setCfg(abc);

        /**
         * 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
         */
        //gen.setTemplateEngine(new FreemarkerTemplateEngine());

        /**
         * 模板配置
         */
        autoGenerator.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                        .setController("templates/controller.java.vm")
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );

    }

    private void initConfig(MPGeneratorConfig config) {
        final String prefix = config.getPrefix();
        final String[] tables = config.getTables();
        final String entitySuperClass = config.getEntitySuperClass();
        final String[] entityCommonColumns = config.getEntityCommonColumns();
        final String url = config.getUrl();
        final String username = config.getUsername();
        final String password = config.getPassword();
        final boolean isNeedEntitySuperClass = config.isNeedEntitySuperClass();
        final String driverName = config.getDriverName();
        final DbType dbType = config.getDbType();
        final String projectPath = config.getProjectPath();
        final String basePackage = config.getBasePackage();
        final String authorName = config.getAuthorName();

        this.config.setTables(tables);
        this.config.setProjectPath(projectPath);
        this.config.setBasePackage(basePackage);
        if (StrUtil.isNotBlank(prefix)) {
            this.config.setPrefix(prefix);
        }

        if (isNeedEntitySuperClass) {
            if (StrUtil.isNotBlank(entitySuperClass)) {
                this.config.setEntitySuperClass(entitySuperClass);
            }

            if (entityCommonColumns != null && entityCommonColumns.length > 0) {
                this.config.setEntityCommonColumns(entityCommonColumns);
            }
        } else {
            this.config.setEntitySuperClass(null);
            this.config.setEntityCommonColumns(null);
        }

        if (StrUtil.isNotBlank(url)) {
            this.config.setUrl(url);
        }

        if (StrUtil.isNotBlank(username)) {
            this.config.setUsername(username);
        }

        if (StrUtil.isNotBlank(password)) {
            this.config.setPassword(password);
        }

        if (StrUtil.isNotBlank(driverName)) {
            this.config.setDriverName(driverName);
        }

        if (dbType != null) {
            this.config.setDbType(dbType);
        }

        if (StrUtil.isNotBlank(authorName)) {
            this.config.setAuthorName(authorName);
        }

    }


    public void execute() {
        autoGenerator.execute();
    }
}
