package com.enjoy;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMHelper {
	public static final String NODE = "note";
	
	private String [] elementArray = {"to","from","heading","body"};
	private Holder holder;
	private List<Holder> holderList = new ArrayList<Holder>();;
	
	public File getSingleFileName(String fileURL, int fileIndex){
		String s = fileURL + "/file" + new Integer(fileIndex+1).toString() + ".xml";
		File file1 = new File(s);
		return file1;
	}
	
	
	/*
	 * returns the array names of tags 
	 */
	public String [] getElementArray(){
		return elementArray;
	}
	
	/*
	 * returns single Element of tag array
	 */
	public String getSingleElement(int index){
		return elementArray[index];
	}
	
	public void parseXML(File file) throws ParserConfigurationException, SAXException, IOException{
		holder = new Holder();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement();
		
		NodeList nodeList = doc.getElementsByTagName(NODE);
		for(int i = 0; i < nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				for(int j = 0; j < elementArray.length; j++){
					Element elem1 = (Element) node;
					NodeList nl1 = elem1.getElementsByTagName(elementArray[j]);
					Element elem2 = (Element) nl1.item(0);
					NodeList nl2 = elem2.getChildNodes();
					setHolder(j,nl2.item(0).getNodeValue(), holder);
				}
			}
		}

		addToList(holder);
	}
		
	public void deleteFile(File file){
		file.delete();		
	}
	
	public List<Holder> getHolderList(){
		return holderList;
	}
	
	/*
	 * adds holder object to the list
	 */
	private void addToList(Holder tempHolder){
		holderList.add(tempHolder);
	}
	
	/*
	 * creates instance of the holder object
	 */
	private void setHolder(int index, String s, Holder holder){		
		switch(index){
		case 0:
			holder.setTo(s);
			break;
		case 1:
			holder.setFrom(s);
			break;
		case 2:
			holder.setTitle(s);
			break;
		case 3:
			holder.setMessage(s);
			break;		
		}
	}
	
	/*
	 * Creating and deleting list of files
	 */
	public List<File> createListOfFiles(File directory){
		if(directory.isDirectory()){
			List<File> lst = new ArrayList<File>();
			for(File f : directory.listFiles()){
				lst.add(f);
			}
			return lst;
		}
		return null;
	}
	
	
}
