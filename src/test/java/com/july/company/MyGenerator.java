package com.july.company;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyGenerator {

    private String root;  //项目的根路径，这个是自动获取的。不用填写值。
    private String author = "zengxueqi";  //代码注释中的作者
    private String url = "jdbc:mysql://127.0.0.1:3306/finance?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private String username = "root";
    private String password = "root";
    private String moduleName = null; //子模块的名称
    private String basePackageName = "com.july.company"; //基础包的名称
    private String[] tablePrefix = {"fc"};
    private DbType dbType = DbType.MYSQL; //数据库类型
    private String driverName = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        MyGenerator myGenerator = new MyGenerator();
        myGenerator.testGenerator();
    }

    @Test
    public void testGenerator() {
        root = System.getProperty("user.dir"); //得到项目根路径
        log.info("代码生成开始.");
        AutoGenerator mpg = new AutoGenerator();
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());// 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new BeetlTemplateEngine()); //使用Beetl引擎
        mpg.setTemplate(getTemplateConfig());
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setDataSource(getDataSourceConfig());
        mpg.setPackageInfo(getPackageConfig());
        mpg.setStrategy(getStrategyConfig());
        mpg.setCfg(getInjectionConfig(mpg.getPackageInfo()));
        mpg.execute();// 执行生成
        log.info("代码生成完毕.");
    }

    /**
     * 全局配置.
     * @return 全局配置
     */
    public GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(author);
        gc.setOutputDir(root + "/src/main/java");
        gc.setFileOverride(true);  // 是否覆盖同名文件，默认是false
        gc.setActiveRecord(false);   // 不需要ActiveRecord特性的请改为false
        gc.setBaseResultMap(false);  // SQL 映射文件
        gc.setBaseColumnList(false); // SQL 片段
        gc.setOpen(false);          // 是否打开输出目录
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        //gc.setEntityName("%s");
        //gc.setMapperName("%sMapper"); //Mapper接口的命名
        //gc.setXmlName("%sMapper");    //xml文件的命名
        gc.setServiceName("%sService"); //业务接口的命名,默认是I开头的
        gc.setServiceImplName("%sServiceImpl"); //业务实现类的命名
        //gc.setControllerName("%sController"); //控制器的命名
        gc.setIdType(IdType.AUTO);  // 主键策略
        //gc.setDateType(dateType); //时间类型对应策略默认是使用java.time下的包
        gc.setEnableCache(false);  //是否在xml中添加二级缓存配置
        //gc.setKotlin(false);  //开启 Kotlin 模式
        //gc.setSwagger2(true);//开启 swagger2 模式
        return gc;
    }

    /**
     * 包名配置.
     * @return 包名配置
     */
    public PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackageName)  //其对应的getter方法已经把moduleName加上了
                //以下包名都是相对于getParent(),也就是=basePackageName+moduleName下的子包.
                //.setMapper("mapper")            //映射包名:默认为mapper
                //.setEntity("entity")            //实体包名:默认为entity
                //.setService("service")          //业务包名:默认为service
                //.setServiceImpl("service.impl") //实现业务包: 默认为service.impl
                //.setXml("mapper.xml") 		  //SQL文件路径包: 默认为mapper.xml(由于和类混在一起了一般自定义)
                //.setController("controller");   //控制器路径包:默认为controller
                .setModuleName(moduleName);   //主包下的子模块的路径
        return pc;
    }

    /**
     * 指定生成各个文件的模板文件路径配置.
     * 设置为空表示禁用该模板文件路径配置.
     * @return TemplateConfig
     */
    public TemplateConfig getTemplateConfig() {
        TemplateConfig tc = new TemplateConfig();
        //tc.setMapper(null);     //生成映射器接口
        //tc.setController(null);  //生成控制器模块
        // tc.setEntity("...");     	 //生成实体
        //默认会生成到mapper.xml包下面,跟类混一起了
        //关闭默认xml生成，使用 getInjectionConfig()方法指定的路径生成
        tc.setXml(null);
        //tc.setService(null);  //业务类接口
        //tc.setServiceImpl(null);  //业务类实现类
        //tc.setEntityKt(""); //实体Kotlin模式的名称
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        return tc;
    }

    public StrategyConfig getStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //strategy.setSkipView(false);//是否跳过视图
        strategy.setTablePrefix(null);//表前缀有则使用,没有则不用
        //strategy.containsTablePrefix(""); //表名包含指定前缀
        strategy.setTablePrefix(tablePrefix);
        //strategy.setFieldPrefix("");//字段前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);  // 数据库表映射到实体的命名策略,不做任何改变，nochange原样输出 underline_to_camel 下划线转驼峰命名
        //strategy.setColumnNaming(columnNaming);     //数据库表字段映射到实体的命名策略未指定按照naming执行
        strategy.setEntityLombokModel(true);//是否加入lombok
        strategy.setControllerMappingHyphenStyle(false); //设置controller映射联字符
        strategy.setRestControllerStyle(true); //是否生成rest控制器
        strategy.setInclude(getTableName()); // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        //strategy.setExclude(new String[]{"test"}); 	   // 需要排除的表名，允许正则表达式
        strategy.setSuperEntityClass("com.july.company.base.BaseEntity");// 自定义继承的Entity类全称，带包名
        //自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns("updatedTime", "createdTime", "deleted");
        //strategy.includeSuperEntityColumns();//包含超类实体列
        //strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");//自定义继承的Mapper类全称，带包名
        //strategy.setSuperServiceClass("com.baomidou.demo.TestService");// 自定义继承的Service类全称，带包名
        //strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");// 自定义继承的ServiceImpl类全称，带包名
        //strategy.setSuperControllerClass("com.baomidou.demo.TestController");// 自定义继承的Controller类全称，带包名

        //public static final String ID = "test_id";
        //strategy.setEntityColumnConstant(true);//【实体】是否生成字段常量（默认 false）

        //public User setName(String name) {this.name = name; return this;} //
        strategy.setEntityBuilderModel(true);//【实体】是否为构建者模型（默认 false,每个setter放回当前对象。
        //strategy.setEntityBooleanColumnRemoveIsPrefix(false);//Boolean类型字段是否移除is前缀（默认 false）
        strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
        //strategy.setSuperEntityClass(BaseEntity.class);
        //strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符

        //strategy.setVersionFieldName(""); //乐观锁属性名称
        //strategy.setLogicDeleteFieldName("");//逻辑删除属性名称
        return strategy;
    }

    /**
     * 自定义模板生成
     * 调整 xml 生成目录演示
     * 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改
     * 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
     * @return
     */
    public InjectionConfig getInjectionConfig(final PackageConfig pc) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("author", this.getConfig().getGlobalConfig().getAuthor());
                this.setMap(map);
            }
        };
        //自定义生成xml映射文件的位置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.btl") {
            @Override
            public String outputFile(TableInfo tbInfo) {
                String parent = pc.getParent().replace(".", "/"); //已经包含的moduleName
                String child = pc.getMapper().replace(".", "/");  //mapper接口所在的包路径
                return root + "/src/main/resources/"
                        + parent + "/" + child + "/"
                        + tbInfo.getMapperName()     //对应mapper类的名字
                        + StringPool.DOT_XML;

            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    public DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        return dsc;
    }

    @Before
    public void testInitEnvirment() {
        root = System.getProperty("user.dir"); //得到项目根路径
        log.info("项目路径：{}", root);
    }

    @Test
    public String[] getTableName() {
        /*try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = java.sql.DriverManager.getConnection(url, username, password);
            //获取数据库所有表
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(conn.getCatalog(), null, null, new String[]{"TABLE"});
            List<String> tableNameList = new ArrayList<>();
            while (rs.next()) {
                tableNameList.add(rs.getString("TABLE_NAME"));
            }
            return tableNameList.toArray(new String[tableNameList.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new String[]{"fc_sys_role","fc_user_role"};
    }

}
