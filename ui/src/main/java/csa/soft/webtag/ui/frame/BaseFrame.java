package csa.soft.webtag.ui.frame;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import csa.soft.webtag.ui.util.DialogUtil;
import csa.soft.webtag.ui.util.SizeUtil;

/**
 * 框架基类
 * @author csa
 *
 */
public abstract class BaseFrame extends JFrame{
	Logger logger=LoggerFactory.getLogger(BaseFrame.class);

	public BaseFrame() throws HeadlessException {
		super();
		try{
			logger.debug("执行JFrame无参构造器");
			initView();
			init();
		}catch(Exception e){
			e.printStackTrace();
			DialogUtil.info(e.getMessage());
		}
	}

	public BaseFrame(String title) throws HeadlessException {
		super(title);
		try{
			logger.debug("执行JFrame标题构造器");
			initView();
			init();
		}catch(Exception e){
			e.printStackTrace();
			DialogUtil.info(e.getMessage());
		}
	}
	
	/**
	 * 组件初始化
	 */
	private void initView(){
		//默认关闭
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * 设置居中大小
	 */
	public void setCenterSize(int w,int h){
		int[] size=SizeUtil.getScreenSize();
		setSize(w, h);
		//居中
		setLocationRelativeTo(null);
	}
	
	public abstract void init();
	
	public void setIcon(String path){
		System.out.println(BaseFrame.class.getResource(path));
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(BaseFrame.class.getResource(path)));
	}

}
