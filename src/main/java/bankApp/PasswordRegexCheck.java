package bankApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordRegexCheck {

	public static void main(String[] args) {
		
		String test = "1@aA111111111111";
		
		System.out.println("Tested string : " + test);
		
		System.out.println("Password is 8 to 16 characters long : "  + passwordIs8To16CharactersLong(test));
		System.out.println("Password has a lower case letter : "  + passwordHasALowerCaseLetter(test));
		System.out.println("Password has an upper case letter: "  + passwordHasAnUpperCaseLetter(test));
		System.out.println("Password has a digit: "  + passwordHasADigit(test));
		System.out.println("Password has a special character : "  + passwordHasASpecialCharacter(test));

	}
	
	
	
	static boolean allRequirementsAreFulfilled(String password) {
		return (passwordIs8To16CharactersLong(password) && passwordHasADigit(password) && passwordHasALowerCaseLetter(password) && passwordHasAnUpperCaseLetter(password) && passwordHasASpecialCharacter(password));
	}
	
	
	
	static boolean passwordIs8To16CharactersLong(String password) {
		
		String eightCharactersMinimumRegex = ".{8,16}";
		Pattern pattern = Pattern.compile(eightCharactersMinimumRegex);
		Matcher matcher = pattern.matcher(password);
		
		return matcher.matches();
	}
	
	
	
	static boolean passwordHasALowerCaseLetter(String password) {
		
		String lowerCaseLetterRegex = ".*[a-z].*";
		Pattern pattern = Pattern.compile(lowerCaseLetterRegex);
		Matcher matcher = pattern.matcher(password);
		
		return matcher.matches();
	}
	
	
	
	static boolean passwordHasAnUpperCaseLetter(String password) {
		
		String upperCaseLetterRegex = ".*[A-Z].*";
		Pattern pattern = Pattern.compile(upperCaseLetterRegex);
		Matcher matcher = pattern.matcher(password);
		
		return matcher.matches();
	}
	
	
	
	static boolean passwordHasADigit(String password) {
		
		String atLeastOneDigitRegex = ".*\\d.*";
		Pattern pattern = Pattern.compile(atLeastOneDigitRegex);
		Matcher matcher = pattern.matcher(password);
		
		return matcher.matches();
	}
	
	
	
	static boolean passwordHasASpecialCharacter(String password) {
		
		String specialCharacterRegex = ".*[@#$%^&+=].*";
		Pattern pattern = Pattern.compile(specialCharacterRegex);
		Matcher matcher = pattern.matcher(password);
		
		return matcher.matches();
	}

}
