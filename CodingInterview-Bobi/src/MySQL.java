import java.sql.*;

public class MySQL {
	public static void main(String[] args) throws SQLException {
		Connection conn = getConnection("jdbc:mysql://localhost:3306/test");
		query(conn, "select * from student");
	}

	static void query(Connection conn, String query) throws SQLException {
		if (conn == null || query == null) {
			return;
		}
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	static Connection getConnection(String url) throws IllegalArgumentException {
		return getConnection(url, null, null);
	}

	static Connection getConnection(String url, String user, String password) throws IllegalArgumentException{
		if (url == null) {
			throw new IllegalArgumentException();
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No driver class");
			return null;
		}
		System.out.println("Driver registered!");
		Connection conn = null;

		try {
			if (user == null) {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			} else {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", user, password);
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			return null;
		}

		if (conn == null) {
			System.out.println("Connection failed");
		} else {
			System.out.println("Successfully set up connection!");
		}
		return conn;
	}
}
