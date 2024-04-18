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



class NewUserPane{
	
	private Text titleText, firstNameText, lastNameText, userText, enterNewPasswordText, repeatNewPasswordText;
	
	private String typedUser, typedPassword1, typedPassword2;
	
	private PaneName activePane = PaneName.NEW_USER;
	
	private TextField firstNameField, lastNameField, userField;
	
	private PasswordField enterNewPasswordField, repeatNewPasswordField;
	
	private PasswordCheckElements passwordCheckElements;
	
	private Button createAccountButton;
	
	private VBox pane;
	
	private GridPane grid;
	

	
	void activate(BankApp bankApp) {
		
		bankApp.updateActivePaneName(activePane);
		
		initializeElements();
		
		setTextsFonts();
		
		passwordCheckElements.passwordRequirementsCheck();
		
		addElementsToPane();
		
		setLayouts();
		
		setCreateAccountButtonAction(bankApp);
		
		bankApp.mainPane.setCenter(pane);

		bankApp.displayTopAndBottomSections();
		
	}
	
	
	
	private void initializeElements() {
		titleText = new Text("Create your GreenBank account");
		firstNameText = new Text("Enter your first name : ");
		userText = new Text("Choose a username : ");
		enterNewPasswordText = new Text("Enter a password : ");
		repeatNewPasswordText = new Text("Repeat password : ");

		firstNameField = new TextField();
		firstNameField.setPrefColumnCount(25);
		
		lastNameField = new TextField();
		lastNameField.setPrefColumnCount(25);
		
		userField = new TextField();
		userField.setPrefColumnCount(25);

		enterNewPasswordField = new PasswordField();
		enterNewPasswordField.setPrefColumnCount(25);
		
		repeatNewPasswordField = new PasswordField();
		repeatNewPasswordField.setPrefColumnCount(25);
		
		passwordCheckElements = new PasswordCheckElements(enterNewPasswordField, repeatNewPasswordField);
		
		createAccountButton = new Button("Create account");
		
		pane = new VBox();
		
		grid = new GridPane();

	}
	
	
	
	private void setTextsFonts(){
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		firstNameText.setFont(Font.font("Arial", 20));
		lastNameText = new Text("Enter your last name : ");
		lastNameText.setFont(Font.font("Arial", 20));
		userText.setFont(Font.font("Arial", 20));
		
		enterNewPasswordText.setFont(Font.font("Arial", 20));
		repeatNewPasswordText.setFont(Font.font("Arial", 20));
	}
	
	
	
	private void addElementsToPane() {
		grid.add(firstNameText, 0, 0);
		grid.add(firstNameField, 1, 0);
		grid.add(lastNameText, 0, 1);
		grid.add(lastNameField, 1, 1);
		grid.add(userText, 0, 2);
		grid.add(userField, 1, 2);
		grid.add(enterNewPasswordText, 0,3);
		grid.add(enterNewPasswordField, 1, 3);
		grid.add(repeatNewPasswordText, 0, 4);
		grid.add(repeatNewPasswordField, 1, 4);
		grid.add(createAccountButton, 1, 5);
		grid.add(passwordCheckElements.verticalBox, 1, 6);
	}
	
	
	
	private void setLayouts() {
		
		GridPane.setHalignment(createAccountButton, HPos.RIGHT);

		grid.setPadding(new Insets(15));
		grid.setHgap(7);
		grid.setVgap(7);
		grid.setAlignment(Pos.CENTER);
		
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().add(titleText);
		
		pane.getChildren().add(grid);
		
		passwordCheckElements.verticalBox.setSpacing(3);
	}

	

	private void setCreateAccountButtonAction(BankApp bankApp) {
		
		createAccountButton.setOnAction(event -> {
			
			typedUser = userField.getText();
			typedPassword1 = enterNewPasswordField.getText();
			typedPassword2 = repeatNewPasswordField.getText();
			
			if(typedUser.length() == 0){
				
				popupEmptyUserFieldWindow();
				
			}else if(bankApp.userIDExists(typedUser)) {
				
				popupDuplicateUserIDWindow();
				
			} else if(!typedPassword1.equals(typedPassword2)) {
				
				popupPasswordDontMatchWindow();
				
			} else {
				
				createUser(bankApp);
				popupUserCreatedWindow(bankApp);
			}
		});
	}

	

	private void popupEmptyUserFieldWindow() {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Couldn't create your account");
		alert.setContentText("Username can't be empty.");
		alert.showAndWait();
	}



	private void popupDuplicateUserIDWindow() {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Your account couldn't be created");
		alert.setContentText("The username is already used.");
		alert.showAndWait();
	}
	
	
	
	private void popupPasswordDontMatchWindow() {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Your account couldn't be created");
		alert.setContentText("The 2 passwords don't match");
		alert.showAndWait();
	}



	private void createUser(BankApp bankApp) {
		BankApp.customerList.add(new Customer(typedUser, new MD5().md5Algorithm(typedPassword2), firstNameField.getText(), lastNameField.getText()));
		new Serialize(BankApp.customerList).serializeCustomers();
		
	}

	

	private void popupUserCreatedWindow(BankApp bankApp) {
		Alert success = new Alert(Alert.AlertType.INFORMATION);
		success.setHeaderText("Your account was successfully created");
		success.setOnCloseRequest(closingEvent -> bankApp.userLoginPane.activate(bankApp));
		success.showAndWait();
	}
}

