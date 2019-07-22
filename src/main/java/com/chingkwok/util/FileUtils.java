package com.chingkwok.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by guojingye on 2019/7/22
 */
public class FileUtils {
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
