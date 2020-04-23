package csa.soft.webtag.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件帮助类
 * @author csa
 *
 */
public abstract class FileUtil {
	
	public static boolean exists(String path){
		return new File(path).exists();
	}
	
	public static boolean createDirs(String path){
		return createDirs(new File(path));
	}
	
	public static boolean createDirs(File file){
		if(!file.exists()){
			return file.mkdirs();
		}
		return false;
	}
	
	public static boolean createFile(String path){
		return createFile(new File(path));
	}
	
	public static boolean createFile(File file){
		if(!file.exists()){
			createDirs(file.getParentFile());
			try {
				return file.createNewFile();
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}
		return false;
	}
	
	

}
