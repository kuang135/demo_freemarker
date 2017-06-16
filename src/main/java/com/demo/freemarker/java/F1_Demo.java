package com.demo.freemarker.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.kerneler.com/freemarker2.3.23/
 */
public class F1_Demo {

    @Test
    public void demo() throws IOException, TemplateException {
        //创建configuration实例, 不需要重复创建 Configuration 实例； 它的代价很高，尤其是会丢失缓存。Configuration 实例就是应用级别的单例
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        //mvn项目resource下的目录
        String file = this.getClass().getClassLoader().getResource("templates").getFile();
        config.setDirectoryForTemplateLoading(new File(file));
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //创建数据模型
        Map<String, String> root = new HashMap<String, String>();
        root.put("user", "老高");

        //获取模版，获取之后，可以将它和不同的数据模型进行不限次数的合并
        Template template = config.getTemplate("f1_demo.ftl");

        //合并模版和数据模型
        Writer console = new OutputStreamWriter(System.out);
        template.process(root, console);

        //out为file类型时，必须关闭
        root.put("user", "java");
		Writer fileOut = new OutputStreamWriter(new FileOutputStream(new File("f1_demo.java")));
        template.process(root, fileOut);
        fileOut.close();

        //如果是servle他，无须关闭
    }

}
