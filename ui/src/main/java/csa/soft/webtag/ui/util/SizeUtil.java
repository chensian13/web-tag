package csa.soft.webtag.ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 视图大小获取
 * @author csa
 *
 */
public abstract class SizeUtil {
	
	/**
	 * 获取屏幕大小
	 * @return
	 */
	public static int[] getScreenSize(){
		int[] size=new int[2];
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		size[0] = screensize.width;
		size[1] = screensize.height;
		return size;
	}
	
	public static int getScreenWidth(){
		return getScreenSize()[0];
	}
	
	public static int getScreenHeight(){
		return getScreenSize()[1];
	}

}
