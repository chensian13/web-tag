package csa.soft.webtag.ui.view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import csa.soft.webtag.common.entity.WebTag;
import csa.soft.webtag.common.util.JSONUtil;
import csa.soft.webtag.io.JSONIO;
import csa.soft.webtag.ui.App;

/**
 * tag标签树
 * @author csa
 *
 */
public class TagTreeView extends JPanel{
	private Logger logger=LoggerFactory.getLogger(TagTreeView.class);
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private WebTag webTag;

	public TagTreeView() {
		super();
		initView();
	}
	
	/**
	 * 组件初始化
	 */
	private void initView(){
		setVisible(true);		
	}
	
	/**
	 * 初始化树的根节点
	 * @param rootTag
	 */
	private void initTree(WebTag rootTag){
		root=new DefaultMutableTreeNode(rootTag);
		treeModel=new DefaultTreeModel(root);
		tree=new JTree(treeModel);
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer)tree.getCellRenderer();
        cellRenderer.setTextNonSelectionColor(Color.black);
        cellRenderer.setTextSelectionColor(Color.blue);
        
        add(tree);
	}
	
	/**
	 * 当前选择的节点下新增节点
	 * @param parent
	 * @param webTag
	 */
	public void addNode(WebTag webTag){
		if(webTag==null) return ;
		if(webTag.getParentId()==null) throw new RuntimeException("请选择一个标签节点");
		logger.debug("新增节点parentId："+webTag.getParentId());
		DefaultMutableTreeNode pNode=getNode(webTag.getParentId());
		if(pNode==null) return ;
		DefaultMutableTreeNode node=new DefaultMutableTreeNode(webTag);
		treeModel.insertNodeInto(node, pNode, pNode.getChildCount());
		node.setParent(pNode); //保存父节点引用
		reloadNode(pNode); //局部刷新
		
		//WebTagTreeHelper.addNode(webTag, this.webTag);
	}
	
	public void expandNode(DefaultMutableTreeNode node){
		tree.expandPath(new TreePath(node.getPath())); //展开节点
	}
	public void collapseNode(DefaultMutableTreeNode node){
		tree.collapsePath(new TreePath(node.getPath())); //展开节点
	}
	/**
	 * 局部刷新
	 * @param node
	 */
	public void reloadNode(DefaultMutableTreeNode node){
		if(node==null){
			node=root;
		}
		collapseNode(node); //折叠
		//之后
		expandNode(node); //展开
	}
	
	
	/**
	 * 获取当前选择节点
	 * @return
	 */
	public String getNowTagId(){
		if(tree.getSelectionPath()==null) return null;
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		if(node==null) return null;
		WebTag webTag=getNodeUserObject(node);
		if(webTag!=null) return webTag.getId();
		return null;
	}
	
	/**
	 * 更新节点
	 * @param id
	 * @param webTag
	 */
	public void updNode(WebTag webTag){
		String id=getNowTagId();
		if(id==null) throw new RuntimeException("请选择一个标签节点");
		logger.debug("修改节点id："+id);
		if(webTag==null) return ;
		DefaultMutableTreeNode node=getNode(id);
		node.setUserObject(webTag);
		reloadNode(node.getParent()==null?null:(DefaultMutableTreeNode)node.getParent());
		
		//WebTagTreeHelper.updNode(id, this.webTag);
	}
	
	public boolean delNode(){
		String id=getNowTagId();
		if(id==null) throw new RuntimeException("请选择一个标签节点");
		logger.debug("删除节点id："+id);
		if(App.rootId.equals(id))throw new RuntimeException("根标签无法删除");
		DefaultMutableTreeNode node=getNode(id);
		if(node.getChildCount()>0)throw new RuntimeException("有子标签的无法直接删除");
		//在删除节点之前先获取节点的父节点
		DefaultMutableTreeNode pNode=(DefaultMutableTreeNode)node.getParent();
		//node.removeFromParent();
		treeModel.removeNodeFromParent(node);
		reloadNode(pNode); //删除后展开父节点
		
		//WebTagTreeHelper.delNode(id, this.webTag);
		return true;
	}
	
	
	/**
	 * 获取节点
	 * @param parent
	 * @param webTag
	 */
	public WebTag getNodeData(String id){
		logger.debug("获取数据节点id："+id);
		if(root==null) return null;
		return getNodeUserObject(getNode(id));
	}
	
	/**
	 * 获取节点
	 * @param parent
	 * @param webTag
	 */
	public DefaultMutableTreeNode getNode(String id){
		if(root==null) return null;
		return getNode(root, id);
	}
	/**
	 * 递归获取节点
	 * @param parent
	 * @param id
	 * @return
	 */
	public DefaultMutableTreeNode getNode(DefaultMutableTreeNode node,String id){
		if(getNodeUserObject(node).getId().equals(id)) return node;
		DefaultMutableTreeNode needNode=null;
		//比较子节点
		for(int i=0;i<node.getChildCount();i++){
			DefaultMutableTreeNode n=(DefaultMutableTreeNode)node.getChildAt(i);
			DefaultMutableTreeNode n2=getNode(n, id);
			if(n2!=null){
				//匹配成功
				return n2;
			}
		}
		return null;
	}
	
	public WebTag getNodeUserObject(DefaultMutableTreeNode node){
		return (WebTag)node.getUserObject();
	}
	
	public WebTag getNodeUserObject(TreeNode node){
		DefaultMutableTreeNode node2=(DefaultMutableTreeNode)node;
		return (WebTag)node2.getUserObject();
	}
	
	/**
	 * 渲染数据
	 * @param webTag
	 */
	public void showData(WebTag webTag){
		showData(webTag, true);
	}
	/**
	 * 递归渲染数据
	 * @param webTag
	 * @param isRoot
	 */
	public void showData(WebTag webTag,boolean isRoot){
		if(webTag==null) return ;
		this.webTag=webTag;
		if(!isRoot){
			addNode(webTag);
		}else{
			//根节点
			initTree(webTag);
		}
		if(webTag.getWebTags()!=null){
			for(WebTag tag:webTag.getWebTags()){
				showData(tag,false);
			}
		}
	}
	/**
	 * 包装数据
	 * @return
	 */
	public JSONObject wrapData(){
		WebTag webTag=getNodeUserObject(root);
		JSONObject jsonObject=new JSONObject();
		wrapData(root,jsonObject);
		return jsonObject;
	}
	
	public String wrapDataJson(){
		return wrapData().toString();
	}
	/**
	 * 递归封装需要持久化的数据
	 * @param node
	 * @param jsonObject
	 * @return
	 */
	public void wrapData(DefaultMutableTreeNode node,JSONObject jsonObject){
		if(node==null) return ;
		JSONUtil.parseJSONObject(getNodeUserObject(node),jsonObject);
		JSONArray list=new JSONArray();
		for(int i=0;i<node.getChildCount();i++){
			DefaultMutableTreeNode n=(DefaultMutableTreeNode)node.getChildAt(i);
			list.add(new JSONObject());
			wrapData(n,list.getJSONObject(i));
		}
		jsonObject.put("webTags", list);
	}

	/**
	 * 持久化
	 */
	public void localIO(){
		//执行持久化
		JSONIO.newInstance().write(wrapDataJson(), App.treeFile);
	}
	
	

}
