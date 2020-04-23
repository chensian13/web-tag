package csa.soft.webtag.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 文本流
 * @author csa
 *
 */
public class TextIO extends BaseIO{
	//默认UTF8编码
	protected String charSet="UTF-8";
	protected int bufferSize=8*1024;
	protected boolean append=false;
	
	protected TextIO(){}
	
	public static TextIO newInstance(){
		return new TextIO();
	}
	
	
	public void write(String json,OutputStream out){
		Writer osw=null;
		try{
			osw=getOutputStreamWriter(out,charSet,bufferSize,append);
			osw.write(json);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			closeStream(osw);
		} //end finally
	}
	/**
	 * 
	 * @param json
	 * @param objFile
	 */
	public void write(String json,File objFile){
		Writer osw=null;
		try{
			osw=getOutputStreamWriter(objFile,charSet,bufferSize,append);
			osw.write(json);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			closeStream(osw);
		} //end finally
	}
	public void write(String json,String objPath){
		write(json,new File(objPath));
	}
	
	
	/**
	 * 
	 * @param in
	 * @return
	 */
	public String read(InputStream in){
		Reader isr=null;
		StringBuffer sb=new StringBuffer();
		int len=-1;
		char[] cs=new char[1024];
		try{
			isr=getInputStreamReader(in,charSet,bufferSize);
			while((len=isr.read(cs))!=-1){
				sb.append(cs,0,len);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			closeStream(isr);
		} //end finally
		return sb.toString();
	}
	/**
	 * 
	 * @param file
	 * @return
	 */
	public String read(File file){
		Reader isr=null;
		StringBuffer sb=new StringBuffer();
		int len=-1;
		char[] cs=new char[1024];
		try{
			isr=getInputStreamReader(file,charSet,bufferSize);
			while((len=isr.read(cs))!=-1){
				sb.append(cs,0,len);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			closeStream(isr);
		} //end finally
		return sb.toString();
	}
	public String read(String path){
		return read(new File(path));
	}
	
	
	
	public void copy(File src,File obj){
		Reader isr=null;
		Writer osw=null;
		int len=-1;
		char[] cs=new char[1024];
		try{
			isr=getInputStreamReader(src,charSet,bufferSize);
			osw=getOutputStreamWriter(obj,charSet,bufferSize,append);
			while((len=isr.read(cs))!=-1){
				osw.write(cs,0,len);
				osw.flush();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			closeStream(isr,osw);
		} //end finally
	}
	public void copy(String src,String obj){
		copy(new File(src),new File(obj));
	}
	
	/**
	 * 清空目标文件内容
	 * @param out
	 */
	public void empty(OutputStream out){
		try{
			out.write("".getBytes());
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			closeStream(out);
		} //end finally
	}
	public void empty(File objFile){
		FileOutputStream out=null;
		try{
			out=new FileOutputStream(objFile,false);
			out.write("".getBytes());
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			closeStream(out);
		} //end finally
	}
	public void empty(String path){
		empty(new File(path));
	}
	
	//********************************设置读写属性*******************************
	/**
	 * 设置编码集属性
	 * @param charSet
	 */
	public TextIO setCharSet(String charSet) {
		this.charSet = charSet;
		return this;
	}
	/**
	 * 设置缓冲区大小
	 * @param charSet
	 */
	public TextIO setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
		return this;
	}

	public TextIO setAppend(boolean append) {
		this.append = append;
		return this;
	}
	
	//*****************************获取高级流***************************
	protected InputStreamReader getInputStreamReader(InputStream src,String charSet,int bufferSize) 
			throws UnsupportedEncodingException, FileNotFoundException {
		return new InputStreamReader(
				new BufferedInputStream(src,bufferSize)
				, charSet);
	}
	
	protected InputStreamReader getInputStreamReader(File src,String charSet,int bufferSize) 
			throws UnsupportedEncodingException, FileNotFoundException {
		return new InputStreamReader(
				new BufferedInputStream(new FileInputStream(src),bufferSize)
				, charSet);
	}
	
	protected OutputStreamWriter getOutputStreamWriter(File obj,String charSet,int bufferSize,boolean append) 
			throws UnsupportedEncodingException, FileNotFoundException {
		return new OutputStreamWriter(
				new BufferedOutputStream(new FileOutputStream(obj,append), bufferSize)
				,charSet);
	}
	
	protected OutputStreamWriter getOutputStreamWriter(OutputStream obj,String charSet,int bufferSize,boolean append) 
			throws UnsupportedEncodingException, FileNotFoundException {
		return new OutputStreamWriter(
				new BufferedOutputStream(obj, bufferSize)
				,charSet);
	}

}
