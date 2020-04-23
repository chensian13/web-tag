package csa.soft.webtag.common.util;

import java.util.Collection;

/**
 * 
 * @author csa
 *
 */
public abstract class EmptyUtil {
	
	public static boolean isEmpty(Object obj){
		if(obj==null) return true;
		if(obj instanceof String){
			String str=(String)obj;
			if(str.trim().equals("")){
				return true;
			}
		}
			
		if(obj instanceof Collection){
			Collection col=(Collection)obj;
			if(col.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEmpty(Object...objs){
		if(objs==null 
				|| objs.length==0){
			return true;
		}
		return false;
	}

}
