package csa.soft.webtag.ui;

import csa.soft.webtag.common.entity.WebTag;
import csa.soft.webtag.common.util.FileUtil;
import csa.soft.webtag.common.util.JSONUtil;
import csa.soft.webtag.io.JSONIO;
import csa.soft.webtag.ui.frame.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
	public static final String treeFile="data/tree.json";
	public static final String rootId="root";
    public static void main( String[] args ){
    	if(!FileUtil.exists(treeFile)){
    		FileUtil.createFile(treeFile);
    		WebTag webTag=new WebTag();
    		webTag.setId(rootId);
    		webTag.setTagName("标签树");
    		webTag.setTime(System.currentTimeMillis());
    		JSONIO.newInstance().write(JSONUtil.format(webTag), treeFile);
    	}
        MainFrame mainFrame=new MainFrame("web tag");
    }
}
