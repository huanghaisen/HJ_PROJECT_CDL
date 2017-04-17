package com.framework.util;

import java.io.File;

public class Utils {
	
   /**
	 * 删除文件
	 * @param filePath
	 */
	public static void delDF(File delFile) {
		if (delFile.exists()) {
			if (delFile.isFile()) {
				delFile.delete();
			} else if (delFile.isDirectory()) {
				File files[] = delFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					Utils.delDF(files[i]);
				}
			}
			delFile.delete();
		} else {
			
		}
	}
	
	/**
	 * 删除文件文件夹
	 * @param filePath
	 */
	public static void delDirectoryAndFile(String filePath) {
		if(filePath!=null&&filePath!=""){
		File delFile = new File(filePath);
			if(delFile.exists()){
				try {
					delDF(delFile);
				} catch (RuntimeException e) {
					System.out.println("删除文件操作出错");
					e.printStackTrace();
				}
			}
		}
	}
	
	 /**
     * 添加本地文件夹
     * @param fileName
     * @return
     */
    public static int addFile(String fileName){
		File file = new File(fileName);
		int count = 0;
		try {
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			count = 1;
		} catch (Exception e) {
		}
		return count;

    }
}
