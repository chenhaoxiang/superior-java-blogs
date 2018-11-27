/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author chenhx
 * @version FileUtils.java, v 0.1 2018-11-27 下午 11:57 chenhx
 */
public class FileUtils {
    /**
     * 获取当前路径下所有文件
     *
     * @param path
     * @return
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<>();
        if (path == null) {
            return files;
        }
        File file = new File(path);
        if (!file.exists()) {
            return files;
        }
        File[] tempList = file.listFiles();
        if (tempList == null) {
            return files;
        }
        for (File aTempList : tempList) {
            if (aTempList.isFile()) {
                files.add(aTempList.toString());
            } else if (aTempList.isDirectory()) {
                files.addAll(getFiles(aTempList.getPath()));
            }
        }
        return files;
    }

    /**
     * 递归取到当前目录所有文件
     *
     * @param dir
     * @return
     */
    public static List<String> getFilesName(String dir) {
        List<String> lstFiles = new ArrayList<>();
        File[] files = new File(dir).listFiles();
        if (files == null) {
            return lstFiles;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                lstFiles.addAll(getFilesName(f.getAbsolutePath()));
            } else {
                lstFiles.add(f.getAbsolutePath());
            }
        }
        return lstFiles;
    }

    /**
     * 创建目录
     *
     * @param destFilePath
     * @return
     */
    public static boolean createDir(String destFilePath) {
        File file = new File(destFilePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 移除路径下文件
     *
     * @param path
     */
    public static void removeFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }

    public static void main(String[] args) {
        System.out.println(getFiles("D:\\github\\alidayu-tools"));

    }

}