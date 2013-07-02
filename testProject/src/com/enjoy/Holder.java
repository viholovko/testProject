package com.enjoy;

public class Holder {
	public static final String LAST_TAG = "body";
	public static final String FIRST_TAG = "to";
	private String [] valuesArray;
	
	private String to;
	private String from;
	private String title;
	private String message;
		
	//setters
	public void setTo(String to){this.to = to;}
	public void setFrom(String from){this.from = from;}
	public void setTitle(String title){this.title = title;}
	public void setMessage(String message){this.message = message;}
	public void setValuesArray(String [] array){
		this.valuesArray = array;
	}
	
	//getters
	public String getTo(){return to;}
	public String getFrom(){return from;}
	public String getTitle(){return title;}
	public String getMessage(){return message;}
	public String[] getValuesArray(){return valuesArray;};
}
