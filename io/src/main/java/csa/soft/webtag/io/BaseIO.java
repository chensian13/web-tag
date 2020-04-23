package csa.soft.webtag.io;

import java.io.Closeable;

/**
 * 阻塞流基础类
 * @author csa
 *
 */
public abstract class BaseIO {
	
	/**
	 * 关闭流
	 * @param stream
	 */
	protected void closeStream(Closeable stream) {
		try {
			stream.close();
		}catch(Exception e){			
		} finally {
			stream=null;
		}
	}
	
	/**
	 * 关闭流
	 * @param stream
	 */
	protected void closeStream(Closeable in,Closeable out) {
		closeStream(in);
		closeStream(out);
	}
	
	
	
	

}
