package csa.soft.webtag.ui.view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 功能菜单
 * @author csa
 *
 */
public class PopMenu extends JPopupMenu{
	private JMenuItem look;
	private JMenuItem edit;
	private JMenuItem del;
	
	public PopMenu() {
		super();
		init();
	}
	public PopMenu(String label) {
		super(label);
		init();
	}
	
	private void init(){
		look=new JMenuItem("查看");
		edit=new JMenuItem("编辑");
		del=new JMenuItem("删除");
		
		
	}

}
