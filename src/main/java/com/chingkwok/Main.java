package com.chingkwok;

import com.alibaba.druid.pool.DruidDataSource;
import com.chingkwok.entity.Project;
import com.chingkwok.entity.Table;
import com.chingkwok.util.DatabaseUtil;
import com.chingkwok.util.FileUtils;
import com.chingkwok.util.GeneratorUtil;
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

    public static void main(String[] args) throws IOException {
        Project project = new Project();
        project.setPackageName("com.chingkwok");
        project.setProjectCode("autogenerator");
        project.setPort("3306");
        project.setDatasourceName("auto_generator");
        project.setUsername("root");
        project.setIpAddress("134.175.6.168");
        project.setPassword("a602b854-8218-4857-b22c-f733bcd52671");
        long time = new Date().getTime();
        String dir = System.getProperty("user.dir");
        String s = dir + SEPARATOR + "targetFile" + SEPARATOR + time;
        GeneratorUtil.generate(TEMPLATE_PATH, s, project);
//        // step1 创建freeMarker配置实例
//        Configuration configuration = new Configuration();
//        Writer out = null;
//        String url = "jdbc:mysql://134.175.6.168:3306/auto_generator";
//        String username = "root";
//        String password = "a602b854-8218-4857-b22c-f733bcd52671";
//        // 获取表结构
//        List<Table> allTable = DatabaseUtil.getAllTable(url, username, password);
//        // 获取project信息
//        Project project = new Project();
//        project.setPackageName("com.chingkwok");
//        project.setProjectCode("autogenerator");
//
//        String projectPath = System.getProperty("user.dir");
//        File file = new File(projectPath + SEPARATOR + "targetFile");
//        FileUtils.deleteFile(file);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        String time = String.valueOf(new Date().getTime());
//        File file1 = new File(projectPath + SEPARATOR + "targetFile" + SEPARATOR + time);
//        if (file1.exists()) {
//            FileUtils.deleteFile(file1);
//        }
//        file1.mkdir();
//        for (Table t : allTable
//        ) {
//            try {
//                // 获取模版路径
//                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
//                // 创建数据模型
//                Map<String, Object> dataMap = new HashMap<>();
//                dataMap.put("table", t);
//                dataMap.put("project", project);
//                // 加载mapper模版文件
//                Template template = configuration.getTemplate("mapper.ftl");
//                // 生成数据
//                File mapperFile = new File(file1.getCanonicalPath() + SEPARATOR + "mapper_xml");
//                mapperFile.mkdir();
//                File docFile = new File(mapperFile.getCanonicalPath() + SEPARATOR + t.getEntityName() + "Mapper.xml");
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
//                // 输出文件
//                template.process(dataMap, out);
//                System.out.println("文件创建成功 !");
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (null != out) {
//                        out.flush();
//                    }
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
//        }

    }

}
