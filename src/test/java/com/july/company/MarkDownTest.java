package com.july.company;

import com.july.doc.JulyDoc;
import com.july.doc.config.DocLanguage;
import com.july.doc.entity.ApiConfig;
import com.july.doc.entity.SourceCodePath;
import com.july.doc.showdoc.JulyShowDocUtil;
import com.july.doc.showdoc.ShowDoc;
import com.july.doc.showdoc.ShowDocModel;
import org.junit.Test;

import java.util.List;

/**
 * 自动生成接口文档
 * @author zqk
 * @since 2020/01/06
 */
public class MarkDownTest {

    /**
     * showdoc项目唯一标识
     */
    private static final String app_key = "58d684ed2b7fed88883624b4a45e8f911422647872";
    /**
     * showdoc项目唯一token
     */
    private static final String app_token = "69d7a70741728cb223d257c5966d6a541678184484";
    /**
     * show doc部署地址
     */
    private static final String showdoc_url = "https://www.showdoc.cc";

    /**
     * 生成一个控制器或者控制器里面的一个API
     * @throws Exception
     * @author zqk
     * @since 2020/01/06
     */
    @Test
    public void generateDocApi() {
        ApiConfig config = new ApiConfig();
        //设置接口文档生成的语言格式，默认为：中文
        config.setLanguage(DocLanguage.CHINESE);
        //接口地址，不设置默认为：http://localhost:8080
        config.setServerUrl("http://127.0.0.1:9001/finance");
        //指定控制器
        config.setControllerName("com.july.company.controller.FinanceCompanyController");
        //指定控制器里面的那个接口
        config.setMethodName("getFinanceCompany");
        //show doc错误代码列表页面地址
        //config.setErrorUrl("http://api-doc.sjhh-inner.com/web/#/73?page_id=2872");
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePath(SourceCodePath.path().setPath("src/main/java"));
        //是否生成本地markdown文件，为false时不生成本地文件，把文件上传到showdoc上
        //config.setIsOpenLocal(true);
        //放在showdoc的哪个目录
        config.setFolder("股权融资");
        //生成本地接口文档的保存地址
        config.setOutPath("./target/doc");

        //获取所有的Markdown文件
        List<ShowDocModel> showDocModels = JulyDoc.generateDocApi(config);
        //上传markdown文档至show doc
        JulyShowDocUtil.doPost(new ShowDoc(config.getIsOpenLocal(), showdoc_url, app_key, app_token, showDocModels));
    }

    /**
     * 生成一个控制器或者控制器里面的一个API
     * @throws Exception
     * @author zqk
     * @since 2020/01/06
     */
    @Test
    public void generateHtmlApi() {
        ApiConfig config = new ApiConfig();
        //设置接口文档生成的语言格式，默认为：中文
        config.setLanguage(DocLanguage.CHINESE);
        //接口地址，不设置默认为：http://localhost:8080
        config.setServerUrl("http://area-service.tcampus.local/teacherleave");
        //show doc错误代码列表页面地址
        config.setErrorUrl("http://api-doc.sjhh-inner.com/web/#/73?page_id=2872");
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePath(SourceCodePath.path().setPath("src/main/java"));
        config.setOutPath("./target/doc");
        JulyDoc.generateHtmlApi(config);
    }

}
