import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		DbConnection.establishDbConnection();
		menu();
	}

	private static void menu() {

		do {
			System.out.println("What do your want to do? \n");
			System.out.println("1: Search for a consultant");
			System.out.println("2: Search for a profile");
			System.out.println("3: List all consultants");
			System.out.println("4: Create consultant");
			System.out.println("5: Delete consultant");
			System.out.println("6: Update consultant");

			switch (input.nextInt()) 
			
			{
			case 1:
				getUser();
				break;

			case 2:
				getUsers();
				break;

			case 3:
				getAllUsers();
				break;
				
			case 4:
				createConsultent();
				break;
				
			case 5:
				deleteConsultent();
				break;
			
			case 6: 
				updateConsultentInfo();
				break;
				
			default:
				System.out.println("Only 1-6");
				break;
			}
		} while (true);
		
	}

	private static void getUser() {
		System.out.println("Please enter the name of the consultant your want to find: ");
		String profile = input.next();
		User user = DbConnection.getUser(profile);
		if (user != null) {
			System.out.println("Consultent found: \n\n" + 
					"Consultent ID: " + user.getId() + 
					"\nNavn: " + user.getName() + 
					"\nProfile: " + user.getProfile() + 
					"\nAvaliable: " + user.getAvaliable() +
					"\nPrice: " + user.getPrice() + " DKK" +
					"\nSalary: " + user.getSalary() + " DKK" + 
					"\nPassword: " + user.getPassword() + 
					"\nLanguage: " + user.getSprog() + 
					"\nCertificates: " + user.getCertificates()
					+ "\n");
		} else {
			System.out.println("User not found");
		}
	}

	private static void getUsers() {
		System.out.println("Profile: ");
		String profile = input.next();

		for (User user : DbConnection.getUsers(profile)) {
			System.out.println("Users found: \n\n" + "Profil: " + user.getProfile() + "\nNavn: " + user.getName() + "\n");
		}
	}
	

	private static void getAllUsers() {
		String profile = input.nextLine();
		ArrayList<User> users = DbConnection.getUsers(profile);
		System.out.println("** Users found **");
		
		for (User user : DbConnection.getUsers(profile)) {
			System.out.println("Profil: " + user.getProfile() + "\nNavn: " + user.getName() + "\n");
		}
		
	}
	
	
	private static void createConsultent()  {
		
		System.out.println("Please enter the profile of the consultent you want to create: ");
		String profile = input.next();
		System.out.println("Please enter the name of the consultent: ");
		String name = input.next();
		System.out.println("Is the consultent available: yes/no?");
		String available = input.next();
		System.out.println("Please enter the hourly price of the consultent");
		int price = input.nextInt();
		System.out.println("Please enter the salary of which Consultlocus requestst: ");
		int salary = input.nextInt();
		System.out.println("Please enter the password of the consultent: ");
		String password = input.next();
		System.out.println("Please enter the languege of which the consultent speaks: ");
		String sprog = input.next();
		System.out.println("Please enter the consultents existing certificates: ");
		String certificates = input.next();
		
		DbConnection.createConsultent(profile, name, available, price, salary, password, sprog, certificates);
		
		System.out.println("The person has now been created");
	
	}

	private static void deleteConsultent() {
		System.out.println("Please enter the id of the consultent you want to delete: ");
		String id = input.next();
		
		DbConnection.deleteConsultent(id);
		
		System.out.println("The person has now been deleted from the system \n");
	}

	private static void updateConsultentInfo() {
		String profile, available, password, sprog, certificates;
		int id, price, salary;
		
		System.out.println("Please enter id of the user you want to update: ");
		id = input.nextInt();
		System.out.println("Please enter a new profile: ");
		profile = input.next();
		System.out.println("Please enter a new available: ");
		available = input.next();
		System.out.println("Please enter a new price: ");
		price = input.nextInt();
		System.out.println("Please enter a new salary: ");
		salary = input.nextInt();
		System.out.println("Please enter a new password: ");
		password = input.next();
		System.out.println("Please enter a new sprog: ");
		sprog = input.next();
		System.out.println("Please enter a new certificates: ");
		certificates = input.next();
		
		
		boolean approved = DbConnection.updateConsultentInfo(id, profile, available, price, salary, password, sprog, certificates);
		if(approved){
			System.out.println("Consultant updated \n");
		}
		else{
			System.out.println("Couldnt update consultant \n");
		}
	}
	
}

