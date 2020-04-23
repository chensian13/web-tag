package csa.soft.webtag.ui.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import csa.soft.webtag.ui.util.FontUtil;

/**
 * 
 * @author csa
 *
 */
public class BaseMenuBar extends JMenuBar{

	public BaseMenuBar() {
		super();
		initView();
	}
	
	private void initView(){
		setVisible(true);
	}
	
	
	
	/**
	 * 添加子菜单
	 */
	public void addItem(JMenu jMenu,JMenuItem menuItem,ActionListener actionListener){
		menuItem.addActionListener(actionListener);
		menuItem.setVisible(true);
		menuItem.setFont(FontUtil.getDefaultFont());
		jMenu.add(menuItem);
	}
	
	/**
	 * 添加子菜单加分割符
	 */
	public void addItemWithSeparator(JMenu jMenu,JMenuItem menuItem,ActionListener actionListener){
		addItem(jMenu,menuItem, actionListener);
		jMenu.addSeparator();
	}
	
	


}
