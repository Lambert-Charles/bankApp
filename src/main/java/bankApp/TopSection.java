package bankApp;

import java.io.File;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

class TopSection{
	
	private BankApp bankApp;
	
	private Image logo;
	
	private static ImageView withdrawImageView = new ImageView(new Image(new File("src/main/resources/bankApp/images/Withdraw.png").toURI().toString()));
	private static ImageView depositImageView = new ImageView(new Image(new File("src/main/resources/bankApp/images/Deposit.png").toURI().toString()));
	private static ImageView historialImageView = new ImageView(new Image(new File("src/main/resources/bankApp/images/Historial.png").toURI().toString()));
	private static ImageView balanceImageView = new ImageView(new Image(new File("src/main/resources/bankApp/images/Balance.png").toURI().toString()));
	private static ImageView logoutImageView = new ImageView(new Image(new File("src/main/resources/bankApp/images/Logout.png").toURI().toString()));
	
	private ImageView logoImageView;
	
	private JFXHamburger hamburgerMenu;
	
	private HamburgerBasicCloseTransition burgerTask;
	
	private Button withdrawButton, depositButton,  balanceButton, historialButton, logoutButton;
	
	private VBox drawerBox;
	
	private JFXDrawer drawer;
	
	
	
	TopSection(BankApp bankApp){
		
		this.bankApp = bankApp;
		
	}
	
	
	
	void display() {
		
		initializeElements();
		
		setLogoLayout(bankApp);
	
		if(currentPaneRequiersHamburguerMenu(bankApp)) {
		
			setHamburgerMenu();
			
			setButtons(bankApp);
			
			setDrawerMenu();
			
			displayLogoAndHamburgerMenu();
			
		}else {
			
			displayLogoOnly();

		}
	}
	
	
	
	private void initializeElements() {
		logo = new Image(new File("src/main/resources/bankApp/images/greenbankAndLogoSinFondo.png").toURI().toString());
		logoImageView = new ImageView(logo);	
		hamburgerMenu = new JFXHamburger();
		burgerTask = new HamburgerBasicCloseTransition(hamburgerMenu);
		drawerBox = new VBox();
		drawer = new JFXDrawer();
	}

	
	
	private void setLogoLayout(BankApp bankApp) {
		logoImageView.setFitHeight(51);
		logoImageView.setFitWidth(300);
		logoImageView.setLayoutX((bankApp.scene.getWidth() - logoImageView.getFitWidth()) / 2 );
		logoImageView.setLayoutY(15);
	}



	private boolean currentPaneRequiersHamburguerMenu(BankApp bankApp) {
		return bankApp.activePane != PaneName.LOGIN && bankApp.activePane != PaneName.LOGOUT && bankApp.activePane != PaneName.NEW_USER && bankApp.activePane != PaneName.RESET_PASSWORD;
	
	}
	
	
	
	private void setDrawerMenu() {
		
		drawerBox.setBackground(BankApp.greenBackground);
		
		addElementstoDrawerBox();
		
		drawer.setDefaultDrawerSize(160);
		drawer.setMouseTransparent(true);
		drawer.setLayoutX(20);
		drawer.setLayoutY(45);
		drawer.setSidePane(drawerBox);
		
	}



	private void addElementstoDrawerBox() {
		drawerBox.getChildren().add(depositButton);
		drawerBox.getChildren().add(withdrawButton);
		drawerBox.getChildren().add(balanceButton);
		drawerBox.getChildren().add(historialButton);
		drawerBox.getChildren().add(logoutButton);
	}



	private void displayLogoAndHamburgerMenu() {
		BankApp.topGroup.getChildren().clear();
		BankApp.topGroup.getChildren().addAll(hamburgerMenu, logoImageView, drawer);
	}

	

	private void displayLogoOnly() {
		BankApp.topGroup.getChildren().clear();
		BankApp.topGroup.getChildren().addAll(logoImageView);
		
	}



	private void setHamburgerMenu() {
		setHamburgerMenuLayout();
		
		setHamburgerMenuEvent();
	}

	
	
