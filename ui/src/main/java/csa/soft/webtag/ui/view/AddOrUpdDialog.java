package csa.soft.webtag.ui.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import csa.soft.webtag.common.entity.WebTag;
import csa.soft.webtag.common.util.EmptyUtil;
import csa.soft.webtag.common.util.StringUtil;
import csa.soft.webtag.ui.util.DialogUtil;
import csa.soft.webtag.ui.util.SizeUtil;

/**
 * 新增节点弹窗
 * @author csa
 *
 */
public class AddOrUpdDialog extends JDialog{
	private JLabel lurl,lusername,lpass,lname,larea;
	private JTextField turl,tusername,tpass,tname,tarea;
	private JButton bSave;
	private TagTreeView tagTreeView;
	private boolean save=true; //默认保存
	private JPanel panel1,panel2,panel3,panel4,panel5,panelArea;

	public AddOrUpdDialog(String title) {
		super();
		setTitle(title);
		init();
	}

	public AddOrUpdDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setTitle(title);
		init();
	}
	
	public void emptyForm(){
		tname.setText(null);
		turl.setText(null);
		tusername.setText(null);
		tpass.setText(null);
	}
	
	public AddOrUpdDialog setSave(boolean save) {
		this.save = save;
		return this;
	}
	
	public AddOrUpdDialog showData(WebTag webTag) {
		tname.setText(webTag.getTagName());
		turl.setText(webTag.getUrl());
		tusername.setText(webTag.getUsername());
		tpass.setText(webTag.getPassword());
		tarea.setText(webTag.getInfo());
		return this;
	}

	public AddOrUpdDialog setTagTreeView(TagTreeView tagTreeView) {
		this.tagTreeView = tagTreeView;
		return this;
	}

	/**
	 * 组件初始化
	 */
	private void init(){
		panel1=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel2=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel3=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel4=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel5=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelArea=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		lname=new JLabel("名称");
		lurl=new JLabel("网址");
		lusername=new JLabel("用户");
		lpass=new JLabel("密码");
		larea=new JLabel("描述");
		
		tname=new JTextField(20);
		turl=new JTextField(20);
		tusername=new JTextField(20);
		tpass=new JTextField(20);
		tarea=new JTextField(20);
		
		bSave=new JButton("保存");
		
		setLayout(new GridLayout(6, 1));
		panel1.add(lname);
		panel1.add(tname);
		panel2.add(lurl);
		panel2.add(turl);
		panel3.add(lusername);
		panel3.add(tusername);
		panel4.add(lpass);
		panel4.add(tpass);
		panel5.add(bSave);
		panelArea.add(larea);
		panelArea.add(tarea);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panelArea);
		add(panel5);
		
		bSave.setBackground(Color.black);
		bSave.setForeground(Color.white);
		
		int w=SizeUtil.getScreenWidth();
		int h=SizeUtil.getScreenHeight();
		setLocation((w-320)/2,(h-320)/2);
		pack();
		
		bSave.addActionListener(e->{
			try{
				if(tagTreeView==null 
						|| tagTreeView.getNowTagId()==null) {
					return;
				}
				if(EmptyUtil.isEmpty(tname.getText())) throw new RuntimeException("标签名称不能为空");
				if(save){
					WebTag webTag=new WebTag();
					webTag.setId(StringUtil.generateUUID32());
					webTag.setTime(System.currentTimeMillis());
					webTag.setTagName(tname.getText());
					webTag.setUrl(turl.getText());
					webTag.setUsername(tusername.getText());
					webTag.setPassword(tpass.getText());
					webTag.setParentId(tagTreeView.getNowTagId());
					webTag.setInfo(tarea.getText());
					tagTreeView.addNode(webTag);
				}else{
					WebTag webTag=tagTreeView.getNodeUserObject(tagTreeView.getNode(tagTreeView.getNowTagId()));
					webTag.setTagName(tname.getText());
					webTag.setUrl(turl.getText());
					webTag.setUsername(tusername.getText());
					webTag.setPassword(tpass.getText());
					webTag.setInfo(tarea.getText());
					tagTreeView.updNode(webTag);
				}
				//执行持久化
				tagTreeView.localIO();
				dispose();
			}catch(Exception ee){
				DialogUtil.info(ee.getMessage());
			}
		});
	}

}
