package com.tonic.utils;

import com.tonic.enums.ConfigProperties;

import java.util.List;
import java.util.Random;

/**
 * Application related common functions to be written here
 * Author: Gaurav Purwar
 */
public class ApplicationUtils {

	public ApplicationUtils(){
	}

	public static String getRandomString(int stringLength) {
		String randomString = "ABCDabcd1234567890";
		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			int randIndex = rand.nextInt(randomString.length());
			res.append(randomString.charAt(randIndex));
		}
		return res.toString();
	}

	public static String getRandomPassword() {
		String prefix = PropertyBuilder.getPropValue(ConfigProperties.PASSWORDPREFIX);
		String password = prefix + getRandomString(4);
		return password;
	}

	public String getRandomUserFromList(List<String> users) {

		if (users.isEmpty()) {
			throw new RuntimeException("No users found in the list");
		}
		int randomIndex = (int) (Math.random() * users.size());
		return users.get(randomIndex);
	}

	public String removeDollarSymbol(String input) {
		return input.replace("$", "").trim();
	}

	public float convertStringToFloat(String input) {
		try {
			return Float.parseFloat(input);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Failed to convert string to float: " + input, e);
		}
	}

	public String formatFloatToString(float value) {
		return String.format("%.2f", value);
	}

	public double convertStringToDouble(String input) {
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Failed to convert string to double: " + input, e);
		}
	}

	public String convertDoubleToString(double value) {
		return String.format("%.2f", value);
	}
}
