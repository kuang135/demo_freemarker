package com.demo.freemarker.java;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;

public class F3_Config {

    private static Configuration config;

    private F3_Config() {
    }

    //创建configuration实例, 不需要重复创建 Configuration 实例； 它的代价很高，尤其是会丢失缓存。Configuration 实例就是应用级别的单例
    public static Configuration getConfig() {
        if (config != null) {
            return config;
        }
        try {
            config = new Configuration(Configuration.VERSION_2_3_23);
            //mvn项目resource下的目录
            String file = F3_Config.class.getClassLoader().getResource("templates").getFile();
            config.setDirectoryForTemplateLoading(new File(file));
            config.setDefaultEncoding("UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }


}
