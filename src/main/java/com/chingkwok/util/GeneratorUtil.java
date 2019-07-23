package com.chingkwok.util;

import com.chingkwok.entity.Project;
import com.chingkwok.entity.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guojingye on 2019/7/23
 */
public class GeneratorUtil {
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String JDBC_PREFIX = "jdbc:mysql://";

    public static void generate(String templateUrl, String targetUrl, Project project) {
        Writer out = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(templateUrl));
            File targetFile = new File(targetUrl);

            targetFile.mkdir();
            // 获取表结构
            String jdbcUrl = getJdbcUrl(project.getIpAddress(), project.getPort(), project.getDatasourceName());
            List<Table> allTable = DatabaseUtil.getAllTable(jdbcUrl, project.getUsername(), project.getPassword());
            //创建mapper_xml
            File beanFile = new File(targetFile + SEPARATOR + "bean");
            File entityFile = new File(beanFile.getCanonicalPath() + SEPARATOR + "entity");
            File mapperFile = new File(targetUrl + SEPARATOR + "mapper_xml");
            Template mapperTemplate = configuration.getTemplate("mapper.ftl");
            Template entityTemplate = configuration.getTemplate("entity.ftl");
            mapperFile.mkdir();
            beanFile.mkdir();
            entityFile.mkdir();
            for (Table t : allTable
            ) {

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("table", t);
                dataMap.put("project", project);

                File docFile = null;
                docFile = new File(mapperFile.getCanonicalPath() + SEPARATOR + t.getEntityName() + "Mapper.xml");
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                mapperTemplate.process(dataMap, out);
                docFile = new File(entityFile.getCanonicalPath() + SEPARATOR + t.getEntityName() + ".java");
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                entityTemplate.process(dataMap, out);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
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

    private static String getJdbcUrl(String ip, String port, String database) {
        return JDBC_PREFIX + ip + ":" + port + "/" + database;
    }
}
