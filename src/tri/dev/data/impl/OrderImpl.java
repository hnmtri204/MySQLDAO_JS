package tri.dev.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tri.dev.data.dao.OrderDao;
import tri.dev.data.driver.MySQLDriver;
import tri.dev.data.model.Order;

public class OrderImpl implements OrderDao {
	@Override
	public boolean insert(Order order) {
		String sql = "INSERT INTO ORDERS(CODE, STATUS, USER_ID) VALUES(?, ?, ?)";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.code);
			stmt.setString(2, order.status);
			stmt.setInt(3, order.user_id);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean update(Order order) {
		String sql = "UPDATE ORDERS SET code = ?, status = ? user_id = ? WHERE id = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.code);
			stmt.setString(2, order.status);
			stmt.setInt(3, order.user_id);
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
		String sql = "DELETE FROM ORDERS WHERE ID = ?";
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
	public Order find(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDERS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString("code");
				String status = rs.getString("status");
				int user_is = rs.getInt("user_id");
				Timestamp createdAt =rs.getTimestamp("created_at");

				return new Order(id, code, status, user_is, createdAt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		List<Order> orderList = new ArrayList<Order>();
		String sql = "SELECT * FROM ORDERS";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String status = rs.getString("status");
				int user_is = rs.getInt("user_id");
				Timestamp createdAt =rs.getTimestamp("created_at");
				orderList.add(new Order(id, code, status, user_is, createdAt));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

}