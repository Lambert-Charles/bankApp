package bankApp;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PasswordCheckElements {
	
	VBox verticalBox = new VBox();
	
	private Text shouldBe8To16CharactersLong, shouldHaveLowercase, shouldHaveUppercase, shouldHaveDigit, shouldHaveSpecialCharacters, passwordsDontMatch;

	private PasswordField enterNewPasswordField, repeatNewPasswordField;
	
	PasswordCheckElements(PasswordField password1, PasswordField password2){
		
		enterNewPasswordField = password1;
		
		repeatNewPasswordField = password2;
		
		initializeElements();
		
		setFonts();
		
		buildVBox();
	}
	
	
	
	void passwordRequirementsCheck() {
		
		enterNewPasswordField.setOnKeyTyped(event -> {
    		String typedPassword1 = enterNewPasswordField.getText();
    		
    		repeatNewPasswordField.clear();
    		
    		checkPasswordsRequirements(typedPassword1);
			
		});
		
		repeatNewPasswordField.setOnKeyTyped(event ->{
			String typedPassword1 = enterNewPasswordField.getText();
			String typedPassword2 = repeatNewPasswordField.getText();
			
			checkPasswordsMatch(typedPassword1, typedPassword2);
			
		});
	}

	
	
	private void initializeElements() {
		
		shouldBe8To16CharactersLong = new Text("Password should be 8 to 16 characters long");
		shouldHaveLowercase = new Text("Password should have a lowercase letter");
		shouldHaveUppercase = new Text("Password should have an uppercase letter");
		shouldHaveDigit = new Text("Password should have a numerical character");
		shouldHaveSpecialCharacters = new Text("Password should have one of these: @ # $ % ^ & + =");
		passwordsDontMatch = new Text("Passwords should match");
		
	}

	
	private void setFonts() {
    	
    	shouldBe8To16CharactersLong.setFont(Font.font("Arial", 12));
    	shouldBe8To16CharactersLong.setFill(Color.RED);
    	
		shouldHaveLowercase.setFont(Font.font("Arial", 12));
		shouldHaveLowercase.setFill(Color.RED);
		
		shouldHaveUppercase.setFont(Font.font("Arial", 12));
		shouldHaveUppercase.setFill(Color.RED);
		
		shouldHaveDigit.setFont(Font.font("Arial", 12));
		shouldHaveDigit.setFill(Color.RED);
		
		shouldHaveSpecialCharacters.setFont(Font.font("Arial", 12));
		shouldHaveSpecialCharacters.setFill(Color.RED);

		passwordsDontMatch.setFont(Font.font("Arial", 12));
		passwordsDontMatch.setFill(Color.RED);
	}
	
	
	
	private void buildVBox() {
		
		verticalBox.getChildren().add(shouldBe8To16CharactersLong);
		verticalBox.getChildren().add(shouldHaveLowercase);
		verticalBox.getChildren().add(shouldHaveUppercase);
		verticalBox.getChildren().add(shouldHaveDigit);
		verticalBox.getChildren().add(shouldHaveSpecialCharacters);
		verticalBox.getChildren().add(passwordsDontMatch);
    	
		verticalBox.setSpacing(7);		
	}



	private void checkPasswordsRequirements(String typedPassword) {
		
		passwordLengthCheck(typedPassword);
		passwordLowercaseCharacterCheck(typedPassword);
		passwordUppercaseCharacterCheck(typedPassword);
		passwordSpecialCharacterCharacterCheck(typedPassword);
		passwordDigitCheck(typedPassword);
		
	}



	private void passwordLengthCheck(String typedPassword) {
		if(PasswordRegexCheck.passwordIs8To16CharactersLong(typedPassword)) {
			shouldBe8To16CharactersLong.setFill(Color.GREEN);
			
		}else {
			shouldBe8To16CharactersLong.setFill(Color.RED);

		}
	}



	private void passwordLowercaseCharacterCheck(String typedPassword) {
		if(PasswordRegexCheck.passwordHasALowerCaseLetter(typedPassword)) {
			shouldHaveLowercase.setFill(Color.GREEN);
		}else {
			shouldHaveLowercase.setFill(Color.RED);
		}		
	}

	
	
	private void passwordUppercaseCharacterCheck(String typedPassword) {
		if(PasswordRegexCheck.passwordHasAnUpperCaseLetter(typedPassword)) {
			shouldHaveUppercase.setFill(Color.GREEN);
		}else {
			shouldHaveUppercase.setFill(Color.RED);
		}	
		
	}



	private void passwordSpecialCharacterCharacterCheck(String typedPassword) {
		if(PasswordRegexCheck.passwordHasASpecialCharacter(typedPassword)) {
			shouldHaveSpecialCharacters.setFill(Color.GREEN);
		}else {
			shouldHaveSpecialCharacters.setFill(Color.RED);
		}	
		
	}



	private void passwordDigitCheck(String typedPassword) {
		if(PasswordRegexCheck.passwordHasADigit(typedPassword)) {
			shouldHaveDigit.setFill(Color.GREEN);
		}else {
			shouldHaveDigit.setFill(Color.RED);
		}	
		
	}



	private void checkPasswordsMatch(String typedPassword1, String typedPassword2) {
		if(typedPassword1.equals(typedPassword2)) {
			passwordsDontMatch.setFill(Color.GREEN);
		}else {
			passwordsDontMatch.setFill(Color.RED);

		}
	}
	
	
}
