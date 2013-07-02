package com.enjoy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriteHelper {
	public static final String XML_FIRST_LINE= "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	private BufferedWriter bufWriter;
	private StringBuffer buf;
	private String separt;
	private Holder holder;
	private DOMHelper helpDOM;
	
	public FileWriteHelper(){
		buf = new StringBuffer();
		separt = System.getProperty("line.separator");
		buf.append(XML_FIRST_LINE + separt);
		holder = new Holder();
		helpDOM = new DOMHelper();
	}
	
	public void writeToFile(File destination){
		try{
			FileOutputStream outp = new FileOutputStream(destination);
			bufWriter = new BufferedWriter(new OutputStreamWriter(outp)); 
			bufWriter.write(buf.toString());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			try{
				bufWriter.close();
			}catch(IOException e){}
		}
	}
	
	
	/*
	 * Adds information to string buffer (buf) to be used in writeToFile method  
	 */
	public void createXMLBuffer(String tag, int index, Holder Holder){
		String s = getTagValue(index, Holder);
		if(tag.equals(holder.FIRST_TAG)){
			buf.append("<" + helpDOM.NODE + ">" + separt);
		}
		buf.append( "<" + tag + ">" + s + "</" + tag + ">" + separt);
		
		if(tag.equals(holder.LAST_TAG)){
			buf.append("</" + helpDOM.NODE + ">" + separt);
		}
	}
	
	public String getBuffer(){
		return buf.toString();
	}
	
	private String getTagValue(int index, Holder holder){
		switch(index){
		case 0:
			return holder.getTo();
		case 1:
			return holder.getFrom();
		case 2:
			return holder.getTitle();
		case 3:
			return holder.getMessage();
		default: 
			return null;
		}
	}
}
