package tri.dev.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.UserDao;
import tri.dev.data.driver.MySQLDriver;
import tri.dev.data.model.Category;
import tri.dev.data.model.User;

public class UserImpl implements UserDao {
	@Override
	public boolean insert(User user) {
		String sql = "INSERT INTO USERS(EMAIL, PASSWORD, ROLE) VALUES(?, ?, ?)";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.email);
			stmt.setString(2, user.password);
			stmt.setString(3, user.role);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean update(User user) {
		String sql = "UPDATE USERS SET email = ?, password = ?, role = ? WHERE id = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.email);
			stmt.setString(2, user.password);
			stmt.setString(3, user.role);
//	            stmt.setInt(3, category.id);
			stmt.setInt(3, 12);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM USERS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM USERS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");

				return new User(id, email, password, role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM USERS";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				userList.add(new User(id, email, password, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}