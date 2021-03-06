package StringMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length() > s2.length()) {
			return s1;
		}
		else if(s1.length() < s2.length()) {
			return s2;
		}
		else {
			return "equal";
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			s = s.replace(' ', '_');
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		int firsts1 = 0;
		int firsts2 = 0;
		int firsts3 = 0;
		Character lasts1 = ' ';
		Character lasts2 = ' ';
		Character lasts3 = ' ';

		for (int i = 0; i < s1.length(); i++) {
			if(Character.isLetter(s1.charAt(i))) {
				 firsts1 = i;
				break;
			}
		}
		for(int i = firsts1+1; i < s1.length(); i ++) {
			if(s1.charAt(i-1) == ' ') {
				lasts1 = s1.charAt(i);
			}
		}
		
		for (int i = 0; i < s2.length(); i++) {
			if(Character.isLetter(s2.charAt(i))) {
				 firsts2 = i;
				break;
			}
		}
		for(int i = firsts2+1; i < s2.length(); i ++) {
			if(s2.charAt(i-1) == ' ') {
				lasts2 = s2.charAt(i);
			}
		}
		
		for (int i = 0; i < s3.length(); i++) {
			if(Character.isLetter(s3.charAt(i))) {
				 firsts3 = i;
				break;
			}
		}
		for(int i = firsts3+1; i < s3.length(); i ++) {
			if(s3.charAt(i-1) == ' ') {
				lasts3 = s3.charAt(i);
			}
		}
		
		if(lasts1 < lasts2 && lasts1 < lasts3) {
			return s1.strip();
		}
		else if(lasts2 < lasts1 && lasts2 < lasts3){
			return s2.strip();
		}
		else {
			return s3.strip();
		}
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				sum += Character.getNumericValue(s.charAt(i));
			}
		}
		return sum;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int count = 0;
		for (int i = 0; i < s.length() - substring.length() + 1; i++) {
			if(substring.equals(s.substring(i, i+substring.length()))) {
				count++;
			}
		}
		return count;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] plain = s.getBytes();
		byte newKey = (byte)key;
		String encrypted = Utilities.encrypt(plain, newKey);
		return encrypted;
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String decrypted = Utilities.decrypt(s, (byte) key);
		return decrypted;
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String word[] = s.split(" ");
		int count = 0;
		for(int i = 0; i < word.length; i ++) {
			if(word[i].endsWith(substring)) {
				count ++;
			}
		}
		return count;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int first = s.indexOf(substring) + substring.length();
		int last = s.lastIndexOf(substring);
		int distance = last - first;
		return distance;
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String reverse = "";
		for(int i = s.length()-1; i >= 0; i --) {
			reverse = reverse + s.charAt(i);
			
		}
		reverse = reverse.replaceAll("\s", "");
		s = s.replaceAll("\s", "");
		reverse = reverse.replaceAll("\\p{P}", "");
		s = s.replaceAll("\\p{P}", "");
		System.out.println(reverse);
		
		if(reverse.compareToIgnoreCase(s) == 0) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static void encrypt(String s, char key) {
		// TODO Auto-generated method stub
		
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
