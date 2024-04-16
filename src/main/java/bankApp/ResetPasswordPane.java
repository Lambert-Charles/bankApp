package bankApp;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


class ResetPasswordPane{
	
	private VBox pane;
	
	private PaneName activePane = PaneName.RESET_PASSWORD;
	
	private Text titleText, userText, enterNewPasswordText, repeatNewPasswordText;
	
	private TextField userField;

	private PasswordField enterNewPasswordField, repeatNewPasswordField;
	
	private PasswordCheckElements passwordCheckElements;
	
	private Button resetPasswordButton;
	
	private GridPane grid;

	
	
	void activate(BankApp bankApp) {
    	
		bankApp.updateActivePaneName(activePane);
    	
    	initializeElements();
    	
    	setFonts();

    	grid.setPadding(new Insets(15));
		grid.setHgap(7);
		grid.setVgap(7);
    	grid.setAlignment(Pos.CENTER);
    	
    	userField.setPrefColumnCount(25);
    	
    	addElementsToGrid();
    	
    	GridPane.setHalignment(resetPasswordButton, HPos.RIGHT);
    	
    	passwordCheckElements.passwordRequirementsCheck();
    	
    	setResetPasswordButtonEvent(bankApp);
    	
    	pane.getChildren().add(titleText);
    	pane.getChildren().add(grid);
    	
    	bankApp.mainPane.setCenter(pane);

		bankApp.displayTopAndBottomSections();

    }
	
	

	private void initializeElements() {
		pane = new VBox();
		titleText = new Text("Password reset");
		userText = new Text("User : ");
		enterNewPasswordText = new Text("Enter new password : ");
		repeatNewPasswordText = new Text("Repeat new password : ");
		
		userField = new TextField();

		enterNewPasswordField = new PasswordField();
		repeatNewPasswordField = new PasswordField();
		
		passwordCheckElements = new PasswordCheckElements(enterNewPasswordField, repeatNewPasswordField);
		
		resetPasswordButton = new Button("Reset Password");
		
		grid = new GridPane();

	}
	
	
	
	private void setFonts() {
		
    	titleText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    	userText.setFont(Font.font("Arial", 20));
    	enterNewPasswordText.setFont(Font.font("Arial", 20));
    	repeatNewPasswordText.setFont(Font.font("Arial", 20));
    	
	}
	
	
	
	private void addElementsToGrid() {
		
    	grid.add(userText, 0, 0);
    	grid.add(userField, 1, 0);
    	grid.add(enterNewPasswordText, 0, 1);
    	grid.add(enterNewPasswordField, 1, 1);
    	grid.add(repeatNewPasswordText, 0, 2);
    	grid.add(repeatNewPasswordField, 1, 2);
    	
    	grid.add(resetPasswordButton, 1, 3);  
    	
    	grid.add(passwordCheckElements.verticalBox, 1, 4);
    	
	}
	
	
	
	private void setResetPasswordButtonEvent(BankApp bankApp) {
		
		resetPasswordButton.setOnAction(event -> {
			
    		String typedUser = userField.getText();
    		String typedPassword1 = enterNewPasswordField.getText();
    		String typedPassword2 = repeatNewPasswordField.getText();
    		
    		if(typedUser.length() == 0){
    			popupEmptyUserWindow();
    			
    		}else if(!bankApp.userIDExists(typedUser)) {
    			popupUserDoesntExistWindow();

			} else if(!(typedPassword1.equals(typedPassword2) && PasswordRegexCheck.allRequirementsAreFulfilled(typedPassword1))) {
    			popupPasswordFormatNotGoodWindow();
    			
    		} else {
    			setNewPassword(typedPassword2);
    			popupPasswordSuccessfullyResetWindow(bankApp);

    		}
    	});
	}

	
	
	private void popupPasswordFormatNotGoodWindow() {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Couldn't reset password");
		alert.setContentText("Your password does not meet minimum safety requirements");
		alert.showAndWait();
		
	}



	private void popupEmptyUserWindow() {
		
    	Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Couldn't reset password");
		alert.setContentText("You must enter your user name.");
		alert.showAndWait();
		
	}
	
	
	
    private void popupUserDoesntExistWindow() {
    	
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Couldn't reset password");
		alert.setContentText("Specified user doesn't exist.");
		alert.showAndWait();
		
	}

    

	private void setNewPassword(String password) {
		
		BankApp.customerList.get(BankApp.customerNumber).setPassword(new MD5().md5Algorithm(password));
		new Serialize(BankApp.customerList).serializeCustomers();
		
	}



	private void popupPasswordSuccessfullyResetWindow(BankApp bankApp) {

		Alert success = new Alert(Alert.AlertType.INFORMATION);
		success.setHeaderText("Password successfully reset. You can now log in.");
		success.setOnCloseRequest(closingEvent -> bankApp.userLoginPane.activate(bankApp));
		success.showAndWait();
		
	}
}
