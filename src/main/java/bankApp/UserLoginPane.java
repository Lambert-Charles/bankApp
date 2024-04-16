package bankApp;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



class UserLoginPane{
	
	private PaneName activePane = PaneName.LOGIN;
	
	private GridPane pane;
	
	private Text enterInfoText, userIDText, userPasswordText, userIDNotFoundText, newUserText;
	
	private Hyperlink forgotPassword, newUserLink;
	
	private TextField userIDTextField;
	
	private Button userLoginButton;
	
	private PasswordField userPasswordTextField;
	
	private VBox newUserBox;
	
	private FlowPane newUserPane;
	
	
	
    void activate(BankApp bankApp) {
    	
    	bankApp.updateActivePaneName(activePane);
    	
		initializeElements();
		
		setTextsFonts();
		
		addElementsToPane();
		
		setUserLoginButtonAction(bankApp);
		
		setNewUserPane(bankApp);
		
		setLayouts(bankApp);
		
    	forgotPassword.setOnAction(event -> bankApp.resetPasswordPane.activate(bankApp));
    	
		bankApp.displayTopAndBottomSections();

    }



	private void initializeElements(){
		
    	enterInfoText = new Text("Please enter your information");
    	userIDText = new Text("Login : ");
    	userPasswordText = new Text("Password : ");
    	userIDNotFoundText = new Text();
    	userIDNotFoundText.setFill(Color.TRANSPARENT);
    	newUserText = new Text("Not a member of Greenbank yet? ");

    	
    	userIDTextField = new TextField();
    	userIDTextField.setPrefColumnCount(25);
    	
    	userLoginButton = new Button("Enter");
    	
    	forgotPassword = new Hyperlink("Forgot your password?");
    	newUserLink = new Hyperlink("Sign up!");
    	
    	userPasswordTextField = new PasswordField();
    	
    	pane = new GridPane();
    	
    	newUserPane = new FlowPane();
    	
    	newUserBox = new VBox();
    }
	
    
	
	private void setTextsFonts(){
		
    	enterInfoText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    	userIDText.setFont(Font.font("Arial", FontWeight.BOLD, 20));    	
    	userPasswordText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		forgotPassword.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	newUserText.setFont(Font.font("Arial", 15));
	   	newUserLink.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	}
	
	
	
	private void addElementsToPane() {
		
    	pane.add(enterInfoText, 0 ,0);
    	pane.add(userIDText, 0 ,1);
    	pane.add(userIDTextField, 1 ,1);
    	pane.add(userPasswordText, 0 ,2);
    	pane.add(userPasswordTextField, 1 ,2);
		pane.add(userLoginButton, 1, 3);
		pane.add(userIDNotFoundText, 1, 4);
		pane.add(forgotPassword, 1, 5);
	}
	

	
	private void setUserLoginButtonAction(BankApp bankApp) {
		
		GridPane.setHalignment(userLoginButton, HPos.RIGHT);

		userLoginButton.setOnAction(event -> {
			if(bankApp.userIDExists(userIDTextField.getText())) {
				
				bankApp.customer.setName(userIDTextField.getText());
				
				if(bankApp.userPasswordIsCorrect(userPasswordTextField.getText())) {
					goToMainMenu(bankApp);
					
				}else {
					activateWrongPassordText();
				}
				
			} else {
				
				activateUserIDNotFoundText();
			}
		});
		
		userLoginButton.setDefaultButton(true);
	}



	private void goToMainMenu(BankApp bankApp) {
		bankApp.homePane.activate(bankApp);		
	}
   
	
    
    private void activateWrongPassordText() {
    	userIDNotFoundText.setText("\tIncorrect password, please try again");
		userIDNotFoundText.setFill(Color.RED);
	}
    
    
    
	private void activateUserIDNotFoundText() {
		
    	userIDNotFoundText.setText("User ID not found in database, please try again");
		userIDNotFoundText.setFill(Color.RED);
	}



	private void setNewUserBox(FlowPane newUserPane) {
		
    	newUserBox.setAlignment(Pos.CENTER);
    	newUserBox.getChildren().add(pane);
    	newUserBox.getChildren().add(newUserPane);
    	newUserBox.setLayoutY(200);
	}
	
	
	
	private void setNewUserPane(BankApp bankApp) {
		
		newUserPane.setAlignment(Pos.CENTER);;
	   	newUserLink.setOnAction(event -> bankApp.newUserPane.activate(bankApp));
	   	
    	newUserPane.getChildren().add(newUserText);
    	newUserPane.getChildren().add(newUserLink);
    	
    	setNewUserBox(newUserPane);
		
	}
	
	
	
	private void setLayouts(BankApp bankApp) {
		pane.setPadding(new Insets(12));
		pane.setHgap(6);
		pane.setVgap(6);
		pane.setAlignment(Pos.CENTER);
		
		GridPane.setHalignment(userIDNotFoundText, HPos.RIGHT);
		GridPane.setHalignment(forgotPassword, HPos.RIGHT);
		
		bankApp.mainPane.setLayoutY(100);
    	bankApp.mainPane.setCenter(newUserBox);
	}
}
