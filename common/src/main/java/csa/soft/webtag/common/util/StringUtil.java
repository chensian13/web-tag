package csa.soft.webtag.common.util;

import java.util.UUID;

/**
 * 
 * @author csa
 *
 */
public abstract class StringUtil {
	
	/**
	 * 32位uuid
	 * @return
	 */
	public static String generateUUID32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
	/**
	 * get方法对应的属性名称
	 * @return
	 */
	public static String getFieldName(String getMethodName){
		if(getMethodName==null
				|| !getMethodName.startsWith("get")
				|| getMethodName.startsWith("getClass")
				|| getMethodName.length()<4) return null;
		StringBuffer sb=new StringBuffer(getMethodName.substring(3, 4).toLowerCase());
		sb.append(getMethodName.subSequence(4,getMethodName.length()));
		return sb.toString();
	}

}
