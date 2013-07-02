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
	private static final String DESTINATION_FILE_URL = "D:/MyXML/test.xml";
	private DOMHelper helper;
	private List<File> fileList;
	private FileWriteHelper writeFile;
	
	public static void main(String[] args) {
		TestXML test = new TestXML();
		try {
//			java.net.URL url = new URL(MY_URL);
//			URLConnection conn = url.openConnection();
			
			test.helper = new DOMHelper();
			File file = new File(MY_URL);
			File destinationFile = new File(DESTINATION_FILE_URL);
			test.fileList = test.helper.createListOfFiles(file);
			test.writeFile = new FileWriteHelper();
			
			if(test.fileList!=null){
				for(File tempFile : test.fileList){
					test.helper.parseXML(tempFile);
					test.helper.deleteFile(file);
					test.helper.deleteFile(tempFile);
				}
			}
			test.showXML();
			test.writeFile.writeToFile(destinationFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}finally{}
	}	
	
		
	private void showXML(){
		if(helper.getHolderList()!=null && helper.getHolderList().size()>0){
			List<Holder> list = helper.getHolderList();
			Iterator<Holder> iter = list.iterator();
			while(iter.hasNext()){
				Holder tempHolder = (Holder) iter.next();
				for(int i = 0; i < helper.getElementArray().length; i++){
					writeFile.createXMLBuffer(helper.getSingleElement(i),i,tempHolder);
				}
			}
		}
	}
}
