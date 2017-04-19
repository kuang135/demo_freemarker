package com.demo.freemarker.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.kerneler.com/freemarker2.3.23/
 */
public class F1_Type {

    @Test
    public void scalar() throws IOException, TemplateException {
        Map<String, String> root = new HashMap<String, String>();
        root.put("user", "老高");

        //加载模板文件
        Configuration config = F1_Config.getConfig();
        Template template = config.getTemplate("demo.ftl");

        //显示生成的数据,将合并后的数据打印到控制台
        Writer out = new OutputStreamWriter(System.out);
        //可以用于生成java文件
//		Writer out = new OutputStreamWriter(new FileOutputStream(new File("demo.java")));
        template.process(root, out);
        out.close();
    }

}
