import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/ConsultLocus";
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
		/*
		public static void createUser(String name, String password) {

			try {
				java.sql.PreparedStatement createUser = connection.prepareStatement("insert into customers (name, password) values (?, ?)");
				createUser.setString(1, name);
				createUser.setString(2, password);
				createUser.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}	
			
		public static void deleteUser(String name){
			try{
			java.sql.PreparedStatement deleteUser = connection.prepareStatement("DELETE from customers WHERE name = ?");
			deleteUser.setString(1, name);
			deleteUser.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		public static boolean updateUserInfo(String name, String password){
			try {
				java.sql.PreparedStatement updateUserInfo = connection.prepareStatement("UPDATE customers SET password = ? WHERE name = ?");
				updateUserInfo.setString(1, password);
				updateUserInfo.setString(2, name);
				
				int rowsAffected = updateUserInfo.executeUpdate();
				
				if(rowsAffected == 0){
					return false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
			
				return true;
		}
*/
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
