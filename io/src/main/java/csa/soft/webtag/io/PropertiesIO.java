package csa.soft.webtag.io;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

/**
 * 
 * @author csa
 *
 */
public class PropertiesIO extends TextIO{
	protected String comments="";
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	public Properties load(File file){
		Reader in=null;
		Properties pros=null;
		try{
			pros=new Properties();
			in=getInputStreamReader(file, charSet, bufferSize);
			pros.load(in);
		}catch(Exception e){
			new RuntimeException(e);
		} finally{
			closeStream(in);
		}
		return pros;
	}
	public Properties load(String path){
		return load(new File(path));
	}
	
	
	/**
	 * 
	 * @param pros
	 * @param file
	 */
	public void store(Properties pros,File file){
		Writer out=null;
		try{
			pros=new Properties();
			out=getOutputStreamWriter(file, charSet, bufferSize, append);
			pros.store(out, comments);
		}catch(Exception e){
			new RuntimeException(e);
		} finally{
			closeStream(out);
		}
	}
	public void store(Properties pros,String path){
		store(pros, new File(path));
	}
	
	
	//********************************设置读写属性*******************************
	/**
	 * 设置编码集属性
	 * @param charSet
	 */
	public PropertiesIO setCharSet(String charSet) {
		this.charSet = charSet;
		return this;
	}
	/**
	 * 设置缓冲区大小
	 * @param charSet
	 */
	public PropertiesIO setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
		return this;
	}

	public PropertiesIO setAppend(boolean append) {
		this.append = append;
		return this;
	}
	public PropertiesIO setComments(String comments) {
		this.comments = comments;
		return this;
	}
	

}
