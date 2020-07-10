package com.example.data;

import java.util.Date;



public class ClientUtils {

	public static Person stringToPerson(String string) {
		string = string.substring(1, string.length()-1);
		String[] strings = string.split(" ");
		String bornString = strings[5];
		System.out.println(bornString);
		Date date = null;
		if (!bornString.equals("null")) {
			date = DateUtils.stringToUDate(bornString);
		}
		Person person = new Person(strings[0], strings[1], strings[2], strings[3], Integer.parseInt(strings[4]), date, Integer.parseInt(strings[6]), strings[7], Integer.parseInt(strings[8]));
		return person;
	}
}