	private void setButtons(BankApp bankApp) {
		setWithdrawButton(bankApp);
		
		setDepositButton(bankApp);
		
		setBalanceButton(bankApp);
		
		setHistorialButton(bankApp);
		
		setLogoutButton(bankApp);
	}

	
	
	private void setHamburgerMenuLayout() {
		hamburgerMenu.setLayoutX(20);
		hamburgerMenu.setLayoutY(20);
		burgerTask.setRate(-1);
	}

	
	
	private void setHamburgerMenuEvent() {
		hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> 
		{
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();
			if(drawer.isOpened()) {
				drawer.setMouseTransparent(true);
        	    drawer.close();
			}else {
        	    drawer.open();
				drawer.setMouseTransparent(false);
			}
        });
	}



	private void setWithdrawButton(BankApp bankApp) {
		
		setImageViewNotHovered(withdrawImageView);
		
		withdrawButton = new Button();
		withdrawButton.setStyle("-fx-background-color: transparent");
		withdrawButton.setGraphic(withdrawImageView);
		withdrawButton.setOnMouseEntered(e -> setImageViewHovered(withdrawImageView));
		withdrawButton.setOnMouseExited(e -> setImageViewNotHovered(withdrawImageView));
		withdrawButton.setOnAction(e -> bankApp.withdrawalPane.activate());
		withdrawButton.setFocusTraversable(false);
		
	}



	private void setDepositButton(BankApp bankApp) {
		
		setImageViewNotHovered(depositImageView);
		
		depositButton = new Button();
		depositButton.setStyle("-fx-background-color: transparent");
		depositButton.setGraphic(depositImageView);
		depositButton.setOnMouseEntered(e -> setImageViewHovered(depositImageView));
		depositButton.setOnMouseExited(e -> setImageViewNotHovered(depositImageView));
		depositButton.setOnAction(e -> bankApp.depositPane.activate());
		depositButton.setFocusTraversable(false);
		
	}



	private void setBalanceButton(BankApp bankApp) {
		
		setImageViewNotHovered(balanceImageView);
		
		balanceButton = new Button();
		balanceButton.setStyle("-fx-background-color: transparent");
		balanceButton.setGraphic(balanceImageView);
		balanceButton.setOnMouseEntered(e -> setImageViewHovered(balanceImageView));
		balanceButton.setOnMouseExited(e -> setImageViewNotHovered(balanceImageView));
		balanceButton.setOnAction(e -> bankApp.balancePane.activate(bankApp));
		balanceButton.setFocusTraversable(false);
		
	}



	private void setHistorialButton(BankApp bankApp) {
		
		setImageViewNotHovered(historialImageView);
		
		historialButton = new Button();
		historialButton.setStyle("-fx-background-color: transparent");
		historialButton.setGraphic(historialImageView);
		historialButton.setOnMouseEntered(e -> setImageViewHovered(historialImageView));
		historialButton.setOnMouseExited(e -> setImageViewNotHovered(historialImageView));
		historialButton.setOnAction(e -> bankApp.previousTransactionsPane.activate(0));
		historialButton.setFocusTraversable(false);
		
	}



	private void setLogoutButton(BankApp bankApp) {
		
		setImageViewNotHovered(logoutImageView);
		
		logoutButton = new Button();
		logoutButton.setStyle("-fx-background-color: transparent");
		logoutButton.setGraphic(logoutImageView);
		logoutButton.setOnMouseEntered(e -> setImageViewHovered(logoutImageView));
		logoutButton.setOnMouseExited(e -> setImageViewNotHovered(logoutImageView));
		logoutButton.setOnAction(e -> bankApp.logoutPane.activate());
		logoutButton.setFocusTraversable(false);
		
	}		
	
	
	
	private void setImageViewNotHovered(ImageView imageView) {
		imageView.setFitHeight(30);
		imageView.setFitWidth(145);
	}
	
	
	
	private void setImageViewHovered(ImageView imageView) {
		imageView.setFitHeight(36);
		imageView.setFitWidth(175);
	}
	
}