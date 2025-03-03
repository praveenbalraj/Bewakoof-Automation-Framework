package com.qa.bk.genericUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Praveen B here we are storing methods related to Java operations
 */
public class JavaUtility {

	/**
	 * This method return Date and Time in DD-MM-YY-HH-MM-SS format
	 * 
	 * @return
	 */
	public String getSystemDateTime() {
		Date date = new Date();
		String currentDate = date.toString();
		String[] arr = currentDate.split(" ");
		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth() + 1;
		String time = arr[3].replace(":", "");
		String formate = dd + mm + yyyy + time;
		return formate;

	}

	/**
	 * This method return Date and Time in YYYY-MM-DD format
	 * 
	 * @return
	 */
	public String getSystemDateInYYYYMMDD() {
		Date date = new Date();
		String currentDate = date.toString();
		System.out.println(currentDate);
		String[] arr = currentDate.split(" ");
		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth() + 1;
		String formate = yyyy + "-" + mm + "-" + dd;
		return formate;
	}

	// Helper method to safely convert JavaScript return values to long
	public long getLongValue(Object scriptResult) {
		if (scriptResult instanceof Double) {
			return ((Double) scriptResult).longValue();
		} else if (scriptResult instanceof Long) {
			return (Long) scriptResult;
		} else {
			throw new IllegalArgumentException("Unexpected script result type: " + scriptResult.getClass());
		}
	}

	/**
	 * This method will return a random number from 0-range, mainly used on
	 * elements.get(index)
	 * 
	 * @param range
	 * @return
	 */
	public int getRanDomNumberInInteger(int range) {
		Random ranDom = new Random();
		int ranDomNum = ranDom.nextInt(range);
		String randomInteger = Integer.toString(ranDomNum);
		int num = this.returnNumber(randomInteger);
		return num;

	}

	/**
	 * This method will return number in Integer format, mainly used for conversion
	 * from String to Integer
	 * 
	 * @param priceSTR
	 * @return
	 */
	public int returnNumber(String priceSTR) {
		int numberValue;
		String conc = "";
		for (int i = 0; i < priceSTR.length(); i++) {
			if (priceSTR.charAt(i) >= 48 || priceSTR.charAt(i) <= 57) {
				conc = conc + priceSTR.charAt(i);
			}
		}
		numberValue = Integer.parseInt(conc);
		System.out.println(numberValue);
		return numberValue;

	}

	/**
	 * this method will extract numbers if the string as Alphanumeric or number with
	 * special char (eg. ₹2,499)
	 * 
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
			if (Character.isDigit(c) || c == '.') {
				sb.append(c);
			}
		}
		float num = Float.parseFloat(sb.toString());
		return num;
	}

	// Method to jumble a collection name and create an invalid one
	public String jumbleName(String validName) {
		 // Convert the input string to a list of characters
        List<Character> charList = new ArrayList<Character>();
        for (char c : validName.toCharArray()) {
            charList.add(c);
        }

        // Shuffle the list of characters
        Collections.shuffle(charList);

        // Convert the shuffled list back to a string
        StringBuilder jumbled = new StringBuilder();
        for (char c : charList) {
            jumbled.append(c);
        }

        return jumbled.toString();
	}

	// Method to generate random junk data (invalid data)
   public String generateJunkData(int length) {
        // Create a string containing letters (both upper-case and lower-case), digits, and special characters
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        StringBuilder junk = new StringBuilder();
        
        // Generate a random string of the specified length
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(characters.length());
            junk.append(characters.charAt(index));
        }

        // Introduce a number at the start or invalid pattern (e.g., starting with a digit)
        if (rand.nextBoolean()) {
            junk.insert(0, rand.nextInt(10)); // Prepend a random digit
        }

        return junk.toString();
    }
    
}
