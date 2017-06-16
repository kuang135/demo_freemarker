package com.demo.freemarker.java;

import freemarker.template.*;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法变量在存于实现了 TemplateMethodModel 接口的模板中, TemplateMethodModelEx 接口扩展了 TemplateMethodModel 接口
 *      这个接口包含一个方法： TemplateModel exec(java.util.List arguments)
 *      形式参数应该直接以 TemplateModel 的形式放进 java.util.List。否则将会以 String 形式放入list
 *      当使用 方法调用表达式 调用方法时，exec 方法将会被调用
 */
public class F2_Method implements TemplateMethodModelEx {

    @Override
    public Object exec(List args) throws TemplateModelException {
        if (args.size() != 2) {
            throw new TemplateModelException("Wrong arguments");
        }
        SimpleScalar s1 = (SimpleScalar) args.get(1);
        SimpleScalar s0 = (SimpleScalar) args.get(0);
        return new SimpleNumber(s1.toString().indexOf(s0.toString()));
    }

    @Test
    public void test() throws IOException, TemplateException {
        //创建configuration实例, 不需要重复创建 Configuration 实例； 它的代价很高，尤其是会丢失缓存。Configuration 实例就是应用级别的单例
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        //mvn项目resource下的目录
        String file = this.getClass().getClassLoader().getResource("templates").getFile();
        config.setDirectoryForTemplateLoading(new File(file));
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //创建数据模型
        Map<String, Object> root = new HashMap<>();
        root.put("indexOf", new F2_Method());

        //获取模版，获取之后，可以将它和不同的数据模型进行不限次数的合并
        Template template = config.getTemplate("f2_method.ftl");

        //合并模版和数据模型
        Writer console = new OutputStreamWriter(System.out);
        template.process(root, console);
    }
}
