package csa.soft.webtag.ui.util;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;

/**
 * 系统剪贴板
 * @author csa
 *
 */
public class ClipboardUtil {
	private Clipboard clipboard;
	
	public ClipboardUtil(JFrame frame){
		 clipboard = frame.getToolkit().getSystemClipboard();
	}
	
	/**
	 * 
	 * @param text
	 */
	public void setContent(String text){
		StringSelection editText = 
			     new StringSelection(text);
		clipboard.setContents(editText,null);
	}

}
