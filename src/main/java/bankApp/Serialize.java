package bankApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


class Serialize {

	private List<Customer> customerList;

	Serialize(List<Customer> customerList){
		this.customerList = customerList;
	}
	
	
	void serializeCustomers() {
    	try {
	         FileOutputStream fileOut = new FileOutputStream("src/main/resources/bankApp/serialize/customerList.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(customerList);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
    }
	
}



class Deserialize {
	
	
		ArrayList<Customer> deserializeCustomers(){
		
		List<Customer> customerList = new ArrayList<>();
		
	      try {
	         FileInputStream fileIn = new FileInputStream("src/main/resources/bankApp/serialize/customerList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         customerList = (ArrayList<Customer>)in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	    	 System.out.println("In IOException catch");
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Customer class not found");
	         c.printStackTrace();
	      }
	      
		return (ArrayList<Customer>) customerList;
    }
	
}
