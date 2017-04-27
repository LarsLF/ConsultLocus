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
			System.out.println("What do your want to do?");
			System.out.println("1: Search for a consultant");
			System.out.println("2: Search for a profile");
			System.out.println("3: List all consultants");
			/*
			System.out.println("4: Delete user");
			System.out.println("5: Update user info");
			*/

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
				/*
			case 4:
				deleteUser();
				break;

			case 5:
				updateUserInfo();
				break;
				*/

			default:
				System.out.println("Only 1-3");
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
		ArrayList<User> users = DbConnection.getUsers(profile);
		
	
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
	
	/*
	private static void createUser()  {
		
		System.out.println("Please enter the name of the person you want to create: ");
		String name = input.next();
		System.out.println("Please enter the password of the person you want to have: ");
		String password = input.next();
		
		DbConnection.createUser(name, password);
		
		System.out.println("The person has now been created");
	
	}

	private static void deleteUser() {
		System.out.println("Please enter the name of the person you want to delete");
		String name = input.next();
		
		DbConnection.deleteUser(name);
		
		System.out.println("The person has now been deleted from the system");
	}

	private static void updateUserInfo() {
		String name, password;
		
		System.out.println("Please enter name of the user you want to update: ");
		name = input.next();
		System.out.println("Please enter a new password: ");
		password = input.next();
		
		boolean approved = DbConnection.updateUserInfo(name, password);
		if(approved){
			System.out.println("Password updated");
		}
		else{
			System.out.println("Couldnt update password");
		}
	}
	*/
}

