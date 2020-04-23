package csa.soft.webtag.ui.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import csa.soft.webtag.common.entity.WebTag;
import csa.soft.webtag.ui.util.BrowseUtil;
import csa.soft.webtag.ui.util.ClipboardUtil;
import csa.soft.webtag.ui.util.DialogUtil;
import csa.soft.webtag.ui.util.SizeUtil;

/**
 * 查看节点弹窗
 * @author csa
 *
 */
public class LookDialog extends JDialog{
	private JLabel lurl,lusername,lpass,lname,linfo;
	private JTextField turl,tusername,tpass,tname,tinfo;
	private JButton bGo;
	private TagTreeView tagTreeView;
	private JFrame frame;
	private JPanel panel1,panel2,panel3,panel4,panel5,panelInfo;

	public LookDialog(String title) {
		super();
		setTitle(title);
		init();
	}

	public LookDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setTitle(title);
		init();
	}
	
	public void emptyForm(){
		tname.setText(null);
		turl.setText(null);
		tusername.setText(null);
		tpass.setText(null);
		tinfo.setText(null);
	}
	
	
	public LookDialog showData(WebTag webTag) {
		tname.setText(webTag.getTagName());
		turl.setText(webTag.getUrl());
		tusername.setText(webTag.getUsername());
		tpass.setText(webTag.getPassword());
		tinfo.setText(webTag.getInfo());
		return this;
	}

	public LookDialog setTagTreeView(TagTreeView tagTreeView) {
		this.tagTreeView = tagTreeView;
		return this;
	}
	
	public LookDialog setFrame(JFrame frame) {
		this.frame = frame;
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
		panelInfo=new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		lname=new JLabel("名称");
		lurl=new JLabel("网址");
		lusername=new JLabel("用户");
		lpass=new JLabel("密码");
		linfo=new JLabel("描述");
		
		tname=new JTextField(20);
		turl=new JTextField(20);
		tusername=new JTextField(20);
		tpass=new JTextField(20);
		tinfo=new JTextField(20);
		
		tname.setEditable(false);
		turl.setEditable(false);
		tusername.setEditable(false);
		tpass.setEditable(false);
		tinfo.setEditable(false);
		
		bGo=new JButton("前往");
		
		
		setLayout(new GridLayout(6, 1));
		panel1.add(lname);
		panel1.add(tname);
		panel2.add(lurl);
		panel2.add(turl);
		panel3.add(lusername);
		panel3.add(tusername);
		panel4.add(lpass);
		panel4.add(tpass);
		panel5.add(bGo);
		panelInfo.add(linfo);
		panelInfo.add(tinfo);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panelInfo);
		add(panel5);
		
		bGo.setBackground(Color.black);
		bGo.setForeground(Color.white);
		
		int w=SizeUtil.getScreenWidth();
		int h=SizeUtil.getScreenHeight();
		setLocation((w-320)/2,(h-320)/2);
		pack();
		
		bGo.addActionListener(e->{
			try{
				BrowseUtil.defaultBrowse(turl.getText());
			}catch(Exception ee){
				DialogUtil.info(ee.getMessage());
			}
		});
		tname.addMouseListener(new ClipListener(tname));
		turl.addMouseListener(new ClipListener(turl));
		tpass.addMouseListener(new ClipListener(tpass));
		tusername.addMouseListener(new ClipListener(tusername));
		tinfo.addMouseListener(new ClipListener(tinfo));
	}
	
	
	private class ClipListener implements MouseListener{
		private JTextField textField;

		public ClipListener(JTextField textField) {
			super();
			this.textField = textField;
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			try{
				new ClipboardUtil(frame).setContent(textField.getText());
			}catch(Exception ee){
				ee.printStackTrace();
				DialogUtil.info(ee.getMessage());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

}
