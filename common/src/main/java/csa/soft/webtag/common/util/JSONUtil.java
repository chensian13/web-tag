package csa.soft.webtag.common.util;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author csa
 *
 */
public abstract class JSONUtil {
	
	public static String format(Object obj){
		if(obj==null) return null;
		return JSON.toJSONString(obj);
	}
	
	public static <T> T parse(String json,Class<T> clazz){
		if(json==null) return null;
		return JSON.parseObject(json, clazz);
	}
	
	public static boolean isJSONString(String json){
		return JSON.isValid(json);
	}
	
	public static JSONObject parseJSONObject(String json){
		if(json==null) return null;
		return JSON.parseObject(json);
	}
	
	public static JSONObject parseJSONObject(Object obj){
		if(obj==null) return null;
		JSONObject jsonObject=new JSONObject();
		parseJSONObject(obj, jsonObject);
		return jsonObject;
	}
	
	public static void parseJSONObject(Object obj,JSONObject jsonObject){
		if(obj==null || jsonObject==null) return ;
		try{
			Method[] methods=obj.getClass().getMethods();
			for(Method method:methods){
				String fieldName=StringUtil.getFieldName(method.getName());
				if(fieldName!=null){
					jsonObject.put(fieldName,method.invoke(obj, null));
				}					
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
