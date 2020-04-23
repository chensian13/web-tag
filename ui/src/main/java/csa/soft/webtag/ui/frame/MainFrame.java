package csa.soft.webtag.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import csa.soft.webtag.common.entity.WebTag;
import csa.soft.webtag.common.util.JSONUtil;
import csa.soft.webtag.io.JSONIO;
import csa.soft.webtag.ui.App;
import csa.soft.webtag.ui.util.DialogUtil;
import csa.soft.webtag.ui.util.SizeUtil;
import csa.soft.webtag.ui.view.AddOrUpdDialog;
import csa.soft.webtag.ui.view.LookDialog;
import csa.soft.webtag.ui.view.MenuBarView;
import csa.soft.webtag.ui.view.TagTreeView;

/**
 * app主面板
 * @author csa
 *
 */
public class MainFrame extends BaseFrame{
	private MenuBarView menuBarView;
	private TagTreeView tagTreeView;
	private JPanel btns,treePanel;
	private JScrollPane scrollPane;
	private JButton look,edit,del,add;

	public MainFrame() throws HeadlessException {
		super();
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
	}
	 
	public void init() {
		//菜单栏
		menuBarView=new MenuBarView();
		setJMenuBar(menuBarView);
		
		btns=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		treePanel=new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(btns,BorderLayout.NORTH);
		
		
		look=new JButton("查看");
		add=new JButton("新增");
		edit=new JButton("编辑");
		del=new JButton("删除");
		btns.add(look);
		btns.add(add);
		btns.add(edit);
		btns.add(del);
		
		scrollPane=new JScrollPane(treePanel);
		tagTreeView=new TagTreeView();
		treePanel.add(tagTreeView);
		add(scrollPane,BorderLayout.CENTER);
		
		refreshTree();
		treeListen();
		
		int w=SizeUtil.getScreenWidth()/4;
		setCenterSize(w, (int)(w*1.5));
		
		aboutColor();
		
		setIcon("icon.png");
	}
	
	/**
	 * 颜色设置
	 */
	private void aboutColor(){
		tagTreeView.setBackground(Color.white);
		treePanel.setBackground(Color.white);
		edit.setBackground(Color.black);
		edit.setForeground(Color.white);
		del.setBackground(Color.red);
		del.setForeground(Color.white);
		look.setBackground(Color.orange);
		look.setForeground(Color.white);
		add.setBackground(Color.blue);
		add.setForeground(Color.white);
	}
	
	/**
	 * 刷新树
	 */
	private void refreshTree(){
		WebTag webTag=JSONUtil.parse(JSONIO.newInstance().read(App.treeFile), WebTag.class);
		tagTreeView.showData(webTag);
	}
	
	/**
	 * 树监听
	 */
	private void treeListen(){
		look.addActionListener(e->{
			String id=tagTreeView.getNowTagId();
			if(id==null){
				DialogUtil.info("请选择一个标签");
				return ;
			}
			new LookDialog(this,"查看标签",true)
				.setFrame(this)
				.setTagTreeView(tagTreeView)
				.showData(tagTreeView.getNodeData(id))
				.show();
		});
		add.addActionListener(e->{
			String id=tagTreeView.getNowTagId();
			if(id==null){
				DialogUtil.info("请选择一个标签");
				return ;
			}
			new AddOrUpdDialog(this,"新增标签",true)
				.setTagTreeView(tagTreeView)
				.show();
		});
		edit.addActionListener(e->{
			String id=tagTreeView.getNowTagId();
			if(id==null){
				DialogUtil.info("请选择一个标签");
				return ;
			}
			new AddOrUpdDialog(this,"编辑标签",true)
				.setTagTreeView(tagTreeView)
				.setSave(false)
				.showData(tagTreeView.getNodeData(id))
				.show();
		});
		del.addActionListener(e->{
			try{
				tagTreeView.delNode();
				//执行持久化
				tagTreeView.localIO();
			}catch(Exception ee){
				ee.printStackTrace();
				DialogUtil.info(ee.getMessage());
			}
		});
	}

}
