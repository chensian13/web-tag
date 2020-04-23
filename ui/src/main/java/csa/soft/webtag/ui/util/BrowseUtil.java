package csa.soft.webtag.ui.util;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author csa
 *
 */
public class BrowseUtil {
	
	/**
	 * 打开默认浏览器
	 * @param url
	 * @throws IOException
	 */
	public static void defaultBrowse(String url) throws IOException{
		if(!verifyUrl(url)) throw new RuntimeException("url地址不合法");
		if (java.awt.Desktop.isDesktopSupported()) {
			// 创建一个URI实例
			URI uri=URI.create(url);
            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                // 获取系统默认浏览器打开链接
                dp.browse(uri);
            }     
        }
	}
	
	
	/**
	 * 验证是否是URL
	 * @param url
	 * @return
	 */
	public static boolean verifyUrl(String url){
	    // URL验证规则
	    String regEx ="[a-zA-z]+://[^\\s]*";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(url);
	    // 字符串是否与正则表达式相匹配
	    boolean rs = matcher.matches();
	    return rs;
		
	}

}
