package csa.soft.webtag.ui.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * 
 * @author csa
 *
 */
public class MenuBarView extends BaseMenuBar{
	private JMenu workMenu=new JMenu("功能区");

	public MenuBarView() {
		super();
		initView();
	}
	
	
	private void initView(){
		setVisible(true);
		addItem(workMenu, new JMenuItem("我的标签"), e->{
			
		});
		add(workMenu);
	}
	
	

}
