//package springboottools.MPG;
//
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
//import com.baomidou.mybatisplus.generator.config.po.TableField;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//public class Generator {
//
//    public static void main(String[] args) {
//        final String path = Thread.currentThread().getClass().getResource("/").getPath().substring(1)
//                                    .replace("/classes/", "") + "/code-generated";
//
//        File file = new File(path) ;
//        if (!file.exists()) {
//            file.mkdir() ;
//        }
//
//        //path = path.replace("/classes/", "");
//        FastAutoGenerator.create("jdbc:mysql://rm-bp18049g2j4s6eqc9do.mysql.rds.aliyuncs.com:3306/lam-bop-dev?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true", "link_saas_dev", "LinkSaaS2022!")
//            .globalConfig(builder -> {
//                builder.author("Virgil.Chen") // 设置作者
//                        .enableSpringdoc()
//                    //.enableSwagger() // 开启 swagger 模式
//                    //fileOverride() // 覆盖已生成文件
//                    //.outputDir("D:\\Projects\\ioa-generator\\target"); // 指定输出目录
//                    .dateType(DateType.ONLY_DATE)
//                    .outputDir(path) ;
//            })
//            .packageConfig(builder -> {
//                builder.parent("com.nwcs.lam") // 设置父包名
//                    .moduleName("bop") // 设置父包模块名
//                    .entity("entity")
//                    .service("service")
//                    .serviceImpl("service.impl")
//                    .mapper("dao")
//                    .xml("mapper.xml")
//                    .controller("controller")
//                    //.other("other")
//                    //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Projects\\ioa-generator\\target")); // 设置mapperXml生成路径
//                    .pathInfo(Collections.singletonMap(OutputFile.xml, path)); // 设置mapperXml生成路径
//            })
//            .strategyConfig(builder -> {
//                builder.addInclude("bop_crm_config") // 设置需要生成的表名
//                .addInclude("bop_role_user_rel")
//                .addInclude("bop_user")
//                .addInclude("bop_user_position_rel")
//                .addInclude("bop_wechat_user")
//                    .addTablePrefix("bop_"); // 设置过滤表前缀
//                    //.formatMapperFileName("%sDao")
//                builder.mapperBuilder().formatMapperFileName("%sDao");
//                builder.entityBuilder().enableFileOverride().enableTableFieldAnnotation().enableLombok().logicDeleteColumnName("deleted");
//                builder.serviceBuilder().enableFileOverride();
//                builder.controllerBuilder().enableFileOverride().enableRestStyle();
//                builder.mapperBuilder().enableFileOverride();
//            })
//            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//            .templateConfig(builder -> {
//                builder
//                    .entity("/templates/lam/entity.java")
//                    .service("/templates/lam/service.java")
//                    .serviceImpl("/templates/lam/serviceImpl.java")
//                    .mapper("/templates/lam/mapper.java")
//                    .xml("/templates/lam/mapper.xml")
//                    .controller("/templates/lam/controller.java");
//            }).injectionConfig(builder -> {
//                builder.beforeOutputFile((tableInfo, objectMap) -> {
//                    Iterator<TableField> iterator = tableInfo.getFields().iterator() ;
//                    List<String> skipCols = new ArrayList<String>() ;
//                    skipCols.add("created_on") ;
//                    skipCols.add("created_at") ;
//                    skipCols.add("created_by") ;
//                    skipCols.add("updated_on") ;
//                    skipCols.add("updated_at") ;
//                    skipCols.add("updated_by") ;
//                    skipCols.add("versions") ;
//                    skipCols.add("id") ;
//
//                    while (iterator.hasNext()) {
//                        TableField tf = iterator.next() ;
//
//                        if (skipCols.contains(tf.getColumnName())) {
//                            iterator.remove();
//                        }
//                    }
//
//                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
//                });
//                builder.customFile(new CustomFile.Builder().fileName("SO.java").templatePath("/templates/lam/entitySO.java.ftl").packageName("so").enableFileOverride().build()) ;
//                builder.customFile(new CustomFile.Builder().fileName("Result.java").templatePath("/templates/lam/entityResult.java.ftl").packageName("result").enableFileOverride().build()) ;
//                builder.customFile(new CustomFile.Builder().fileName("Dto.java").templatePath("/templates/lam/entityDto.java.ftl").packageName("dto").enableFileOverride().build()) ;
//
//            })/*
//            .injectionConfig(builder -> {
//                builder.beforeOutputFile((tableInfo, objectMap) -> {
//                    objectMap.
//                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
//                });
//            }) */
//            .execute();
//
//        System.out.println(path);
//    }
//}
