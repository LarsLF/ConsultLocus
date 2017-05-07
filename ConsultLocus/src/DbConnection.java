import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/ConsultLocus?autoReconnect=true&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Lars";

	private static java.sql.Connection connection = null;

	/**
	 * Method used to establish database connection
	 */
	public static void establishDbConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		}
	}

	public static User getUser(String name) {
		ResultSet resultSet = null;
		User user = null;

		try {
			java.sql.PreparedStatement getUser = connection.prepareStatement("SELECT * from consultants WHERE name LIKE ?");
			getUser.setString(1, "%" + name + "%");

			
			resultSet = getUser.executeQuery();

			while (resultSet.next())
				
			{
				user = new User(resultSet.getInt("id"), 
						resultSet.getString("profile"), 
						resultSet.getString("name"), 
						resultSet.getString("available"), 
						resultSet.getInt("price"), 
						resultSet.getInt("salary"), 
						resultSet.getString("password"), 
						resultSet.getString("sprog"), 
						resultSet.getString("certificates"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				close();
			}
		}
		return user;
	}
	
	
	
	
		public static ArrayList <User> getUsers(String profile){
			ResultSet resultSet = null;
			ArrayList<User> users = null;

			try {
				java.sql.PreparedStatement getUser = connection.prepareStatement("SELECT * from consultants WHERE profile LIKE ?");
				getUser.setString(1, "%" + profile + "%");

				resultSet = getUser.executeQuery();				
				users = new ArrayList<User>();

				while (resultSet.next())
					
				{
					users.add (new User(0, resultSet.getString("profile"), resultSet.getString("name"), resultSet.getString("available"), 0, 0, null, null, null));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					close();
				}
			}
			return users;
		}
		
		public static ArrayList <User> getAllUsers(){
			ResultSet resultSet = null;
			ArrayList<User> users = null;

			try {
				java.sql.PreparedStatement getUser = connection.prepareStatement("SELECT * from consultants");



				resultSet = getUser.executeQuery();
				
				users = new ArrayList<User>();

				while (resultSet.next())
					
				{
					users.add (new User(0, resultSet.getString("profile"), resultSet.getString("name"), resultSet.getString("available"), 0, 0, null, null, null));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					close();
				}
			}
			return users;
		}
		
		public static void createConsultent(String profile, String name, String avaliable, int price, int salary, String password, String sprog, String certificates) {

			try {
				java.sql.PreparedStatement createConsultent = connection.prepareStatement("insert into consultants (profile, name, available, price, salary, password, sprog, certificates) values (?, ?, ?, ?, ?, ?, ?, ?)");
				createConsultent.setString(1, profile);
				createConsultent.setString(2, name);
				createConsultent.setString(3, avaliable);
				createConsultent.setInt(4, price);
				createConsultent.setInt(5, salary);
				createConsultent.setString(6, password);
				createConsultent.setString(7, sprog);
				createConsultent.setString(8, certificates);
				createConsultent.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}	
			
		public static void deleteConsultent(String id){
			try{
			java.sql.PreparedStatement deleteConsultent = connection.prepareStatement("DELETE from consultants WHERE id = ?");
			deleteConsultent.setString(1, id);
			deleteConsultent.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
		public static boolean updateConsultentInfo(int id, String profile, String available, int price, int salary, String password, String sprog, String certificates){
			try {
				java.sql.PreparedStatement updateConsultentInfo = connection.prepareStatement("UPDATE consultants SET profile = ?, available = ?, price = ?, salary = ?, password = ?, sprog = ?, certificates = ? WHERE id = ?");
				updateConsultentInfo.setInt(8, id);
				updateConsultentInfo.setString(1, profile);
				updateConsultentInfo.setString(2, available);
				updateConsultentInfo.setInt(3, price);
				updateConsultentInfo.setInt(4, salary);
				updateConsultentInfo.setString(5, password);
				updateConsultentInfo.setString(6, sprog);
				updateConsultentInfo.setString(7, certificates);
				
				int rowsAffected = updateConsultentInfo.executeUpdate();
				
				if(rowsAffected == 0){
					return false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
			
				return true;
		}

		
		
		
	/**
	 * Method used to close the database connection
	 */
		
		
	private static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
