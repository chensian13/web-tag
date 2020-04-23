package csa.soft.webtag.common.entity;

import csa.soft.webtag.common.util.EmptyUtil;

/**
 * WebTag树结构操作节点帮助类
 * @author csa
 *	暂未使用
 */
public abstract class WebTagTreeHelper {
	
	/**
	 * 依据id查询节点
	 * @param id
	 */
	public static WebTag getNodeById(String id,WebTag webTag){
		if(EmptyUtil.isEmpty(id,webTag)) return null;
		return getNodeByIdCircle(id,webTag);
	}
	/**
	 * 
	 * @param id
	 * @param webTag
	 * @return
	 */
	private static WebTag getNodeByIdCircle(String id,WebTag webTag){
		if(id.equals(webTag.getId())){
			return webTag;
		}
		if(EmptyUtil.isEmpty(webTag.getWebTags())) return null;
		WebTag tagObj=null;
		for(WebTag tag:webTag.getWebTags()){
			tagObj=getNodeByIdCircle(id,tag);
			if(tagObj!=null) break;
		}
		return tagObj;
	}
	
	/**
	 * 新增节点，指定父节点
	 * @param parentId
	 * @param webTag
	 * @return
	 */
	public static boolean addNode(WebTag webTag,WebTag root){
		if(EmptyUtil.isEmpty(webTag.getParentId(),root)) return false;
		WebTag pTag=getNodeById(webTag.getParentId(),root);
		if(pTag==null) return false;
		return pTag.initWebTags().add(root);
	}

	/**
	 * 更新节点
	 * @param id
	 * @param webTag
	 * @return
	 */
	public static boolean updNode(String id,WebTag webTag){
		if(EmptyUtil.isEmpty(id,webTag)) return false;
		WebTag tag=getNodeById(id,webTag);
		if(tag==null) return false;
		tag.setId(webTag.getId());
		tag.setParentId(webTag.getParentId());
		tag.setPassword(webTag.getPassword());
		tag.setTagName(webTag.getTagName());
		tag.setTime(webTag.getTime());
		tag.setUrl(webTag.getUrl());
		tag.setUsername(webTag.getUsername());
		tag.setWebTags(webTag.getWebTags());
		return true;
	}
	
	/**
	 * 删除节点
	 * @param id
	 * @param webTag
	 * @return
	 */
	public static WebTag delNode(String id,WebTag webTag){
		if(EmptyUtil.isEmpty(id,webTag)) return null;
		WebTag tag=getNodeById(id, webTag);
		if(tag==null) return null;
		WebTag pTag=getNodeById(tag.getParentId(), webTag);
		if(pTag==null || pTag.getWebTags()==null) return null;
		pTag.getWebTags().remove(tag);
		return tag;
	}
	

}
