import java.util.ArrayList;

public class ResourceCentre {

	public static void main(String[] args) {

		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		int option = 0;

		while (option != 5) {

			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all items
				ResourceCentre.viewAllCamcorder(camcorderList);
				ResourceCentre.viewAllChromebook(chromebookList);

			} else if (option == 2) {
				// Add a new item
				ResourceCentre.setHeader("ADD");			
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Add a camcorder
					Camcorder cc = inputCamcorder();
					ResourceCentre.addCamcorder(camcorderList, cc);

				} else if (itemType == 2) {
					// Add Chromebook
					Chromebook cb = inputChromebook();
					ResourceCentre.addChromebook(chromebookList, cb);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 3) {
				// Loan item
				ResourceCentre.setHeader("LOAN");			
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Loan camcorder
					ResourceCentre.loanCamcorder(camcorderList);
				} else if (itemType == 2) {
					// Loan Chromebook
					ResourceCentre.loanChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 4) {
				// Return item
				ResourceCentre.setHeader("RETURN");				
				ResourceCentre.setHeader("ITEM TYPES");
				System.out.println("1. Camcorder");
				System.out.println("2. Chromebook");
				
				int itemType = Helper.readInt("Enter option to select item type > ");
				if (itemType == 1) {
					// Return camcorder
					ResourceCentre.returnCamcorder(camcorderList);
				} else if (itemType == 2) {
					// Return Chromebook
					ResourceCentre.returnChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 5) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");

	}
	
	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	//================================= Option 1 View =================================
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = "";

		for (int i = 0; i < camcorderList.size(); i++) {

			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", camcorderList.get(i).getAssetTag(),
					camcorderList.get(i).getDescription(), 
					ResourceCentre.showAvailability(camcorderList.get(i).getIsAvailable()),
					camcorderList.get(i).getDueDate(),camcorderList.get(i).getOpticalZoom());
		}
		return output;
	}
	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.setHeader("CAMCORDER LIST");
		String output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION",
				"AVAILABLE", "DUE DATE","OPTICAL ZOOM");
		 output += retrieveAllCamcorder(camcorderList);	
		System.out.println(output);
	}

	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		// write your code here
		return output;
	}
	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {
		
		String output = retrieveAllChromebook(chromebookList);
		System.out.println(output);
	}

	//================================= Option 2 Add =================================
	public static Camcorder inputCamcorder() {
		//done by ijas
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		int zoom = Helper.readInt("Enter optical zoom > ");

		Camcorder cc= new Camcorder(tag, description, zoom);
		return cc;
		
	}
	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {
		//done by ijas
		camcorderList.add(cc);
		System.out.println("Camcorder added");
	}
	
	public static Chromebook inputChromebook() {	
		//done by ijas
		Chromebook cb =null;
		return cb;
		
	}	
	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {
		//done by ijas
		
	}
	
	//================================= Option 3 Loan =================================
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {
		//done by ryan
		boolean isLoaned = false;

		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == true) {
				
				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(dueDate);
				
				isLoaned = true;
				
			}
		}
		return isLoaned;
	}
	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		//done by ryan
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned =doLoanCamcorder(camcorderList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " loaned out");
		}
	}
	
	public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		// done by gordon
		boolean isLoaned = false;
		for (int b = 0; b < chromebookList.size(); b++) {
			if(tag.equalsIgnoreCase(chromebookList.get(b).getAssetTag()) && chromebookList.get(b).getIsAvailable() == true) {
				chromebookList.get(b).setIsAvailable(false);
				chromebookList.get(b).setDueDate(dueDate);
				isLoaned = true;
			}
		}
		return isLoaned;
	}
	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		// done by gordon
		ResourceCentre.viewAllChromebook(chromebookList);
		String chromtag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned =doLoanChromebook(chromebookList,chromtag,due);
		if(isLoaned == false){
			System.out.println("Invalid asset tag");
		}else {
			System.out.println("Chrome book " + chromtag + "has been loaned out");
		}
	}
	
	//================================= Option 4 Return =================================
	public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList,String tag) {
		//done by sebastian
		boolean isReturned = false; 

		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == false) {
				camcorderList.get(i).setIsAvailable(true);
				camcorderList.get(i).setDueDate("");
				isReturned = true;
				
			}
		}
		return isReturned;
		
	}
	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		//done by sebastian
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		Boolean isReturned = doReturnCamcorder(camcorderList, tag);
		
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " returned");
		}
	}

	public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList,String tag){ //Shawn,
		boolean isReturned = false;
		// write your code here
		
		for(int i = 0; i < chromebookList.size(); i++)
		{
			if(chromebookList.get(i).getAssetTag() == tag && chromebookList.get(i).getIsAvailable() == false)
			{
				chromebookList.get(i).setIsAvailable(true);
				chromebookList.get(i).setDueDate(" ");
				isReturned = true;
				
				System.out.println("Chromebook Returned");
				System.out.println(" ");
			}
			
			else
			{	System.out.println("Request Failed...");		}
		}//End of for loop,
		
		return isReturned;
		
	}//doReturnChromebook,
	
	public static void returnChromebook(ArrayList<Chromebook> chromebookList) { //Shawn,
		// write your code here
		// write your code here
		
		System.out.println("Returning Chromebook");
		String x = String.format("%-10s%-25s%-10s\n", "ASSET TAG", "DESCRIPTION", "OPERATING SYSTEM");
		
		//Displaying loaned out chromebooks,
		for(int i = 0; i < chromebookList.size(); i++)
		{
			if(chromebookList.get(i).getIsAvailable() == false)
			{	x = String.format("%-10s%-25s%-10s\n", chromebookList.get(i).getAssetTag(), chromebookList.get(i).getDescription(), chromebookList.get(i).getOs());			}
				
		}
		
		System.out.println(x);
		
		//Processing,
		String dataAT = Helper.readString("Enter Chromebook Asset Tag: ");
		System.out.println(" ");
		
		System.out.println("Request Processing...");
		doReturnChromebook(chromebookList, dataAT);
		System.out.println(" ");
		
		
		
	}//ReturnChrombook,
	
}//Class,
