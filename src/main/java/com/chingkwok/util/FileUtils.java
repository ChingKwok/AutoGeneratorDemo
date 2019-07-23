package com.chingkwok.util;

import com.chingkwok.entity.Project;
import com.chingkwok.entity.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by guojingye on 2019/7/22
 */
public class FileUtils {


    /**
     * 删除文件夹
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(File file) throws IOException {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                deleteFile(new File(file, list[i]));
            }
        }
        return file.delete();
    }


}
