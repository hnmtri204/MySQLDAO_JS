package tri.dev.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tri.dev.data.dao.CategoryDao;
import tri.dev.data.dao.OrderItemDao;
import tri.dev.data.driver.MySQLDriver;
import tri.dev.data.model.Category;
import tri.dev.data.model.OrderItem;

public class OrderItemImpl implements OrderItemDao {
	@Override
	public boolean insert(OrderItem orderItem) {
		String sql = "INSERT INTO ORDERITEMS(NAME, THUMBNAIL) VALUES(?, ?)";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderItem.quantity);
			stmt.setDouble(2, orderItem.price);
			stmt.setInt(3, orderItem.order_id);
			stmt.setInt(4, orderItem.product_id);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean update(OrderItem orderItem) {
		String sql = "UPDATE ORDERITEMS SET quantity = ?, price = ?, order_id = ?, product_id = ? WHERE id = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderItem.quantity);
			stmt.setDouble(2, orderItem.price);
			stmt.setInt(3, orderItem.order_id);
			stmt.setInt(4, orderItem.product_id);
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
		String sql = "DELETE FROM ORDERITEMS WHERE ID = ?";
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
	public OrderItem find(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDERITEMS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int quantity = rs.getInt("quantity");
				Double price = rs.getDouble("price");
				int order_id = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");

				return new OrderItem(id, quantity, price, order_id, product_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderItem> findAll() {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		String sql = "SELECT * FROM ORDERITEMS";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				Double price = rs.getDouble("price");
				int order_id = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				orderItemList.add(new OrderItem(id, quantity, price, order_id, product_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItemList;
	}

}