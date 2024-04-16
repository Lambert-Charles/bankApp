package bankApp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class BankApp extends Application {

	static int customerNumber;
    
	BorderPane mainPane = new BorderPane();
    
	static Group bottomGroup = new Group();
	static Group topGroup = new Group();
	private Group mainGroup = new Group(mainPane, bottomGroup, topGroup);
	
	Scene scene = new Scene(mainGroup, 700, 550);

	static ArrayList<Customer> customerList = new ArrayList<>();
	
	String currentPaneName = "mainPane";
	
	PaneName activePane;
	
	private static BackgroundFill greenBackgroundFill = new BackgroundFill(Color.rgb(158, 250, 185), null, null);
	static Background greenBackground = new Background(greenBackgroundFill);
	
	static int transactionToDisplay = 0 ;
	
	Customer customer = new Customer();
    
	UserLoginPane userLoginPane = new UserLoginPane();
	
	NewUserPane newUserPane = new NewUserPane();
	
	HomePane homePane = new HomePane();
	
	TopSection topSection = new TopSection(this);
	
	BottomSection bottomSection = new BottomSection(this);
	
	BalancePane balancePane = new BalancePane();
	
	ResetPasswordPane resetPasswordPane = new ResetPasswordPane();
	
	WithdrawalPane withdrawalPane = new WithdrawalPane(this);
	
	AmountWithdrawn amountWithdrawn = new AmountWithdrawn(this);
	
	DepositPane depositPane = new DepositPane(this);
	
	DepositSuccessfullPane depositSuccessfullPane = new DepositSuccessfullPane(this);
	
	LogoutPane logoutPane = new LogoutPane(this);
	
	PreviousTransactionsPane previousTransactionsPane = new PreviousTransactionsPane(this);
        
    
    
    public static void main(String[] arg){
        
    	Application.launch(arg);
    	
    }
    
    
    
	@Override
	public void start(Stage stage) throws Exception {
		
    	BankApp.customerList = new Deserialize().deserializeCustomers();
		
		mainPane.setBackground(greenBackground);

		userLoginPane.activate(this);
		
		mainPane.setLayoutX((scene.getWidth() - 610) / 2);
		mainPane.setPrefWidth(610);
		
		mainPane.setLayoutY(100);
		
		scene.setFill(Color.rgb(158, 250, 185));

		stage.setTitle("GreenBank");
		stage.setScene(scene);
		stage.show();

	} 



	boolean userIDExists(String givenID){
		
		boolean userIDExists = false;
    	for(int i = 0 ; i < customerList.size(); i++) {
    		if(customerList.get(i).getUserID().equals(givenID)) {
    			userIDExists = true;
    			customerNumber = i;
    		}
    	}
    	
		return userIDExists;
		
    }
    
    
    
    boolean userPasswordIsCorrect(String givenPassword){
    	
    	String hashedGivenPassword = new MD5().md5Algorithm(givenPassword);
		return (customerList.get(customerNumber).getPassword()).equals(hashedGivenPassword);
		
    }
    
    

	void updateCurrentPaneName(String paneName) {
		this.currentPaneName = paneName;
		
	}
	
	
	void updateActivePaneName(PaneName paneName) {
		this.activePane = paneName;
		
	}



	public static void updateBalance(int amount) {
		
			customerList.get(customerNumber).setBalance(customerList.get(customerNumber).getBalance() + amount);
			
	}



	public static void saveDataToFile() {
		
		new Serialize(BankApp.customerList).serializeCustomers();
		
	}



	public static void addTransactionToHistorial(Transaction transaction) {
		
		BankApp.customerList.get(BankApp.customerNumber).addTransaction(transaction);
		
	}



	public void displayTopAndBottomSections() {
		
    	topSection.display();
    	
    	bottomSection.display();
		
	}
}



enum PaneName{
	
	BALANCE,
	DEPOSIT_REQUEST,
	DEPOSIT_SUCCESS,
	HOME,
	LOGIN,
	LOGOUT,
	NEW_USER,
	PREVIOUS_TRANSACTIONS,
	RESET_PASSWORD,
	WITHDRAWAL_REQUEST,
	WITHDRAWAL_SUCCESS
	
}