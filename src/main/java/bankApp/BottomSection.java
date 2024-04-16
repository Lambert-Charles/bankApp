package bankApp;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

class BottomSection {
	
	private BankApp bankApp;
	
	private Button backButton;
	
	
	
	BottomSection(BankApp bankApp){
		
		this.bankApp = bankApp;
		
	}
	
	
	
	void display() {
		
		setBackButtonStyle();
		
		setBackButtonAction();
		
		BankApp.bottomGroup.getChildren().clear();
		BankApp.bottomGroup.getChildren().add(backButton);
	
	}

	
	
	private void setBackButtonStyle() {
		backButton = new Button("Back");
		backButton.setFont(Font.font(20));
		backButton.setFocusTraversable(false);
		backButton.setLayoutX(300);
		backButton.setLayoutY(450);
	}

	
	
	private void setBackButtonAction() {
		
		switch(bankApp.activePane) {
		
			case HOME:
				backButton.setText("Log out");
				
				backButton.setOnAction(event -> {
					bankApp.logoutPane.activate();
				});
				
				break;
			
			case LOGOUT: 
				backButton.setText("Log in");
				
				backButton.setOnAction(event -> {
					bankApp.userLoginPane.activate(bankApp);
		    	});
				
				break;
			
			case NEW_USER:
			case RESET_PASSWORD :
				backButton.setOnAction(event -> {
					bankApp.userLoginPane.activate(bankApp);
				});
				
				break;
				
			case LOGIN: 
				backButton.setVisible(false);
				
				break;
				
			default: 
				backButton.setOnAction(event -> {
					bankApp.homePane.activate(bankApp);
				});
				
				break;
		}
		
	}
}
