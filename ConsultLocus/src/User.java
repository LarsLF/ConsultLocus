import java.util.ArrayList;

public class User {

	private int id;
	private String profile;
	private String name;
	private String avaliable;
	private int price;
	private int salary;
	private String password;
	private String sprog;
	private String certificates;

	public User(int id, String profile, String name, String avaliable, int price, int salary, String password, String sprog, String certificates){
		
		this.id = id;
		this.profile = profile;
		this.name = name;
		this.avaliable = avaliable;
		this.price = price;
		this.salary = salary;
		this.password = password;
		this.sprog = sprog;
		this.certificates = certificates;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfil(String profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(String avaliable) {
		this.avaliable = avaliable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSprog() {
		return sprog;
	}

	public void setSprog(String sprog) {
		this.sprog = sprog;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	
	public static ArrayList<User> getUser() {
		return getUser();
	}	
	
}