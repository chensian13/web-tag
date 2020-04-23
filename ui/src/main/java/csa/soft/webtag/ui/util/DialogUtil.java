package csa.soft.webtag.ui.util;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author csa
 *
 */
public abstract class DialogUtil {
	
	/**
	 * 默认提示
	 * @param title
	 * @param message
	 */
	public static void info(String message){
		JOptionPane.showMessageDialog(new JPanel(), message, "提示",JOptionPane.INFORMATION_MESSAGE);  
	}
	
	

}
