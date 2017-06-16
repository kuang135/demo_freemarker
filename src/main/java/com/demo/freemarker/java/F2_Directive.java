package com.demo.freemarker.java;

import freemarker.core.Environment;
import freemarker.template.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 *  使用 TemplateDirectiveModel 接口在Java代码中实现自定义指令
 */
public class F2_Directive implements TemplateDirectiveModel{
    /*转换为大写*/
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (!params.isEmpty()) {
            throw new TemplateModelException("This directive doesn't allow parameters.");
        }
        if (loopVars.length != 0) {
            throw new TemplateModelException("This directive doesn't allow loop variables.");
        }
        if (body != null) {
            body.render(new UpperCaseFilterWriter(env.getOut()));
        } else {
            throw new RuntimeException("missing body");
        }
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
        root.put("upper", new F2_Directive());

        //获取模版，获取之后，可以将它和不同的数据模型进行不限次数的合并
        Template template = config.getTemplate("f2_directive.ftl");

        //合并模版和数据模型
        Writer console = new OutputStreamWriter(System.out);
        template.process(root, console);
    }


    private static class UpperCaseFilterWriter extends Writer {
        private final Writer out;
        UpperCaseFilterWriter (Writer out) {
            this.out = out;
        }
        public void write(char[] cbuf, int off, int len)
                throws IOException {
            char[] transformedCbuf = new char[len];
            for (int i = 0; i < len; i++) {
                transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
            }
            out.write(transformedCbuf);
        }
        public void flush() throws IOException {
            out.flush();
        }
        public void close() throws IOException {
            out.close();
        }
    }

}
