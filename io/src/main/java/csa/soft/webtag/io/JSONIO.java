package csa.soft.webtag.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import csa.soft.webtag.common.util.JSONUtil;

/**
 * 阻塞流
 * 切记copy方法没有json格式校验
 * @author csa
 *
 */
public class JSONIO extends TextIO{
	
	/**
	 * 保证子类可以使用super
	 */
	protected JSONIO(){}
	
	public static JSONIO newInstance(){
		return new JSONIO();
	}
	
	
	
	@Override
	public void write(String json, OutputStream out) {
		isJSONString(json);
		super.write(json, out);
	}

	@Override
	public void write(String json, File objFile) {
		isJSONString(json);
		super.write(json, objFile);
	}

	@Override
	public void write(String json, String objPath) {
		this.write(json, new File(objPath));
	}

	@Override
	public String read(InputStream in) {
		String str=super.read(in);
		isJSONString(str);
		return str;
	}

	@Override
	public String read(File file) {
		String str=super.read(file);
		isJSONString(str);
		return str;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public String read(String path) {
		return this.read(new File(path));
	}

	
	//*************************************设置属性***********************************
	/**
	 * 设置编码集属性
	 * @param charSet
	 */
	@Override
	public JSONIO setCharSet(String charSet) {
		super.setCharSet(charSet);
		return this;
	}
	/**
	 * 设置缓冲区大小
	 * @param charSet
	 */
	@Override
	public JSONIO setBufferSize(int bufferSize) {
		super.setBufferSize(bufferSize);
		return this;
	}
	@Override
	public JSONIO setAppend(boolean append) {
		super.setAppend(append);
		return this;
	}
	
	
	//*********************************protected way*********************************
	protected void isJSONString(String str){
		if(!JSONUtil.isJSONString(str))
			throw new RuntimeException(str+"不是合法json字符串");
	}

}
