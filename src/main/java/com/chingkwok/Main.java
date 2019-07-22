package com.chingkwok;

import com.alibaba.druid.pool.DruidDataSource;
import com.chingkwok.entity.Project;
import com.chingkwok.entity.Table;
import com.chingkwok.util.DatabaseUtil;
import com.chingkwok.util.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guojingye on 2019/7/18
 */
public class Main {
    private static final String TEMPLATE_PATH = "src/main/java/com/chingkwok/templates";
    private static final String CLASS_PATH = "src/main/java/com/chingkwok/targetFile";
    private static final String SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        String url = "jdbc:mysql://134.175.6.168:3306/auto_generator";
        String username = "root";
        String password = "a602b854-8218-4857-b22c-f733bcd52671";
        // 获取表结构
        List<String> user = DatabaseUtil.getPrimaryKey(url, username, password, "user");

        List<Table> allTable = DatabaseUtil.getAllTable(url, username, password);
        // 获取project信息
        Project project = new Project();
        project.setPackageName("com.chingkwok");
        project.setProjectCode("autogenerator");
        for (Table t : allTable
        ) {
            try {
                // step2 获取模版路径
                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
                // step3 创建数据模型
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("table", t);
                dataMap.put("project", project);
                // step4 加载模版文件
                Template template = configuration.getTemplate("mapper.ftl");
                // step5 生成数据
                String projectPath = System.getProperty("user.dir");
                File file = new File(projectPath + SEPARATOR + "targetFile");
                FileUtils.deleteFile(file);
                if (!file.exists()) {
                    file.mkdir();
                }
                String time = String.valueOf(new Date().getTime());
                File file1 = new File(projectPath + SEPARATOR + "targetFile" + SEPARATOR + time);
                if (file1.exists()) {
                    FileUtils.deleteFile(file1);
                }
                file1.mkdir();

                File docFile = new File(file1.getCanonicalPath() + SEPARATOR + "Mapper.xml");
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                // step6 输出文件
                template.process(dataMap, out);
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != out) {
                        out.flush();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

    }

}
