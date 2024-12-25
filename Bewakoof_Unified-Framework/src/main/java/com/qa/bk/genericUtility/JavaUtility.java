package com.qa.bk.genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * @author Praveen B
 * here we are storing methods related to Java operations
 */
public class JavaUtility {

	/**
	 * This method return Date and Time in DD-MM-YY-HH-MM-SS format
	 * @return
	 */
	public String  getSystemDateTime() {
		Date date = new Date();
		String currentDate = date.toString();
		String[] arr = currentDate.split(" ");
		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth()+1;
		String time = arr[3].replace(":", "");
		String formate = dd+mm+yyyy+time;
		return formate;

	}
	
	/**
	 *This method return Date and Time in YYYY-MM-DD format
	 * @return
	 */
	public String  getSystemDateInYYYYMMDD() {
		Date date = new Date();
		String currentDate = date.toString();
		System.out.println(currentDate);
		String[] arr = currentDate.split(" ");
		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth()+1;
		String formate = yyyy+"-"+mm+"-"+dd;
		return formate;
	}
	
	/**
	 * This method will return a random number from 0-range, mainly used on elements.get(index)
	 * @param range
	 * @return
	 */
	public int getRanDomNumberInInteger(int range) {
		Random ranDom = new Random();
		int ranDomNum =  ranDom.nextInt(range);
		String randomInteger = Integer.toString(ranDomNum);
		int num = this.returnNumber(randomInteger);
		return num;

	}
	
	/**
	 * This method will return number in Integer format, mainly used for conversion from String to Integer
	 * @param priceSTR
	 * @return
	 */
	public int returnNumber(String priceSTR) {
		int numberValue;
		String conc="";
		for (int i = 0; i < priceSTR.length(); i++) {
			if (priceSTR.charAt(i)>=48 || priceSTR.charAt(i)<=57) {
				conc=conc+priceSTR.charAt(i);
			}	
		}
		numberValue = Integer.parseInt(conc);
		System.out.println(numberValue);
		return numberValue;


	}
	
	  /**
	   * this method will extract numbers if the string as Alphanumeric or number with special char (eg. ₹2,499)
	 * @param input
	 * @return
	 */
	public int extractNumbers(String input) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < input.length(); i++) {
	            char c = input.charAt(i);
	            if (Character.isDigit(c)) {
	                sb.append(c);
	            }
	        }
	        int num = Integer.parseInt(sb.toString());
	        return num;
	    }
	  public float extractAmount(String input) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < input.length(); i++) {
	            char c = input.charAt(i);
	            if (Character.isDigit(c) || c=='.') {
	                sb.append(c);
	            }
	        }
	        float num = Float.parseFloat(sb.toString());
	        return num;
	    }
	  
	  
	  
}
