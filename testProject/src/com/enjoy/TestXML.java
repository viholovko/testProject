package com.enjoy;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TestXML {
	//private static final String MY_URL = "http://www.w3schools.com/xml/note.xml";
	private static final String MY_URL = "D:/XMLTest";
	private DOMHelper helper;
	
	public static void main(String[] args) {
		TestXML test = new TestXML();
		try {
//			java.net.URL url = new URL(MY_URL);
//			URLConnection conn = url.openConnection();
			test.helper = new DOMHelper();
			File file = new File(MY_URL);
			if(file.isDirectory()){
				int length = file.listFiles().length;
				for(int i = 0; i < length; i++){
					file = test.helper.getSingleFileName(MY_URL, i);
					test.helper.parseXML(file);
					test.helper.deleteFile(file);
				}
			}				
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		test.showXML();
		
	}	
	
	private void showXML(){
		if(helper.getHolderList()!=null && helper.getHolderList().size()>0){
			List<Holder> list = helper.getHolderList();
			Iterator<Holder> iter = list.iterator();
			while(iter.hasNext()){
				Holder hold = (Holder) iter.next();
				System.out.println("To: " + hold.getTo());
				System.out.println("From: " + hold.getFrom());
				System.out.println("Title: " + hold.getTitle());
				System.out.println("Message: " + hold.getMessage());
			}
		}
	}
}
